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

/**
 *
 * @author MARCELO
 */
public class InformacionRepository {
    
    private static InformacionRepository instance ;
    
    public static InformacionRepository getIntance() throws ClassNotFoundException, SQLException, IOException {
        synchronized(InformacionRepository.class){
            if(instance == null){
                instance = new InformacionRepository();
            }
            return instance;
        }
    }
    
    private final Connection conn;
    
    public InformacionRepository() throws ClassNotFoundException, SQLException, IOException{
        conn = ConexionUtilSQL.getConnection();
    }
    
    public List<String[]> getInformacion() throws SQLException{
        List<String[]> informacion = new ArrayList<>();
        
        try (PreparedStatement ps = conn.prepareStatement("Select * from sysinfo")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] infor = new String[8];
                infor[0] = Util.getString(rs.getString("num_persona"));
                infor[1] = Util.getString(rs.getString("ruc_cedident"));//ruc
                infor[2] = Util.getString(rs.getString("nomb_comer"));
                infor[3] = Util.getString(rs.getString("direccion"));
                infor[4] = Util.getString(rs.getString("telf_1"));
                infor[5] = Util.getString(rs.getString("e_mail"));
                infor[6] = Util.getString(rs.getString("pr_iva"));
                infor[7] = Util.getString(rs.getString("gremi_puntopartida"));
                informacion.add(infor);
            }   rs.close();
        }
        return informacion;
    }
    
    public String[] getInformacionEmisior(String id) throws SQLException{
        String[] infor = new String[8];
        try (PreparedStatement ps = conn.prepareStatement("Select * from sysinfo where num_persona = "+ id)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                infor[0] = Util.getString(rs.getString("num_persona"));
                infor[1] = Util.getString(rs.getString("ruc_cedident"));//ruc
                infor[2] = Util.getString(rs.getString("nomb_comer"));
                infor[3] = Util.getString(rs.getString("direccion"));
                infor[4] = Util.getString(rs.getString("telef1"));
                infor[5] = Util.getString(rs.getString("e_mail"));
                infor[6] = Util.getString(rs.getString("pr_iva"));
                infor[7] = Util.getString(rs.getString("gremi_puntopartida"));
            }   rs.close();
        }
        return infor;
    }
    
}
