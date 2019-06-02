/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.repository;

import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author MARCELO
 */
public class FactoryRepositorio {
    
    public static CargosRepository createCargosRepository() throws ClassNotFoundException, SQLException, IOException{
        return CargosRepository.getIntance();
    }
    
    public static InformacionRepository createInformacionRepository() throws ClassNotFoundException, SQLException, IOException {
        return InformacionRepository.getIntance();
    }
    
    public static PersonaRepository createPersonaRepository() throws ClassNotFoundException, SQLException, IOException {
        return PersonaRepository.getIntance();
    }
    
    public static VentaRepository createVentaRepository() throws ClassNotFoundException, SQLException, IOException {
        return VentaRepository.getIntance();
    }
    
    public static DevolucionRepository createDevolucionRepository() throws ClassNotFoundException, SQLException, IOException{
        return DevolucionRepository.getIntance();
    }
    
    public static CreditosRepository createCreditosRepository() throws ClassNotFoundException, SQLException, IOException {
        return CreditosRepository.getInstance();
    }
    
}
