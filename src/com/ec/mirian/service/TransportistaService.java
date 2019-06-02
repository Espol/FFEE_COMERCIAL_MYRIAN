/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.service;

import com.ec.mirian.bean.Transportista;
import com.ec.mirian.repository.FactoryRepositorio;
import com.ec.mirian.repository.PersonaRepository;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MARCELO
 */
public class TransportistaService {
    
    private static TransportistaService instance;
    
    public static TransportistaService create(){
        synchronized(TransportistaService.class){
            if(instance == null){
                instance= new TransportistaService();
            }
            return instance;
        }
    }
    
    public List<Transportista> getTransportistas() throws SQLException, ClassNotFoundException, IOException{
        List<Transportista> transportistas = new ArrayList<>();
        PersonaRepository pr = FactoryRepositorio.createPersonaRepository();
        List<String[]> list = pr.getTransportista();
        for(String[] str : list ) {
            Transportista t = new Transportista();
            t.setId(Long.parseLong(str[0]));
            t.setNombreComercial(str[18]);
            t.setIdentificacion(str[1]);
            t.setDireccion(str[12]);
            transportistas.add(t);
        }
        return transportistas;
        
        
    }
    
}
