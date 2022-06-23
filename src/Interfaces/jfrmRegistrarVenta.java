
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
    public static int canAc;
    public int presU;
    
    
    jfBuscarProducto bProducto = new jfBuscarProducto("");
    DefaultTableModel modelo;
    public String idProducto, nomProducto, cantidadP,PrecioU, precentacion, sbtotalA,idDT;
    ConexionMySQL cnn = new ConexionMySQL();
    Connection cn = cnn.ConexionMySQL();
    public jfrmRegistrarVenta() {
        initComponents();
        btnBuscarP.setText("<html> <center/> BUSCAR <br/> PRODUCTO <center/> </html>");
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
       txtPrecioUnidad.getDocument().addDocumentListener(new DocumentListener(){
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
        String [] Titulos = {"NO. FOLIO","PRODUCTO","PRECIO",
            "CANTIDAD", "PRESENTACION", "SUBTOTAL"};
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
              
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar la tabla de Detalle Venta "
            + error.getMessage());
        }
    }
    
    public void ActualizarProductos(){
        String sql;
        if(txtNombreProducto.getText().length() > 0){
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
            
        }else{
            JOptionPane.showMessageDialog(this, "Ningun registro seleccionado para actualizar");
        }
    }
    
    public void EliminarDato(String consulta){
        try {
                PreparedStatement elim = cn.prepareStatement(consulta);

                int n = elim.executeUpdate();
                if (n>0) {
                    tblRegistrarVenta.setModel(new DefaultTableModel());
                    CargarTabla();
                }
                else{
                    JOptionPane.showMessageDialog(null, "El detalle de venta no se borro");
                }
            
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        txtFolioB = new javax.swing.JTextField();
        btnBuscarP = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtPrecentacion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPrecioUnidad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistrarVenta = new javax.swing.JTable();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        jButton5.setText("jButton5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("NO. FOLIO:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("NOMBRE DEL PRODUCTO:");

        txtFolioB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFolioBActionPerformed(evt);
            }
        });

        btnBuscarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/buscar.png"))); // NOI18N
        btnBuscarP.setText("BUSCAR PRODUCTO");
        btnBuscarP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBuscarP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBuscarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("PRESENTACION:");

        txtPrecentacion.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("PRECIO UNITARIO:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("CANTIDAD:");

        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("SUBTOTAL:");

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Nueva_Venta.png"))); // NOI18N
        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/actualizar.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/eliminar_DV.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
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

        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Aceptar.png"))); // NOI18N
        btnAceptar.setText("ACEPTAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Cancelar.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(txtFolioB)
                    .addComponent(txtPrecentacion)
                    .addComponent(txtPrecioUnidad)
                    .addComponent(txtCantidad)
                    .addComponent(txtSubTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBuscarP, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(btnAceptar)
                .addGap(98, 98, 98)
                .addComponent(btnCancelar)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFolioB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarP, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrecentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPrecioUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPActionPerformed
        // TODO add your handling code here:
        bProducto.Cargar("");
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
        
       // actualizarStock();
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
        if(cantidad.length() > 0){
            if(Revisar()){
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

                   Interfaces.Ventas.Productos.put(idProducto, cantidad);
                   CargarTabla();
                   obtenerUtimoR();
                   vaciarTextField();
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(this, "Error al insertar: " + error);
                }
            }
        }else{
            JOptionPane.showMessageDialog(this, "Cantidad aun no ingresada");
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
        
        Interfaces.Ventas.txtTotal.setText(Float.toString(obtenerTotal()));
        this.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        
        ActualizarProductos();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        boolean presKey = key >= 48 && key <= 57;
        
        if(!presKey)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    boolean Revisar()
    {
        int valor;
        boolean verificar = false;
        if(txtCantidad.getText().length() > 0)
        {
            valor = Integer.parseInt(txtCantidad.getText());
            verificar = true;
            
        }else{
            valor = 0;
        }
        if(canAc == 0)
        {
            JOptionPane.showMessageDialog(this, "Producto aun no seleccionado");
        }else if(valor>canAc){
            JOptionPane.showMessageDialog(this, "Excede la cantidad de producto en Inventario : " + canAc);
            verificar = false;
            txtCantidad.setText(Integer.toString(canAc));
        }
        return verificar;
    }
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
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
