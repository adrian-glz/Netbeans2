/*******************************************************************************
 * Copyright 2009-2015 Amazon Services. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 *
 * You may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 * specific language governing permissions and limitations under the License.
 *******************************************************************************
 * Marketplace Web Service Products
 * API Version: 2011-10-01
 * Library Version: 2015-09-01
 * Generated: Thu Sep 10 06:52:13 PDT 2015
 */
package reportes;

import java.util.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;

import com.amazonservices.mws.client.*;
import com.amazonservices.mws.products.*;
import com.amazonservices.mws.products.model.*;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import static reportes.reports.fjasperreports;
import reportes.util.ResultXML;


/** Sample call for GetCompetitivePricingForSKU. */
public class GetCompetitivePricingForSKUSample {

    /**
     * Call the service, log response and exceptions.
     *
     * @param client
     * @param request
     *
     * @return The response.
     */
    
    public static void rMasVentasAnalisisAmazon() {
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
        parameters.put("dfecha1", Menu.dfecha1);
        parameters.put("dfecha2", Menu.dfecha2);
        
        //JasperPrint print = JasperFillManager.fillReport("rIngresosSemanasParametros.jasper", parameters, conexion);
        JasperReport reportTemplate = (JasperReport) fjasperreports("rApiAmazon.jasper",Menu.jasperreports);
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

    public static void rDadosAltaAmazon() {
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
        parameters.put("dfecha1", Menu.dfecha1);
        parameters.put("dfecha2", Menu.dfecha2);
        
        //JasperPrint print = JasperFillManager.fillReport("rIngresosSemanasParametros.jasper", parameters, conexion);
        JasperReport reportTemplate = (JasperReport) fjasperreports("rApiAmazonDadosAlta.jasper",Menu.jasperreports);
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
    
    public static GetCompetitivePricingForSKUResponse invokeGetCompetitivePricingForSKU(
            MarketplaceWebServiceProducts client, 
            GetCompetitivePricingForSKURequest request) {
        try {
            // Call the service.
            GetCompetitivePricingForSKUResponse response = client.getCompetitivePricingForSKU(request);
            ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            // We recommend logging every the request id and timestamp of every call.
            System.out.println("Response:");
            System.out.println("RequestId 1: "+rhmd.getRequestId());
            System.out.println("Timestamp: "+rhmd.getTimestamp());
            String responseXml = response.toXML();
            ResultXML.setValuesXML(responseXml);
            System.out.println("");
            System.out.println(ResultXML.getstatus());
            System.out.println(ResultXML.getASIN());
            System.out.println(ResultXML.getSellerSKU());
            System.out.println(ResultXML.getbelongsToRequester());
            System.out.println(ResultXML.getLandedPrice_CurrencyCode());
            System.out.println(ResultXML.getLandedPrice_Amount());
            System.out.println(ResultXML.getOfferListingCount_condition_Any());
            System.out.println(ResultXML.getSalesRank_ProductCategoryId_ce_display_on_website());
            System.out.println(ResultXML.getSalesRank_ProductCategoryId_Rank());
            System.out.println(ResultXML.getSalesRank_ProductCategoryId_nodo());
            System.out.println(ResultXML.getSalesRank_ProductCategoryId_nodo_Rank());
            //System.out.println(responseXml);
            return response;
        } catch (MarketplaceWebServiceProductsException ex) {
            // Exception properties are important for diagnostics.
            System.out.println("Service Exception:");
            ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
            if(rhmd != null) {
                System.out.println("RequestId 2: "+rhmd.getRequestId());
                System.out.println("Timestamp: "+rhmd.getTimestamp());
            }
            System.out.println("Message: "+ex.getMessage());
            System.out.println("StatusCode: "+ex.getStatusCode());
            System.out.println("ErrorCode: "+ex.getErrorCode());
            System.out.println("ErrorType: "+ex.getErrorType());
            throw ex;
        }
    }

    /**
     *  Command line entry point.
     */
    public static void main(String[] args) {

        // Get a client connection.
        // Make sure you've set the variables in MarketplaceWebServiceProductsSampleConfig.
        MarketplaceWebServiceProductsClient client = MarketplaceWebServiceProductsSampleConfig.getClient();

        // Create a request.
        GetCompetitivePricingForSKURequest request = new GetCompetitivePricingForSKURequest();
        String sellerId = "example";
        request.setSellerId(sellerId);
        String mwsAuthToken = "example";
        request.setMWSAuthToken(mwsAuthToken);
        String marketplaceId = "example";
        request.setMarketplaceId(marketplaceId);
        SellerSKUListType sellerSKUList = new SellerSKUListType();
        request.setSellerSKUList(sellerSKUList);

        // Make the call.
        GetCompetitivePricingForSKUSample.invokeGetCompetitivePricingForSKU(client, request);

    }

}
