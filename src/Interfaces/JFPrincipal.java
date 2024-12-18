/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import javax.swing.WindowConstants;
/**
 *
 * @author alore
 */
public class JFPrincipal extends javax.swing.JFrame {

    Ventas venta = new Ventas();
    frmProveedores proveedores = new frmProveedores();
    frmClientes clientes = new frmClientes();
    jfDetalleVenta detalleVenta = new jfDetalleVenta();
    jfProductos productos = new jfProductos();
    
    /**
     * Creates new form JFPrincipal
     */
    public JFPrincipal() {
        initComponents();
        this.setResizable(false);
        this.setBounds(590, 500, 590, 500);
        this.setLocationRelativeTo(null);
        //this.setTitle("Ventana Principal");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnDetalleVenta = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miVenta = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        miClientes = new javax.swing.JMenuItem();
        miProveedores = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana Principal");
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("MATERIALES MARICARMEN");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(80, 0, 410, 40);

        btnDetalleVenta.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnDetalleVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Detalle_Venta.png"))); // NOI18N
        btnDetalleVenta.setText("DETALLE VENTA");
        btnDetalleVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDetalleVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleVentaActionPerformed(evt);
            }
        });
        jPanel1.add(btnDetalleVenta);
        btnDetalleVenta.setBounds(220, 240, 145, 80);

        btnVentas.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Venta.png"))); // NOI18N
        btnVentas.setText("VENTAS");
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });
        jPanel1.add(btnVentas);
        btnVentas.setBounds(30, 240, 140, 80);

        btnClientes.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Clientes.png"))); // NOI18N
        btnClientes.setText("CLIENTES");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        jPanel1.add(btnClientes);
        btnClientes.setBounds(410, 240, 140, 80);

        btnSalir.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Salir.png"))); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir);
        btnSalir.setBounds(410, 350, 140, 80);

        btnProveedores.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Proveedores.png"))); // NOI18N
        btnProveedores.setText("PROVEEDORES");
        btnProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedoresActionPerformed(evt);
            }
        });
        jPanel1.add(btnProveedores);
        btnProveedores.setBounds(30, 350, 140, 80);

        btnProductos.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Productos.png"))); // NOI18N
        btnProductos.setText("PRODUCTOS");
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });
        jPanel1.add(btnProductos);
        btnProductos.setBounds(220, 350, 140, 80);

        jLabel1.setBackground(new java.awt.Color(0, 153, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cementera.png"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(570, 522));
        jLabel1.setMinimumSize(new java.awt.Dimension(570, 522));
        jPanel1.add(jLabel1);
        jLabel1.setBounds(170, 40, 240, 190);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(-1, -1, 580, 460);

        jMenu1.setText("Pestañas");

        miVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Venta.png"))); // NOI18N
        miVenta.setText("Ventas");
        miVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miVentaActionPerformed(evt);
            }
        });
        jMenu1.add(miVenta);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Detalle_Venta.png"))); // NOI18N
        jMenuItem2.setText("Detalle Ventas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        miClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Clientes.png"))); // NOI18N
        miClientes.setText("Clientes");
        miClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miClientesActionPerformed(evt);
            }
        });
        jMenu1.add(miClientes);

        miProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Proveedores.png"))); // NOI18N
        miProveedores.setText("Proveedores");
        miProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miProveedoresActionPerformed(evt);
            }
        });
        jMenu1.add(miProveedores);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Productos.png"))); // NOI18N
        jMenuItem5.setText("Productos");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Salir.png"))); // NOI18N
        jMenuItem6.setText("Salir");
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void miVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miVentaActionPerformed
        // TODO add your handling code here:
        venta.Cargar("");
        venta.setVisible(true);
        //venta.setResizable(false);
        //v
        //venta.setLocationRelativeTo(JFPrincipal);
    }//GEN-LAST:event_miVentaActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        // TODO add your handling code here:
        venta.Cargar("");
        venta.setVisible(true);
    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedoresActionPerformed
        // TODO add your handling code here:
        proveedores.Cargar("");
        proveedores.setVisible(true);
        //venta.setResizable(false);
    }//GEN-LAST:event_btnProveedoresActionPerformed

    private void miProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miProveedoresActionPerformed
        // TODO add your handling code here:
        proveedores.Cargar("");
        proveedores.setVisible(true);
    }//GEN-LAST:event_miProveedoresActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        // TODO add your handling code here:
        clientes.Cargar("");
        clientes.setVisible(true);
        
    }//GEN-LAST:event_btnClientesActionPerformed

    private void miClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miClientesActionPerformed
        // TODO add your handling code here:
        clientes.Cargar("");
        clientes.setVisible(true);
    }//GEN-LAST:event_miClientesActionPerformed

    private void btnDetalleVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleVentaActionPerformed
        // TODO add your handling code here:
        detalleVenta.Cargar("");
        detalleVenta.setVisible(true);
    }//GEN-LAST:event_btnDetalleVentaActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        // TODO add your handling code here:
        productos.Cargar("");
        productos.setVisible(true);
    }//GEN-LAST:event_btnProductosActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        detalleVenta.Cargar("");
        detalleVenta.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        productos.Cargar("");
        productos.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnDetalleVenta;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem miClientes;
    private javax.swing.JMenuItem miProveedores;
    private javax.swing.JMenuItem miVenta;
    // End of variables declaration//GEN-END:variables
}
