
package Interfaces;

import Clases.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class jfrmRegistrarVenta extends javax.swing.JFrame {

    public static String idDe, idP;
    public int presU;
    jfBuscarProducto bProducto = new jfBuscarProducto("");
    DefaultTableModel modelo;
    public String idProducto, nomProducto, cantidadP,PrecioU, precentacion, sbtotalA,idDT;
    ConexionMySQL cnn = new ConexionMySQL();
    Connection cn = cnn.ConexionMySQL();
    public jfrmRegistrarVenta() {
        initComponents();
        obtenerUtimoR();
        txtFolioB.setText("1");
        CargarTabla();
        desactivarTXT();
        txtCantidad.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent de) {
                obtenerSubtotal();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                obtenerSubtotal();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                obtenerSubtotal();
            }

            
       });
        tblRegistrarVenta.getTableHeader().setReorderingAllowed(false);
    }
    private void obtenerSubtotal() {
        if(txtPrecioUnidad.getText().length() > 0 && txtCantidad.getText().length() > 0){
            presU = Integer.parseInt(txtPrecioUnidad.getText());
            txtSubTotal.setText(Integer.toString(presU * Integer.parseInt(txtCantidad.getText())));
        }else
        {
            txtSubTotal.setText("0");
        }
    }
    
    public void vaciarTextField()
    {
        txtNombreProducto.setText("");
        txtPrecentacion.setText("");
        txtPrecioUnidad.setText("");
        txtCantidad.setText("");
        txtSubTotal.setText("");
        
    }
    
    float obtenerTotal(){
        String Consulta = "select subtotal from DetalleVentas "
                + "where idventa = "+ txtFolioB.getText(); 
        float suma = 0;
        
        try {
           System.out.println("\n Entro");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(Consulta);
            while(rs.next())
            {
                suma += rs.getFloat("subtotal");
    
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this,
                    "Error al sumar los datos"
            + error.getMessage());
        }
        return suma;
    }
    void desactivarTXT()
    {
        txtFolioB.setEnabled(false);
        txtNombreProducto.setEnabled(false);
        txtPrecentacion.setEnabled(false);
        txtPrecioUnidad.setEnabled(false);
        txtSubTotal.setEnabled(false);
    }
   public void CargarTabla(){
        String Consulta = "select * from DetalleVentas "
                + "where idventa = "+ txtFolioB.getText(); 
        String [] Titulos = {"No. Folio","Nombre del Producto","Precio",
            "Cantidad", "Presentacion", "Subtotal"};
        String [] Registros = new String[100];
        modelo = new DefaultTableModel(null, Titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(Consulta); //ResultSet=Consulta de resultados
            while(rs.next())
            {
                Registros[0] = rs.getString("IDVenta");
                Registros[1] = rs.getString("NombreProducto");
                Registros[2] = rs.getString("Precio");
                Registros[3] = rs.getString("Cantidad");
                Registros[4] = rs.getString("Presentacion");
                Registros[5] = rs.getString("Subtotal");
                modelo.addRow(Registros);
                
            }
            tblRegistrarVenta.setModel(modelo);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar la tabla de Detalle Venta "
            + error.getMessage());
        }
    }
    
    void obtenerUtimoR()
    {
        String Consulta = "select max(idDetalleVenta) as ultimoId from DetalleVentas";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(Consulta); //ResultSet=Consulta de resultados
            if(rs.next())
            {   
                idDe = rs.getString("ultimoId");
                idDe = Integer.toString(Integer.parseInt(idDe)+1);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar la tabla de Detalle Venta "
            + error.getMessage());
        }
    }
    
    void BuscarProducto(){
        String consulta = "Select * FROM DetalleVentas WHERE idventa='"
                        + txtFolioB.getText() + "'"+" and nombreproducto = '"+
                        txtNombreProducto.getText() + "' and  presentacion = '"+
                        txtPrecentacion.getText()+"' and cantidad = '"+
                        txtCantidad.getText()+"'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(consulta); //ResultSet=Consulta de resultados
            if(rs.next())
            {   
                idDT = rs.getString("IDDetalleVenta");
               /* idProducto = rs.getString("IDProducto");
                nomProducto = rs.getString("NombreProducto");
                PrecioU = rs.getString("Precio");
                cantidadP = rs.getString("Cantidad");
                precentacion = rs.getString("Presentacion");
                sbtotalA = rs.getString("Subtotal");*/
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar la tabla de Detalle Venta "
            + error.getMessage());
        }
    }
    
    public void ActualizarProductos(){
        //if(idDT.length() > 0){
        String sql;
        if(idP == null){
            sql ="update DetalleVentas set "
                    + " NombreProducto = '" + txtNombreProducto.getText()
                    + "', Precio = " + txtPrecioUnidad.getText()
                    + ", Cantidad = " + txtCantidad.getText()
                    + ", Presentacion = '" + txtPrecentacion.getText()
                    + "', Subtotal = " + txtSubTotal.getText()
                    + " where IDDetalleVenta = " + idDT
                    + " ";
        }else
        {
         sql ="update DetalleVentas set "
                    + " IDProducto = " + idP
                    + ", NombreProducto = '" + txtNombreProducto.getText()
                    + "', Precio = " + txtPrecioUnidad.getText()
                    + ", Cantidad = " + txtCantidad.getText()
                    + ", Presentacion = '" + txtPrecentacion.getText()
                    + "', Subtotal = " + txtSubTotal.getText()
                    + " where IDDetalleVenta = " + idDT
                    + " ";  
        }
            
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.executeUpdate();
                CargarTabla();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
            
        //}
    }
    
    public void EliminarDato(String consulta){
        try {
            //if(valor ==0){
                PreparedStatement elim = cn.prepareStatement(consulta);

                int n = elim.executeUpdate();
                if (n>0) {
                    tblRegistrarVenta.setModel(new DefaultTableModel());
                    CargarTabla();
                }
                else{
                    JOptionPane.showMessageDialog(null, "El detalle de venta no se borro");
                }
            //}
            
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error + "No se elimino el registro");
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

        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistrarVenta = new javax.swing.JTable();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtFolioB = new javax.swing.JTextField();
        txtNombreProducto = new javax.swing.JTextField();
        txtPrecioUnidad = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtSubTotal = new javax.swing.JTextField();
        btnBuscarP = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtPrecentacion = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();

        jButton5.setText("jButton5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblRegistrarVenta.setModel(new javax.swing.table.DefaultTableModel(
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
        tblRegistrarVenta.setDragEnabled(true);
        tblRegistrarVenta.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblRegistrarVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRegistrarVentaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblRegistrarVenta);

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jLabel1.setText("No.Folio:");

        jLabel2.setText("Nombre del producto:");

        jLabel3.setText("Precio unitario");

        jLabel4.setText("Cantidad:");

        txtFolioB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFolioBActionPerformed(evt);
            }
        });

        btnBuscarP.setText("BuscarP");
        btnBuscarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel5.setText("Subtotal:");

        txtPrecentacion.setEditable(false);

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(btnEliminar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                    .addComponent(txtPrecioUnidad)
                                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtFolioB, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBuscarP)
                                    .addComponent(txtPrecentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAgregar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnActualizar)))))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addGap(84, 84, 84)
                .addComponent(btnCancelar)
                .addGap(113, 113, 113))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFolioB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPrecioUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar)
                    .addComponent(btnActualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btnEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPActionPerformed
        // TODO add your handling code here:
        bProducto.setVisible(true);
    }//GEN-LAST:event_btnBuscarPActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        //txtFolioB.setText(Interfaces.Ventas);
    }//GEN-LAST:event_formWindowOpened

    private void txtFolioBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFolioBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFolioBActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        String idDetalle, idVenta, idProducto, nombre,precio, cantidad, presentacion, subtotal;
        String SQL;
        idDetalle = idDe;
        idVenta = txtFolioB.getText();
        idProducto= idP;
        nombre= txtNombreProducto.getText();
        precio= txtPrecioUnidad.getText();
        cantidad=txtCantidad.getText();
        presentacion=txtPrecentacion.getText();
        subtotal= txtSubTotal.getText();
        
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
           CargarTabla();
           obtenerUtimoR();
           vaciarTextField();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this, "Error al insertar: " + error);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        String consulta = "DELETE FROM DetalleVentas WHERE idventa='"
                        + txtFolioB.getText() + "'"+" and nombreproducto = '"+
                        txtNombreProducto.getText() + "' and  presentacion = '"+
                        txtPrecentacion.getText()+"' and cantidad = '"+
                        txtCantidad.getText()+"'";
        EliminarDato(consulta);
        vaciarTextField();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tblRegistrarVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrarVentaMouseClicked
        // TODO add your handling code here:
        int fila = tblRegistrarVenta.getSelectedRow();
        
        txtFolioB.setText(tblRegistrarVenta.getValueAt(fila,0).toString());
        txtNombreProducto.setText(tblRegistrarVenta.getValueAt(fila,1).toString());
        txtPrecioUnidad.setText(tblRegistrarVenta.getValueAt(fila,2).toString());
        txtCantidad.setText(tblRegistrarVenta.getValueAt(fila,3).toString());
        txtPrecentacion.setText(tblRegistrarVenta.getValueAt(fila,4).toString());
        txtSubTotal.setText(tblRegistrarVenta.getValueAt(fila,5).toString());
        
        BuscarProducto();
    }//GEN-LAST:event_tblRegistrarVentaMouseClicked

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        //System.out.println("\nEl resultado de la suma es : "+obtenerTotal());
        Interfaces.Ventas.txtTotal.setText(Float.toString(obtenerTotal()));
        this.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        
        ActualizarProductos();
    }//GEN-LAST:event_btnActualizarActionPerformed

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
            java.util.logging.Logger.getLogger(jfrmRegistrarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jfrmRegistrarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jfrmRegistrarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jfrmRegistrarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jfrmRegistrarVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarP;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblRegistrarVenta;
    public static javax.swing.JTextField txtCantidad;
    public javax.swing.JTextField txtFolioB;
    public static javax.swing.JTextField txtNombreProducto;
    public static javax.swing.JTextField txtPrecentacion;
    public static javax.swing.JTextField txtPrecioUnidad;
    public javax.swing.JTextField txtSubTotal;
    // End of variables declaration//GEN-END:variables
}
