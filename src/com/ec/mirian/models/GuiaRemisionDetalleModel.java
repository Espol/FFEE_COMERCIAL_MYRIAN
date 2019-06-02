/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.mirian.models;

import ec.incloud.ce.bean.guia.GuiaRemisionDetalle;
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
public class GuiaRemisionDetalleModel implements TableModel {

    private final List<GuiaRemisionDetalle> detalles = new ArrayList<>();

    private final LinkedList listeners = new LinkedList();

    private final String[] nombresColumnas = {"Cantidad", "Descripciòn"};

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
        GuiaRemisionDetalle gd = detalles.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return gd.getCantidad();
            case 1:
                return gd.getDescripcion();
            default:
                return "";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        GuiaRemisionDetalle gd = detalles.get(rowIndex);
        switch (columnIndex) {
            case 0:
                gd.setCantidad(aValue != null ? aValue.toString() : "");
                break;
            case 1:
                gd.setDescripcion(aValue != null ? aValue.toString() : "");
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

    public void addRow(GuiaRemisionDetalle fd) {
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