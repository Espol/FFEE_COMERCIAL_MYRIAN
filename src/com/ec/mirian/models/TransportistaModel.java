/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.models;

import com.ec.mirian.bean.Transportista;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author MARCELO
 */
public class TransportistaModel implements ComboBoxModel{
    
    private List<Transportista> transportistas ;
    
    private Transportista trans = null;
    
    private final LinkedList listeners = new LinkedList();

    @Override
    public void setSelectedItem(Object anItem) {
        trans = (Transportista) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return trans;
    }

    @Override
    public int getSize() {
        return transportistas.size();
    }

    @Override
    public Object getElementAt(int index) {
        return transportistas.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners.add(l);
    }
    
    public void addRowTransportistaModel(Transportista fd) {
        transportistas.add(fd);
    }

    public Transportista getTrans() {
        return trans;
    }

    public void setTrans(Transportista trans) {
        this.trans = trans;
    }

    public List<Transportista> getTranspostistas() {
        return transportistas;
    }

    public void setTranspostistas(List<Transportista> transpostistas) {
        this.transportistas = transpostistas;
    }
    
    public void remove(){
        this.transportistas.clear();
    }
    
}
