package Interfaces;

import Clases.ConexionMySQL;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.*;

public class jfProductos extends javax.swing.JFrame {

    ConexionMySQL cnn = new ConexionMySQL();
    Connection cn = cnn.ConexionMySQL();

    DefaultTableModel modelo;
    int Filas;

    public jfProductos() {
        initComponents();
        Bloquear();
        btnCancelar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnNuevo.setEnabled(true);
        btnBuscar.setEnabled(true);
        btnEliminar.setEnabled(false);
        Cargar("");

        this.setResizable(false);
        this.setTitle("Productos");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    void Bloquear() {
        txtIDProducto.setEnabled(false);
        txtNombre.setEnabled(false);
        txtCantidad.setEnabled(false);
        txtIDUnidad.setEnabled(false);
        txtPreciocompra.setEnabled(false);
        txtPrecioventa.setEnabled(false);
        txtIDProveedor.setEnabled(false);
    }

    void Desbloquear() {
        txtIDProducto.setEnabled(true);
        txtNombre.setEnabled(true);
        txtCantidad.setEnabled(true);
        txtIDUnidad.setEnabled(true);
        txtPreciocompra.setEnabled(true);
        txtPrecioventa.setEnabled(true);
        txtIDProveedor.setEnabled(true);
        txtIDProducto.grabFocus();
    }
    void Desbloquerbotones(){
        btnActualizar.setEnabled(true);
        btnBuscar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnNuevo.setEnabled(false);}

    void Limpiar() {
        txtIDProducto.setText("");
        txtNombre.setText("");
        txtCantidad.setText("");
        txtIDUnidad.setText("");
        txtPreciocompra.setText("");
        txtPrecioventa.setText("");
        txtIDProveedor.setText("");
    }

    void Cargar(String Valor) {
        
        String Consulta = "select * from Productos "
                + "where concat(IDProducto, NombreProducto, "
                + "+ Cantidad , IDUnidad, PrecioCompra, PrecioVenta, IDProveedor) "
                + "like '%" + Valor + "%'";
        String[] Titulos = {"IDProducto", "Nombre del Producto", "Cantidad", "IDUnidad",
            "Precio de compra", "Precio de Venta", "IDProveedor"};
        String[] Registros = new String[100];
        modelo = new DefaultTableModel(null, Titulos);
        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(Consulta); //ResultSet=Consulta de resultados
            while (rs.next()) {
                Registros[0] = rs.getString("IDProducto");
                Registros[1] = rs.getString("NombreProducto");
                Registros[2] = rs.getString("Cantidad");
                Registros[3] = rs.getString("IDUnidad");
                Registros[4] = rs.getString("PrecioCompra");
                Registros[5] = rs.getString("PrecioVenta");
                Registros[6] = rs.getString("IDProveedor");
                modelo.addRow(Registros);
                

            }
            tblProductos.setModel(modelo);
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar la tabla de Productos "
                    + error.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtIDProducto = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtIDUnidad = new javax.swing.JTextField();
        txtPreciocompra = new javax.swing.JTextField();
        txtPrecioventa = new javax.swing.JTextField();
        txtIDProveedor = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        btnReporte = new javax.swing.JButton();

        jScrollPane2.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRODUCTOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Black", 1, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("ID PRODUCTO");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("NOMBRE DEL PRODUCTO");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("CANTIDAD");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("PRESENTACION");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("PRECIO DE COMPRA");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("PRECIO DE VENTA");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("ID PROVEEDOR");

        txtPrecioventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioventaActionPerformed(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(244, 244, 244));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/registro_DV.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/guardar_DV.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/actualizar.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/eliminar_DV.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Cancelar.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/buscar_DV.png"))); // NOI18N
        btnBuscar.setText("BUSCAR");
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(78, 78, 78)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIDProducto)
                    .addComponent(txtNombre)
                    .addComponent(txtCantidad)
                    .addComponent(txtIDUnidad)
                    .addComponent(txtPreciocompra)
                    .addComponent(txtPrecioventa)
                    .addComponent(txtIDProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIDProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIDUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPreciocompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtPrecioventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtIDProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        tblProductos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductos);

        btnReporte.setText("Reporte");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(339, 339, 339))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPrecioventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioventaActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:

        Limpiar();
        txtIDProducto.grabFocus();
        Desbloquear();
        btnCancelar.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnNuevo.setEnabled(false);
        btnBuscar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        String id, nombre, cantidad, unidad, compra, venta, proveedor;
        String SQL;
        id = txtIDProducto.getText();
        nombre = txtNombre.getText();
        cantidad = txtCantidad.getText();
        unidad = txtIDUnidad.getText();
        compra = txtPreciocompra.getText();
        venta = txtPrecioventa.getText();
        proveedor = txtIDProveedor.getText();

        SQL = "insert into Productos (IDProducto, NombreProducto, Cantidad, IDUnidad, "
                + "PrecioCompra, PrecioVenta, IDProveedor) "
                + "values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(SQL);
            ps.setString(1, id);
            ps.setString(2, nombre);
            ps.setString(3, cantidad);
            ps.setString(4, unidad);
            ps.setString(5, compra);
            ps.setString(6, venta);
            ps.setString(7, proveedor);

            int n = ps.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(this, "Producto insertado");
            }
            Cargar("");
            Limpiar();
            Bloquear();
            btnCancelar.setEnabled(true);
            btnGuardar.setEnabled(false);
            btnActualizar.setEnabled(false);
            btnNuevo.setEnabled(true);
            btnBuscar.setEnabled(true);
            btnEliminar.setEnabled(false);

        } catch (Exception error) {
            JOptionPane.showMessageDialog(this, "Error al insertar: " + error);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "update Productos set "
                    + "NombreProducto = '" + txtNombre.getText()
                    + "', Cantidad = '" + txtCantidad.getText()
                    + "', PrecioCompra = '" + txtPreciocompra.getText()
                    + "', PrecioVenta = '" + txtPrecioventa.getText()
                    + "' where IDProducto = '" + txtIDProducto.getText()
                    + "' ";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.executeUpdate();
            Cargar("");
            Limpiar();
            Bloquear();
            btnCancelar.setEnabled(false);
            btnGuardar.setEnabled(false);
            btnActualizar.setEnabled(false);
            btnNuevo.setEnabled(true);
            btnBuscar.setEnabled(true);
            btnEliminar.setEnabled(true);
            JOptionPane.showMessageDialog(this, "Datos actualizados");

        } catch (Exception error) {
            JOptionPane.showMessageDialog(this, "Error: " + error.getMessage());
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        // TODO add your handling code here:
        int filaSele = tblProductos.getSelectedRow();
        /*txtCantidad.setEnabled(true);
        txtPreciocompra.setEnabled(true);
        txtPrecioventa.setEnabled(true);*/

        txtIDProducto.setText(tblProductos.getValueAt(filaSele, 0).toString());
        txtNombre.setText(tblProductos.getValueAt(filaSele, 1).toString());
        txtCantidad.setText(tblProductos.getValueAt(filaSele, 2).toString());
        txtIDUnidad.setText(tblProductos.getValueAt(filaSele, 3).toString());
        txtPreciocompra.setText(tblProductos.getValueAt(filaSele, 4).toString());
        txtPrecioventa.setText(tblProductos.getValueAt(filaSele, 5).toString());
        txtIDProveedor.setText(tblProductos.getValueAt(filaSele, 6).toString());

        Filas = filaSele;
        txtCantidad.setEnabled(true);
        txtPreciocompra.setEnabled(true);
        txtPrecioventa.setEnabled(true);
        txtIDProducto.setEnabled(false);
        txtNombre.setEnabled(false);
        txtIDUnidad.setEnabled(false);
        txtIDProveedor.setEnabled(false);
        btnActualizar.setEnabled(true);
        Desbloquerbotones();
        
    }//GEN-LAST:event_tblProductosMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int fila = tblProductos.getSelectedRow();
        String cod = "";
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
        } else {
            int valor = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar este registro?", "Advertencia",
                    JOptionPane.YES_NO_OPTION);

            if (valor == JOptionPane.YES_OPTION) {
                int filaselec = tblProductos.getSelectedRow();

                cod = tblProductos.getValueAt(fila, 0).toString();
            }
        }

        try {
            PreparedStatement elim = cn.prepareStatement("DELETE FROM Productos WHERE IDProducto='" + cod + "'");

            int n = elim.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Producto borrado");
                tblProductos.setModel(new DefaultTableModel());
                Cargar("");
                Limpiar();
                Bloquear();
                btnCancelar.setEnabled(false);
                btnGuardar.setEnabled(false);
                btnActualizar.setEnabled(false);
                btnNuevo.setEnabled(true);
                btnBuscar.setEnabled(true);
                btnEliminar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "El producto no se borro");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error + "No se elimino el registro");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Limpiar();
        Bloquear();
        btnCancelar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnNuevo.setEnabled(true);
        btnBuscar.setEnabled(true);
        btnEliminar.setEnabled(false);

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        String palabraBuscar;
        palabraBuscar = JOptionPane.showInputDialog(null, "INGRESA LO QUE DESEA BUSCAR");
        if (palabraBuscar == null || palabraBuscar == " ") {
            Cargar("");
            btnCancelar.setEnabled(true);
            btnGuardar.setEnabled(false);
            btnActualizar.setEnabled(false);
            btnNuevo.setEnabled(false);
            btnBuscar.setEnabled(false);
            btnEliminar.setEnabled(true);
        } else {
            Cargar(palabraBuscar);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        // TODO add your handling code here:
        try{
        String Ruta = "C:\\Users\\BRISEIDA\\OneDrive\\Documentos\\NetBeansProjects\\Cementera\\src\\Reportes\\producto.jasper";
                 JasperReport reporte = null ; 
                  reporte=(JasperReport) JRLoader.loadObjectFromFile(Ruta);
            
                  JasperPrint imprimir = JasperFillManager.
                          fillReport(reporte,null,cnn.ConexionMySQL());
                  
                  
                  JasperViewer visor = new JasperViewer (imprimir, false);
                   visor.setTitle("Mi reporte");
                   visor.setVisible(true);
       }
       catch(Exception e)
       {
           System.out.println("Error: "+ e);
           
       }
    }//GEN-LAST:event_btnReporteActionPerformed

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
            java.util.logging.Logger.getLogger(jfProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jfProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jfProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jfProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jfProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnReporte;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtIDProducto;
    private javax.swing.JTextField txtIDProveedor;
    private javax.swing.JTextField txtIDUnidad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPreciocompra;
    private javax.swing.JTextField txtPrecioventa;
    // End of variables declaration//GEN-END:variables
}
