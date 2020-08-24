/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;


import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.util.JRLoader;
import javax.swing.JOptionPane;
import reportes.util.TunnelSSHPostgres;
import reportes.data.DatabasePostgres;

import com.amazonservices.mws.products.*;
import com.amazonservices.mws.products.model.*;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import reportes.data.DatabaseMySql;
import reportes.data.DatabaseSQL;
import reportes.util.ResultXML;
import reportes.util.TunnelSSHMySql;
import reportes.util.Util;


/**
 *
 * @author jmendoza
 */
public class Menu extends javax.swing.JDialog {
    
    public static int nRet_rFechas = 0;
    public static int nRet_rGruposGeneros = 0;
    public static int nRet_rAnosMeses= 0;
    public static int nRet_rSucursal = 0;
    public static Date dfecha1ant = null;
    public static Date dfecha2ant = null;
    public static Date dfecha1 = null;
    public static Date dfecha2 = null;
    public static int nGrupo;
    public static int nGenero;
    public static int nAno;
    public static int nMes;
    public static int nSucursal;
    static JFrame frame;
    public static Object[][] jasperreports;
    public static Boolean lConnected;
    public static Boolean lConnectedMySql;
    public static Boolean lsessionPg;
    public static Boolean lsessionMySql;
    public static String[] img;
    public static String cMenError;
    public int nCantidad;
    

    /**
     * Creates new form Menu
     */
    public Menu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        img = new String[6];
        img[0] = "angelm";
        img[1] = "Madljda1";
        img[2] = "customssn";
        img[3] = "Madljda1";          
        img[4] = "uCML";
        img[5] = "Madljda1";          
        Menu.lsessionPg = false;
        TunnelSSHPostgres.main();
        Menu.lConnected = true;
        DatabasePostgres.isConnected(); 

        Menu.lsessionMySql = false;
        TunnelSSHMySql.main();
        Menu.lConnectedMySql = true;        
        DatabaseMySql.isConnected();
        try {
            jasperreports = new Object[14][2];
            jasperreports[0][0] = "rIngresosSemanas.jasper";
            jasperreports[0][1] = JRLoader.loadObject(this.getClass().getResource("reports/rIngresosSemanas.jasper"));
            jasperreports[1][0] = "rIngresosSemanasParametros.jasper";
            jasperreports[1][1] = JRLoader.loadObject(this.getClass().getResource("reports/rIngresosSemanasParametros.jasper"));
            jasperreports[2][0] = "rIngresosSemanasResumen.jasper";
            jasperreports[2][1] = JRLoader.loadObject(this.getClass().getResource("reports/rIngresosSemanasResumen.jasper"));
            jasperreports[3][0] = "rIngresosSemanasxSemana.jasper";
            jasperreports[3][1] = JRLoader.loadObject(this.getClass().getResource("reports/rIngresosSemanasxSemana.jasper"));
            jasperreports[4][0] = "repApartadosComparativa.jasper";
            jasperreports[4][1] = JRLoader.loadObject(this.getClass().getResource("reports/repApartadosComparativa.jasper"));
            jasperreports[5][0] = "repDollaresFinalBank.jasper";
            jasperreports[5][1] = JRLoader.loadObject(this.getClass().getResource("reports/repDollaresFinalBank.jasper"));
            jasperreports[6][0] = "rFaltantes.jasper";
            jasperreports[6][1] = JRLoader.loadObject(this.getClass().getResource("reports/rFaltantes.jasper"));
            jasperreports[7][0] = "rFaltantesDetalle.jasper";
            jasperreports[7][1] = JRLoader.loadObject(this.getClass().getResource("reports/rFaltantesDetalle.jasper"));
            jasperreports[8][0] = "repCuentasContablesPivot.jasper";
            jasperreports[8][1] = JRLoader.loadObject(this.getClass().getResource("reports/repCuentasContablesPivot.jasper"));
            jasperreports[9][0] = "repTraspasosPivot.jasper";
            jasperreports[9][1] = JRLoader.loadObject(this.getClass().getResource("reports/repTraspasosPivot.jasper"));
            jasperreports[10][0] = "repVentasSucursalGrupo.jasper";
            jasperreports[10][1] = JRLoader.loadObject(this.getClass().getResource("reports/repVentasSucursalGrupo.jasper"));
            jasperreports[11][0] = "rSeries.jasper";
            jasperreports[11][1] = JRLoader.loadObject(this.getClass().getResource("reports/rSeries.jasper"));
            jasperreports[12][0] = "rApiAmazon.jasper";
            jasperreports[12][1] = JRLoader.loadObject(this.getClass().getResource("reports/rApiAmazon.jasper"));
            jasperreports[13][0] = "rApiAmazonDadosAlta.jasper";
            jasperreports[13][1] = JRLoader.loadObject(this.getClass().getResource("reports/rApiAmazonDadosAlta.jasper"));
        } catch (JRException ex) {
            //Logger.getLogger(class.getName()).log(Level.SEVERE, null, ex);
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

        jButtonAyuda = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jButton1 = new javax.swing.JButton();
        jButtonPronosticosGrupoGenero = new javax.swing.JButton();
        jButtonPronosticos = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButtonParametros = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jButtonDolaresxSucursal = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButtonReparticiones = new javax.swing.JButton();
        jButtonTraspasos = new javax.swing.JButton();
        jButtonVentasGrupoSuc = new javax.swing.JButton();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jLabel4 = new javax.swing.JLabel();
        jButtonVerificarSeries = new javax.swing.JButton();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jLabel5 = new javax.swing.JLabel();
        jButtonApiAmazonDadosAlta = new javax.swing.JButton();
        jButtonApiAmazonMasVentas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reportes Estadisticos");
        setLocationByPlatform(true);
        setResizable(false);

        jButtonAyuda.setText("Ayuda");
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAyudaActionPerformed(evt);
            }
        });

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("Comparativa Apartados");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton1);
        jButton1.setBounds(40, 40, 300, 23);

        jButtonPronosticosGrupoGenero.setText("Pronosticos por Semana, Grupo y Genero");
        jButtonPronosticosGrupoGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPronosticosGrupoGeneroActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButtonPronosticosGrupoGenero);
        jButtonPronosticosGrupoGenero.setBounds(40, 70, 300, 23);

        jButtonPronosticos.setText("Pronosticos por Semana");
        jButtonPronosticos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPronosticosActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButtonPronosticos);
        jButtonPronosticos.setBounds(40, 100, 300, 23);

        jButton2.setText("Pronosticos Resumen");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton2);
        jButton2.setBounds(40, 130, 300, 23);

        jButtonParametros.setText("Pronosticos Parametros");
        jButtonParametros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonParametrosActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButtonParametros);
        jButtonParametros.setBounds(40, 160, 300, 23);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Reportes Estadisticos");
        jLayeredPane1.add(jLabel1);
        jLabel1.setBounds(20, 10, 350, 22);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonDolaresxSucursal.setText("Dolares por Sucursal");
        jButtonDolaresxSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDolaresxSucursalActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButtonDolaresxSucursal);
        jButtonDolaresxSucursal.setBounds(40, 40, 300, 23);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Reportes Contables");
        jLayeredPane2.add(jLabel2);
        jLabel2.setBounds(20, 10, 350, 22);

        jButtonReparticiones.setText("Reparticiones por Grupo y Sucursal");
        jButtonReparticiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReparticionesActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButtonReparticiones);
        jButtonReparticiones.setBounds(40, 70, 300, 23);

        jButtonTraspasos.setText("Traspasos por Grupo y Sucursal");
        jButtonTraspasos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTraspasosActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButtonTraspasos);
        jButtonTraspasos.setBounds(40, 100, 300, 23);

        jButtonVentasGrupoSuc.setText("Ventas por Grupo y Sucursal");
        jButtonVentasGrupoSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVentasGrupoSucActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButtonVentasGrupoSuc);
        jButtonVentasGrupoSuc.setBounds(40, 130, 300, 23);

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton4.setText("Faltante Detalle");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jLayeredPane3.add(jButton4);
        jButton4.setBounds(40, 80, 300, 23);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Reportes Faltantes");
        jLayeredPane3.add(jLabel3);
        jLabel3.setBounds(20, 10, 350, 22);

        jButton5.setText("Faltante General");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jLayeredPane3.add(jButton5);
        jButton5.setBounds(40, 50, 300, 23);

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Reportes Aduanales");
        jLayeredPane4.add(jLabel4);
        jLabel4.setBounds(20, 10, 350, 22);

        jButtonVerificarSeries.setText("Verificar Series");
        jButtonVerificarSeries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerificarSeriesActionPerformed(evt);
            }
        });
        jLayeredPane4.add(jButtonVerificarSeries);
        jButtonVerificarSeries.setBounds(40, 50, 300, 23);

        jLayeredPane5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Reporte Amazon");
        jLayeredPane5.add(jLabel5);
        jLabel5.setBounds(20, 10, 350, 22);

        jButtonApiAmazonDadosAlta.setText("Reporte de Dados de Alta en Amazon");
        jButtonApiAmazonDadosAlta.setActionCommand("Reporte de Codigos Dados de Alta en Amazon");
        jButtonApiAmazonDadosAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApiAmazonDadosAltaActionPerformed(evt);
            }
        });
        jLayeredPane5.add(jButtonApiAmazonDadosAlta);
        jButtonApiAmazonDadosAlta.setBounds(40, 70, 300, 23);

        jButtonApiAmazonMasVentas.setText("Reporte de Mas Ventas Sounds Tiendas");
        jButtonApiAmazonMasVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApiAmazonMasVentasActionPerformed(evt);
            }
        });
        jLayeredPane5.add(jButtonApiAmazonMasVentas);
        jButtonApiAmazonMasVentas.setBounds(40, 40, 300, 23);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonAyuda))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLayeredPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAyuda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLayeredPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLayeredPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        rFechas rfechas = new rFechas(frame, true);
        rfechas.setVisible(true);
        if ((Menu.nRet_rFechas != 0) && (Menu.dfecha1 != null) && (Menu.dfecha2 != null)) {
            Menu.dfecha1ant = Menu.dfecha1;
            Menu.dfecha2ant = Menu.dfecha2;
            rFechas rfechas2 = new rFechas(frame, true);
            rfechas2.setVisible(true);
            if ((Menu.nRet_rFechas != 0) && (Menu.dfecha1 != null) && (Menu.dfecha2 != null)) {
                try {
                    reports.rApartadosComparativa(Menu.dfecha1ant, Menu.dfecha2ant, Menu.dfecha1, Menu.dfecha2);
                } catch (Exception ex) {
                    System.out.println("Error en fechas");
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonPronosticosGrupoGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPronosticosGrupoGeneroActionPerformed
        rGruposGeneros rgruposgeneros = new rGruposGeneros(frame, true);
        rgruposgeneros.setVisible(true); 
        if (Menu.nRet_rGruposGeneros != 0) {
            try {
                reports.rIngresosSemanasGrupoGenero(Menu.nGrupo, Menu.nGenero);
            } catch (Exception ex) {
                System.out.println("Error en fechas");
            }
        }
        
    }//GEN-LAST:event_jButtonPronosticosGrupoGeneroActionPerformed

    private void jButtonPronosticosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPronosticosActionPerformed
        try {
            reports.rIngresosSemanas();
        } catch (Exception ex) {
            System.out.println("Error en reporte");
        }
    }//GEN-LAST:event_jButtonPronosticosActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            reports.rIngresosSemanasResumen();
        } catch (Exception ex) {
            System.out.println("Error en reporte");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonParametrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonParametrosActionPerformed
        try {
            reports.rIngresosSemanasParametros();
        } catch (Exception ex) {
            System.out.println("Error en reporte");
        }
    }//GEN-LAST:event_jButtonParametrosActionPerformed

    private void jButtonAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAyudaActionPerformed
        try {
            try {
                Desktop.getDesktop().browse(new URI("http://192.168.1.89/rEstadisticos/RepEstadisticos_intro/RepEstadisticos_intro.html"));
            } catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            System.out.println("Error en ayuda"); 
        }
    }//GEN-LAST:event_jButtonAyudaActionPerformed

    private void jButtonDolaresxSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDolaresxSucursalActionPerformed
        rSucursal rsucursal = new rSucursal(frame, true);
        rsucursal.setVisible(true); 
        if (Menu.nRet_rSucursal != 0) {
            rFechas rfechas = new rFechas(frame, true);
            rfechas.setVisible(true);
            if ((Menu.nRet_rFechas != 0) && (Menu.dfecha1 != null) && (Menu.dfecha2 != null)) {
                try {
                    reports.rDollaresFinal(Menu.nSucursal, Menu.dfecha1, Menu.dfecha2);
                } catch (Exception ex) {
                    System.out.println("Error en fechas");
                } finally {
                    JOptionPane.showMessageDialog(frame, 
                    "Reporte Procesado !. Sucursal: " + Menu.nSucursal + " . Favor de revisar en: " + 
                    System.getProperty("user.home")+"\\repDollares.xls", 
                    "Informacion", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButtonDolaresxSucursalActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        rSucursal rsucursal = new rSucursal(frame, true);
        rsucursal.setVisible(true); 
        if (Menu.nRet_rSucursal != 0) {
            try {
                reports.rFaltantesDetalle(Menu.nSucursal);
            } catch (Exception ex) {
                System.out.println("Error en reporte");
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            reports.rFaltantes();
        } catch (Exception ex) {
            System.out.println("Error en reporte");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButtonReparticionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReparticionesActionPerformed
        rFechas rfechas = new rFechas(frame, true);
        rfechas.setVisible(true);
        if ((Menu.nRet_rFechas != 0) && (Menu.dfecha1 != null) && (Menu.dfecha2 != null)) {
            try {
                reports.rReparticiones(Menu.dfecha1, Menu.dfecha2);
            } catch (Exception ex) {
                System.out.println("Error en fechas");
            }
        }
    }//GEN-LAST:event_jButtonReparticionesActionPerformed

    private void jButtonTraspasosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTraspasosActionPerformed
        rFechas rfechas = new rFechas(frame, true);
        rfechas.setVisible(true);
        if ((Menu.nRet_rFechas != 0) && (Menu.dfecha1 != null) && (Menu.dfecha2 != null)) {
            try {
                reports.rTraspasos(Menu.dfecha1, Menu.dfecha2);
            } catch (Exception ex) {
                System.out.println("Error en fechas");
            }
        }
    }//GEN-LAST:event_jButtonTraspasosActionPerformed

    private void jButtonVentasGrupoSucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVentasGrupoSucActionPerformed
        rAnoMes rAnoMes = new rAnoMes(frame, true);
        rAnoMes.setVisible(true); 
        if (Menu.nRet_rAnosMeses != 0) {
            try {
                reports.rVentasGrupoSuc(Menu.nAno, Menu.nMes);
            } catch (Exception ex) {
                System.out.println("Error en fechas");
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButtonVentasGrupoSucActionPerformed

    private void jButtonVerificarSeriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerificarSeriesActionPerformed
        VerificarSeries verificarSeries = new VerificarSeries(frame, true);
        verificarSeries.setVisible(true); 
    }//GEN-LAST:event_jButtonVerificarSeriesActionPerformed

    private void procApiAmazon(String cCodigo) {
        MarketplaceWebServiceProductsClient client = MarketplaceWebServiceProductsSampleConfig.getClient();
        // Create a request.
        GetCompetitivePricingForSKURequest request = new GetCompetitivePricingForSKURequest();
        String sellerId = "AZPP5FIWTLUTQ";
        request.setSellerId(sellerId);
        String mwsAuthToken = "amzn.mws.74aa15da-d4a6-af7e-85c0-3d6aad991a75";
        request.setMWSAuthToken(mwsAuthToken);
        String marketplaceId = "A1AM78C64UM0Y8";
        request.setMarketplaceId(marketplaceId);
        SellerSKUListType sellerSKUList = new SellerSKUListType();
        List<String> listSKU = Arrays.asList(cCodigo);
        sellerSKUList.setSellerSKU(listSKU);
        request.setSellerSKUList(sellerSKUList);
        // Make the call.
        GetCompetitivePricingForSKUSample.invokeGetCompetitivePricingForSKU(client, request);
    }
    
    private void jButtonApiAmazonDadosAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApiAmazonDadosAltaActionPerformed
    String cval;
    int nval;                        
            
        final JDialog dialog = new JDialog(Frame.getFrames()[0], "Reportes Estadisticos", true);
        Thread runnable_progress = new Thread() {
            public void run() {
                JTextArea msgLabel;        
                JProgressBar progressBar;
                final int MAXIMUM = 100;
                JPanel panel;
                progressBar = new JProgressBar(0, MAXIMUM);
                progressBar.setIndeterminate(true);
                msgLabel = new JTextArea("Procesando Reporte. Por favor espere...");
                msgLabel.setEditable(false);
                panel = new JPanel(new BorderLayout(5, 5));
                panel.add(msgLabel, BorderLayout.PAGE_START);
                panel.add(progressBar, BorderLayout.CENTER);
                panel.setBorder(BorderFactory.createEmptyBorder(11, 11, 11, 11));
                dialog.getContentPane().add(panel);
                dialog.setResizable(false);
                dialog.pack();
                dialog.setSize(500, dialog.getHeight());
                dialog.setLocationRelativeTo(null);
                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                dialog.setAlwaysOnTop(false);
                msgLabel.setBackground(panel.getBackground());
                ///////////////////////        
                String cSql;
                String cCodigo;
                ResultSet rs;
                int nren;

                cSql = "select * from 00_amazon";
                rs = DatabaseMySql.getRS(cSql);
                cSql = "delete from amazon";
                DatabaseSQL.execQuery(cSql);
                //JOptionPane.showMessageDialog(frame, "Se vacio tabla Temporal: Amazon ", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                try {
                    while (!(rs.isAfterLast())) {
                        cSql = "insert into amazon (sku, asin, price, quantity) ";
                        cSql = cSql+" values('"+Util.cFixString(rs.getString("sku").trim())+"','" + 
                        Util.cFixString(rs.getString("asin").trim()) +"',"+
                        rs.getDouble("price")+","+
                        rs.getInt("quantity")+")";
                        DatabaseSQL.execQuery(cSql);                        
                        rs.next();
                    }
                    //JOptionPane.showMessageDialog(frame, "Se Termino de Llenar tabla Temporal: Amazon ", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    System.out.println("Error en reporte");
                }
                
                cSql  = " select \n" +
                " (((preciouni/1.16)-costoreposicion)/(preciouni/1.16)) as margen,\n" +
                " (case when (peso_kg) <= 5 then 107 else (case when (peso_kg) <= 15 then 128 else (case when (peso_kg) <= 30 then 166 else (case when (peso_kg) <= 40 then 316 else (case when (peso_kg) <= 50 then 446 else (case when (peso_kg) <= 65 then 691 else 1300 end)end) end) end) end) end) as envioAmazon, \n" +                        
                "round((case when (preciouni>=1) then (preciouni/0.95/0.988) else (preciouni/0.95) end)+\n" +
                " (case when (peso_kg) <= 5 then 107 else (case when (peso_kg) <= 15 then 128 else (case when (peso_kg) <= 30 then 166 else (case when (peso_kg) <= 40 then 316 else (case when (peso_kg) <= 50 then 446 else (case when (peso_kg) <= 65 then 691 else 1300 end)end) end) end) end) end)\n" +
                " ,0) as precioSoundsAmazonEnvio, *\n" +
                "from (\n" +
                "select coalesce(preciooferta, preciounitario) as preciouni, *\n" +
                "from (\n" +
                "select xyz2.*, ABCX3.preciooferta\n" +
                "from (\n" +
                "select  *\n" +
                "from (\n" +
                "\n" +
                "select abc.codigo, grupo,  grupodescripcion, generodescripcion, descripcion, costoreposicion, preciounitario,  \n" +
                " peso_kg, sum(coalesce(existenciafinal,0)-coalesce(apartados,0)) as existencia \n" +
                " \n" +
                " from (  \n" +
                " \n" +
                " select c.codigo, c.grupo, (select descripcion from grupos where grupo=c.grupo) as grupodescripcion, c.genero, \n" +
                " (select descripcion from generos where genero=c.genero) as generodescripcion, c.descripcion, c.costoreposicion, c.precioventa as preciounitario,  \n" +
                " coalesce((select peso_kg from Codigos_info where codigo = c.codigo),0) as peso_kg\n" +
                " from codigos c, amazon v where c.nacional<> 0 and rtrim(ltrim(c.codigo)) = rtrim(ltrim(v.sku)) and c.grupo<=70    \n" +
                " and c.grupo not in (20,25,45,55,56) \n" +
                " group by c.codigo, c.grupo, c.genero, c.descripcion, c.precioventa, c.costoreposicion  \n" +
                " \n" +
                " union all  \n" +
                " \n" +
                " select c.codigo, c.grupo, (select descripcion from grupos where grupo=c.grupo) as grupodescripcion, c.genero, \n" +
                " (select descripcion from generos where genero=c.genero) as generodescripcion, c.descripcion,  \n" +
                " round((c.costoreposicion*(select top 1 tipocambioventa from infor)),0) as costoreposicion, \n" +
                " round((c.precioventa*(select top 1 tipocambioventa from infor)),0) as preciounitario, \n" +
                " coalesce((select peso_kg from Codigos_info where codigo = c.codigo),0) as peso_kg\n" +
                " from codigos c, amazon v \n" +
                " where c.nacional=0 and rtrim(ltrim(c.codigo)) = rtrim(ltrim(v.sku)) and c.grupo<=70  and c.grupo not in (20,25,45,55,56) \n" +
                " group by c.codigo, c.grupo, c.genero, c.descripcion, c.precioventa, c.costoreposicion\n" +
                "\n" +
                " ) abc left join periodo p on (abc.codigo = p.codigo and p.ano = year(getdate()) and p.mes = month(getdate()) and p.sucursal in (3,9,17,19,22)) \n" +
                " \n" +
                " group by abc.codigo, grupo, grupodescripcion, generodescripcion, descripcion, costoreposicion, preciounitario, peso_kg\n" +
                " having sum(coalesce(existenciafinal,0)-coalesce(apartados,0)) > 0\n" +
                "\n" +
                " -----\n" +
                " \n" +
                " ) xyz) xyz2\n" +
                "\n" +
                "\n" +
                " left join\n" +
                "\n" +
                " (\n" +
                " select *\n" +
                " from (\n" +
                " select * from (\n" +
                "select codigo, (case when precioventa15 < precioventa then precioventa15 else precioventa end) as preciooferta,\n" +
                "convert(char(10),(getdate()-1),102) as fechainicio, convert(char(10),(getdate()+7),102) as fechatermino \n" +
                "from (\n" +
                "\n" +
                "select g.descripcion as genero, c.codigo, c.codigo2, c.descripcion, \n" +
                "(case when xyz.preciooferta < round((case when nacional=0 then c.precioventa*(select tipocambioventa from infor) else c.PrecioVenta end),0) then\n" +
                "xyz.preciooferta else round((case when nacional=0 then c.precioventa*(select tipocambioventa from infor) else c.PrecioVenta end),0) end) as precioventa,\n" +
                "round((case when nacional=0 then (-1*(1.16*\n" +
                "(case when c.grupo=50 then (c.costoreposicion*1.05) else c.costoreposicion end)\n" +
                "/(0.15-1)))*(select tipocambioventa from infor) else (-1*(1.16*\n" +
                "(case when c.grupo=50 then (c.costoreposicion*1.05) else c.costoreposicion end)\n" +
                "/(0.15-1))) end),0) as precioventa15\n" +
                "\n" +
                "from generos g, codigos c left join\n" +
                "\n" +
                "(\n" +
                "select *\n" +
                "from (\n" +
                "SELECT Codigo, round((PrecioOferta),0) as PrecioOferta, convert(char(10),fechainicio,102) as fechainicio, \n" +
                "convert(char(10),fechatermino+1,102) as fechatermino\n" +
                "FROM OfertasCodigo\n" +
                "WHERE sucursal=99 and ((convert(char(10),fechainicio,102)) <= (convert(char(10),getdate(),102))) \n" +
                "AND ((convert(char(10),fechatermino,102)) >= (convert(char(10),getdate(),102))) AND (Activa <> 0) and\n" +
                "codigo in (select codigo from codigos where nacional<>0)\n" +
                "\n" +
                "union all\n" +
                "\n" +
                "SELECT Codigo, round(((PrecioOferta*(select tipocambioventa from infor))),0) as PrecioOferta, \n" +
                "convert(char(10),fechainicio,102), convert(char(10),fechatermino+1,102)\n" +
                "FROM OfertasCodigo\n" +
                "WHERE sucursal=99 and ((convert(char(10),fechainicio,102)) <= (convert(char(10),getdate(),102))) \n" +
                "AND ((convert(char(10),fechatermino,102)) >= (convert(char(10),getdate(),102))) AND (Activa <> 0) and\n" +
                "codigo in (select codigo from codigos where nacional=0)\n" +
                "\n" +
                "\n" +
                ") ABC\n" +
                "--order by codigo\n" +
                ") XYZ on (c.codigo = XYZ.codigo)\n" +
                "\n" +
                "where c.genero=g.genero --and c.genero in (106,47,115,95,83,137,85,138,82,136,100,101,154,105,91,327,330,127,188,107,1002,92,59,93,702,94,598,108,182,104)\n" +
                "-- and c.genero<>154\n" +
                "and c.grupo in (30,31,33,34,36,37,38,39,50)\n" +
                "and exists(\n" +
                "select *\n" +
                "from periodo\n" +
                "where ano=year(getdate()) and mes=month(getdate()) and sucursal in(3,5,9,17,19,22) and existenciafinal>0 and codigo = c.codigo)\n" +
                "--order by c.codigo, g.descripcion, c.descripcion\n" +
                "\n" +
                ") abc1\n" +
                "--order by  abc.codigo, abc.genero, abc.descripcion\n" +
                ") xyz1\n" +
                "where xyz1.preciooferta > 0\n" +
                "and not exists(\n" +
                "select *\n" +
                "from codigos\n" +
                "where codigo = xyz1.codigo and round((case when nacional=0 then precioventa*(select tipocambioventa from infor) else PrecioVenta end),0) = \n" +
                "round(xyz1.preciooferta,0)\n" +
                ")\n" +
                "union\n" +
                "select *\n" +
                "from (\n" +
                "SELECT Codigo, round((PrecioOferta),0) as PrecioOferta, convert(char(10),fechainicio,102) as fechainicio, \n" +
                "convert(char(10),fechatermino+1,102) as fechatermino\n" +
                "FROM OfertasCodigo\n" +
                "WHERE sucursal=99 and ((convert(char(10),fechainicio,102)) <= (convert(char(10),getdate(),102))) \n" +
                "AND ((convert(char(10),fechatermino,102)) >= (convert(char(10),getdate(),102))) AND (Activa <> 0) and\n" +
                "codigo in (select codigo from codigos where nacional<>0 and grupo not in (30,31,33,34,36,37,38,39,50))\n" +
                "\n" +
                "union all\n" +
                "\n" +
                "SELECT Codigo, round(((PrecioOferta*(select tipocambioventa from infor))),0) as PrecioOferta, \n" +
                "convert(char(10),fechainicio,102), convert(char(10),fechatermino+1,102)\n" +
                "FROM OfertasCodigo\n" +
                "WHERE sucursal=99 and ((convert(char(10),fechainicio,102)) <= (convert(char(10),getdate(),102))) \n" +
                "AND ((convert(char(10),fechatermino,102)) >= (convert(char(10),getdate(),102))) AND (Activa <> 0) and\n" +
                "codigo in (select codigo from codigos where nacional=0 and grupo not in (30,31,33,34,36,37,38,39,50))\n" +
                ") ABCX\n" +
                ") ABCX2\n" +
                ") ABCX3\n" +
                "on (xyz2.codigo = ABCX3.codigo)\n" +
                ") ABCX4\n" +
                ") ABCX5\n" +
                "--where codigo = 'XR4120'\n" +
                "\n" +
                " order by grupo, descripcion \n" +
                "";

                rs = DatabaseSQL.getRS(cSql);
                //JOptionPane.showMessageDialog(frame, "Se ejecutó con Exito Query Principal de Reporte!", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                nren=0;
                try {
                    nren = rs.getRow();
                    if (nren > 0) {
                        cSql = "delete from amazon_analisis";
                        DatabaseSQL.execQuery(cSql);
                        //JOptionPane.showMessageDialog(frame, "Se vacio tabla Temporal: amazon_analisis ", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                        while (!(rs.isAfterLast())) {
                            cCodigo = rs.getString("codigo").trim();
                            procApiAmazon(cCodigo);
                            cSql = "insert into amazon_analisis (codigo, grupo, grupodescripcion, generodescripcion, descripcion, costoreposicion, preciounitario, "
                            + " envioAmazon, margen, peso_kg, existencia, precioSoundsAmazonEnvio, api_Status, api_SellerSKU, api_ASIN, api_belongsToRequester, api_CurrencyCode,"
                            + "api_Amount, api_Condition_Any, api_SalesRank_category, api_SalesRank_category_rank, api_SalesRank_nodo, api_SalesRank_nodo_rank) ";
                            cSql = cSql+" values('"+Util.cFixString(cCodigo)+"'," + rs.getInt("grupo")+",'"+Util.cFixString(rs.getString("grupodescripcion").trim())+"','"+
                            Util.cFixString(rs.getString("generodescripcion").trim())+"','"+
                            Util.cFixString(rs.getString("descripcion").trim())+"',"+rs.getDouble("costoreposicion")+","+rs.getDouble("preciouni")+","+rs.getDouble("envioAmazon")+","+rs.getDouble("margen")+","+rs.getDouble("peso_kg")+","+rs.getDouble("existencia")+","+
                            rs.getDouble("precioSoundsAmazonEnvio")+",'"+
                            ResultXML.getstatus()+"','"+ResultXML.getSellerSKU()+"','"+ResultXML.getASIN()+"','"+ResultXML.getbelongsToRequester()+"','"+ResultXML.getLandedPrice_CurrencyCode()+"',"+
                            ResultXML.getLandedPrice_Amount()+",'"+ResultXML.getOfferListingCount_condition_Any()+"','"+ResultXML.getSalesRank_ProductCategoryId_ce_display_on_website()+"',"+
                            ResultXML.getSalesRank_ProductCategoryId_Rank()+",'"+ResultXML.getSalesRank_ProductCategoryId_nodo()+"',"+ResultXML.getSalesRank_ProductCategoryId_nodo_Rank()+")";
                            DatabaseSQL.execQuery(cSql);
                            rs.next();
                        }
                        //JOptionPane.showMessageDialog(frame, "Se Termino de Llenar tabla Temporal: amazon_analisis ", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    System.out.println("Error en reporte");
                }

                ///////////////////////
                progressBar.setIndeterminate(false);
                dialog.dispose(); 
            }
        };
        runnable_progress.start();    
        dialog.setVisible(true);                  

        GetCompetitivePricingForSKUSample.rDadosAltaAmazon();

    }//GEN-LAST:event_jButtonApiAmazonDadosAltaActionPerformed

    private void jButtonApiAmazonMasVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApiAmazonMasVentasActionPerformed
    String cval;
    int nval;                        
        rFechas rfechas = new rFechas(frame, true);
        rfechas.setVisible(true);
        if ((Menu.nRet_rFechas != 0) && (Menu.dfecha1 != null) && (Menu.dfecha2 != null)) {
            cval = JOptionPane.showInputDialog(frame, "Cuantos productos desea? ", "Capture Cantidad", JOptionPane.QUESTION_MESSAGE);
            nCantidad = Integer.parseInt(cval.trim());         
            
            final JDialog dialog = new JDialog(Frame.getFrames()[0], "Reportes Estadisticos", true);
            Thread runnable_progress = new Thread() {
                public void run() {
                    JTextArea msgLabel;        
                    JProgressBar progressBar;
                    final int MAXIMUM = 100;
                    JPanel panel;
                    progressBar = new JProgressBar(0, MAXIMUM);
                    progressBar.setIndeterminate(true);
                    msgLabel = new JTextArea("Procesando Reporte. Por favor espere...");
                    msgLabel.setEditable(false);
                    panel = new JPanel(new BorderLayout(5, 5));
                    panel.add(msgLabel, BorderLayout.PAGE_START);
                    panel.add(progressBar, BorderLayout.CENTER);
                    panel.setBorder(BorderFactory.createEmptyBorder(11, 11, 11, 11));
                    dialog.getContentPane().add(panel);
                    dialog.setResizable(false);
                    dialog.pack();
                    dialog.setSize(500, dialog.getHeight());
                    dialog.setLocationRelativeTo(null);
                    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                    dialog.setAlwaysOnTop(false);
                    msgLabel.setBackground(panel.getBackground());
                    ///////////////////////        
                    String cSql;
                    String strDate1;
                    String strDate2;  
                    SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
                    String cCodigo;
                    ResultSet rs;
                    int nren;

                    strDate1 = sm.format(Menu.dfecha1);
                    strDate2 = sm.format(Menu.dfecha2);   

                    cSql  = " select top "+nCantidad+" abc.codigo, grupo,  grupodescripcion, generodescripcion, descripcion, preciounitario, cantidad, costoreposicion, PrecioVentaNeto, utilidad, margen,  \n" +
                    " peso_kg, sum(coalesce(existenciafinal,0)-coalesce(apartados,0)) as existencia ,\n" +
                    " round((case when (preciounitario>=1) then (preciounitario/0.95/0.988) else (preciounitario/0.95) end)+\n" +
                    " (case when (peso_kg) <= 5 then 107 else (case when (peso_kg) <= 15 then 128 else (case when (peso_kg) <= 30 then 166 else (case when (peso_kg) <= 40 then 316 else (case when (peso_kg) <= 50 then 446 else (case when (peso_kg) <= 65 then 691 else 1300 end)end) end) end) end) end)\n" +
                    " ,0) as precioSoundsAmazonEnvio\n" +
                    " \n" +
                    " from (  select v.codigo, c.grupo, (select descripcion from grupos where grupo=c.grupo) as grupodescripcion, c.genero, \n" +
                    " (select descripcion from generos where genero=c.genero) as generodescripcion, c.descripcion, c.precioventa as preciounitario, sum(v.cantidad) as cantidad, \n" +
                    " sum(v.cantidad*v.costoreposicion) as costoreposicion,  sum(v.cantidad*v.PrecioVentaNeto) as PrecioVentaNeto,  \n" +
                    " sum(v.cantidad*((v.PrecioVentaNeto/1.16)-v.costoreposicion)) as utilidad,    \n" +
                    " (((sum(v.PrecioVentaNeto*v.cantidad)/1.16)-sum(v.costoreposicion*v.cantidad))/(sum(v.PrecioVentaNeto*v.cantidad)/1.16)) as margen,\n" +
                    " coalesce((select peso_kg from Codigos_info where codigo = v.codigo),0) as peso_kg\n" +
                    " from codigos c, ventas v where c.nacional<>0 and c.codigo = v.codigo and v.fecha >= '"+strDate1+"' and v.fecha <= '"+strDate2+"'  and c.grupo<=70    \n" +
                    " and c.grupo not in (20,25,45,55,56) group by v.codigo, c.grupo, c.genero, c.descripcion, c.precioventa having sum(v.PrecioVentaNeto*v.cantidad)>0  \n" +
                    " \n" +
                    " union all  \n" +
                    " \n" +
                    " select v.codigo, c.grupo, (select descripcion from grupos where grupo=c.grupo) as grupodescripcion, c.genero, \n" +
                    " (select descripcion from generos where genero=c.genero) as generodescripcion, c.descripcion,  \n" +
                    " round((c.precioventa*(select top 1 tipocambioventa from infor)),0) as preciounitario, sum(v.cantidad) as cantidad,  \n" +
                    " sum(v.cantidad*v.costoreposicion)*(select top 1 tipocambioventa from infor) as costoreposicion,  \n" +
                    " sum(v.cantidad*v.PrecioVentaNeto)*(select top 1 tipocambioventa from infor) as PrecioVentaNeto,  \n" +
                    " sum(v.cantidad*((v.PrecioVentaNeto/1.16)-v.costoreposicion))*(select top 1 tipocambioventa from infor) as utilidad, \n" +
                    " (((sum(v.PrecioVentaNeto*v.cantidad)/1.16)-sum(v.costoreposicion*v.cantidad))/(sum(v.PrecioVentaNeto*v.cantidad)/1.16)) as margen,\n" +
                    " coalesce((select peso_kg from Codigos_info where codigo = v.codigo),0) as peso_kg\n" +
                    " from codigos c, ventas v \n" +
                    " where c.nacional=0 and c.codigo = v.codigo and v.fecha >= '"+strDate1+"' and v.fecha <= '"+strDate2+"' and c.grupo<=70  and c.grupo not in (20,25,45,55,56) \n" +
                    " group by v.codigo, c.grupo, c.genero, c.descripcion, c.precioventa having sum(v.PrecioVentaNeto*v.cantidad)>0  \n" +
                    " ) abc left join periodo p on (abc.codigo = p.codigo and p.ano = year(getdate()) and p.mes = month(getdate()) and p.sucursal in (3,9,17,19,22)) \n" +
                    " \n" +
                    " group by abc.codigo, grupo, grupodescripcion, generodescripcion, descripcion, preciounitario, cantidad, costoreposicion, PrecioVentaNeto, utilidad, margen, peso_kg\n" +
                    " order by cantidad desc \n";
                    
                    cSql = "select \n" +
                    "  (((preciouni/1.16)-(select (case when(nacional=0) then costoreposicion*(select top 1 tipocambioventa from infor) else costoreposicion end) from codigos where codigo = ABCX5.codigo))/(preciouni/1.16)) as margen, \n" +
                    "round((case when (preciouni>=1) then (preciouni/0.95/0.988) else (preciouni/0.95) end)+\n" +
                    " (case when (peso_kg) <= 5 then 107 else (case when (peso_kg) <= 15 then 128 else (case when (peso_kg) <= 30 then 166 else (case when (peso_kg) <= 40 then 316 else (case when (peso_kg) <= 50 then 446 else (case when (peso_kg) <= 65 then 691 else 1300 end)end) end) end) end) end)\n" +
                    " ,0) as precioSoundsAmazonEnvio, *\n" +
                    "from (\n" +
                    "select coalesce(preciooferta, preciounitario) as preciouni, *\n" +
                    "from (\n" +
                    "select xyz2.*, ABCX3.preciooferta\n" +
                    "from (\n" +
                    "select  *\n" +
                    "from (\n" +
                    "select top "+nCantidad+" abc.codigo, grupo,  grupodescripcion, generodescripcion, descripcion, preciounitario, cantidad, costoreposicion, PrecioVentaNeto, utilidad,  \n" +
                    " peso_kg, sum(coalesce(existenciafinal,0)-coalesce(apartados,0)) as existencia \n" +
                    " \n" +
                    " from (  select v.codigo, c.grupo, (select descripcion from grupos where grupo=c.grupo) as grupodescripcion, c.genero, \n" +
                    " (select descripcion from generos where genero=c.genero) as generodescripcion, c.descripcion, c.precioventa as preciounitario, sum(v.cantidad) as cantidad, \n" +
                    " sum(v.cantidad*v.costoreposicion) as costoreposicion,  sum(v.cantidad*v.PrecioVentaNeto) as PrecioVentaNeto,  \n" +
                    " sum(v.cantidad*((v.PrecioVentaNeto/1.16)-v.costoreposicion)) as utilidad,    \n" +
                    " coalesce((select peso_kg from Codigos_info where codigo = v.codigo),0) as peso_kg\n" +
                    " from codigos c, ventas v where c.nacional<>0 and c.codigo = v.codigo and v.fecha >= '"+strDate1+"' and v.fecha <= '"+strDate2+"'  and c.grupo<=70    \n" +
                    " and c.grupo not in (20,25,45,55,56) group by v.codigo, c.grupo, c.genero, c.descripcion, c.precioventa having sum(v.PrecioVentaNeto*v.cantidad)>0  \n" +
                    " \n" +
                    " union all  \n" +
                    " \n" +
                    " select v.codigo, c.grupo, (select descripcion from grupos where grupo=c.grupo) as grupodescripcion, c.genero, \n" +
                    " (select descripcion from generos where genero=c.genero) as generodescripcion, c.descripcion,  \n" +
                    " round((c.precioventa*(select top 1 tipocambioventa from infor)),0) as preciounitario, sum(v.cantidad) as cantidad,  \n" +
                    " sum(v.cantidad*v.costoreposicion)*(select top 1 tipocambioventa from infor) as costoreposicion,  \n" +
                    " sum(v.cantidad*v.PrecioVentaNeto)*(select top 1 tipocambioventa from infor) as PrecioVentaNeto,  \n" +
                    " sum(v.cantidad*((v.PrecioVentaNeto/1.16)-v.costoreposicion))*(select top 1 tipocambioventa from infor) as utilidad, \n" +
                    " coalesce((select peso_kg from Codigos_info where codigo = v.codigo),0) as peso_kg\n" +
                    " from codigos c, ventas v \n" +
                    " where c.nacional=0 and c.codigo = v.codigo and v.fecha >= '"+strDate1+"' and v.fecha <= '"+strDate2+"' and c.grupo<=70  and c.grupo not in (20,25,45,55,56) \n" +
                    " group by v.codigo, c.grupo, c.genero, c.descripcion, c.precioventa having sum(v.PrecioVentaNeto*v.cantidad)>0  \n" +
                    " ) abc left join periodo p on (abc.codigo = p.codigo and p.ano = year(getdate()) and p.mes = month(getdate()) and p.sucursal in (3,9,17,19,22)) \n" +
                    " \n" +
                    " group by abc.codigo, grupo, grupodescripcion, generodescripcion, descripcion, preciounitario, cantidad, costoreposicion, PrecioVentaNeto, utilidad, peso_kg\n" +
                    " ) xyz) xyz2\n" +
                    "\n" +
                    "\n" +
                    " left join\n" +
                    "\n" +
                    " (\n" +
                    " select *\n" +
                    " from (\n" +
                    " select * from (\n" +
                    "select codigo, (case when precioventa15 < precioventa then precioventa15 else precioventa end) as preciooferta,\n" +
                    "convert(char(10),(getdate()-1),102) as fechainicio, convert(char(10),(getdate()+7),102) as fechatermino \n" +
                    "from (\n" +
                    "\n" +
                    "select g.descripcion as genero, c.codigo, c.codigo2, c.descripcion, \n" +
                    "(case when xyz.preciooferta < round((case when nacional=0 then c.precioventa*(select tipocambioventa from infor) else c.PrecioVenta end),0) then\n" +
                    "xyz.preciooferta else round((case when nacional=0 then c.precioventa*(select tipocambioventa from infor) else c.PrecioVenta end),0) end) as precioventa,\n" +
                    "round((case when nacional=0 then (-1*(1.16*\n" +
                    "(case when c.grupo=50 then (c.costoreposicion*1.05) else c.costoreposicion end)\n" +
                    "/(0.15-1)))*(select tipocambioventa from infor) else (-1*(1.16*\n" +
                    "(case when c.grupo=50 then (c.costoreposicion*1.05) else c.costoreposicion end)\n" +
                    "/(0.15-1))) end),0) as precioventa15\n" +
                    "\n" +
                    "from generos g, codigos c left join\n" +
                    "\n" +
                    "(\n" +
                    "select *\n" +
                    "from (\n" +
                    "SELECT Codigo, round((PrecioOferta),0) as PrecioOferta, convert(char(10),fechainicio,102) as fechainicio, \n" +
                    "convert(char(10),fechatermino+1,102) as fechatermino\n" +
                    "FROM OfertasCodigo\n" +
                    "WHERE sucursal=99 and ((convert(char(10),fechainicio,102)) <= (convert(char(10),getdate(),102))) \n" +
                    "AND ((convert(char(10),fechatermino,102)) >= (convert(char(10),getdate(),102))) AND (Activa <> 0) and\n" +
                    "codigo in (select codigo from codigos where nacional<>0)\n" +
                    "\n" +
                    "union all\n" +
                    "\n" +
                    "SELECT Codigo, round(((PrecioOferta*(select tipocambioventa from infor))),0) as PrecioOferta, \n" +
                    "convert(char(10),fechainicio,102), convert(char(10),fechatermino+1,102)\n" +
                    "FROM OfertasCodigo\n" +
                    "WHERE sucursal=99 and ((convert(char(10),fechainicio,102)) <= (convert(char(10),getdate(),102))) \n" +
                    "AND ((convert(char(10),fechatermino,102)) >= (convert(char(10),getdate(),102))) AND (Activa <> 0) and\n" +
                    "codigo in (select codigo from codigos where nacional=0)\n" +
                    "\n" +
                    "\n" +
                    ") ABC\n" +
                    "--order by codigo\n" +
                    ") XYZ on (c.codigo = XYZ.codigo)\n" +
                    "\n" +
                    "where c.genero=g.genero --and c.genero in (106,47,115,95,83,137,85,138,82,136,100,101,154,105,91,327,330,127,188,107,1002,92,59,93,702,94,598,108,182,104)\n" +
                    "-- and c.genero<>154\n" +
                    "and c.grupo in (30,31,33,34,36,37,38,39,50)\n" +
                    "and exists(\n" +
                    "select *\n" +
                    "from periodo\n" +
                    "where ano=year(getdate()) and mes=month(getdate()) and sucursal in(3,5,9,17,19,22) and existenciafinal>0 and codigo = c.codigo)\n" +
                    "--order by c.codigo, g.descripcion, c.descripcion\n" +
                    "\n" +
                    ") abc1\n" +
                    "--order by  abc.codigo, abc.genero, abc.descripcion\n" +
                    ") xyz1\n" +
                    "where xyz1.preciooferta > 0\n" +
                    "and not exists(\n" +
                    "select *\n" +
                    "from codigos\n" +
                    "where codigo = xyz1.codigo and round((case when nacional=0 then precioventa*(select tipocambioventa from infor) else PrecioVenta end),0) = \n" +
                    "round(xyz1.preciooferta,0)\n" +
                    ")\n" +
                    "union\n" +
                    "select *\n" +
                    "from (\n" +
                    "SELECT Codigo, round((PrecioOferta),0) as PrecioOferta, convert(char(10),fechainicio,102) as fechainicio, \n" +
                    "convert(char(10),fechatermino+1,102) as fechatermino\n" +
                    "FROM OfertasCodigo\n" +
                    "WHERE sucursal=99 and ((convert(char(10),fechainicio,102)) <= (convert(char(10),getdate(),102))) \n" +
                    "AND ((convert(char(10),fechatermino,102)) >= (convert(char(10),getdate(),102))) AND (Activa <> 0) and\n" +
                    "codigo in (select codigo from codigos where nacional<>0 and grupo not in (30,31,33,34,36,37,38,39,50))\n" +
                    "\n" +
                    "union all\n" +
                    "\n" +
                    "SELECT Codigo, round(((PrecioOferta*(select tipocambioventa from infor))),0) as PrecioOferta, \n" +
                    "convert(char(10),fechainicio,102), convert(char(10),fechatermino+1,102)\n" +
                    "FROM OfertasCodigo\n" +
                    "WHERE sucursal=99 and ((convert(char(10),fechainicio,102)) <= (convert(char(10),getdate(),102))) \n" +
                    "AND ((convert(char(10),fechatermino,102)) >= (convert(char(10),getdate(),102))) AND (Activa <> 0) and\n" +
                    "codigo in (select codigo from codigos where nacional=0 and grupo not in (30,31,33,34,36,37,38,39,50))\n" +
                    ") ABCX\n" +
                    ") ABCX2\n" +
                    ") ABCX3\n" +
                    "on (xyz2.codigo = ABCX3.codigo)\n" +
                    ") ABCX4\n" +
                    ") ABCX5\n" +
                    "--where codigo = 'XR4120'\n" +
                    "\n" +
                    " order by ABCX5.cantidad desc \n" +
                    "";
                    rs = DatabaseSQL.getRS(cSql);
                    nren=0;
                    try {
                        nren = rs.getRow();
                        if (nren > 0) {
                            cSql = "delete from amazon_analisis";
                            DatabaseSQL.execQuery(cSql);
                            while (!(rs.isAfterLast())) {
                                cCodigo = rs.getString("codigo").trim();
                                procApiAmazon(cCodigo);
                                cSql = "insert into amazon_analisis (codigo, grupo, grupodescripcion, generodescripcion, descripcion, preciounitario, cantidad, costoreposicion,"
                                + "PrecioVentaNeto, utilidad, margen, peso_kg, existencia, precioSoundsAmazonEnvio, api_Status, api_SellerSKU, api_ASIN, api_belongsToRequester, api_CurrencyCode,"
                                + "api_Amount, api_Condition_Any, api_SalesRank_category, api_SalesRank_category_rank, api_SalesRank_nodo, api_SalesRank_nodo_rank) ";
                                cSql = cSql+" values('"+Util.cFixString(cCodigo)+"'," + rs.getInt("grupo")+",'"+Util.cFixString(rs.getString("grupodescripcion").trim())+"','"+
                                Util.cFixString(rs.getString("generodescripcion").trim())+"','"+
                                Util.cFixString(rs.getString("descripcion").trim())+"',"+rs.getDouble("preciouni")+","+rs.getDouble("cantidad")+","+rs.getDouble("costoreposicion")+","+
                                rs.getDouble("PrecioVentaNeto")+","+rs.getDouble("utilidad")+","+rs.getDouble("margen")+","+rs.getDouble("peso_kg")+","+rs.getDouble("existencia")+","+
                                rs.getDouble("precioSoundsAmazonEnvio")+",'"+
                                ResultXML.getstatus()+"','"+ResultXML.getSellerSKU()+"','"+ResultXML.getASIN()+"','"+ResultXML.getbelongsToRequester()+"','"+ResultXML.getLandedPrice_CurrencyCode()+"',"+
                                ResultXML.getLandedPrice_Amount()+",'"+ResultXML.getOfferListingCount_condition_Any()+"','"+ResultXML.getSalesRank_ProductCategoryId_ce_display_on_website()+"',"+
                                ResultXML.getSalesRank_ProductCategoryId_Rank()+",'"+ResultXML.getSalesRank_ProductCategoryId_nodo()+"',"+ResultXML.getSalesRank_ProductCategoryId_nodo_Rank()+")";
                                DatabaseSQL.execQuery(cSql);
                                rs.next();
                            }
                        }
                    } catch (Exception ex) {
                        System.out.println("Error en reporte");
                    }

                    ///////////////////////
                    progressBar.setIndeterminate(false);
                    dialog.dispose(); 
                }
            };
            runnable_progress.start();    
            dialog.setVisible(true);                  

            GetCompetitivePricingForSKUSample.rMasVentasAnalisisAmazon();
        }
    }//GEN-LAST:event_jButtonApiAmazonMasVentasActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                Menu dialog = new Menu(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonApiAmazonDadosAlta;
    private javax.swing.JButton jButtonApiAmazonMasVentas;
    private javax.swing.JButton jButtonAyuda;
    private javax.swing.JButton jButtonDolaresxSucursal;
    private javax.swing.JButton jButtonParametros;
    private javax.swing.JButton jButtonPronosticos;
    private javax.swing.JButton jButtonPronosticosGrupoGenero;
    private javax.swing.JButton jButtonReparticiones;
    private javax.swing.JButton jButtonTraspasos;
    private javax.swing.JButton jButtonVentasGrupoSuc;
    private javax.swing.JButton jButtonVerificarSeries;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    // End of variables declaration//GEN-END:variables
}
