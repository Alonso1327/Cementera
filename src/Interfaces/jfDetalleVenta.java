/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Clases.ConexionMySQL;
import java.io.InputStream;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class jfDetalleVenta extends javax.swing.JFrame {

    ConexionMySQL cnn = new ConexionMySQL();
    Connection cn = cnn.ConexionMySQL();
    
    DefaultTableModel modelo;
    int Filas;
    boolean decimal = false;
    int decimalPos=0;
    jfBuscarProducto bProducto = new jfBuscarProducto("jfVentaD");
    private int ultimoId;
    
    public jfDetalleVenta() {
        initComponents();
       txtCantidad.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent de) {
                obtenerSubtotal(); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                obtenerSubtotal(); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                obtenerSubtotal(); //To change body of generated methods, choose Tools | Templates.
            }
       });
       
       txtPrecio.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent de) {
                obtenerSubtotal(); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                obtenerSubtotal(); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                obtenerSubtotal(); //To change body of generated methods, choose Tools | Templates.
            }
       });
       
        Bloquear();
        Cargar("");
        this.setResizable(false);
        this.setTitle("DetalleVenta");
    }
    
    void obtenerSubtotal(){
        float precioU,cantidad,subtotal;
        
        if(txtPrecio.getText().length() > 0 && txtCantidad.getText().length() > 0){
        precioU = Float.parseFloat(txtPrecio.getText());
        cantidad = Float.parseFloat(txtCantidad.getText());
        subtotal = precioU * cantidad;
        txtSubtotal.setText(Float.toString(subtotal));
        }else{
            txtSubtotal.setText("0");
        }
            
        
    }
    
    void Bloquear(){
        txtIDDetalleVenta.setEnabled(false);
        txtFolio.setEnabled(false);
        txtIDProducto.setEnabled(false);
        txtNombreProducto.setEnabled(false);
        txtPrecio.setEnabled(false);
        txtCantidad.setEnabled(false);
        cbPresentacion.setEnabled(false);
        txtSubtotal.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnBuscarProducto.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnNuevo.setEnabled(true);
    }
    
    void Desbloquearbotones(){
        btnActualizar.setEnabled(true);
        btnBuscarProducto.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnNuevo.setEnabled(false);
    }
    
    void Desbloquear(){
        //txtIDDetalleVenta.setEnabled(true);
        txtFolio.setEnabled(true);
        txtIDProducto.setEnabled(true);
        txtNombreProducto.setEnabled(true);
        txtPrecio.setEnabled(true);
        txtCantidad.setEnabled(true);
        cbPresentacion.setEnabled(true);
        txtSubtotal.setEnabled(true);
    }
    
    void Limpiar(){
        txtIDDetalleVenta.setText("");
        txtFolio.setText("");
        txtIDProducto.setText("");
        txtNombreProducto.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtSubtotal.setText("");
        
        
    }
    
    
    void Cargar (String Valor)
    {
        String Consulta = "select * from DetalleVentas "
                + "where concat(IDDetalleVenta, IDVenta ,IDProducto, NombreProducto, "
                + "+ Precio, Cantidad , Presentacion, Subtotal) "
                + "like '%" + Valor + "%'"; 
        
        String [] Titulos = {"IDDetalleVenta", "No. Folio","IDProducto", 
            "Nombre del Producto","Precio", "Cantidad", "Presentacion", "Subtotal"};
        String [] Registros = new String[100];
        modelo = new DefaultTableModel (null, Titulos);
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(Consulta); //ResultSet=Consulta de resultados
            while(rs.next())
            {
                Registros[0] = rs.getString("IDDetalleVenta");
                Registros[1] = rs.getString("IDVenta");
                Registros[2] = rs.getString("IDProducto");
                Registros[3] = rs.getString("NombreProducto");
                Registros[4] = rs.getString("Precio");
                Registros[5] = rs.getString("Cantidad");
                Registros[6] = rs.getString("Presentacion");
                Registros[7] = rs.getString("Subtotal");
                ultimoId = rs.getInt("IDDetalleVenta");
                modelo.addRow(Registros);
                
            }
            tblDetalleVenta.setModel(modelo);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar la tabla de Detalle Venta "
            + error.getMessage());
        }
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtIDDetalleVenta = new javax.swing.JTextField();
        txtFolio = new javax.swing.JTextField();
        txtIDProducto = new javax.swing.JTextField();
        txtNombreProducto = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtSubtotal = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cbPresentacion = new javax.swing.JComboBox<>();
        btnActualizar = new javax.swing.JButton();
        btnBuscarProducto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleVenta = new javax.swing.JTable();
        btnReporte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalle Venta");
        setPreferredSize(new java.awt.Dimension(893, 710));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DETALLE VENTA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Black", 1, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("ID DETALLE VENTA");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("NO. FOLIO");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("ID PRODUCTO");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("NOMBRE DEL PRODUCTO");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("PRECIO");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("CANTIDAD");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("PRESENTACION");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("SUBTOTAL");

        txtIDDetalleVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDDetalleVentaActionPerformed(evt);
            }
        });

        txtNombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreProductoActionPerformed(evt);
            }
        });

        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        txtSubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubtotalActionPerformed(evt);
            }
        });

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Nueva_Venta.png"))); // NOI18N
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

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/buscar_DV.png"))); // NOI18N
        btnBuscar.setText("BUSCAR");
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
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

        cbPresentacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BTO", "PZA", "M3", "CMN", "KG", "TON" }));
        cbPresentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPresentacionActionPerformed(evt);
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

        btnBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/buscar_DV.png"))); // NOI18N
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(71, 71, 71)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbPresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtIDProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtIDDetalleVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtIDDetalleVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIDProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbPresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        tblDetalleVenta.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblDetalleVenta.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDetalleVenta.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDetalleVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleVentaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDetalleVenta);

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
                .addComponent(btnReporte)
                .addGap(403, 403, 403))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReporte)
                .addGap(132, 132, 132))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDDetalleVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDDetalleVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDDetalleVentaActionPerformed

    private void txtNombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreProductoActionPerformed

    private void txtSubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubtotalActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int valor =JOptionPane.showConfirmDialog(null,"¿Seguro que desea eliminar este registro?","Advertencia",
                JOptionPane.YES_NO_OPTION);
        try {
            if(valor ==0){
                PreparedStatement elim = cn.prepareStatement("DELETE FROM DetalleVentas WHERE IDDetalleVenta='"
                        + txtIDDetalleVenta.getText() + "'");

                int n = elim.executeUpdate();
                if (n>0) {
                    JOptionPane.showMessageDialog(null, "Detalle de venta borrado");
                    tblDetalleVenta.setModel(new DefaultTableModel());
                    Cargar("");
                    Limpiar();
                    Bloquear();
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "El detalle de venta no se borro");
                }
            }
            
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error + "No se elimino el registro");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        
        Limpiar();
        Desbloquear();
        txtFolio.grabFocus();
        txtIDDetalleVenta.setText(Integer.toString(ultimoId+1));
        txtCantidad.setText("0");
        txtSubtotal.setText("0");
        txtSubtotal.setEnabled(false);
        txtPrecio.setEnabled(false);
        txtNombreProducto.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnNuevo.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnBuscarProducto.setEnabled(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void cbPresentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPresentacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPresentacionActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        String idDetalle, idVenta, idProducto, nombre,precio, cantidad, presentacion, subtotal;
        String SQL;
        idDetalle= txtIDDetalleVenta.getText();
        idVenta=txtFolio.getText();
        idProducto= txtIDProducto.getText();
        nombre= txtNombreProducto.getText();
        precio= txtPrecio.getText();
        cantidad=txtCantidad.getText();
        presentacion=cbPresentacion.getSelectedItem().toString();
        subtotal= txtSubtotal.getText();
        
        SQL = "insert into DetalleVentas (IDDetalleVenta,IDVenta,IDProducto, NombreProducto, Precio, Cantidad, Presentacion, "
                + "Subtotal) "
                + "values(?,?,?,?,?,?,?,?)";
        try {
           PreparedStatement ps = cn.prepareStatement(SQL);
           ps.setString(1, idDetalle);
           ps.setString(2, idVenta);
           ps.setString(3, idProducto);
           ps.setString(4, nombre);
           ps.setString(5, precio);
           ps.setString(6, cantidad);
           ps.setString(7, presentacion);
           ps.setString(8, subtotal);
           
           int n = ps.executeUpdate();
            if (n>0) {
                JOptionPane.showMessageDialog(this, "Detalle de venta insertado");  
            }
            Cargar("");
            Limpiar();
            Bloquear();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this, "Error al insertar: " + error);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Limpiar();
        Bloquear();
         
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        String IDVenta = JOptionPane.showInputDialog(null,"INGRESA EL FOLIO QUE DESEE BUSCAR");
        String Consulta = "select IDDetalleVenta, IDVenta ,IDProducto, NombreProducto, "
                + "+ Precio, Cantidad , Presentacion, Subtotal from DetalleVentas "
                + "where IDVenta =  " + IDVenta; 
        
        String [] Titulos = {"IDDetalleVenta", "No. Folio","IDProducto", "Nombre del Producto","Precio", "Cantidad", "Presentacion", 
            "Subtotal"};
        String [] Registros = new String[1000];
        modelo = new DefaultTableModel (null, Titulos);
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(Consulta); //ResultSet=Consulta de resultados
            while(rs.next())
            {
                Registros[0] = rs.getString("IDDetalleVenta");
                Registros[1] = rs.getString("IDVenta");
                Registros[2] = rs.getString("IDProducto");
                Registros[3] = rs.getString("NombreProducto");
                Registros[4] = rs.getString("Precio");
                Registros[5] = rs.getString("Cantidad");
                Registros[6] = rs.getString("Presentacion");
                Registros[7] = rs.getString("Subtotal");
                modelo.addRow(Registros);
                
            }
            tblDetalleVenta.setModel(modelo);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar la tabla de Detalle Venta "
            + error.getMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        
        try {
            String sql ="update DetalleVentas set "
                    + " IDVenta = " + txtFolio.getText()
                    + ", IDProducto = " + txtIDProducto.getText()
                    + ", NombreProducto = '" + txtNombreProducto.getText()
                    + "', Precio = " + txtPrecio.getText()
                    + ", Cantidad = " + txtCantidad.getText()
                    + ", Presentacion = '" + cbPresentacion.getSelectedItem().toString()
                    + "', Subtotal = " + txtSubtotal.getText()
                    + " where IDDetalleVenta = " + txtIDDetalleVenta.getText()
                    + " ";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.executeUpdate();
            Cargar("");
            Limpiar();
            Bloquear();
            JOptionPane.showMessageDialog(this, "Datos actualizados");
            
            
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this, "Error: " + error.getMessage());
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tblDetalleVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleVentaMouseClicked
        // TODO add your handling code here:
        int filaSele = tblDetalleVenta.getSelectedRow();
        //String Presentacion = cbPresentacion.getSelectedItem().toString();
        
        txtIDDetalleVenta.setText(tblDetalleVenta.getValueAt(filaSele, 0).toString());
        txtFolio.setText(tblDetalleVenta.getValueAt(filaSele, 1).toString());
        txtIDProducto.setText(tblDetalleVenta.getValueAt(filaSele, 2).toString());
        txtNombreProducto.setText(tblDetalleVenta.getValueAt(filaSele, 3).toString());
        txtPrecio.setText(tblDetalleVenta.getValueAt(filaSele, 4).toString());
        txtCantidad.setText(tblDetalleVenta.getValueAt(filaSele, 5).toString());
        cbPresentacion.setSelectedItem(tblDetalleVenta.getValueAt(filaSele, 6).toString());
        txtSubtotal.setText(tblDetalleVenta.getValueAt(filaSele, 7).toString());
      
        Filas = filaSele;
        
        //txtNombreProducto.setEnabled(true);
        txtCantidad.setEnabled(true);
        cbPresentacion.setEnabled(true);
        txtIDDetalleVenta.setEnabled(false);
        txtFolio.setEnabled(false);
        txtIDProducto.setEnabled(false);
        txtPrecio.setEnabled(false);
        txtSubtotal.setEnabled(false);
        
        Desbloquearbotones();
        btnGuardar.setEnabled(false);
        
    }//GEN-LAST:event_tblDetalleVentaMouseClicked

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        // TODO add your handling code here:
        try{
         String Ruta = "/Reportes/Detalle_Venta.jasper";
            InputStream archivo = getClass().getResourceAsStream(Ruta);
            
                 JasperReport reporte = null ; 
                  reporte=(JasperReport) JRLoader.loadObject(archivo);
            
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

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
        // TODO add your handling code here:
        
        bProducto.setVisible(true);
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if(bProducto.isVisible()== true){
           bProducto.dispose();
        }else{
            this.dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        int key = evt.getKeyChar();
        boolean numeros = (key >= 48 && key <= 57);
        if(key == 46 && decimal == false){
            decimal = true;
            decimalPos = txtCantidad.getText().length();
        }else{
            if(key == 8 && txtCantidad.getText().length() == decimalPos){
                decimal = false;
                decimalPos = 0;
            }
            if (!numeros){
                evt.consume();
            }
            /*if(txtCantidad.getText().length() == 0 && numeros){
                 cantidad ;
                 txtSubtotal.setText(Float.toString(precioU*cantidad)); 
            }else if(txtCantidad.getText().length() == 0)
            {
              txtSubtotal.setText("0"); 
            }else if(numeros){
                cantidad = Float.parseFloat(txtCantidad.getText());
                txtSubtotal.setText(Float.toString(precioU*cantidad)); 
            }*/
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

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
            java.util.logging.Logger.getLogger(jfDetalleVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jfDetalleVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jfDetalleVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jfDetalleVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jfDetalleVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnReporte;
    public static javax.swing.JComboBox<String> cbPresentacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDetalleVenta;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtFolio;
    private javax.swing.JTextField txtIDDetalleVenta;
    public static javax.swing.JTextField txtIDProducto;
    public static javax.swing.JTextField txtNombreProducto;
    public static javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtSubtotal;
    // End of variables declaration//GEN-END:variables
    
}
