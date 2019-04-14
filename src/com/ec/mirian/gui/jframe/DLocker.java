/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.mirian.gui.jframe;

import javax.swing.JDialog;

/**
 *
 * @author MARCELO
 */
public class DLocker extends JDialog {

	

    public DLocker() {
        super(VentanaPrincipal.getInstance(), true);
        initComponents();
        setLocationRelativeTo(null);
    }

    public void mostrarNuevoMensaje(String mensaje) {
        lblDescripcion.setText(mensaje);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        pnlContenedor = new javax.swing.JPanel();
        lblDescripcion = new javax.swing.JLabel();
        lblLoading = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);

        pnlContenedor.setBackground(new java.awt.Color(255, 255, 255));
        pnlContenedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        pnlContenedor.setLayout(new java.awt.BorderLayout(5, 5));

        lblDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        lblDescripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescripcion.setText("Espere por favor");
        lblDescripcion.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 5, 5, 5));
        lblDescripcion.setOpaque(true);
        pnlContenedor.add(lblDescripcion, java.awt.BorderLayout.PAGE_END);

        lblLoading.setBackground(new java.awt.Color(255, 255, 255));
        lblLoading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cargando.gif"))); // NOI18N
        lblLoading.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 1, 5));
        lblLoading.setOpaque(true);
        pnlContenedor.add(lblLoading, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlContenedor, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>

    // Variables declaration - do not modify
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblLoading;
    private javax.swing.JPanel pnlContenedor;
	// End of variables declaration
}
