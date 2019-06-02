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
public class DevolucionRepository {
    
    private static DevolucionRepository instance;
    
    public static DevolucionRepository getIntance() throws ClassNotFoundException, SQLException, IOException {
        synchronized(DevolucionRepository.class){
            if(instance == null){
                instance = new DevolucionRepository();
            }
            return instance;
        }
    }
    
    private final Connection conn;
    
    public DevolucionRepository() throws ClassNotFoundException, SQLException, IOException{
        conn = ConexionUtilSQL.getConnection();
    }
    
    public String[] getDevolucion(String id) throws SQLException {
        String[] devolucion = new String[15];
        try (PreparedStatement ps = conn.prepareStatement("select * from devol where num_devol = " + id); 
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                devolucion[0] = Util.getString(rs.getString("num_devol"));
                devolucion[1] = Util.getString(rs.getString("fe_creacion"));
                devolucion[2] = Util.getString(rs.getString("usr_creacion"));
                devolucion[3] = Util.getString(rs.getString("fe_ultmod"));
                devolucion[4] = Util.getString(rs.getString("usr_ultmod"));
                devolucion[5] = Util.getString(rs.getString("fe_devol"));
                devolucion[6] = Util.getString(rs.getString("usr_devol"));
                devolucion[7] = Util.getString(rs.getString("num_cliente"));
                devolucion[8] = Util.getString(rs.getString("num_ventcred"));
                devolucion[9] = Util.getString(rs.getString("observa"));
                devolucion[10] = Util.getString(rs.getString("sub_total"));
                devolucion[11] = Util.getString(rs.getString("pr_dscto"));
                devolucion[12] = Util.getString(rs.getString("pr_iva"));
                devolucion[13] = Util.getString(rs.getString("tot_devol"));
                devolucion[14] = Util.getString(rs.getString("num_pago"));
            }
        } 
        return devolucion;
    }
    
    public List<String[]> getDetalleDevolucion(String id) throws SQLException{
        List<String[]> detalles = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("Select * from ddevol where num_devol = " + id)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] detalle = new String[5];
                detalle[0] = Util.getString(rs.getString("num_devol"));
                detalle[1] = Util.getString(rs.getString("num_art"));//codigoAuxiliar
                detalle[2] = Util.getString(rs.getString("co_art"));//codigoPrincipal
                detalle[3] = Util.getString(rs.getString("cant_uni"));//cantidad
                detalle[4] = Util.getString(rs.getString("precio_devol"));//preciounitario
                detalles.add(detalle);
            }   rs.close();
            return detalles;
        }
    }
    
}
