/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.mirian.app;

import com.ec.mirian.gui.jframe.Documentos;
import com.ec.mirian.gui.jframe.VentanaPrincipal;

/**
 *
 * @author MARCELO
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Documentos doc = new Documentos();
        doc.setVisible(true);
        
//        VentanaPrincipal vp = new VentanaPrincipal();
//        vp.setVisible(true);
        
//        ConexionService cs = FactoryService.createConexionService();
//        Configuracion c = cs.getConfiguracionById(Constante.ID_CONFIGURACION_DEFECTO);
//        System.out.println("respuestta: " + c.getXsdfactura() );
        
    }
}
