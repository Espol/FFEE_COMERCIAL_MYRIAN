/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.service;

import com.ec.mirian.conexion.ConfiguracionConexion;
import com.ec.mirian.conexion.VentFacCabeceraConexion;
import com.ec.mirian.conexion.VentfactDetalleConexion;
import com.ec.mirian.domain.Cargo;
import com.ec.mirian.domain.Configuracion;
import com.ec.mirian.domain.VentfacCabecera;
import com.ec.mirian.domain.VentfacDetalle;
import com.ec.mirian.factory.Factory;
import com.ec.mirian.gui.jframe.VentanaPrincipal;
import com.ec.mirian.repository.CargosRepository;
import com.ec.mirian.util.Util;
import ec.incloud.ce.bean.factura.Factura;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MARCELO
 */
public class ConexionService {
    
    private static ConexionService instace;
    
    public static ConexionService create() {
        synchronized(ConexionService.class){
            if(instace == null){
                instace = new ConexionService();
            }
            return instace;
        }
    }
    
    public void saveInformacion(String[] cab, List<String[]> det, Factura fa) throws ClassNotFoundException, SQLException, IOException{
        VentfacCabecera ven = this.getVentfacCabeceraNew(cab, fa);
        this.saveCabecera(ven);
        this.saveDetalle(ven, det);
        Cargo c = this.saveCarga(cab);
        c.setObservacion(c.getObservacion().replace("{0}", Util.eliminarCerosInicios(VentanaPrincipal.getInstance().getSecuencial())));
        CargosRepository cr = new CargosRepository();
        cr.saveCargas(c);
    }
    
    private void saveCabecera(VentfacCabecera ven) {
        VentFacCabeceraConexion vfcc = Factory.getVentFacCabeceraConexion();
        vfcc.save(ven);
    }
    
    private void saveDetalle(VentfacCabecera ven,List<String[]> det) {
        for(String[] s : det) {
            VentfacDetalle d = new VentfacDetalle();
            d.setCantidadUni(Util.stringToLong(s[3]));
            d.setCoArt(s[2]);
            d.setNumArt(Util.stringToLong(s[1]));
            d.setNumVentfac(ven);
            d.setPrecioVentfac(Util.stringToBigDecimal(s[4]));
            VentfactDetalleConexion vd = Factory.getVentfactDetalleConexion();
            vd.save(d);
        }
    }
    
    private Cargo saveCarga(String[] cab){
        Cargo c = new Cargo();
        c.setNumCargo(Util.stringToLong(cab[0]));
        c.setFeCreacion(new Date());
        c.setUsrCreacion("DOCU_ELECT");//cab[2]
        c.setFeUltmod(Util.getDateFromString(cab[3], "yyyy-MM-dd HH:mm:ss"));
        c.setUsrUltmod("DOCU_ELECT");//cab[4]
        c.setFeCargo(Util.getDateFromString(cab[1], "yyyy-MM-dd HH:mm:ss"));
        c.setUsrCargo("DOCU_ELECT");//cab[2]
        c.setNumCliente(Util.stringToLong(cab[7]));
        c.setObservacion("IVA DE FAC ELECT {0} (PED "+cab[0]+")");//{0} = SECUENCIAL SIN LO CEROS, {1} NUMERO DE pEDIDO
        c.setVendedor("DOCU_ELECT");//cab[4]
        c.setVmCargo(Util.stringToBigDecimal(cab[19]));
        c.setNumPago(Util.stringToBigDecimal(cab[19]));
        c.setGeneradoxventfac(Util.stringToBigInteger(cab[0]));
        c.setGeneradoxpago(BigInteger.ZERO);
//        CargoConexion cc = Factory.getCargoConexion();
//        cc.save(c);
        return c;
    }
    
    private VentfacCabecera getVentfacCabeceraNew(String[] cab, Factura fa) {
        VentfacCabecera ven = new VentfacCabecera();
        ven.setNumVentfac(Util.stringToLong(cab[0]));
        ven.setCiudad(cab[13]);
        ven.setClaveAcceso(VentanaPrincipal.getInstance().getClaveAcceso());
        ven.setDireccion(cab[11]);
        ven.setFacturaImpresa(VentanaPrincipal.getInstance().getEstabl() +"-"+
                              VentanaPrincipal.getInstance().getPtoEmision() +"-"+
                              VentanaPrincipal.getInstance().getSecuencial());
        ven.setFeCreacion(Util.getDateFromString(cab[1], "yyyy-MM-dd HH:mm:ss"));
        ven.setUsrCreacion(cab[2]);
        ven.setFeUltmod(Util.getDateFromString(cab[3], "yyyy-MM-dd HH:mm:ss"));
        ven.setUsrUltmod(cab[4]);
        ven.setFeVentfact(new Date());
        ven.setNombComer(cab[9]);
        ven.setNombre(cab[10]);
        ven.setNumCliente(Util.stringToLong(cab[7]));
        ven.setNumVentcred(cab[0]);
        ven.setPrDesct(Util.stringToBigDecimal(cab[15]));
        ven.setPrIva(Util.stringToBigDecimal(cab[17]));
        ven.setPrecioTipo(cab[20]);
        ven.setRucCedident(cab[8]);
        ven.setSubTotal(Util.stringToBigDecimal(cab[14]));
        ven.setTelf1(cab[12]);
        ven.setTotVentfac(Util.stringToBigDecimal(cab[19]));
        ven.setUsrVentfact(cab[2]);
        ven.setVmDesct(Util.stringToBigDecimal(cab[16]));
        ven.setVmIva(Util.stringToBigDecimal(cab[18]));
        ven.setSecuencial(VentanaPrincipal.getInstance().getSecuencial());
        ven.setEstablecimiento(VentanaPrincipal.getInstance().getEstabl());
        ven.setPuntoemision(VentanaPrincipal.getInstance().getPtoEmision());
        ven.setXml(VentanaPrincipal.getInstance().getXml());
        ven.setPdf(VentanaPrincipal.getInstance().getPdf());
        ven.setXmlAutorizado(VentanaPrincipal.getInstance().getXmlAutorizado());
        ven.setTipo(VentanaPrincipal.getInstance().getCodigoTipoDocumento());
        return ven;
    }
    
    public VentfacCabecera getVentaCabecera(String numVentfac) {
        VentFacCabeceraConexion vfcc = Factory.getVentFacCabeceraConexion();
        return vfcc.getCargo(Long.parseLong(numVentfac));
    }
    
    public Configuracion getConfiguracionById(Integer id) {
        ConfiguracionConexion cc = Factory.getConfiguracionConexion();
        return cc.getCofiguracionById(id);
    }
    
    public void actualizaConfiguracion(Configuracion config) {
        ConfiguracionConexion cc = Factory.getConfiguracionConexion();
        cc.actualizar(config);
    }
     
}
