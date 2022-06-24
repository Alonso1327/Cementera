package Interfaces;

import Clases.ConexionMySQL;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Ventas extends javax.swing.JFrame {

    int obtenerFecha = 1;
    int obtenerHora = 2;
    int contador = 0;
    int ultimofolio = 0;
    
    public static Map<String, String> Productos = new HashMap<String,String>();

    public static String getIdClientes = "";
    boolean decimal = false;
    int decimalPos = 0;
    ConexionMySQL cnn = new ConexionMySQL();
    Connection cn = cnn.ConexionMySQL();
    jfBuscarCliente buscarCliente = new jfBuscarCliente();
    jfrmRegistrarVenta registrar = new jfrmRegistrarVenta();
    String fol, idClin = "", fecha, hor, total = "";

    DefaultTableModel modelo;
    int Filas;

    public Ventas() {
        initComponents();
        Cargar("");
        txtTotal.setEnabled(false);
        txtFieldsActualizar(false);
        txtTotal.setText("");
        tblVenta.getTableHeader().setReorderingAllowed(false);
        this.setResizable(false);
        DesactivarBotones(false);

    }
   

    public void Cargar(String valor) {
        String Consulta = "select * from Ventas " + "where concat(NoFolio, "
                + "IDCliente,Fecha,Hora,Total)"
                + " like '%" + valor + "%'";
        String[] Titulos = {"NO. FOLIO", "CLIENTE", "FECHA", "HORA", "TOTAL"};
        String[] Registros = new String[9];
        modelo = new DefaultTableModel(null, Titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(Consulta);
            while (rs.next()) {
                Registros[0] = rs.getString("NoFolio");
                Registros[1] = rs.getString("IdCliente");
                Registros[2] = rs.getString("Fecha");
                Registros[3] = rs.getString("Hora");
                Registros[4] = rs.getString("Total");
                ultimofolio = rs.getInt("NoFolio");
                modelo.addRow(Registros);
            }
            tblVenta.setModel(modelo);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla de "
                    + "Clientes: " + error.getMessage());
        }
    }

    void ActualizarRegistro() {
        getDatos();
        String SQL2 = "Update productos set cantidad = cantidad - ?  where idproducto = ?";
        if (idClin.length() > 0 && total.length() > 0) {
            if (Actualizar() > 0) {
                if(btnGuardar.isEnabled()){
                    for (String clave:Productos.keySet()) {
                        String valor = Productos.get(clave);
                        try {
                            SQL2 = "Update productos set cantidad = cantidad - "+valor+
                                    "  where idproducto = "+clave;
                            System.out.println("SQL2: "+ SQL2);
                            PreparedStatement ps = cn.prepareStatement(SQL2);
                            ps.executeUpdate();
                                    
                             System.out.println("el valor es: "+ valor);
                        } catch (Exception e) {
                           JOptionPane.showMessageDialog(this, e);
                        }
                        
                    }
                    Productos.clear();
                    JOptionPane.showMessageDialog(this, "Venta Registrada");
                    
                }else
                {
                    JOptionPane.showMessageDialog(this, "DATOS ACTUALIZADOS");
                }
                
                txtFieldsActualizar(false);
                vaciarCampos();
                DesactivarBotones(false);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Faltan datos por ingresar");
        }
    }

    void EliminarRegistro(String registroEliminar) {
        String SQL, SQLD;
        int opcion;
        SQL = "Delete from Ventas where NoFolio='" + registroEliminar + "'";
        SQLD = "Delete from DetalleVentas where idVenta='" + registroEliminar + "'";
        if (!btnGuardar.isEnabled()) {
            opcion = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea"
                    + " eliminar este registro?");
        } else {
            opcion = 0;
        }
        try {

            if (opcion == 0) {

                PreparedStatement ps1 = cn.prepareStatement(SQLD);
                PreparedStatement ps = cn.prepareStatement(SQL);
                ps1.executeUpdate();
                ps.executeUpdate();

                if (!btnGuardar.isEnabled()) {
                    JOptionPane.showMessageDialog(null, "Eliminado correctamente");
                }
                Cargar("");
                vaciarCampos();
                txtFieldsActualizar(false);
                DesactivarBotones(false);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }

    public String obtenerFechaHoraActual(int valorAObtener) {
        String obtenerDato;
        LocalDateTime locaDate = LocalDateTime.now();
        if (valorAObtener == obtenerFecha) {
            obtenerDato = new SimpleDateFormat("yyyy-MM-dd").
                    format(Calendar.getInstance().getTime());
        } else {
            /*obtenerDato = new SimpleDateFormat("hh:ss").
                    format(Calendar.getInstance().getTime());*/
            int Hora = locaDate.getHour();
            int Min =  locaDate.getMinute();
            int sec = locaDate.getSecond();
            obtenerDato = Hora + ":"+ Min + ":"+sec;
        }  
        return obtenerDato;
    }

    public void getDatos() {
        fol = this.txtFolio.getText();
        idClin = this.txtIDCliente.getText();
        fecha = this.ftxtFecha.getText();
        hor = this.txtHora.getText();
        total = this.txtTotal.getText();
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
        txtFolio = new javax.swing.JTextField();
        txtIDCliente = new javax.swing.JTextField();
        txtHora = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEditarProducto = new javax.swing.JButton();
        ftxtFecha = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVenta = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnReporte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Ventas");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "VENTAS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Black", 1, 18))); // NOI18N

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("NO. FOLIO: ");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("ID CLIENTE:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("FECHA:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("HORA:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("TOTAL:");

        txtFolio.setPreferredSize(null);
        txtFolio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFolioKeyTyped(evt);
            }
        });

        txtIDCliente.setPreferredSize(null);
        txtIDCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDClienteKeyTyped(evt);
            }
        });

        txtHora.setEditable(false);
        txtHora.setPreferredSize(null);
        txtHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoraActionPerformed(evt);
            }
        });

        txtTotal.setPreferredSize(null);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalKeyTyped(evt);
            }
        });

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Nueva_Venta.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/guardar_DV.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
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

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Cancelar.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEditarProducto.setText("EDITAR PRODUCTO");
        btnEditarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEditarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ftxtFecha.setEditable(false);
        ftxtFecha.setPreferredSize(null);
        ftxtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftxtFechaActionPerformed(evt);
            }
        });

        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/buscar.png"))); // NOI18N
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(txtHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFolio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ftxtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIDCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnBuscarCliente))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftxtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tblVenta.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblVenta.setModel(new javax.swing.table.DefaultTableModel(
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
        tblVenta.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVentaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVenta);

        txtBuscar.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtBuscarInputMethodTextChanged(evt);
            }
        });
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/buscar.png"))); // NOI18N

        btnReporte.setText("REPORTE");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addGap(93, 93, 93))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnReporte)
                        .addGap(278, 278, 278))))
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        ActualizarRegistro();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        Actualizar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tblVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentaMouseClicked
        // TODO add your handling code here:
        int filaSele = tblVenta.getSelectedRow();

        txtFolio.setText(tblVenta.getValueAt(filaSele, 0).toString());
        txtIDCliente.setText(tblVenta.getValueAt(filaSele, 1).toString());
        ftxtFecha.setText(tblVenta.getValueAt(filaSele, 2).toString());
        txtHora.setText(tblVenta.getValueAt(filaSele, 3).toString());
        txtTotal.setText(tblVenta.getValueAt(filaSele, 4).toString());

        Filas = filaSele;
        txtFieldsActualizar(true);
        DesactivarBotones(true);
        btnGuardar.setEnabled(false);
        txtFolio.setEnabled(false);
    }//GEN-LAST:event_tblVentaMouseClicked

    public void ObteberIdCliente(String valoridCliente) {
        txtIDCliente.setText(valoridCliente);
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        EliminarRegistro(txtFolio.getText());
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        vaciarCampos();

        ftxtFecha.setText(obtenerFechaHoraActual(obtenerFecha));
        txtHora.setText(obtenerFechaHoraActual(obtenerHora));
        int acFolio = ultimofolio + 1;
        txtFolio.setText(Integer.toString(acFolio));

        btnCancelar.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnBuscarCliente.setEnabled(true);
        btnNuevo.setEnabled(false);
        btnEditarProducto.setEnabled(true);

        txtFieldsActualizar(true);
        registrar.txtFolioB.setText(txtFolio.getText());
        registrar.CargarTabla();
        registrar.vaciarTextField();
        registrar.setVisible(true);

        InsertarDatos();

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtBuscarInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtBuscarInputMethodTextChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_txtBuscarInputMethodTextChanged

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
        Cargar(txtBuscar.getText());
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        if (btnGuardar.isEnabled()) {
            EliminarRegistro(txtFolio.getText());
            Productos.clear();
        }
        txtFieldsActualizar(false);
        vaciarCampos();
        DesactivarBotones(false);


    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtFolioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFolioKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        /*if (txtFolio.getText().trim().length() == 5) {
        evt.consume();
    }*/
    }//GEN-LAST:event_txtFolioKeyTyped

    private void txtIDClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDClienteKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }
    }//GEN-LAST:event_txtIDClienteKeyTyped

    private void txtTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyTyped

        int key = evt.getKeyChar();
        boolean numeros = (key >= 48 && key <= 57);

        if (key == 46 && decimal == false) {
            decimal = true;
            decimalPos = txtTotal.getText().length();
        } else {
            if (key == 8 && txtTotal.getText().length() == decimalPos) {
                decimal = false;
                decimalPos = 0;
            }
            if (!numeros) {
                evt.consume();
            }
        }


    }//GEN-LAST:event_txtTotalKeyTyped

    private void ftxtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftxtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftxtFechaActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        // TODO add your handling code here:
        try {
            String Ruta = "/Reportes/rptVentas.jasper";
            InputStream archivo = getClass().getResourceAsStream(Ruta);

            JasperReport reporte = null;
            reporte = (JasperReport) JRLoader.loadObject(archivo);

            JasperPrint imprimir = JasperFillManager.
                    fillReport(reporte, null, cnn.ConexionMySQL());

            JasperViewer visor = new JasperViewer(imprimir, false);
            visor.setTitle("Reporte Ventas");
            visor.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error: " + e);

        }
    }//GEN-LAST:event_btnReporteActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        // TODO add your handling code here:

        buscarCliente.setVisible(true);

    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (buscarCliente.isShowing() == true || registrar.isShowing() == true ) {
            buscarCliente.dispose();
            registrar.dispose();
            EliminarRegistro(txtFolio.getText());
            Productos.clear();
            dispose();
            
        }else if(btnNuevo.isEnabled() || btnActualizar.isEnabled()){
            Productos.clear();
            this.dispose();
            
        }else{
            int opcion = JOptionPane.showConfirmDialog(this,"¿Desea salir? se perdera"
                   + "la venta");
           if(opcion == 0){
               EliminarRegistro(txtFolio.getText());
               Productos.clear();
               this.dispose();
           }
        }
        
        
    }//GEN-LAST:event_formWindowClosing

    private void btnEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProductoActionPerformed
        // TODO add your handling code here:
        registrar.txtFolioB.setText(txtFolio.getText());
        registrar.CargarTabla();
        registrar.vaciarTextField();
        registrar.setVisible(true);
    }//GEN-LAST:event_btnEditarProductoActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        /*if (buscarCliente.isShowing() == true ) {
            buscarCliente.dispose();
        } else if (registrar.isVisible()) {
            registrar.dispose();
        }else if(btnNuevo.isEnabled() == false && !btnActualizar.isVisible()){
           int opcion = JOptionPane.showConfirmDialog(this,"¿Desea salir? se perdera"
                   + "la venta");
           if(opcion == 0){
               EliminarRegistro(txtFolio.getText());
               this.dispose();
           }
           
        }else{
            this.dispose();
        }*/
    }//GEN-LAST:event_formWindowClosed

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowDeactivated

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
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditarProducto;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnReporte;
    private javax.swing.JTextField ftxtFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVenta;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtFolio;
    private javax.swing.JTextField txtHora;
    public static javax.swing.JTextField txtIDCliente;
    public static javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    private void vaciarCampos() {
        this.txtFolio.setText(" ");
        this.txtHora.setText("00:00");
        this.ftxtFecha.setText("2022/04/06");
        this.txtIDCliente.setText("");
        this.txtTotal.setText(" ");
    }

    private void txtFieldsActualizar(boolean realizar) {
        txtFolio.setEnabled(false);
        txtIDCliente.setEnabled(realizar);
        ftxtFecha.setEnabled(false);
        txtHora.setEnabled(false);
    }

    private void DesactivarBotones(boolean activacion) {
        btnNuevo.setEnabled(!activacion);
        btnGuardar.setEnabled(activacion);
        btnActualizar.setEnabled(activacion);
        btnEliminar.setEnabled(activacion);
        btnCancelar.setEnabled(activacion);
        btnBuscarCliente.setEnabled(activacion);
        btnEditarProducto.setEnabled(activacion);
    }

    private int InsertarDatos() {
        getDatos();

        int n = 0;
        String SQL;
        SQL = "insert into Ventas (NoFolio,IDCliente,Fecha,Hora, Total)"
                + " values(?,?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(SQL);
            if (idClin.length() > 0 && total.length() > 0) {
                ps.setString(1, fol);
                ps.setString(2, idClin);
                ps.setString(3, fecha);
                ps.setString(4, hor);
                ps.setString(5, total);
            } else {
                ps.setString(1, fol);
                ps.setString(2, "1");
                ps.setString(3, fecha);
                ps.setString(4, hor);
                ps.setString(5, "1");
            }

            n = ps.executeUpdate();

        } catch (Exception error) {
            JOptionPane.showMessageDialog(this, "Error al insertar: "
                    + error.getMessage() + "\n" + error);
        }
        return n;
    }

    private int Actualizar() {
        int n = 0;
        try {
            String sql = "Update Ventas set IDCliente = '"
                    + txtIDCliente.getText() + "',"
                    + "Fecha = '" + ftxtFecha.getText()
                    + "'," + " Hora = '" + txtHora.getText() + "', "
                    + "Total = '" + txtTotal.getText() + "' where NoFolio = '"
                    + txtFolio.getText() + "'";
            PreparedStatement ps = cn.prepareStatement(sql);
            n = ps.executeUpdate();
            Cargar("");
            if(!btnGuardar.isEnabled()){
                    
                   JOptionPane.showMessageDialog(this, "DATOS ACTUALIZADOS");
                    
            }
            
        } catch (Exception error) {
            
               JOptionPane.showMessageDialog(this, "Error: ID CLIENTE NO EXISTE"); 
           
        }

        return n;
    }
}
