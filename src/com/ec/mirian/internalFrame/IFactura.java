/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.internalFrame;

import com.ec.mirian.gui.jframe.DLocker;
import com.ec.mirian.models.FacturaDetalleModel;

/**
 *
 * @author MARCELO
 */
public class IFactura extends javax.swing.JInternalFrame {

    /**
     * Creates new form IFactura
     */
    public IFactura() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        pnlPrincipal = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlPedido = new javax.swing.JPanel();
        pnlInputPedido = new javax.swing.JPanel();
        lblPedido = new javax.swing.JLabel();
        txtNumeroPedido = new javax.swing.JTextField();
        btnConfirmarDatos = new javax.swing.JButton();
        btnXml = new javax.swing.JButton();
        pnlInfoTributario = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNombreComercial = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtMatriz = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtEstablecimiento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPtoEmision = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSecuencial = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblFechaEmision = new javax.swing.JLabel();
        txtFechaEmision = new javax.swing.JTextField();
        lblIdTipoComprador = new javax.swing.JLabel();
        txtTipoIdComprador = new javax.swing.JTextField();
        lblIdComprador = new javax.swing.JLabel();
        txtIdComprador = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtCiudadCliente = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtCorreoCliente = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        lblRSComprador = new javax.swing.JLabel();
        txtRsComprador = new javax.swing.JTextField();
        lblDirComprador = new javax.swing.JLabel();
        txtDirComprador = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtCodigoCliente = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbDetalle = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        txtPorDescuento = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        txtPorIva = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtIva = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtImporteTotal = new javax.swing.JTextField();
        pnlEmision = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtRutaXml = new javax.swing.JTextField();
        btnValidar = new javax.swing.JButton();
        btnRecepcion = new javax.swing.JButton();
        btnAutorizacion = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtXmlAutorizado = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtPdf = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtclaveAcceso = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtFechaAutorizacion = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        edtConsolaFactura = new javax.swing.JEditorPane();

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setClosable(true);
        setMaximizable(true);
        setTitle(".:: FACTURAS ::.");
        getContentPane().setLayout(new java.awt.CardLayout());

        pnlPrincipal.setLayout(new java.awt.GridLayout(0, 1));

        pnlInputPedido.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlInputPedido.setLayout(new javax.swing.BoxLayout(pnlInputPedido, javax.swing.BoxLayout.LINE_AXIS));

        lblPedido.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPedido.setText("Pedido:");
        pnlInputPedido.add(lblPedido);

        txtNumeroPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroPedidoActionPerformed(evt);
            }
        });
        pnlInputPedido.add(txtNumeroPedido);

        btnConfirmarDatos.setText("Ver Datos");
        btnConfirmarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarDatosActionPerformed(evt);
            }
        });
        pnlInputPedido.add(btnConfirmarDatos);

        btnXml.setText("XML");
        btnXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXmlActionPerformed(evt);
            }
        });
        pnlInputPedido.add(btnXml);

        pnlInfoTributario.setBorder(javax.swing.BorderFactory.createTitledBorder("Información Tributaria"));
        pnlInfoTributario.setLayout(new java.awt.GridLayout(3, 0, 0, 3));

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jLabel3.setText("Razon Social:");
        jPanel1.add(jLabel3);

        txtRazonSocial.setEditable(false);
        jPanel1.add(txtRazonSocial);

        jLabel4.setText("  Nombre Comercial:");
        jPanel1.add(jLabel4);

        txtNombreComercial.setEditable(false);
        jPanel1.add(txtNombreComercial);

        pnlInfoTributario.add(jPanel1);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jLabel9.setText("Matriz:");
        jPanel2.add(jLabel9);

        txtMatriz.setEditable(false);
        jPanel2.add(txtMatriz);

        pnlInfoTributario.add(jPanel2);

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jLabel6.setText("Establecimiento:");
        jPanel3.add(jLabel6);

        txtEstablecimiento.setEditable(false);
        jPanel3.add(txtEstablecimiento);

        jLabel7.setText("  Pto Emision:");
        jPanel3.add(jLabel7);

        txtPtoEmision.setEditable(false);
        jPanel3.add(txtPtoEmision);

        jLabel8.setText("  Secuencial:");
        jPanel3.add(jLabel8);

        txtSecuencial.setEditable(false);
        jPanel3.add(txtSecuencial);

        jLabel24.setText("  Identificacion:");
        jPanel3.add(jLabel24);

        txtDocumento.setEditable(false);
        txtDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDocumentoActionPerformed(evt);
            }
        });
        jPanel3.add(txtDocumento);

        pnlInfoTributario.add(jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Información Factura"), "Información Factura"));
        jPanel4.setLayout(new java.awt.GridLayout(2, 0, 0, 3));

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        lblFechaEmision.setText("Fe. Emisión:");
        jPanel5.add(lblFechaEmision);

        txtFechaEmision.setEditable(false);
        jPanel5.add(txtFechaEmision);

        lblIdTipoComprador.setText("  Tipo:");
        jPanel5.add(lblIdTipoComprador);

        txtTipoIdComprador.setEditable(false);
        jPanel5.add(txtTipoIdComprador);

        lblIdComprador.setText("  Doc.:");
        jPanel5.add(lblIdComprador);

        txtIdComprador.setEditable(false);
        jPanel5.add(txtIdComprador);

        jLabel29.setText("  Ciudad:");
        jPanel5.add(jLabel29);

        txtCiudadCliente.setEditable(false);
        jPanel5.add(txtCiudadCliente);

        jLabel30.setText("  Correo:");
        jPanel5.add(jLabel30);

        txtCorreoCliente.setEditable(false);
        jPanel5.add(txtCorreoCliente);

        jPanel4.add(jPanel5);

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        lblRSComprador.setText("Comprador:");
        jPanel6.add(lblRSComprador);

        txtRsComprador.setEditable(false);
        jPanel6.add(txtRsComprador);

        lblDirComprador.setText("  Dir Comprador:");
        jPanel6.add(lblDirComprador);

        txtDirComprador.setEditable(false);
        txtDirComprador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDirCompradorActionPerformed(evt);
            }
        });
        jPanel6.add(txtDirComprador);

        jLabel31.setText("  Codigo:");
        jPanel6.add(jLabel31);

        txtCodigoCliente.setEditable(false);
        jPanel6.add(txtCodigoCliente);

        jPanel4.add(jPanel6);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));
        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.LINE_AXIS));

        tbDetalle.setModel(new FacturaDetalleModel());
        jScrollPane4.setViewportView(tbDetalle);

        jPanel7.add(jScrollPane4);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Totales"));
        jPanel9.setLayout(new java.awt.GridLayout(1, 0, 3, 0));

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Total"));
        jPanel16.setLayout(new javax.swing.BoxLayout(jPanel16, javax.swing.BoxLayout.LINE_AXIS));

        jLabel28.setText("$ ");
        jPanel16.add(jLabel28);

        txtValor.setEditable(false);
        txtValor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel16.add(txtValor);

        jPanel9.add(jPanel16);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Descuento"));
        jPanel11.setLayout(new javax.swing.BoxLayout(jPanel11, javax.swing.BoxLayout.LINE_AXIS));

        txtPorDescuento.setEditable(false);
        txtPorDescuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel11.add(txtPorDescuento);

        jLabel17.setText(" % ");
        jPanel11.add(jLabel17);

        jLabel25.setText(" $ ");
        jPanel11.add(jLabel25);

        txtDescuento.setEditable(false);
        txtDescuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel11.add(txtDescuento);

        jPanel9.add(jPanel11);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Subtotales"));
        jPanel15.setLayout(new javax.swing.BoxLayout(jPanel15, javax.swing.BoxLayout.LINE_AXIS));

        jLabel19.setText("$ ");
        jPanel15.add(jLabel19);

        txtSubTotal.setEditable(false);
        txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel15.add(txtSubTotal);

        jPanel9.add(jPanel15);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Iva"));
        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.LINE_AXIS));

        txtPorIva.setEditable(false);
        txtPorIva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel12.add(txtPorIva);

        jLabel26.setText(" % ");
        jPanel12.add(jLabel26);

        jLabel27.setText(" $ ");
        jPanel12.add(jLabel27);

        txtIva.setEditable(false);
        txtIva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel12.add(txtIva);

        jPanel9.add(jPanel12);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Importe Total"));
        jPanel14.setLayout(new javax.swing.BoxLayout(jPanel14, javax.swing.BoxLayout.LINE_AXIS));

        jLabel18.setText("$ ");
        jPanel14.add(jLabel18);

        txtImporteTotal.setEditable(false);
        txtImporteTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel14.add(txtImporteTotal);

        jPanel9.add(jPanel14);

        javax.swing.GroupLayout pnlPedidoLayout = new javax.swing.GroupLayout(pnlPedido);
        pnlPedido.setLayout(pnlPedidoLayout);
        pnlPedidoLayout.setHorizontalGroup(
            pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlInputPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlInfoTributario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE)
            .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE))
        );
        pnlPedidoLayout.setVerticalGroup(
            pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedidoLayout.createSequentialGroup()
                .addComponent(pnlInputPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInfoTributario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 84, Short.MAX_VALUE))
            .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPedidoLayout.createSequentialGroup()
                    .addContainerGap(357, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Confirmación Pedido", pnlPedido);

        jPanel10.setLayout(new java.awt.GridLayout(2, 0, 3, 3));

        jPanel13.setLayout(new javax.swing.BoxLayout(jPanel13, javax.swing.BoxLayout.X_AXIS));

        jLabel1.setText(" XML: ");
        jLabel1.setAlignmentY(0.3F);
        jPanel13.add(jLabel1);

        txtRutaXml.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtRutaXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRutaXmlActionPerformed(evt);
            }
        });
        jPanel13.add(txtRutaXml);

        btnValidar.setText("Validar XML");
        btnValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidarActionPerformed(evt);
            }
        });
        jPanel13.add(btnValidar);

        btnRecepcion.setText("Recepción");
        btnRecepcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecepcionActionPerformed(evt);
            }
        });
        jPanel13.add(btnRecepcion);

        btnAutorizacion.setText("Autorizar");
        btnAutorizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAutorizacionActionPerformed(evt);
            }
        });
        jPanel13.add(btnAutorizacion);

        jPanel10.add(jPanel13);

        jPanel17.setLayout(new javax.swing.BoxLayout(jPanel17, javax.swing.BoxLayout.LINE_AXIS));

        jLabel20.setText("   Autorizado:  ");
        jPanel17.add(jLabel20);

        txtXmlAutorizado.setEditable(false);
        txtXmlAutorizado.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jPanel17.add(txtXmlAutorizado);

        jLabel21.setText("  PDF:  ");
        jPanel17.add(jLabel21);

        txtPdf.setEditable(false);
        txtPdf.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jPanel17.add(txtPdf);

        jPanel10.add(jPanel17);

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Log"));
        jPanel18.setLayout(new java.awt.BorderLayout());

        jPanel20.setLayout(new javax.swing.BoxLayout(jPanel20, javax.swing.BoxLayout.LINE_AXIS));

        jLabel5.setText("  Clave de Acceso:  ");
        jPanel20.add(jLabel5);

        txtclaveAcceso.setEditable(false);
        jPanel20.add(txtclaveAcceso);

        lblEstado.setText("  Estado:  ");
        jPanel20.add(lblEstado);

        txtEstado.setEditable(false);
        jPanel20.add(txtEstado);

        jLabel22.setText("  Fecha Autorizacion:  ");
        jPanel20.add(jLabel22);

        txtFechaAutorizacion.setEditable(false);
        jPanel20.add(txtFechaAutorizacion);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel18.add(jPanel19, java.awt.BorderLayout.PAGE_START);

        edtConsolaFactura.setBorder(null);
        edtConsolaFactura.setContentType("text/html"); // NOI18N
        jScrollPane2.setViewportView(edtConsolaFactura);

        jPanel18.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pnlEmisionLayout = new javax.swing.GroupLayout(pnlEmision);
        pnlEmision.setLayout(pnlEmisionLayout);
        pnlEmisionLayout.setHorizontalGroup(
            pnlEmisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlEmisionLayout.setVerticalGroup(
            pnlEmisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmisionLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Emisión", pnlEmision);

        pnlPrincipal.add(jTabbedPane1);

        getContentPane().add(pnlPrincipal, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNumeroPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroPedidoActionPerformed

    private void btnConfirmarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarDatosActionPerformed
        // TODO add your handling code here:
        final DLocker bloqueador = new DLocker();
        Thread hilo = new Thread() {
            @Override
            public void run() {
                try {
//                    confirmarDatos();
                } finally {
                    bloqueador.dispose();
                }
            }
        };
        hilo.start();
        bloqueador.setVisible(true);
    }//GEN-LAST:event_btnConfirmarDatosActionPerformed

    private void btnXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXmlActionPerformed
        // TODO add your handling code here:
        final DLocker bloqueador = new DLocker();
        Thread hilo = new Thread() {
            @Override
            public void run() {
                try {
//                    generacionXML();
                } finally {
                    bloqueador.dispose();
                }
            }
        };
        hilo.start();
        bloqueador.setVisible(true);
    }//GEN-LAST:event_btnXmlActionPerformed

    private void txtDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocumentoActionPerformed

    private void txtDirCompradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDirCompradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDirCompradorActionPerformed

    private void btnValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidarActionPerformed
        // TODO add your handling code here:
        final DLocker bloqueador = new DLocker();
        Thread hilo = new Thread() {
            @Override
            public void run() {
                try {
//                    validarAndFirmarXML();
                } finally {
                    bloqueador.dispose();
                }
            }
        };
        hilo.start();
        bloqueador.setVisible(true);
    }//GEN-LAST:event_btnValidarActionPerformed

    private void btnRecepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecepcionActionPerformed
        final DLocker bloqueador = new DLocker();
        Thread hilo = new Thread() {
            @Override
            public void run() {
                try {
//                    recepcionSRI();
                } finally {
                    bloqueador.dispose();
                }
            }
        };
        hilo.start();
        bloqueador.setVisible(true);
    }//GEN-LAST:event_btnRecepcionActionPerformed

    private void btnAutorizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAutorizacionActionPerformed
        // TODO add your handling code here:
        final DLocker bloqueador = new DLocker();
        Thread hilo = new Thread() {
            @Override
            public void run() {
                try {
//                    autorizadoSRI();
                } finally {
                    bloqueador.dispose();
                }
            }
        };
        hilo.start();
        bloqueador.setVisible(true);
    }//GEN-LAST:event_btnAutorizacionActionPerformed

    private void txtRutaXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRutaXmlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRutaXmlActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAutorizacion;
    private javax.swing.JButton btnConfirmarDatos;
    private javax.swing.JButton btnRecepcion;
    private javax.swing.JButton btnValidar;
    private javax.swing.JButton btnXml;
    private javax.swing.JEditorPane edtConsolaFactura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDirComprador;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaEmision;
    private javax.swing.JLabel lblIdComprador;
    private javax.swing.JLabel lblIdTipoComprador;
    private javax.swing.JLabel lblPedido;
    private javax.swing.JLabel lblRSComprador;
    private javax.swing.JPanel pnlEmision;
    private javax.swing.JPanel pnlInfoTributario;
    private javax.swing.JPanel pnlInputPedido;
    private javax.swing.JPanel pnlPedido;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTable tbDetalle;
    private javax.swing.JTextField txtCiudadCliente;
    private javax.swing.JTextField txtCodigoCliente;
    private javax.swing.JTextField txtCorreoCliente;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtDirComprador;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtEstablecimiento;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFechaAutorizacion;
    private javax.swing.JTextField txtFechaEmision;
    private javax.swing.JTextField txtIdComprador;
    private javax.swing.JTextField txtImporteTotal;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtMatriz;
    private javax.swing.JTextField txtNombreComercial;
    private javax.swing.JTextField txtNumeroPedido;
    private javax.swing.JTextField txtPdf;
    private javax.swing.JTextField txtPorDescuento;
    private javax.swing.JTextField txtPorIva;
    private javax.swing.JTextField txtPtoEmision;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtRsComprador;
    private javax.swing.JTextField txtRutaXml;
    private javax.swing.JTextField txtSecuencial;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTipoIdComprador;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txtXmlAutorizado;
    private javax.swing.JTextField txtclaveAcceso;
    // End of variables declaration//GEN-END:variables
}