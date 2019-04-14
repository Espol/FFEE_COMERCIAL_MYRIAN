/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.mirian.models;

import ec.incloud.ce.bean.factura.FacturaDetalle;
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
public class FacturaDetalleModel implements TableModel {

    private final List<FacturaDetalle> detalles = new ArrayList<>();

    private final LinkedList listeners = new LinkedList();

    private final String[] nombresColumnas = {"Cod. Principal", "Descripciòn", "Cantidad", "Pre. Unidad", 
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
        FacturaDetalle fd = detalles.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return fd.getCodigoPrincipal();
            case 1:
                return fd.getDescripcion();
            case 2:
                return fd.getCantidad();
            case 3:
                return fd.getPrecioUnitario();
            case 4:
                return fd.getPrecioTotalSinImpuesto();
            default:
                return "";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        FacturaDetalle fd = detalles.get(rowIndex);
        switch (columnIndex) {
            case 0:
                fd.setCodigoPrincipal(aValue != null ? aValue.toString() : "");
                break;
            case 1:
                fd.setDescripcion(aValue != null ? aValue.toString() : "");
                break;
            case 2:
                fd.setCantidad(aValue != null ? aValue.toString() : "");
                break;
            case 3:
                fd.setPrecioUnitario(aValue != null ? aValue.toString() : "");
                break;
            case 4:
                fd.setPrecioTotalSinImpuesto(aValue != null ? aValue.toString() : "");
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

    public void addRowFacturaDetalleModel(FacturaDetalle fd) {
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
