/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.repository;

import com.ec.mirian.domain.Cargo;
import com.ec.mirian.util.ConexionUtilSQL;
import com.ec.mirian.util.Util;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MARCELO
 */
public class CargosRepository {
    
    private static CargosRepository instance ;
    
    public static CargosRepository getIntance() throws ClassNotFoundException, SQLException, IOException {
        synchronized(CargosRepository.class){
            if(instance == null){
                instance = new CargosRepository();
            }
            return instance;
        }
    }
    
    private final Connection conn;
    
    public CargosRepository() throws ClassNotFoundException, SQLException, IOException{
        conn = ConexionUtilSQL.getConnection();
    }
    
    public int getLastRegistro() throws SQLException {
        int max = 0;
        try (PreparedStatement ps = conn.prepareStatement("Select max(num_cargo) as max from cargos")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                max = rs.getInt(1);
            }   rs.close();
        }
        return max;
    }
    
    
    
    public void saveCargas(Cargo cargos ) throws SQLException {
        
        String sql = "INSERT INTO cargos(fe_creacion,usr_creacion,fe_ultmod,usr_ultmod,fe_cargo,"
                + "usr_cargo,num_cliente,observa,vm_cargo, generadoxventfac) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
 
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setTimestamp(1, Util.convertDateToTimestamp(cargos.getFeCreacion()));
            pstmt.setString(2, cargos.getUsrCreacion());
            pstmt.setTimestamp(3, Util.convertDateToTimestamp(cargos.getFeUltmod()));
            pstmt.setString(4, cargos.getUsrUltmod());
            pstmt.setTimestamp(5, Util.convertDateToTimestamp(cargos.getFeCargo()));
            pstmt.setString(6, cargos.getUsrCargo());
            pstmt.setLong(7, cargos.getNumCliente());
            pstmt.setString(8, cargos.getObservacion() != null ? cargos.getObservacion() : "");
            pstmt.setBigDecimal(9, cargos.getVmCargo());
            pstmt.setInt(10, cargos.getGeneradoxventfac().intValue());
            pstmt.execute();
    }
    
}
