/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.service;

import com.ec.mirian.util.PropertiesUtil;
import ec.incloud.ce.bean.factura.Factura;
import ec.incloud.ce.xml.exception.XmlException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author MARCELO
 */
public class FacturaEmision implements Emision<Factura>{
    
    FacturaService facturaService = FactoryService.createFacturaService();
    

    @Override
    public Factura obtenerInformacion(String numeroPedido) throws SQLException, IOException, XmlException, ClassNotFoundException {
//        Factura factura = new Factura();
//        PropertiesUtil.getInstanceProperties();
//        pathXML = PropertiesUtil.getValorPathXML("mirian.pathXML");
//        factura.setVersion(PropertiesUtil.getValorPathXML("mirian.version"));
//        factura.setId("comprobante");
//        factura.setInfoTributaria(getInfoTributaria());
//        factura.setInfoFactura(getInfoFactura());
//        factura.setDetalles(getDetalles());
//        factura.setInfoAdicional(getListCamposAdicional());
        return null;
    }
    
}
