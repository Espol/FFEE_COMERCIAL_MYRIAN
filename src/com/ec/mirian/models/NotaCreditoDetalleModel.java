/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.models;

import ec.incloud.ce.bean.credito.NotaCreditoDetalle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author MARCELO
 */
public class NotaCreditoDetalleModel implements TableModel {
    
    private final List<NotaCreditoDetalle> detalles = new ArrayList<>();
    
    private final LinkedList listeners = new LinkedList();

    private final String[] nombresColumnas = {"Cod. Principal", "Descripciòn", "Cantidad", "Pre. Unidad", "Descuento",
        "SubTotal"};

    @Override
    public int getRowCount() {
        return detalles.size();
    }

    @Override
    public int getColumnCount() {
        return nombresColumnas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return nombresColumnas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NotaCreditoDetalle ncd = detalles.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ncd.getCodigoInterno();
            case 1:
                return ncd.getDescripcion();
            case 2:
                return ncd.getCantidad();
            case 3:
                return ncd.getPrecioUnitario();
            case 4:
                return ncd.getDescuento();
            case 5:
                return ncd.getPrecioTotalSinImpuesto();
            default:
                return "";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        NotaCreditoDetalle ncd = detalles.get(rowIndex);
        switch (columnIndex) {
            case 0:
                ncd.setCodigoInterno(aValue != null ? aValue.toString() : "");
                break;
            case 1:
                ncd.setDescripcion(aValue != null ? aValue.toString() : "");
                break;
            case 2:
                ncd.setCantidad(aValue != null ? aValue.toString() : "");
                break;
            case 3:
                ncd.setPrecioUnitario(aValue != null ? aValue.toString() : "");
                break;
            case 4:
                ncd.setDescuento(aValue != null ? aValue.toString() : "");
                break;
            case 5:
                ncd.setPrecioTotalSinImpuesto(aValue != null ? aValue.toString() : "");
                break;
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }
    
    private void avisaSuscriptores(TableModelEvent evento) {
        int i;
        for (i = 0; i < listeners.size(); i++) {
            ((TableModelListener) listeners.get(i)).tableChanged(evento);
        }
    }

    public void addRow(NotaCreditoDetalle fd) {
        detalles.add(fd);
        TableModelEvent evento;
        evento = new TableModelEvent(this, 
                                    this.getRowCount() - 1, 
                                    this.getRowCount() - 1, 
                                    TableModelEvent.ALL_COLUMNS, 
                                    TableModelEvent.INSERT);
        avisaSuscriptores(evento);
    }
    
    public void limpiar() {
		detalles.clear();
		TableModelEvent evento;
		evento = new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
		avisaSuscriptores(evento);
	}
    
}
