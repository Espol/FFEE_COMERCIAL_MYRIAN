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
import java.sql.SQLException;

/**
 *
 * @author MARCELO
 */
public class CreditosRepository {
    
    private static CreditosRepository instance;
    
    public static CreditosRepository getInstance () throws ClassNotFoundException, SQLException, IOException {
        synchronized(CreditosRepository.class) {
            if (instance == null) {
                instance =  new CreditosRepository();
            }
            return instance;
        }
    }
    
    private final Connection conn;
    
    public CreditosRepository() throws ClassNotFoundException, SQLException, IOException{
        conn = ConexionUtilSQL.getConnection();
    }
    
    
    public void saveCreditos(String[] creditos ) throws SQLException {
        
        String sql = "INSERT INTO creditos(fe_ultmod,usuario,fe_credito,num_cliente,observa,vendedor,vm_credito,num_pago) "
                + "VALUES (?,?,?,?,?,?,?,?)";
 
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setTimestamp(1, Util.convertDateToTimestamp(Util.getDateFromString(creditos[0], "yyyy-MM-dd HH:mm:ss")));
            pstmt.setString(2, creditos[1]);
            pstmt.setTimestamp(3, Util.convertDateToTimestamp(Util.getDateFromString(creditos[2], "yyyy-MM-dd HH:mm:ss")));
            pstmt.setString(4, creditos[3]);
            pstmt.setString(5, creditos[4]);
            pstmt.setString(6, creditos[5]);
            pstmt.setDouble(7, Util.parseDouble(creditos[6]));
            pstmt.setInt(8, Util.stringToInt(creditos[7]));
            pstmt.execute();
    }
    
}
