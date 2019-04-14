/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.service;

import com.ec.mirian.util.Util;
import ec.incloud.ce.xml.exception.XmlException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author MARCELO
 */
public class DocumentoService {
    
    private static DocumentoService instance;
    
    public static DocumentoService create(){
        synchronized(DocumentoService.class) {
            if(instance==null){
                instance = new DocumentoService();
            }
            return instance;
        }
    }
    
    public void generarFactura(String numVentCred) {
        try {
            FacturaService gf = new FacturaService();
            gf.setNumVentCred(numVentCred);
            gf.generarFacturaXML();
        } catch (SQLException ex) {
            Util.mostrarError(ex, "Error1 SQL Exception");
        } catch (IOException ex) {
            Util.mostrarError(ex, "Error2 IOException");
        } catch (XmlException ex) {
            Util.mostrarError(ex, "Error3 XmlException");
        } catch (ClassNotFoundException ex) {
            Util.mostrarError(ex, "Error4 ClassNotFoundException");
        }
    }
    
}
