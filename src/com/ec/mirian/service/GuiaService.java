/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.service;

import com.ec.mirian.enumerado.TipoDocumentoEnum;
import com.ec.mirian.repository.InformacionRepository;
import com.ec.mirian.repository.PersonaRepository;
import com.ec.mirian.util.PropertiesUtil;
import ec.incloud.ce.bean.common.InfoTributaria;
import ec.incloud.ce.bean.guia.GuiaRemision;
import ec.incloud.ce.bean.guia.InfoGuiaRemision;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

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
    
    public GuiaRemision getInformacion() throws UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException {
        GuiaRemision guia = new GuiaRemision();
        PropertiesUtil.getInstanceProperties();
        pathXML = PropertiesUtil.getValorPathXML("mirian.pathXML");
        guia.setVersion(PropertiesUtil.getValorPathXML("mirian.version"));
        guia.setId("comprobante");
        guia.setInfoTributaria(getInfoTributaria());
        guia.setInfoGuiaRemision(null);
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
    
    public InfoGuiaRemision getInfoGuiaRemision() {
        InfoGuiaRemision info = new InfoGuiaRemision();
        info.setObligadoContabilidad(PropertiesUtil.getValorPathXML("mirian.ObliConta"));
        return info;
    }

    

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }
    
}
