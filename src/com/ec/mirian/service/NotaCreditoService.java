/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.service;

import com.ec.mirian.domain.VentfacCabecera;
import com.ec.mirian.domain.VentfacDetalle;
import com.ec.mirian.enumerado.TipoDocumentoEnum;
import com.ec.mirian.repository.CreditosRepository;
import com.ec.mirian.repository.DevolucionRepository;
import com.ec.mirian.repository.FactoryRepositorio;
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
import ec.incloud.ce.bean.common.TotalImpuesto;
import ec.incloud.ce.bean.credito.InfoNotaCredito;
import ec.incloud.ce.bean.credito.NotaCredito;
import ec.incloud.ce.bean.credito.NotaCreditoDetalle;
import ec.incloud.ce.xml.exception.XmlException;
import ec.incloud.ce.xml.services.XmlFactory;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MARCELO
 */
public class NotaCreditoService {
    
    private static NotaCreditoService instance;
    
    private static final TipoDocumentoEnum NOTA_CREDITO = TipoDocumentoEnum.CREDITO;
    
    private final ClaveAccesoGen claveGenerador = new ClaveAccesoGen();
    private String pathXML;
    private String factura;
    private boolean devolucion = false;
    
    public static NotaCreditoService create() {
        synchronized(NotaCreditoService.class){
            if(instance == null){
                instance = new NotaCreditoService();
            }
            return instance;
        }
    }
    
    public NotaCredito getInformacion() throws UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException, ParseException {
        NotaCredito nc = new NotaCredito();
        PropertiesUtil.getInstanceProperties();
        pathXML = PropertiesUtil.getValorPathXML("mirian.pathXML");
        nc.setVersion(PropertiesUtil.getValorPathXML("mirian.version"));
        nc.setId("comprobante");
        nc.setInfoTributaria(getInfoTributaria());
        nc.setInfoNotaCredito(devolucion == false ? getInfoNotaCredito() : getInfoNotaCreditoByDevolucion());
        nc.setDetalles(devolucion == false ? getDetalleByFactura() : getDetalleByDevolucion());
        nc.setInfoAdicional(getInfoAdicional());
        return nc;
    }
    
    private InfoTributaria getInfoTributaria() throws SQLException, UnsupportedEncodingException, IOException, ClassNotFoundException {
        PropertiesUtil.getInstanceProperties();
        String idEmisor = PropertiesUtil.getValorPathXML("mirian.emisor.id");
        
        InformacionRepository ir = FactoryRepositorio.createInformacionRepository();
        
        PersonaRepository pr = FactoryRepositorio.createPersonaRepository();
        
        InfoTributaria it = new InfoTributaria();
        it.setAmbiente(PropertiesUtil.getValorPathXML("mirian.ambiente"));
        it.setCodDoc(NOTA_CREDITO.getCodigo());
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
    
    private InfoNotaCredito getInfoNotaCredito() throws ClassNotFoundException, SQLException, IOException {
        PropertiesUtil.getInstanceProperties();
        String idEmisor = PropertiesUtil.getValorPathXML("mirian.emisor.id");
        
        InformacionRepository ir = FactoryRepositorio.createInformacionRepository();
        String[] emisor = ir.getInformacionEmisior(idEmisor);
        
        ConexionService con = ConexionService.create();
        VentfacCabecera venta = con.getVentaCabeceraByFactura(factura);
        
        VentaRepository vr = FactoryRepositorio.createVentaRepository();
        
        String[] ventaCredito = vr.getVenta(venta.getNumVentcred());
        
        PersonaRepository pr = new PersonaRepository();
        String[] personaEmisor = pr.getPersona(ventaCredito[7]);
        
        InfoNotaCredito info = new InfoNotaCredito();
        info.setObligadoContabilidad(PropertiesUtil.getValorPathXML("mirian.ObliConta"));
        info.setDirEstablecimiento(emisor[3]);
        info.setIdentificacionComprador(personaEmisor[1]);
        info.setTipoIdentificacionComprador(Util.getTipoDocumento(personaEmisor[1]));
        info.setRazonSocialComprador(!personaEmisor[18].equals("") ? personaEmisor[18] : personaEmisor[22]);
        info.setFechaEmision(Util.getStringFromDate(new Date(), "dd/MM/yyyy"));
        info.setCodDocModificado(venta.getTipo());
        info.setFechaEmisionDocSustento(Util.getStringFromDate(Util.getDateFromString(ventaCredito[1],"yyyy-MM-dd HH:mm:ss"),"dd/MM/yyyy"));
        info.setNumDocModificado(venta.getFacturaImpresa());
        info.setTotalSinImpuestos(ventaCredito[19]);
        
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
        
        info.setValorModificacion(Util.dosDigitos(sub + iva));
        
        return info;
    }
    
    private InfoNotaCredito getInfoNotaCreditoByDevolucion() throws UnsupportedEncodingException, IOException, ClassNotFoundException, SQLException {
        PropertiesUtil.getInstanceProperties();
        String idEmisor = PropertiesUtil.getValorPathXML("mirian.emisor.id");
        
        InformacionRepository ir = FactoryRepositorio.createInformacionRepository();
        String[] emisor = ir.getInformacionEmisior(idEmisor);
        
        DevolucionRepository dr = FactoryRepositorio.createDevolucionRepository();
        String[] cabDevolucion = dr.getDevolucion(factura);
        
        VentaRepository vr = FactoryRepositorio.createVentaRepository();
        String[] ventaCredito = vr.getVenta(cabDevolucion[8]);
        
        ConexionService con = ConexionService.create();
        VentfacCabecera venta = con.getVentaCabeceraByNumVenta(Util.stringToLong(cabDevolucion[8]));
        
        PersonaRepository pr = new PersonaRepository();
        String[] personaEmisor = pr.getPersona(ventaCredito[7]);
        
        InfoNotaCredito info = new InfoNotaCredito();
        info.setObligadoContabilidad(PropertiesUtil.getValorPathXML("mirian.ObliConta"));
        info.setDirEstablecimiento(emisor[3]);
        info.setIdentificacionComprador(personaEmisor[1]);
        info.setTipoIdentificacionComprador(Util.getTipoDocumento(personaEmisor[1]));
        info.setRazonSocialComprador(!personaEmisor[18].equals("") ? personaEmisor[18] : personaEmisor[22]);
        info.setFechaEmision(Util.getStringFromDate(new Date(), "dd/MM/yyyy"));
        info.setCodDocModificado(venta.getTipo());
        info.setFechaEmisionDocSustento(Util.getStringFromDate(Util.getDateFromString(ventaCredito[1],"yyyy-MM-dd HH:mm:ss"),"dd/MM/yyyy"));
        info.setNumDocModificado(venta.getFacturaImpresa());
        info.setTotalSinImpuestos(cabDevolucion[13]);
        info.setMotivo(cabDevolucion[9]);
        //info.setValorModificacion(cabDevolucion[13]);
        
        TotalImpuesto ti = new TotalImpuesto();
        ti.setBaseImponible(cabDevolucion[13]);
        ti.setCodigo("2");
        ti.setCodigoPorcentaje("2");
        
        Double sub = Util.parseDouble(cabDevolucion[13]) ;
        
        Double iva = ( Util.parseDouble(emisor[6]) * sub) / 100;
        ti.setValor(Util.dosDigitos(iva));//
        List<TotalImpuesto> totalesImpuestos = new ArrayList<>();
        totalesImpuestos.add(ti);
        info.setTotalConImpuestos(totalesImpuestos);
        
        info.setValorModificacion(Util.dosDigitos(sub + iva));
        
        return info;
    }
    
    private List<NotaCreditoDetalle> getDetalleByDevolucion() throws ClassNotFoundException, SQLException, IOException {
        List<NotaCreditoDetalle> ncDetalles = new ArrayList<>();
        
        PropertiesUtil.getInstanceProperties();
        String idEmisor = PropertiesUtil.getValorPathXML("mirian.emisor.id");
        
        InformacionRepository ir = FactoryRepositorio.createInformacionRepository();
        String[] emisor = ir.getInformacionEmisior(idEmisor);
        
        
        DevolucionRepository dr = FactoryRepositorio.createDevolucionRepository();
        String[] cabDevolucion = dr.getDevolucion(factura);
        List<String[]> detalles = dr.getDetalleDevolucion(factura);
        for(String[] item : detalles){
            NotaCreditoDetalle det = new NotaCreditoDetalle();
            det.setCantidad(item[3]);
            det.setCodigoInterno(item[1]);
            det.setCodigoAdicional(item[2]);
            VentaRepository vr = FactoryRepositorio.createVentaRepository();
            String[] articulo = vr.getArticulo(item[1]);
            det.setDescripcion(articulo[3]);
            det.setPrecioUnitario(item[4]);
            
            Double subtotal = Util.parseDouble(item[4]) * Util.parseDouble(item[3]);
            Double desc = ( Util.parseDouble(cabDevolucion[11]) * subtotal) / 100;
            
            det.setDescuento(Util.dosDigitos(desc));
            det.setPrecioTotalSinImpuesto(Util.dosDigitos(subtotal - desc));
            
            List<Impuesto> impuestos = new ArrayList<>();
            Impuesto imp = new Impuesto();
            Double sub = subtotal - desc;
            imp.setBaseImponible(Util.dosDigitos(sub));
            imp.setCodigo("2");
            imp.setCodigoPorcentaje("2");
            imp.setTarifa(emisor[6]);
            
            Double tarifa = ( Util.parseDouble(emisor[6]) * sub) / 100;
            imp.setValor(Util.dosDigitos(tarifa));//
            
            impuestos.add(imp);
            det.setImpuestos(impuestos);
            ncDetalles.add(det);
        }
        return ncDetalles;
    }
    
    private List<NotaCreditoDetalle> getDetalleByFactura() throws ClassNotFoundException, SQLException, IOException {
        List<NotaCreditoDetalle> ncDetalles = new ArrayList<>();
        ConexionService con = ConexionService.create();
        VentfacCabecera venta = con.getVentaCabeceraByFactura(factura);
        List<VentfacDetalle> detalles =  con.getDetalle(venta);
        
        for(VentfacDetalle detalle : detalles) {
            NotaCreditoDetalle det = new NotaCreditoDetalle();
            det.setCantidad(String.valueOf(detalle.getCantidadUni()));
            det.setCodigoInterno(String.valueOf(detalle.getNumArt()));
            det.setCodigoAdicional(String.valueOf(detalle.getCoArt()));
            VentaRepository vr = FactoryRepositorio.createVentaRepository();
            String[] articulo = vr.getArticulo(String.valueOf(detalle.getNumArt()));
            det.setDescripcion(articulo[3]);
            det.setPrecioUnitario(String.valueOf(detalle.getPrecioVentfac()));
            
            Double subtotal = Double.parseDouble(det.getCantidad()) * detalle.getPrecioVentfac().doubleValue();
            
            det.setPrecioTotalSinImpuesto(Util.dosDigitos(subtotal));
            ncDetalles.add(det);
        }
        
        return ncDetalles;
    }
    
    private List<CampoAdicional> getInfoAdicional() {
        List<CampoAdicional> list = new ArrayList<>();
        CampoAdicional ca = new CampoAdicional();
        ca.setNombre("Devolucion");
        ca.setValue(factura);
        list.add(ca);
        return list;
    }
    
    public String generarXML(NotaCredito nc) throws XmlException {
        Util.createDirectory(this.getXMLPath());
        String pathAbsoluteXML = this.getXMLPath() + this.getXMLName(nc.getInfoTributaria()) + Constante.PUNTO_XML;
        XmlFactory.getNotaCreditoXmlServices().generarXml(nc, pathAbsoluteXML);
        return pathAbsoluteXML;
    }
    
     public String getXMLPath() {
        return new StringBuilder(pathXML).append("/").append(Util.getDirectorioXMLSegunSO()).
                append("/").
                append(NOTA_CREDITO.getDirectorio()).
                append("/").toString();
     }
     
     public String getXMLName(InfoTributaria infoTributaria) {
        return new StringBuilder(NOTA_CREDITO.getPrefijoXml()).append("-").
                append(infoTributaria.getEstab()).append("-").
                append(infoTributaria.getPtoEmi()).append("-").
                append(infoTributaria.getSecuencial()).append("-").
                append(Util.getStringFromDateXmlName(new Date())).toString();
    }
     
    public void saveInformacion(NotaCredito nc) throws ClassNotFoundException, SQLException, IOException{
        DevolucionRepository dr = FactoryRepositorio.createDevolucionRepository();
        String[] cabDevolucion = dr.getDevolucion(factura);
        
        String[] creditos = new String[8];
        creditos[0] = Util.getStringFromDate(new Date(), "yyyy-MM-dd HH:mm:ss");
        creditos[1] = "DOCU_ELECT";
        creditos[2] = Util.getStringFromDate(new Date(), "yyyy-MM-dd HH:mm:ss");
        creditos[3] = cabDevolucion[7];
        creditos[4] = nc.getInfoNotaCredito().getMotivo();
        creditos[5] = "DOCU_ELECT";
        creditos[6] = nc.getInfoNotaCredito().getTotalSinImpuestos();
        creditos[7] = factura;
        
        CreditosRepository cr = FactoryRepositorio.createCreditosRepository();
        cr.saveCreditos(creditos);
        
    }
    public void setFactura(String factura) {
        this.factura = factura;
    }

    public void setDevolucion(boolean devolucion) {
        this.devolucion = devolucion;
    }
}
