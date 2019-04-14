/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.bean;

/**
 *
 * @author MARCELO
 */
public class Transportista {
    
    private long id;
    private String nombreComercial;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }
    
    @Override
    public String toString(){
        return id + " - " + nombreComercial;
    }
    
}
