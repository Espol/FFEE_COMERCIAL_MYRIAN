/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.mirian.service;

import com.ec.mirian.enumerado.TipoDocumentoEnum;
import com.ec.mirian.gui.VentanaPrincipal;
import com.ec.mirian.repository.InformacionRepository;
import com.ec.mirian.repository.PersonaRepository;
import com.ec.mirian.repository.VentaRepository;
import com.ec.mirian.util.ClaveAccesoGen;
import com.ec.mirian.util.Constante;
import com.ec.mirian.util.PropertiesUtil;
import com.ec.mirian.util.Util;
import ec.incloud.ce.bean.common.CampoAdicional;
import ec.incloud.ce.bean.common.Impuesto;
import ec.incloud.ce.bean.common.InfoTributaria;
import ec.incloud.ce.bean.common.Pago;
import ec.incloud.ce.bean.common.TotalImpuesto;
import ec.incloud.ce.bean.factura.Factura;
import ec.incloud.ce.bean.factura.FacturaDetalle;
import ec.incloud.ce.bean.factura.InfoFactura;
import ec.incloud.ce.xml.exception.XmlException;
import ec.incloud.ce.xml.services.XmlFactory;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MARCELO
 */
public class FacturaService {
    
    private static FacturaService instance;

    private Factura factura;
    
    private String numVentCred;
    private String pathXML;
    
    public static FacturaService create(){
        synchronized(FacturaService.class){
            if(instance == null){
                instance = new FacturaService();
            }
            return instance;
        }
    }
    
    private final ClaveAccesoGen claveGenerador = new ClaveAccesoGen();
    private final TipoDocumentoEnum tipo = TipoDocumentoEnum.FACTURA;

    public InfoTributaria getInfoTributaria() throws SQLException, UnsupportedEncodingException, IOException, ClassNotFoundException {
        PropertiesUtil.getInstanceProperties();
        String idEmisor = PropertiesUtil.getValorPathXML("mirian.emisor.id");
        
        InformacionRepository ir = new InformacionRepository();
        
        PersonaRepository pr = new PersonaRepository();
        
        InfoTributaria it = new InfoTributaria();
        it.setAmbiente(PropertiesUtil.getValorPathXML("mirian.ambiente"));
        it.setCodDoc(tipo.getCodigo());
        it.setEstab(PropertiesUtil.getValorPathXML("mirian.estab"));
        it.setPtoEmi(PropertiesUtil.getValorPathXML("mirian.ptoEmi"));
        it.setTipoEmision(PropertiesUtil.getValorPathXML("mirian.tipoEmision"));
        
        String[] emisor = ir.getInformacionEmisior(idEmisor);
        String[] persona = pr.getPersona(idEmisor);
        it.setDirMatriz(emisor[3]);
        it.setRuc(emisor[1]);
        it.setRazonSocial(persona[22]);
        it.setNombreComercial(emisor[2]);
        it.setClaveAcceso(idEmisor);
        
        return it;
    }
    
    private InfoFactura getInfoFactura() throws SQLException, UnsupportedEncodingException, ClassNotFoundException, IOException{
        InfoFactura info = new InfoFactura();
        VentaRepository vr = new VentaRepository();
        
        String[] ventaCredito = vr.getVenta(numVentCred);
        PersonaRepository pr = new PersonaRepository();
        String[] personaEmisor = pr.getPersona(ventaCredito[7]);
        
        info.setIdentificacionComprador(ventaCredito[8]);
        info.setMoneda(PropertiesUtil.getValorPathXML("mirian.moneda"));
        info.setObligadoContabilidad(PropertiesUtil.getValorPathXML("mirian.ObliConta"));
        info.setRazonSocialComprador(ventaCredito[10]);
        info.setFechaEmision(Util.getStringFromDate(Util.getDateFromString(ventaCredito[1],"yyyy-MM-dd HH:mm:ss"),"dd/MM/yyyy"));
        info.setTotalSinImpuestos(ventaCredito[14]);
        info.setTotalDescuento(ventaCredito[16]);
        VentanaPrincipal.getInstance().setPorDescuento(!ventaCredito[15].equals("") ? ventaCredito[15] : "0");
        //info.setImporteTotal(ventaCredito[19]);
        info.setTipoIdentificacionComprador(Util.getTipoDocumento(ventaCredito[8]));
        info.setDireccionComprador(personaEmisor[12]);
        
        VentanaPrincipal.getInstance().setCodigoCliente(personaEmisor[0]);
        VentanaPrincipal.getInstance().setCorreo(personaEmisor[13]);
        VentanaPrincipal.getInstance().setTxtCorreoCliente(personaEmisor[13]);
        VentanaPrincipal.getInstance().setTxtCiudadCliente(personaEmisor[11]);
        
        InformacionRepository ir = new InformacionRepository();
        PropertiesUtil.getInstanceProperties();
        String idEmisor = PropertiesUtil.getValorPathXML("mirian.emisor.id");
        String[] emisor = ir.getInformacionEmisior(idEmisor);
        
        TotalImpuesto ti = new TotalImpuesto();
        ti.setBaseImponible(ventaCredito[14]);
        ti.setCodigo("2");
        ti.setCodigoPorcentaje("2");
        ti.setTarifa(emisor[6]);
        
        Double sub = Util.parseDouble(ventaCredito[14]) - Util.parseDouble(ventaCredito[16]) ;
        
        Double iva = ( Util.parseDouble(emisor[6]) * sub) / 100;
        ti.setValor(Util.dosDigitos(iva));//
        List<TotalImpuesto> totalesImpuestos = new ArrayList<>();
        totalesImpuestos.add(ti);
        info.setTotalConImpuestos(totalesImpuestos);
        
        info.setImporteTotal(Util.dosDigitos(sub + iva));
        
        Pago p = new Pago ();
        p.setFormaPago("01");
        p.setPlazo("0");
        p.setTotal(ventaCredito[19]);
        p.setUnidadTiempo("Dias");
        List<Pago> pagos = new ArrayList<>();
        pagos.add(p);
        
        info.setPagos(pagos);
        return info;
    }
    
    private List<CampoAdicional> getListCamposAdicional() {
        List<CampoAdicional> list = new ArrayList<>();
        CampoAdicional ca = new CampoAdicional();
        ca.setNombre("NoPedido");
        ca.setValue(numVentCred);
        list.add(ca);
        
        CampoAdicional ca1 = new CampoAdicional();
        ca1.setNombre("ciudad");
        ca1.setValue(VentanaPrincipal.getInstance().getTxtCiudadCliente());
        list.add(ca1);
        
        CampoAdicional ca2 = new CampoAdicional();
        ca2.setNombre("correo");
        ca2.setValue(VentanaPrincipal.getInstance().getTxtCorreoCliente());
        list.add(ca2);
        return list;
    }
    
    private List<FacturaDetalle> getDetalles() throws SQLException {
        List<FacturaDetalle> detalles = new ArrayList<>();
        
        VentaRepository vr = new VentaRepository();
        List<String[]> ventas =  vr.getDetallesVenta(numVentCred);
        
        for(String[] venta : ventas) {
            FacturaDetalle fd = new FacturaDetalle();
            fd.setCantidad(venta[3]);
            fd.setCodigoAuxiliar(venta[1]);
            fd.setCodigoPrincipal(venta[2]);
            
            String PorDesc = VentanaPrincipal.getInstance().getPorDescuento();
            
            Double subtotal = Util.parseDouble(venta[4]) * Util.parseDouble(venta[3]);
            fd.setPrecioTotalSinImpuesto(Util.dosDigitos(subtotal));
            
            Double descuento = (subtotal * Util.parseDouble(PorDesc))/100;
            fd.setDescuento(Util.dosDigitos(descuento));
            
            String[] articulo = vr.getArticulo(venta[1]);
            fd.setDescripcion(articulo[3]);
            fd.setPrecioUnitario(venta[4]);
            
            List<Impuesto> impuestos = new ArrayList<>();
            Impuesto i = new Impuesto();
            i.setBaseImponible(Util.dosDigitos(subtotal));
            i.setCodigo("2");
            i.setCodigoPorcentaje("2");
            i.setTarifa("12");
            i.setValor(Util.getCalularPorcentaje(venta[4], "12"));
            impuestos.add(i);
            fd.setImpuestos(impuestos);
            
            detalles.add(fd);
        }
        
        return detalles;
    }
    
    private void generarClave() {
        String clave = this.claveGenerador.generarClaveAcceso(Util
                .getDateFromString(this.factura.getInfoFactura().getFechaEmision(),"dd/MM/yyyy"),
                tipo.getCodigo(),
                factura.getInfoTributaria().getRuc(),
                this.factura.getInfoTributaria().getAmbiente(),
                this.factura.getInfoTributaria().getEstab() + this.factura.getInfoTributaria().getPtoEmi(),
                this.factura.getInfoTributaria().getSecuencial(),
                this.factura.getInfoTributaria().getSecuencial().substring(2, 8) + tipo.getCodigo(),
                this.factura.getInfoTributaria().getTipoEmision());
        this.factura.getInfoTributaria().setClaveAcceso(clave);
    }
    
    private String generarXML() throws XmlException {
        Util.createDirectory(this.getXMLPath());
        String pathAbsoluteXML = this.getXMLPath() + this.getXMLName() + Constante.PUNTO_XML;
        XmlFactory.getFacturaXmlServices().generarXml(this.factura, pathAbsoluteXML);
        return pathAbsoluteXML;
    }
    
     public String getXMLPath() {
        return new StringBuilder(pathXML).append("/").append(Util.getDirectorioXMLSegunSO()).
                append("/").
                append(tipo.getDirectorio()).
                append("/").toString();
     }
     
     public String getXMLName() {
        return new StringBuilder(tipo.getPrefijoXml()).append("-").
                append(factura.getInfoTributaria().getEstab()).append("-").
                append(factura.getInfoTributaria().getPtoEmi()).append("-").
                append(factura.getInfoTributaria().getSecuencial()).append("-").
                append(Util.getStringFromDateXmlName(new Date())).toString();
    }
     
    public void getSecuencial() throws IOException {
        String secuencia = Util.getSecuencial(pathXML +"/"+ Util.getDirectorioSegunSO() +"/"+getNombreFileSecuencial());
        factura.getInfoTributaria().setSecuencial(Util.getFormatoNueveDigitos(secuencia));
        Util.aumentarYgrabarSecuencia(pathXML +"/"+ Util.getDirectorioSegunSO() +"/"+getNombreFileSecuencial(),secuencia);
    }
    
    private String getNombreFileSecuencial() {
        return new StringBuilder(factura.getInfoTributaria().getEstab()).append("-").
                append(factura.getInfoTributaria().getEstab()).append(".txt").toString();
    }
    
    public String generarFacturaXML() throws SQLException, IOException, XmlException, ClassNotFoundException {
        factura = new Factura();
        PropertiesUtil.getInstanceProperties();
        pathXML = PropertiesUtil.getValorPathXML("mirian.pathXML");
        factura.setVersion(PropertiesUtil.getValorPathXML("mirian.version"));
        factura.setId("comprobante");
        factura.setInfoTributaria(getInfoTributaria());
        factura.setInfoFactura(getInfoFactura());
        factura.setDetalles(getDetalles());
        factura.setInfoAdicional(getListCamposAdicional());
        getSecuencial();
        VentanaPrincipal.getInstance().setSecuencial(factura.getInfoTributaria().getSecuencial());
        generarClave();
        VentanaPrincipal.getInstance().setClaveAcceso(factura.getInfoTributaria().getClaveAcceso());
        return generarXML();
    }
    
    public Factura obtenerInformacion() throws SQLException, IOException, XmlException, ClassNotFoundException {
        factura = new Factura();
        PropertiesUtil.getInstanceProperties();
        pathXML = PropertiesUtil.getValorPathXML("mirian.pathXML");
        factura.setVersion(PropertiesUtil.getValorPathXML("mirian.version"));
        factura.setId("comprobante");
        factura.setInfoTributaria(getInfoTributaria());
        factura.setInfoFactura(getInfoFactura());
        factura.setDetalles(getDetalles());
        factura.setInfoAdicional(getListCamposAdicional());
        return factura;
    }
    
    public void saveInformacion() throws SQLException, ClassNotFoundException, IOException {
        VentaRepository vr = new VentaRepository();
        String[] venta = vr.getVenta(numVentCred);
        List<String[]> ventasDetalles =  vr.getDetallesVenta(numVentCred);
        ConexionService cs = new ConexionService();
        cs.saveInformacion(venta, ventasDetalles, factura);
    }

    public String getNumVentCred() {
        return numVentCred;
    }

    public void setNumVentCred(String numVentCred) {
        this.numVentCred = numVentCred;
    }

    public String getPathXML() {
        return pathXML;
    }

    public void setPathXML(String pathXML) {
        this.pathXML = pathXML;
    }

    public Factura getFactura() {
        return factura;
    }
}