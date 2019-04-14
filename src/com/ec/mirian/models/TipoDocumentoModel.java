/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.models;

import com.ec.mirian.enumerado.TipoDocumentoEnum;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author MARCELO
 */
public class TipoDocumentoModel implements  ComboBoxModel {
    
    TipoDocumentoEnum[] tipo = {TipoDocumentoEnum.FACTURA,
                                TipoDocumentoEnum.GUIA,
                                TipoDocumentoEnum.CREDITO,
                                TipoDocumentoEnum.DEBITO,
                                TipoDocumentoEnum.RETENCION};
    
    TipoDocumentoEnum seleccionado = null;

    @Override
    public void setSelectedItem(Object anItem) {
        seleccionado = (TipoDocumentoEnum) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return seleccionado;
    }

    @Override
    public int getSize() {
         return tipo.length;
    }

    @Override
    public Object getElementAt(int index) {
        return tipo[index];
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        
    }
    
}
