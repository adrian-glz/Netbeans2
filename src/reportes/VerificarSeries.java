/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import reportes.data.DatabasePostgres;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.io.File;


/**
 *
 * @author jmendoza
 */
public class VerificarSeries extends javax.swing.JDialog {

    public static TableModel model;
    static JFrame frame;
    Date date = new Date();
    static int nfields1;
    static int nfields2;
    static int[] awidths;
    BackgroundPanel jPanelImage;
       
    /**
     * Creates new form Customers
     */
    public VerificarSeries(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        fActjTable1();
        TxtFactura.requestFocusInWindow();
        
        jTable1.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                String cFactura="";
                if (jTable1.getSelectedRow()>=0) {
                    cFactura = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
                    fActjTable2(cFactura);
                }        
            }
        });  
        
        /*
        jPanelImage = new BackgroundPanel("http://sounds.mx/sn_fotos/SERIE_20141016_172638-692264719.jpg");
        jPanelImage.setSize(240, 140);
        jPanel1.add(jPanelImage);
        */
    }

    public static void fActjTable1() {
        nfields1 = 3;
        model = new DefaultTableModel(fLoadData1(),fLoadHeaders1())
        {
            Class[] types = fLoadDataType1();
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        jTable1.setModel(model);
        jTable1.setEnabled(true);
        awidths = fLoadWidths1();
        for (int ncont = 0; ncont<nfields1; ncont++) {
            jTable1.getColumnModel().getColumn(ncont).setPreferredWidth(awidths[ncont]);
        }
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public static Object[][] fLoadData1() {
        String  cSql;
        ResultSet rs;
        Integer nren;
        int nrec;
        int nid;
        Object [][] aRet=null;        
        cSql  = " select count(*) as reg\n" +
        "from (\n" +
        "select date(fecha) as fecha, factura, count(*) as reg\n" +
        "from customssn\n" +
        "group by date(fecha), factura\n" +
        ") abc ";
        rs = DatabasePostgres.getRS(cSql);
        nren=0;
        try {
            nren = rs.getRow();
        } catch(Exception ex){  
            System.out.println("Error en sql"); 
            return null;
        }
        if (nren > 0) {
            nrec=0;
            try {
                nrec = rs.getInt("reg");
                //products = new Product[nrec];
                aRet = new Object[nrec][nfields1]; 
                nid = 0;
                rs.close();
                cSql  = " select date(fecha) as fecha, factura, count(*) as series\n" +
                "from customssn\n" +
                "group by date(fecha), factura\n" +
                "order by date(fecha) desc, factura ";
                rs = DatabasePostgres.getRS(cSql);
                while (!(rs.isAfterLast())) {
                    aRet[nid][0] = rs.getDate("fecha");
                    aRet[nid][1] = rs.getString("factura");
                    aRet[nid][2] = rs.getInt("series");
                    nid++;
                    rs.next();
                }
            } catch (Exception ex) {
                System.out.println("Error en sql"); 
                return null;
            }
        }
        return aRet;
    }

    public static String[] fLoadHeaders1() {
        String[] cRet=null;
        cRet = new String[nfields1]; 
        cRet[0] = "Fecha:";
        cRet[1] = "Factura:";
        cRet[2] = "Series:";
        return cRet;
    }
    
    public static Class[] fLoadDataType1() {
        Class[] aRet=null;
        aRet = new Class[nfields1];
        aRet[0] = java.util.Date.class;
        aRet[1] = java.lang.String.class;
        aRet[2] = java.lang.Integer.class;
        return aRet;
    }
   
    public static int[] fLoadWidths1() {
        int[] aRet=null;
        aRet = new int[nfields1]; 
        aRet[0] = 150;
        aRet[1] = 200;
        aRet[2] = 60;
        return aRet;
    }
    
    public static void fActjTable2(String cFactura) {
        nfields2 = 6;
        model = new DefaultTableModel(fLoadData2(cFactura),fLoadHeaders2())
        {
            Class[] types = fLoadDataType2();
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        jTable2.setModel(model);
        jTable2.setEnabled(true);
        awidths = fLoadWidths2();
        for (int ncont = 0; ncont<nfields2; ncont++) {
            jTable2.getColumnModel().getColumn(ncont).setPreferredWidth(awidths[ncont]);
        }
        jTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public static Object[][] fLoadData2(String cFactura) {
        String  cSql;
        ResultSet rs;
        Integer nren;
        int nrec;
        int nid;
        Object [][] aRet=null;        
        cSql  = " select count(*) as reg from (\n" +
        " select marca, modelo, origen, serie, foto\n" +
        " from customssn\n" +
        " where factura = '" + cFactura +
        "') abc";
        rs = DatabasePostgres.getRS(cSql);
        nren=0;
        try {
            nren = rs.getRow();
        } catch(Exception ex){  
            System.out.println("Error en sql"); 
            return null;
        }
        if (nren > 0) {
            nrec=0;
            try {
                nrec = rs.getInt("reg");
                //products = new Product[nrec];
                aRet = new Object[nrec][nfields2]; 
                nid = 0;
                rs.close();
                cSql  = " select marca, modelo, origen, serie, subida, foto " +
                " from customssn " +
                " where factura = '" + cFactura +
                "' order by id ";
                rs = DatabasePostgres.getRS(cSql);
                while (!(rs.isAfterLast())) {
                    aRet[nid][0] = rs.getString("marca");
                    aRet[nid][1] = rs.getString("modelo");
                    aRet[nid][2] = rs.getString("origen");
                    aRet[nid][3] = rs.getString("serie");
                    if (rs.getBoolean("subida")) {
                        aRet[nid][4] = "SI";
                    } else {
                        aRet[nid][4] = "NO";
                    }
                    aRet[nid][5] = rs.getString("foto");
                    nid++;
                    rs.next();
                }
            } catch (Exception ex) {
                System.out.println("Error en sql"); 
                return null;
            }
        }
        return aRet;
    }

    public static String[] fLoadHeaders2() {
        String[] cRet=null;
        cRet = new String[nfields2]; 
        cRet[0] = "Marca:";
        cRet[1] = "Modelo:";
        cRet[2] = "Origen:";
        cRet[3] = "Serie:";
        cRet[4] = "Subida:";
        cRet[5] = "Foto:";
        return cRet;
    }
    
    public static Class[] fLoadDataType2() {
        Class[] aRet=null;
        aRet = new Class[nfields2];
        aRet[0] = java.lang.String.class;
        aRet[1] = java.lang.String.class;
        aRet[2] = java.lang.String.class;
        aRet[3] = java.lang.String.class;
        aRet[4] = java.lang.String.class;
        aRet[5] = java.lang.String.class;
        return aRet;
    }
   
    public static int[] fLoadWidths2() {
        int[] aRet=null;
        aRet = new int[nfields2]; 
        aRet[0] = 110;
        aRet[1] = 150;
        aRet[2] = 80;
        aRet[3] = 350;
        aRet[4] = 70;
        aRet[5] = 1000;
        return aRet;
    }
    
    private void newFilter() {
        //TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        TableRowSorter<TableModel> sorter = new TableRowSorter(model);
        RowFilter rf = null;
        //RowFilter<Object, Object> rf = null;
        try {
            rf = RowFilter.regexFilter(TxtFactura.getText());
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
        jTable1.setRowSorter(sorter);
    }

    /**
     * This method is called from " + SiMo.cschema + ".within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        TxtFactura = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        ButtonCerrar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButtonActDatos = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButtonActDatos1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 600));
        setPreferredSize(new java.awt.Dimension(1280, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Verificación de Numeros de Series para Importación");

        TxtFactura.setToolTipText("Teclee para Filtrar y Buscar");
        TxtFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtFacturaActionPerformed(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), "a", null, null},
                { new Integer(2), "b", null, null},
                { new Integer(3), "c", null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id:", "Modelo:", "Codigo de Barras:", "Costo:"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setRowHeight(35);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable1PropertyChange(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jLabel1.setText("Filtro Busqueda:");

        ButtonCerrar.setMnemonic('C');
        ButtonCerrar.setText("Cerrar");
        ButtonCerrar.setToolTipText("");
        ButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCerrarActionPerformed(evt);
            }
        });

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.setColumnSelectionAllowed(true);
        jTable2.setRowHeight(35);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable2KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jButtonActDatos.setMnemonic('A');
        jButtonActDatos.setText("Actualizar");
        jButtonActDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActDatosActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Foto"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 467, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButtonActDatos1.setMnemonic('I');
        jButtonActDatos1.setText("Imprimir");
        jButtonActDatos1.setToolTipText("Imprimir Factura");
        jButtonActDatos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActDatos1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1252, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ButtonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonActDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButtonActDatos1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonActDatos1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ButtonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonActDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        TxtFactura.getDocument().addDocumentListener(
            new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    newFilter();
                }
                public void insertUpdate(DocumentEvent e) {
                    newFilter();
                }
                public void removeUpdate(DocumentEvent e) {
                    newFilter();
                }
            });

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        TxtFactura.requestFocusInWindow();
    }//GEN-LAST:event_formWindowOpened

    private void ButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_ButtonCerrarActionPerformed

    private void TxtFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtFacturaActionPerformed
    }//GEN-LAST:event_TxtFacturaActionPerformed

    private void jTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable1PropertyChange
    }//GEN-LAST:event_jTable1PropertyChange

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String cFactura="";
        if (jTable1.getSelectedRow()>=0) {
            cFactura = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
            fActjTable2(cFactura);
        }        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButtonActDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActDatosActionPerformed
        String cFactura="";
        if (jTable1.getSelectedRow()>=0) {
            cFactura = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
            fActjTable2(cFactura);
        }     
        
        String cFoto="";
        File f;
        if (jTable2.getSelectedRow()>=0) {
            cFoto = jTable2.getValueAt(jTable2.getSelectedRow(), 5).toString(); //Foto
            f = new File(cFoto);
            cFoto = "http://cfdi.sounds.mx/sn_fotos/" + f.getName();
            jPanelImage = new BackgroundPanel(cFoto);
            jPanelImage.setSize(500, 478);
            jPanel1.removeAll();
            jPanel1.add(jPanelImage); 
            jPanel1.repaint();
        }        
        
    }//GEN-LAST:event_jButtonActDatosActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        String cFactura="";
        if (jTable1.getSelectedRow()>=0) {
            cFactura = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
            fActjTable2(cFactura);
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        String cFoto="";
        File f;
        if (jTable2.getSelectedRow()>=0) {
            cFoto = jTable2.getValueAt(jTable2.getSelectedRow(), 5).toString(); //Foto
            f = new File(cFoto);
            cFoto = "http://cfdi.sounds.mx/sn_fotos/" + f.getName();
            jPanelImage = new BackgroundPanel(cFoto);
            jPanelImage.setSize(500, 478);
            jPanel1.removeAll();
            jPanel1.add(jPanelImage); 
            jPanel1.repaint();
        }        
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyReleased
        String cFoto="";
        File f;
        if (jTable2.getSelectedRow()>=0) {
            cFoto = jTable2.getValueAt(jTable2.getSelectedRow(), 5).toString(); //Foto
            f = new File(cFoto);
            cFoto = "http://cfdi.sounds.mx/sn_fotos/" + f.getName();
            jPanelImage = new BackgroundPanel(cFoto);
            jPanelImage.setSize(500, 478);
            jPanel1.removeAll();
            jPanel1.add(jPanelImage); 
            jPanel1.repaint();
        }        
    }//GEN-LAST:event_jTable2KeyReleased

    private void jButtonActDatos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActDatos1ActionPerformed
        String cFactura="";
        String cProveedor="";
        Date dFecha = null;
        if (jTable1.getSelectedRow()>=0) {
            dFecha = (Date) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            cFactura = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString(); 
            cProveedor = JOptionPane.showInputDialog(frame, "Proveedor: ", "Capture Nombre de Proveedor para procesar reporte", JOptionPane.QUESTION_MESSAGE);
            try {
                reports.rSeries(dFecha, cFactura, cProveedor);
            } catch (Exception ex) {
                System.out.println("Error en reporte");
            }            
        }     
        
        
    }//GEN-LAST:event_jButtonActDatos1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VerificarSeries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerificarSeries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerificarSeries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerificarSeries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                VerificarSeries dialog = new VerificarSeries(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCerrar;
    private javax.swing.JTextField TxtFactura;
    private javax.swing.JButton jButtonActDatos;
    private javax.swing.JButton jButtonActDatos1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
