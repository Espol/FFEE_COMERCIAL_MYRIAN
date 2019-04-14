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
public class VentaRepository {
    
    private Connection conn;
    
    public VentaRepository(){
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
    
    public String[] getVenta(String id) throws SQLException{
        String[] ventas = new String[22];
        try (PreparedStatement ps = conn.prepareStatement("Select * from ventas where num_ventcred = " + id)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ventas[0] = Util.getString(rs.getString("num_ventcred"));
                ventas[1] = Util.getString(rs.getString("fe_creacion"));
                ventas[2] = Util.getString(rs.getString("usr_creacion"));
                ventas[3] = Util.getString(rs.getString("fe_ultmod"));
                ventas[4] = Util.getString(rs.getString("usr_ultmod"));
                ventas[5] = Util.getString(rs.getString("fe_ventcred"));
                ventas[6] = Util.getString(rs.getString("usr_ventcred"));
                ventas[7] = Util.getString(rs.getString("num_cliente"));
                ventas[8] = Util.getString(rs.getString("ruc_cedident"));//identificacionComprador
                ventas[9] = Util.getString(rs.getString("nomb_comer"));//razonSocialComprador
                ventas[10] = Util.getString(rs.getString("nombre"));
                ventas[11] = Util.getString(rs.getString("direccion"));//dirEstablecimiento
                ventas[12] = Util.getString(rs.getString("telf_1"));
                ventas[13] = Util.getString(rs.getString("ciudad"));
                ventas[14] = Util.getString(rs.getString("sub_total"));//totalSinImpuestos
                ventas[15] = Util.getString(rs.getString("pr_dscto"));
                ventas[16] = Util.getString(rs.getString("vm_dscto"));//totalDescuento
                ventas[17] = Util.getString(rs.getString("pr_iva"));
                ventas[18] = Util.getString(rs.getString("vm_iva"));
                ventas[19] = Util.getString(rs.getString("tot_ventcred"));//importeTotal
                ventas[20] = Util.getString(rs.getString("precio_tipo"));
                ventas[21] = Util.getString(rs.getString("num_pago"));
            }   rs.close();
        }
         return ventas;
    }
    
    public List<String[]> getDetallesVenta(String id) throws SQLException {
        
        List<String[]> detalles = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("Select * from dventas where num_ventcred = " + id)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] detalle = new String[5];
                detalle[0] = Util.getString(rs.getString("num_ventcred"));
                detalle[1] = Util.getString(rs.getString("num_art"));//codigoAuxiliar
                detalle[2] = Util.getString(rs.getString("co_art"));//codigoPrincipal
                detalle[3] = Util.getString(rs.getString("cant_uni"));//cantidad
                detalle[4] = Util.getString(rs.getString("precio_ventcred"));//precioTotalSinImpuesto
                detalles.add(detalle);
            }   rs.close();
            return detalles;
        }
    }
    
    public String[] getArticulo(String id) throws SQLException {
        String[] articulo = new String[16];
        try (PreparedStatement ps = conn.prepareStatement("Select * from articulos where num_art = " + id)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                articulo[0] = Util.getString(rs.getString("co_art"));
                articulo[1] = Util.getString(rs.getString("num_art"));
                articulo[2] = Util.getString(rs.getString("ds_art"));//descripcion
                articulo[3] = Util.getString(rs.getString("ab_art"));
                articulo[4] = Util.getString(rs.getString("co_tipoart"));
                articulo[5] = Util.getString(rs.getString("empaq_uni"));
                articulo[6] = Util.getString(rs.getString("co_unimedida"));
                articulo[7] = Util.getString(rs.getString("cubicaje"));
                articulo[8] = Util.getString(rs.getString("precio_costo"));//precioUnitario
                articulo[9] = Util.getString(rs.getString("precio_xmayor"));
                articulo[10] = Util.getString(rs.getString("precio_pvp"));
                articulo[11] = Util.getString(rs.getString("precio_plazo1"));
                articulo[12] = Util.getString(rs.getString("tipo_statusart"));
                articulo[13] = Util.getString(rs.getString("motivo"));
                articulo[14] = Util.getString(rs.getString("fe_statusart"));
                articulo[15] = Util.getString(rs.getString("usr_statusart"));
            }   rs.close();
            return articulo;
        }
    }
    
}
