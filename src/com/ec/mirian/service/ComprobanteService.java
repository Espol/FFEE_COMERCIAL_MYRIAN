/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.service;

import com.ec.mirian.util.ClaveAccesoGen;
import com.ec.mirian.util.Constante;
import com.ec.mirian.util.PropertiesUtil;
import com.ec.mirian.util.Util;
import ec.incloud.ce.bean.common.InfoTributaria;
import ec.incloud.ce.xml.exception.XmlException;
import ec.incloud.ce.xml.services.XmlFactory;
import java.io.IOException;

/**
 *
 * @author MARCELO
 */
public class ComprobanteService {
    
    private static ComprobanteService instance ;
    
    private final ClaveAccesoGen claveGenerador = new ClaveAccesoGen();
    
    public static ComprobanteService create () {
        synchronized(ComprobanteService.class){
            if(instance == null) {
                instance = new ComprobanteService();
            }
            return instance;
        }
    }
    
    public String getSecuencial(InfoTributaria infoTributaria) throws IOException {
        PropertiesUtil.getInstanceProperties();
        String pathXML = PropertiesUtil.getValorPathXML("mirian.pathXML");
        String pathSecuencial = getNombreFileSecuencial(infoTributaria.getCodDoc(),infoTributaria.getEstab(), infoTributaria.getPtoEmi());
        String filePath = pathXML +"/"+ Util.getDirectorioSegunSO() +"/"+pathSecuencial;
        String secuencia = Util.getSecuencial(filePath);
        Util.aumentarYgrabarSecuencia(filePath,secuencia);
        return Util.getFormatoNueveDigitos(secuencia);
    }
    
    private String getNombreFileSecuencial(String tipo, String establecimiento, String ptoEmision) {
        return new StringBuilder(tipo).
                append("-").
                append(establecimiento).
                append("-").
                append(ptoEmision).
                append(".txt").
                toString();
    }
    
   public String generarClave(InfoTributaria infoTributaria, String fechaEmision) {
        return claveGenerador.generarClaveAcceso(Util
                .getDateFromString(fechaEmision,"dd/MM/yyyy"),
                infoTributaria.getCodDoc(),
                infoTributaria.getRuc(),
                infoTributaria.getAmbiente(),
                infoTributaria.getEstab() + infoTributaria.getPtoEmi(),
                infoTributaria.getSecuencial(),
                infoTributaria.getSecuencial().substring(2, 8) + infoTributaria.getCodDoc(),
                infoTributaria.getTipoEmision());
    }
   
   
    
}
