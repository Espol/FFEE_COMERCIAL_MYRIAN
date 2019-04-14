/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.service;

/**
 *
 * @author MARCELO
 */
public class FactoryService {
    
    public static ConexionService createConexionService() {
        return ConexionService.create();
    }
    
    public static DocumentoService createDocumentoService() {
        return DocumentoService.create();
    }
    
    public static FacturaService createFacturaService() {
        return FacturaService.create();
    }
    
    public static TransportistaService createTransportistaService() {
        return TransportistaService.create();
    }
    
    public static GuiaService createGuiaService(){
        return GuiaService.create();
    }
    
}
