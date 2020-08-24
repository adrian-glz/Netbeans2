package reportes;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import java.util.Locale;
//import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;


public class reports {
  
    public static Object fjasperreports(String cReport, Object[][] oReport) {
        Object oRet=null;
        for (int nfor=0; nfor<=oReport.length-1; nfor++) {
            if (oReport[nfor][0] != null) {
                if (oReport[nfor][0] == cReport) {
                    oRet = oReport[nfor][1];
                    break;
                }
            }
        }
        return oRet;
    }

    
    public static void rApartadosComparativa(Date fecha1ant, Date fecha2ant, Date fecha1, Date fecha2) {
      javax.swing.JButton jButtonAyuda;
      Connection conexion = null;
      try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      }
        catch (ClassNotFoundException e) {
        System.out.println("SQLServer JDBC Driver no se encuentra!");
        System.exit(1);
      }
      try {
        conexion = DriverManager.getConnection("jdbc:sqlserver://192.168.1.80:56704;databaseName=CML", "usounds", "madljda");
      }
      catch (SQLException e) {
        System.out.println("Error de conexion: " + e.getMessage());
        System.exit(1);
      }
      try {
        // Esto es para que el reporte aparezca encima del JDialog que es modal
        JDialog viewer = new JDialog(Menu.frame,"Imprimir", true); 
        viewer.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        viewer.setSize(1024, 600);
        viewer.setLocationRelativeTo(null);
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(false); 
          
        Map parameters = new HashMap();
        parameters.put("dfecha1ant", fecha1ant);
        parameters.put("dfecha2ant", fecha2ant);
        parameters.put("dfecha1", fecha1);
        parameters.put("dfecha2", fecha2);
        //JasperPrint print = JasperFillManager.fillReport("repApartadosComparativa.jasper", parameters, conexion);
        //JasperViewer.viewReport(print, false);
        JasperReport reportTemplate = (JasperReport) fjasperreports("repApartadosComparativa.jasper",Menu.jasperreports);
        JasperPrint print = JasperFillManager.fillReport(reportTemplate, parameters, conexion);    
        
        JasperViewer jrViewer = new JasperViewer(print, false); 

        jButtonAyuda = new javax.swing.JButton();
        jButtonAyuda.setText("Ayuda");
        jButtonAyuda.setSize(63, 23);
        jButtonAyuda.getComponentAt(900, 1);
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    try {
                        Desktop.getDesktop().browse(new URI("http://192.168.1.89/rEstadisticos/RepEstadisticos_comparativa/RepEstadisticos_comparativa.html"));
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(viewer.getContentPane());
        viewer.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        
        //viewer.getContentPane().add(jrViewer.getContentPane()); 
        viewer.setVisible(true); 
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);  
        jrViewer.dispose(); 
        print=null; 
        viewer.dispose(); 
        viewer=null;
      }
      catch (Exception e) {
        e.printStackTrace();
        System.exit(1);
      }
    }

    public static void rIngresosSemanasGrupoGenero(int nGrupo, int nGenero) {
      javax.swing.JButton jButtonAyuda;
      Connection conexion = null;
      try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      }
        catch (ClassNotFoundException e) {
        System.out.println("SQLServer JDBC Driver no se encuentra!");
        System.exit(1);
      }
      try {
        conexion = DriverManager.getConnection("jdbc:sqlserver://192.168.1.80:56704;databaseName=CML", "usounds", "madljda");
      }
      catch (SQLException e) {
        System.out.println("Error de conexion: " + e.getMessage());
        System.exit(1);
      }
      try {
        // Esto es para que el reporte aparezca encima del JDialog que es modal
        JDialog viewer = new JDialog(Menu.frame,"Imprimir", true); 
        viewer.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        viewer.setSize(1024, 600);
        viewer.setLocationRelativeTo(null);
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(false); 
          
        Map parameters = new HashMap();
        parameters.put("nGrupo", nGrupo);
        parameters.put("nGenero", nGenero);
        //JasperPrint print = JasperFillManager.fillReport("rIngresosSemanas.jasper", parameters, conexion);
        JasperReport reportTemplate = (JasperReport) fjasperreports("rIngresosSemanas.jasper",Menu.jasperreports);
        JasperPrint print = JasperFillManager.fillReport(reportTemplate, parameters, conexion);    
        
        
        JasperViewer jrViewer = new JasperViewer(print, false); 

        jButtonAyuda = new javax.swing.JButton();
        jButtonAyuda.setText("Ayuda");
        jButtonAyuda.setSize(63, 23);
        jButtonAyuda.getComponentAt(900, 1);
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    try {
                        Desktop.getDesktop().browse(new URI("http://192.168.1.89/rEstadisticos/RepEstadisticos_pronosticos_semana/RepEstadisticos_pronosticos_semana.html"));
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    System.out.println("Error en ayuda"); 
                }
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(viewer.getContentPane());
        viewer.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        
        //viewer.getContentPane().add(jrViewer.getContentPane()); 
        viewer.setVisible(true); 
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);  
        jrViewer.dispose(); 
        print=null; 
        viewer.dispose(); 
        viewer=null;
      }
      catch (Exception e) {
        e.printStackTrace();
        System.exit(1);
      }
    }

    public static void rIngresosSemanas() {
      javax.swing.JButton jButtonAyuda;
      Connection conexion = null;
      try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      }
        catch (ClassNotFoundException e) {
        System.out.println("SQLServer JDBC Driver no se encuentra!");
        System.exit(1);
      }
      try {
        conexion = DriverManager.getConnection("jdbc:sqlserver://192.168.1.80:56704;databaseName=CML", "usounds", "madljda");
      }
      catch (SQLException e) {
        System.out.println("Error de conexion: " + e.getMessage());
        System.exit(1);
      }
      try {
        // Esto es para que el reporte aparezca encima del JDialog que es modal
        JDialog viewer = new JDialog(Menu.frame,"Imprimir", true); 
        viewer.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        viewer.setSize(1024, 600);
        viewer.setLocationRelativeTo(null);
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(false); 
          
        Map parameters = new HashMap();
        //JasperPrint print = JasperFillManager.fillReport("rIngresosSemanasxSemana.jasper", parameters, conexion);
        JasperReport reportTemplate = (JasperReport) fjasperreports("rIngresosSemanasxSemana.jasper",Menu.jasperreports);
        JasperPrint print = JasperFillManager.fillReport(reportTemplate, parameters, conexion);    
        
        JasperViewer jrViewer = new JasperViewer(print, false); 

        jButtonAyuda = new javax.swing.JButton();
        jButtonAyuda.setText("Ayuda");
        jButtonAyuda.setSize(63, 23);
        jButtonAyuda.getComponentAt(900, 1);
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    try {
                        Desktop.getDesktop().browse(new URI("http://192.168.1.89/rEstadisticos/RepEstadisticos_pronosticos_semana_total/RepEstadisticos_pronosticos_semana_total.html"));
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    System.out.println("Error en ayuda"); 
                }
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(viewer.getContentPane());
        viewer.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        
        //viewer.getContentPane().add(jrViewer.getContentPane()); 
        viewer.setVisible(true); 
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);  
        jrViewer.dispose(); 
        print=null; 
        viewer.dispose(); 
        viewer=null;
      }
      catch (Exception e) {
        e.printStackTrace();
        System.exit(1);
      }
    }
    
    public static void rIngresosSemanasResumen() {
      javax.swing.JButton jButtonAyuda;
      Connection conexion = null;
      try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      }
        catch (ClassNotFoundException e) {
        System.out.println("SQLServer JDBC Driver no se encuentra!");
        System.exit(1);
      }
      try {
        conexion = DriverManager.getConnection("jdbc:sqlserver://192.168.1.80:56704;databaseName=CML", "usounds", "madljda");
      }
      catch (SQLException e) {
        System.out.println("Error de conexion: " + e.getMessage());
        System.exit(1);
      }
      try {
        // Esto es para que el reporte aparezca encima del JDialog que es modal
        JDialog viewer = new JDialog(Menu.frame,"Imprimir", true); 
        viewer.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        viewer.setSize(1024, 600);
        viewer.setLocationRelativeTo(null);
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(false); 
          
        Map parameters = new HashMap();
        //JasperPrint print = JasperFillManager.fillReport("rIngresosSemanasResumen.jasper", parameters, conexion);
        JasperReport reportTemplate = (JasperReport) fjasperreports("rIngresosSemanasResumen.jasper",Menu.jasperreports);
        JasperPrint print = JasperFillManager.fillReport(reportTemplate, parameters, conexion);    
        
        JasperViewer jrViewer = new JasperViewer(print, false); 

        jButtonAyuda = new javax.swing.JButton();
        jButtonAyuda.setText("Ayuda");
        jButtonAyuda.setSize(63, 23);
        jButtonAyuda.getComponentAt(900, 1);
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    try {
                        Desktop.getDesktop().browse(new URI("http://192.168.1.89/rEstadisticos/RepEstadisticos_pronosticos_resumen/RepEstadisticos_pronosticos_resumen.html"));
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    System.out.println("Error en ayuda"); 
                }
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(viewer.getContentPane());
        viewer.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        
        //viewer.getContentPane().add(jrViewer.getContentPane()); 
        viewer.setVisible(true); 
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);  
        jrViewer.dispose(); 
        print=null; 
        viewer.dispose(); 
        viewer=null;
      }
      catch (Exception e) {
        e.printStackTrace();
        System.exit(1);
      }
    }
    
    public static void rIngresosSemanasParametros() {
      javax.swing.JButton jButtonAyuda;
      Connection conexion = null;
      try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      }
        catch (ClassNotFoundException e) {
        System.out.println("SQLServer JDBC Driver no se encuentra!");
        System.exit(1);
      }
      try {
        conexion = DriverManager.getConnection("jdbc:sqlserver://192.168.1.80:56704;databaseName=CML", "usounds", "madljda");
      }
      catch (SQLException e) {
        System.out.println("Error de conexion: " + e.getMessage());
        System.exit(1);
      }
      try {
        // Esto es para que el reporte aparezca encima del JDialog que es modal
        JDialog viewer = new JDialog(Menu.frame,"Imprimir", true); 
        viewer.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        viewer.setSize(1024, 600);
        viewer.setLocationRelativeTo(null);
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(false); 
          
        Map parameters = new HashMap();
        //JasperPrint print = JasperFillManager.fillReport("rIngresosSemanasParametros.jasper", parameters, conexion);
        JasperReport reportTemplate = (JasperReport) fjasperreports("rIngresosSemanasParametros.jasper",Menu.jasperreports);
        JasperPrint print = JasperFillManager.fillReport(reportTemplate, parameters, conexion);    
        
        
        JasperViewer jrViewer = new JasperViewer(print, false); 

        jButtonAyuda = new javax.swing.JButton();
        jButtonAyuda.setText("Ayuda");
        jButtonAyuda.setSize(63, 23);
        jButtonAyuda.getComponentAt(900, 1);
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    try {
                        Desktop.getDesktop().browse(new URI("http://192.168.1.89/rEstadisticos/RepEstadisticos_pronosticos_parametros/RepEstadisticos_pronosticos_parametros.html"));
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    System.out.println("Error en ayuda"); 
                }
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(viewer.getContentPane());
        viewer.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        
        //viewer.getContentPane().add(jrViewer.getContentPane()); 
        
        viewer.setVisible(true); 
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);  
        jrViewer.dispose(); 
        print=null; 
        viewer.dispose(); 
        viewer=null;
      }
      catch (Exception e) {
        e.printStackTrace();
        System.exit(1);
      }
    }
    
    
    public static void rDollaresFinal(int nSucursal, Date fecha1, Date fecha2) {
      javax.swing.JButton jButtonAyuda;
      Connection conexion = null;
  
      try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      }
        catch (ClassNotFoundException e) {
        System.out.println("SQLServer JDBC Driver no se encuentra!");
        System.exit(1);
      }
      try {
        conexion = DriverManager.getConnection("jdbc:sqlserver://192.168.1.80:56704;databaseName=CML", "usounds", "madljda");
      }
      catch (SQLException e) {
        System.out.println("Error de conexion: " + e.getMessage());
        System.exit(1);
      }
      try {
        // Esto es para que el reporte aparezca encima del JDialog que es modal
        /*JDialog viewer = new JDialog(Menu.frame,"Imprimir", true); 
        viewer.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        viewer.setSize(1024, 600);
        viewer.setLocationRelativeTo(null);
        //getJMenuBar();
       //javax.swing.JDialog.setDefaultLookAndFeelDecorated(false); */
          
        Map parameters = new HashMap();
        parameters.put("nSucursal", nSucursal);
        parameters.put("dfecha1", fecha1);
        parameters.put("dfecha2", fecha2);
        JasperReport reportTemplate = (JasperReport) fjasperreports("repDollaresFinalBank.jasper",Menu.jasperreports);
        JasperPrint print = JasperFillManager.fillReport(reportTemplate, parameters, conexion);    
        
        //exportar a excel
        String cfile=System.getProperty("user.home")+"/repDollares.xls";
        OutputStream ouputStream = new FileOutputStream(new File(cfile));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        
        JRXlsExporter exporterXLS = new JRXlsExporter();
        exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT,print);
        exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        exporterXLS.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.FALSE);
        exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,byteArrayOutputStream);
        
        exporterXLS.exportReport();
        ouputStream.write(byteArrayOutputStream.toByteArray()); 
        ouputStream.flush();
        ouputStream.close();
        
        /*
        JasperViewer jrViewer = new JasperViewer(print, false); 

        jButtonAyuda = new javax.swing.JButton();
        jButtonAyuda.setText("Ayuda");
        jButtonAyuda.setSize(63, 23);
        jButtonAyuda.getComponentAt(900, 1);
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    try {
                        Desktop.getDesktop().browse(new URI("http://192.168.1.89/rEstadisticos/RepDollares/RepDollares.html"));
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(viewer.getContentPane());
        viewer.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        
        //viewer.getContentPane().add(jrViewer.getContentPane()); 
        viewer.setVisible(true); 
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);  
        jrViewer.dispose(); 
        print=null; 
        viewer.dispose(); 
        viewer=null;
        * 
        */
      }
      catch (Exception e) {
        e.printStackTrace();
        System.exit(1);
      }
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
    
    
    
    
    public static void rFaltantes() {
      javax.swing.JButton jButtonAyuda;
      Connection conexion = null;
      java.util.Date date = new Date();
      try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      }
        catch (ClassNotFoundException e) {
        System.out.println("SQLServer JDBC Driver no se encuentra!");
        System.exit(1);
      }
      try {
        conexion = DriverManager.getConnection("jdbc:sqlserver://192.168.1.80:56704;databaseName=CML", "usounds", "madljda");
      }
      catch (SQLException e) {
        System.out.println("Error de conexion: " + e.getMessage());
        System.exit(1);
      }
      try {
        // Esto es para que el reporte aparezca encima del JDialog que es modal
        JDialog viewer = new JDialog(Menu.frame,"Imprimir", true); 
        viewer.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        viewer.setSize(1024, 600);
        viewer.setLocationRelativeTo(null);
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(false); 
 
        date = addDays(date,-1);
        
        Map parameters = new HashMap();
        //parameters.put(JRParameter.REPORT_LOCALE, Locale.US); 
        parameters.put("fecha", date);
        parameters.put("SUBREPORT_DIR", "reportes/reports/");
        
        //JasperPrint print = JasperFillManager.fillReport("rIngresosSemanasParametros.jasper", parameters, conexion);
        JasperReport reportTemplate = (JasperReport) fjasperreports("rFaltantes.jasper",Menu.jasperreports);
        JasperPrint print = JasperFillManager.fillReport(reportTemplate, parameters, conexion);    
        
        
        JasperViewer jrViewer = new JasperViewer(print, false); 

        jButtonAyuda = new javax.swing.JButton();
        jButtonAyuda.setText("Ayuda");
        jButtonAyuda.setSize(63, 23);
        jButtonAyuda.getComponentAt(900, 1);
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    try {
                        Desktop.getDesktop().browse(new URI("http://192.168.1.89/rEstadisticos/RepEstadisticos_pronosticos_parametros/RepEstadisticos_pronosticos_parametros.html"));
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    System.out.println("Error en ayuda"); 
                }
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(viewer.getContentPane());
        viewer.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        
        //viewer.getContentPane().add(jrViewer.getContentPane()); 
        
        viewer.setVisible(true); 
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);  
        jrViewer.dispose(); 
        print=null; 
        viewer.dispose(); 
        viewer=null;
      }
      catch (Exception e) {
        e.printStackTrace();
        System.exit(1);
      }
    }

    public static void rFaltantesDetalle(int nSucursal) {
      javax.swing.JButton jButtonAyuda;
      Connection conexion = null;
      java.util.Date date = new Date();
      try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      }
        catch (ClassNotFoundException e) {
        System.out.println("SQLServer JDBC Driver no se encuentra!");
        System.exit(1);
      }
      try {
        conexion = DriverManager.getConnection("jdbc:sqlserver://192.168.1.80:56704;databaseName=CML", "usounds", "madljda");
      }
      catch (SQLException e) {
        System.out.println("Error de conexion: " + e.getMessage());
        System.exit(1);
      }
      try {
        // Esto es para que el reporte aparezca encima del JDialog que es modal
        JDialog viewer = new JDialog(Menu.frame,"Imprimir", true); 
        viewer.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        viewer.setSize(1024, 600);
        viewer.setLocationRelativeTo(null);
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(false); 
 
        Map parameters = new HashMap();
        //parameters.put(JRParameter.REPORT_LOCALE, Locale.US); 
        parameters.put("nSucursal", nSucursal);
        
        //JasperPrint print = JasperFillManager.fillReport("rIngresosSemanasParametros.jasper", parameters, conexion);
        JasperReport reportTemplate = (JasperReport) fjasperreports("rFaltantesDetalle.jasper",Menu.jasperreports);
        JasperPrint print = JasperFillManager.fillReport(reportTemplate, parameters, conexion);    
        
        
        JasperViewer jrViewer = new JasperViewer(print, false); 

        jButtonAyuda = new javax.swing.JButton();
        jButtonAyuda.setText("Ayuda");
        jButtonAyuda.setSize(63, 23);
        jButtonAyuda.getComponentAt(900, 1);
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    try {
                        Desktop.getDesktop().browse(new URI("http://192.168.1.89/rEstadisticos/RepEstadisticos_pronosticos_parametros/RepEstadisticos_pronosticos_parametros.html"));
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    System.out.println("Error en ayuda"); 
                }
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(viewer.getContentPane());
        viewer.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        
        //viewer.getContentPane().add(jrViewer.getContentPane()); 
        
        viewer.setVisible(true); 
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);  
        jrViewer.dispose(); 
        print=null; 
        viewer.dispose(); 
        viewer=null;
      }
      catch (Exception e) {
        e.printStackTrace();
        System.exit(1);
      }
    }
    
     public static void rReparticiones(Date fecha1, Date fecha2) {
      javax.swing.JButton jButtonAyuda;
      Connection conexion = null;
      try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      }
        catch (ClassNotFoundException e) {
        System.out.println("SQLServer JDBC Driver no se encuentra!");
        System.exit(1);
      }
      try {
        conexion = DriverManager.getConnection("jdbc:sqlserver://192.168.1.80:56704;databaseName=CML", "usounds", "madljda");
      }
      catch (SQLException e) {
        System.out.println("Error de conexion: " + e.getMessage());
        System.exit(1);
      }
      try {
        // Esto es para que el reporte aparezca encima del JDialog que es modal
        JDialog viewer = new JDialog(Menu.frame,"Imprimir", true); 
        viewer.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        viewer.setSize(1024, 600);
        viewer.setLocationRelativeTo(null);
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(false); 
          
        Map parameters = new HashMap();
        parameters.put("FechaIni", fecha1);
        parameters.put("FechaFin", fecha2);
        JasperReport reportTemplate = (JasperReport) fjasperreports("repCuentasContablesPivot.jasper",Menu.jasperreports);
        JasperPrint print = JasperFillManager.fillReport(reportTemplate, parameters, conexion);    
        
        JasperViewer jrViewer = new JasperViewer(print, false); 

        jButtonAyuda = new javax.swing.JButton();
        jButtonAyuda.setText("Ayuda");
        jButtonAyuda.setSize(63, 23);
        jButtonAyuda.getComponentAt(900, 1);
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    try {
                        Desktop.getDesktop().browse(new URI("http://192.168.1.89/rEstadisticos/RepEstadisticos_comparativa/RepEstadisticos_comparativa.html"));
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(viewer.getContentPane());
        viewer.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        
        //viewer.getContentPane().add(jrViewer.getContentPane()); 
        viewer.setVisible(true); 
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);  
        jrViewer.dispose(); 
        print=null; 
        viewer.dispose(); 
        viewer=null;
      }
      catch (Exception e) {
        e.printStackTrace();
        System.exit(1);
      }
    }
     
     
     public static void rTraspasos(Date fecha1, Date fecha2) {
      javax.swing.JButton jButtonAyuda;
      Connection conexion = null;
      try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      }
        catch (ClassNotFoundException e) {
        System.out.println("SQLServer JDBC Driver no se encuentra!");
        System.exit(1);
      }
      try {
        conexion = DriverManager.getConnection("jdbc:sqlserver://192.168.1.80:56704;databaseName=CML", "usounds", "madljda");
      }
      catch (SQLException e) {
        System.out.println("Error de conexion: " + e.getMessage());
        System.exit(1);
      }
      try {
        // Esto es para que el reporte aparezca encima del JDialog que es modal
        JDialog viewer = new JDialog(Menu.frame,"Imprimir", true); 
        viewer.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        viewer.setSize(1024, 600);
        viewer.setLocationRelativeTo(null);
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(false); 
          
        Map parameters = new HashMap();
        parameters.put("FechaIni", fecha1);
        parameters.put("FechaFin", fecha2);
        JasperReport reportTemplate = (JasperReport) fjasperreports("repTraspasosPivot.jasper",Menu.jasperreports);
        JasperPrint print = JasperFillManager.fillReport(reportTemplate, parameters, conexion);    
        
        JasperViewer jrViewer = new JasperViewer(print, false); 

        jButtonAyuda = new javax.swing.JButton();
        jButtonAyuda.setText("Ayuda");
        jButtonAyuda.setSize(63, 23);
        jButtonAyuda.getComponentAt(900, 1);
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    try {
                        Desktop.getDesktop().browse(new URI("http://192.168.1.89/rEstadisticos/RepEstadisticos_comparativa/RepEstadisticos_comparativa.html"));
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(viewer.getContentPane());
        viewer.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        
        //viewer.getContentPane().add(jrViewer.getContentPane()); 
        viewer.setVisible(true); 
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);  
        jrViewer.dispose(); 
        print=null; 
        viewer.dispose(); 
        viewer=null;
      }
      catch (Exception e) {
        e.printStackTrace();
        System.exit(1);
      }
    }

     public static void rVentasGrupoSuc(int nAno, int nMes) {
      javax.swing.JButton jButtonAyuda;
      Connection conexion = null;
      try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      }
        catch (ClassNotFoundException e) {
        System.out.println("SQLServer JDBC Driver no se encuentra!");
        System.exit(1);
      }
      try {
        conexion = DriverManager.getConnection("jdbc:sqlserver://192.168.1.80:56704;databaseName=CML", "usounds", "madljda");
      }
      catch (SQLException e) {
        System.out.println("Error de conexion: " + e.getMessage());
        System.exit(1);
      }
      try {
        // Esto es para que el reporte aparezca encima del JDialog que es modal
        JDialog viewer = new JDialog(Menu.frame,"Imprimir", true); 
        viewer.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        viewer.setSize(1024, 600);
        viewer.setLocationRelativeTo(null);
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(false); 
          
        Map parameters = new HashMap();
        parameters.put("nAno", nAno);
        parameters.put("nMes", nMes);
        //JasperPrint print = JasperFillManager.fillReport("rIngresosSemanas.jasper", parameters, conexion);
        JasperReport reportTemplate = (JasperReport) fjasperreports("repVentasSucursalGrupo.jasper",Menu.jasperreports);
        JasperPrint print = JasperFillManager.fillReport(reportTemplate, parameters, conexion);    
        
        
        JasperViewer jrViewer = new JasperViewer(print, false); 

        jButtonAyuda = new javax.swing.JButton();
        jButtonAyuda.setText("Ayuda");
        jButtonAyuda.setSize(63, 23);
        jButtonAyuda.getComponentAt(900, 1);
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    try {
                        Desktop.getDesktop().browse(new URI("http://192.168.1.89/rEstadisticos/RepEstadisticos_pronosticos_semana/RepEstadisticos_pronosticos_semana.html"));
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    System.out.println("Error en ayuda"); 
                }
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(viewer.getContentPane());
        viewer.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        
        //viewer.getContentPane().add(jrViewer.getContentPane()); 
        viewer.setVisible(true); 
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);  
        jrViewer.dispose(); 
        print=null; 
        viewer.dispose(); 
        viewer=null;
      }
      catch (Exception e) {
        e.printStackTrace();
        System.exit(1);
      }
    }

     public static void rSeries(Date dFecha, String cFactura, String cProveedor) {
      javax.swing.JButton jButtonAyuda;
      Connection conexion = null;
      try {
        Class.forName("org.postgresql.Driver");
      }
        catch (ClassNotFoundException e) {
        System.out.println("SQLServer JDBC Driver no se encuentra!");
        System.exit(1);
      }
      try {
        conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CFDi?loginTimeout=30&socketTimeout=30", "postgres", "Madljda1");
      }
      catch (SQLException e) {
        System.out.println("Error de conexion: " + e.getMessage());
        System.exit(1);
      }
      try {
        // Esto es para que el reporte aparezca encima del JDialog que es modal
        JDialog viewer = new JDialog(Menu.frame,"Imprimir", true); 
        viewer.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        viewer.setSize(1024, 600);
        viewer.setLocationRelativeTo(null);
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(false); 
          
        Map parameters = new HashMap();
        parameters.put("fecha", dFecha);
        parameters.put("cFactura", cFactura);
         parameters.put("cProveedor", cProveedor);
        JasperReport reportTemplate = (JasperReport) fjasperreports("rSeries.jasper",Menu.jasperreports);
        JasperPrint print = JasperFillManager.fillReport(reportTemplate, parameters, conexion);    
        
        JasperViewer jrViewer = new JasperViewer(print, false); 

        jButtonAyuda = new javax.swing.JButton();
        jButtonAyuda.setText("Ayuda");
        jButtonAyuda.setSize(63, 23);
        jButtonAyuda.getComponentAt(900, 1);
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    try {
                        Desktop.getDesktop().browse(new URI("http://192.168.1.89/rEstadisticos/RepEstadisticos_comparativa/RepEstadisticos_comparativa.html"));
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(viewer.getContentPane());
        viewer.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonAyuda)
            .addComponent(jrViewer.getContentPane()));
        
        //viewer.getContentPane().add(jrViewer.getContentPane()); 
        viewer.setVisible(true); 
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);  
        jrViewer.dispose(); 
        print=null; 
        viewer.dispose(); 
        viewer=null;
      }
      catch (Exception e) {
        e.printStackTrace();
        System.exit(1);
      }
    }
   
     
}