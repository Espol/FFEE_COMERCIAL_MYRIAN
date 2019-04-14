/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.repository;

import com.ec.mirian.util.ConexionUtilSQL;
import com.ec.mirian.util.Util;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MARCELO
 */
public class PersonaRepository {
    
    private Connection conn;
    
    public PersonaRepository(){
        try {
            conn = ConexionUtilSQL.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PersonaRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PersonaRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PersonaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String[] getPersona(String id) throws SQLException{
        String[] persona = new String[25];
        try (PreparedStatement ps = conn.prepareStatement("Select * from personas where num_persona = " + id)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                persona[0] = Util.getString(rs.getString("num_persona"));
                persona[1] = Util.getString(rs.getString("ruc_cedident"));//ruc
                persona[2] = Util.getString(rs.getString("titulo"));
                persona[3] = Util.getString(rs.getString("nomb_1"));
                persona[4] = Util.getString(rs.getString("nomb_2"));
                persona[5] = Util.getString(rs.getString("apel_1"));
                persona[6] = Util.getString(rs.getString("apel_2"));
                persona[7] = Util.getString(rs.getString("telf_1"));
                persona[8] = Util.getString(rs.getString("telf_2"));
                persona[9] = Util.getString(rs.getString("fax"));
                persona[10] = Util.getString(rs.getString("casilla"));
                persona[11] = Util.getString(rs.getString("ciudad"));
                persona[12] = Util.getString(rs.getString("direccion"));//dir Matriz
                persona[13] = Util.getString(rs.getString("url") != null ? rs.getString("url") : "");//correo
                persona[14] = Util.getString(rs.getString("observa"));
                persona[15] = Util.getString(rs.getString("prov"));
                persona[16] = Util.getString(rs.getString("clien"));
                persona[17] = Util.getString(rs.getString("es_transportista"));
                persona[18] = Util.getString(rs.getString("nomb_comer"));//nombreComercial, razonSocial
                persona[19] = Util.getString(rs.getString("transporte"));
                persona[20] = Util.getString(rs.getString("vend"));
                persona[21] = Util.getString(rs.getString("usuario"));
                persona[22] = Util.getString(rs.getString("nombre"));
                persona[23] = Util.getString(rs.getString("nombre2"));
                persona[24] = Util.getString(rs.getString("nombre3"));
            }   rs.close();
        }
        return persona;
    }
    
    public List<String[]> getTransportista() throws SQLException{
        List<String[]> personas = new ArrayList<>();
        
        try (PreparedStatement ps = conn.prepareStatement("Select * from personas where es_transportista = 'x'")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] persona = new String[25];
                persona[0] = Util.getString(rs.getString("num_persona"));
                persona[1] = Util.getString(rs.getString("ruc_cedident"));//ruc
                persona[2] = Util.getString(rs.getString("titulo"));
                persona[3] = Util.getString(rs.getString("nomb_1"));
                persona[4] = Util.getString(rs.getString("nomb_2"));
                persona[5] = Util.getString(rs.getString("apel_1"));
                persona[6] = Util.getString(rs.getString("apel_2"));
                persona[7] = Util.getString(rs.getString("telf_1"));
                persona[8] = Util.getString(rs.getString("telf_2"));
                persona[9] = Util.getString(rs.getString("fax"));
                persona[10] = Util.getString(rs.getString("casilla"));
                persona[11] = Util.getString(rs.getString("ciudad"));
                persona[12] = Util.getString(rs.getString("direccion"));//dir Matriz
                persona[13] = Util.getString(rs.getString("url"));
                persona[14] = Util.getString(rs.getString("observa"));
                persona[15] = Util.getString(rs.getString("prov"));
                persona[16] = Util.getString(rs.getString("clien"));
                persona[17] = Util.getString(rs.getString("es_transportista"));
                persona[18] = Util.getString(rs.getString("nomb_comer"));//nombreComercial, razonSocial
                persona[19] = Util.getString(rs.getString("transporte"));
                persona[20] = Util.getString(rs.getString("vend"));
                persona[21] = Util.getString(rs.getString("usuario"));
                persona[22] = Util.getString(rs.getString("nombre"));
                persona[23] = Util.getString(rs.getString("nombre2"));
                persona[24] = Util.getString(rs.getString("nombre3"));
                personas.add(persona);
            }   rs.close();
        }
        return personas;
    }
    
    
}
