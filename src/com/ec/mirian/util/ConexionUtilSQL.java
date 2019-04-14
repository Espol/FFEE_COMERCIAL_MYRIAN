/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MARCELO
 */
public class ConexionUtilSQL {
    
    private static Connection conn;
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException, UnsupportedEncodingException, IOException {
        synchronized(ConexionUtilSQL.class) {
            if(conn == null){
                AccesoDB.getInstanceProperties();
                Class.forName(AccesoDB.getValorAcceso("db.class-name"));
                conn =  DriverManager.getConnection(Util.getUrl());
            }
            return conn;
        }
    }
    
    
    
}
