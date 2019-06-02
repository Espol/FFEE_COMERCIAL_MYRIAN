/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.service;

import com.ec.mirian.domain.VentfacCabecera;
import com.ec.mirian.domain.VentfacDetalle;
import com.ec.mirian.enumerado.TipoDocumentoEnum;
import com.ec.mirian.repository.FactoryRepositorio;
import com.ec.mirian.repository.InformacionRepository;
import com.ec.mirian.repository.PersonaRepository;
import com.ec.mirian.repository.VentaRepository;
import com.ec.mirian.util.Constante;
import com.ec.mirian.util.PropertiesUtil;
import com.ec.mirian.util.Util;
import ec.incloud.ce.bean.common.CampoAdicional;
import ec.incloud.ce.bean.common.InfoTributaria;
import ec.incloud.ce.bean.guia.Destinatario;
import ec.incloud.ce.bean.guia.GuiaRemision;
import ec.incloud.ce.bean.guia.GuiaRemisionDetalle;
import ec.incloud.ce.bean.guia.InfoGuiaRemision;
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
public class GuiaService {
    
    private static GuiaService instance ;
    private String numFactura;
    private String pathXML;
    
    private final TipoDocumentoEnum tipo = TipoDocumentoEnum.GUIA;
    
    public static GuiaService create() {
        synchronized(GuiaService.class) {
            if(instance == null) {
                instance = new GuiaService();
            }
            return instance;
        }
    }
    
    public GuiaRemision getInformacion() throws UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException, ParseException {
        GuiaRemision guia = new GuiaRemision();
        PropertiesUtil.getInstanceProperties();
        pathXML = PropertiesUtil.getValorPathXML("mirian.pathXML");
        guia.setVersion(PropertiesUtil.getValorPathXML("mirian.version"));
        guia.setId("comprobante");
        guia.setInfoTributaria(getInfoTributaria());
        guia.setInfoGuiaRemision(getInfoGuiaRemision());
        guia.setDestinatarios(getDestinatario());
        guia.setInfoAdicional(getInfoAdicional());
        return guia;
    }
    
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
    
    public InfoGuiaRemision getInfoGuiaRemision() throws ClassNotFoundException, SQLException, IOException {
        PropertiesUtil.getInstanceProperties();
        String idEmisor = PropertiesUtil.getValorPathXML("mirian.emisor.id");
        
        InformacionRepository ir = FactoryRepositorio.createInformacionRepository();
        String[] emisor = ir.getInformacionEmisior(idEmisor);
        InfoGuiaRemision info = new InfoGuiaRemision();
        info.setObligadoContabilidad(PropertiesUtil.getValorPathXML("mirian.ObliConta"));
        info.setDirPartida(emisor[7]);
        info.setPlaca("GMI0612");
        info.setDirEstablecimiento(emisor[3]);
        info.setRucTransportista(emisor[1]);
        info.setTipoIdentificacionTransportista(Util.getTipoDocumento(emisor[1]));
        info.setRazonSocialTransportista(emisor[2]);
        info.setFechaIniTransporte(Util.getStringFromDate(new Date(), "dd/MM/yyyy"));
        info.setFechaFinTransporte(Util.getStringFromDate(new Date(), "dd/MM/yyyy"));
        return info;
    }

    

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    private List<Destinatario> getDestinatario() throws SQLException, ClassNotFoundException, IOException, ParseException {
        ConexionService con = ConexionService.create();
        VentfacCabecera venta = con.getVentaCabeceraByFactura(numFactura);
        Destinatario dest = new Destinatario();
        dest.setIdentificacionDestinatario(numFactura);
        dest.setCodDocSustento(venta.getTipo());
        dest.setNumAutDocSustento(venta.getNumeroAutorizacion());
        dest.setNumDocSustento(venta.getFacturaImpresa());
        dest.setFechaEmisionDocSustento(Util.getFechaSustento(venta.getFechaAutorizacion()));
        dest.setDetalles(getDetalle(venta));
        List<Destinatario> list = new ArrayList<>();
        list.add(dest);
        return list;
    }

    private List<GuiaRemisionDetalle> getDetalle(VentfacCabecera venta) throws SQLException, ClassNotFoundException, IOException {
        ConexionService con = ConexionService.create();
        List<VentfacDetalle> list = con.getDetalle(venta);
        List<GuiaRemisionDetalle>  detalles = new ArrayList<>();
        VentaRepository vr = FactoryRepositorio.createVentaRepository();
        for(VentfacDetalle det : list) {
            GuiaRemisionDetalle gd = new GuiaRemisionDetalle();
            gd.setCantidad(Long.toString(det.getCantidadUni()));
            gd.setCodigoAdicional(Long.toString(det.getNumArt()));
            String[] articulo = vr.getArticulo(Long.toString(det.getNumArt()));
            gd.setDescripcion(articulo[3]);
            detalles.add(gd);
        }
        return detalles;
    }

    private List<CampoAdicional> getInfoAdicional() throws ClassNotFoundException, SQLException, IOException {
        ConexionService con = ConexionService.create();
        VentfacCabecera venta = con.getVentaCabeceraByFactura(numFactura);
        
        VentaRepository vr = FactoryRepositorio.createVentaRepository();
        
        String[] ventaCredito = vr.getVenta(venta.getNumVentcred());
        PersonaRepository pr = new PersonaRepository();
        String[] personaEmisor = pr.getPersona(ventaCredito[7]);
        
        CampoAdicional destinatario = new CampoAdicional();
        destinatario.setNombre("Nombre Comercial");
        destinatario.setValue(!personaEmisor[18].equals("") ? personaEmisor[18] : personaEmisor[22]);
        
        CampoAdicional cliente = new CampoAdicional();
        cliente.setNombre("Nombre Cliente");
        cliente.setValue(personaEmisor[22]);
        
        CampoAdicional identificacion = new CampoAdicional();
        identificacion.setNombre("RUC/Cedula Identidad");
        identificacion.setValue(personaEmisor[1]);
        
        CampoAdicional ciudad = new CampoAdicional();
        ciudad.setNombre("Ciudad destino");
        ciudad.setValue(personaEmisor[11]);
        
        CampoAdicional direccion = new CampoAdicional();
        direccion.setNombre("Punto Llegada");
        direccion.setValue(personaEmisor[12]);
        
        CampoAdicional telefono = new CampoAdicional();
        telefono.setNombre("Telefono");
        telefono.setValue(personaEmisor[7]);
        
        List<CampoAdicional> list = new ArrayList<>();
        list.add(identificacion);
        list.add(destinatario);
        list.add(cliente);
        list.add(direccion);
        list.add(ciudad);
        list.add(telefono);
        return list;
    }

    public String generarXML(GuiaRemision guia) throws XmlException {
        Util.createDirectory(this.getXMLPath());
        String pathAbsoluteXML = this.getXMLPath() + this.getXMLName(guia.getInfoTributaria()) + Constante.PUNTO_XML;
        XmlFactory.getGuiaRemisionXmlServices().generarXml(guia, pathAbsoluteXML);
        return pathAbsoluteXML;
    }
    
     public String getXMLPath() {
        return new StringBuilder(pathXML).append("/").append(Util.getDirectorioXMLSegunSO()).
                append("/").
                append(tipo.getDirectorio()).
                append("/").toString();
     }
     
     public String getXMLName(InfoTributaria infoTributaria) {
        return new StringBuilder(tipo.getPrefijoXml()).append("-").
                append(infoTributaria.getEstab()).append("-").
                append(infoTributaria.getPtoEmi()).append("-").
                append(infoTributaria.getSecuencial()).append("-").
                append(Util.getStringFromDateXmlName(new Date())).toString();
    }
    
}
