/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package reportes.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import reportes.Menu;
import reportes.util.QueryTool;
import reportes.util.TunnelSSHPostgres;

/**
 *
 * @author jmendoza
 */

public class DatabasePostgres {
    static String driver = "org.postgresql.Driver";
    public static Connection con = null;
    final Object lock = new Object();
    //static Database db = Database.getInstance();
    static JFrame frame;
    static String cUser;
    static String cClave;

    protected static final DatabasePostgres _INSTANCE = new DatabasePostgres();
    public static DatabasePostgres getInstance() { return _INSTANCE; }

    protected DatabasePostgres() {
        try {

            if ( con == null ) {
                Class.forName( driver ).newInstance();
                cUser = Menu.img[2];
                cClave = Menu.img[3];
                
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CFDi?loginTimeout=30&socketTimeout=30", cUser, cClave);
                Menu.lConnected = true;
            }
        }
        catch ( Exception e ) {
            // Database connect failed because it probably doesn't exist
            e.printStackTrace();
            Menu.lConnected = false;
            Menu.cMenError = e.getMessage().trim().toLowerCase();
            if (lFoundConnectionError(Menu.cMenError)) {
                JOptionPane.showMessageDialog(frame, "Sin Conexion a Internet !", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);            
            }
        }

        if ( con != null ) {
            System.out.println("Database connected");
        } else {
            System.out.println("Database NOT connected CFDI");
        }
    }

    private void createTables(String cSql) {
        execQuery(cSql);
        System.out.println("Database tables created");
    }

    public static boolean execQuery(String queryStr) {
        QueryTool query = null;
        Boolean lret = false;
        try {
            //if (DatabasePostgres.isConnectedCheck()) {
            query = new QueryTool(con);
            query.setQuery( queryStr );
            query.exec();
            lret = true;
            //} 
        }
        catch ( Exception e ) {
            e.printStackTrace();
            Menu.cMenError = e.getMessage().trim().toLowerCase();
            if (lFoundConnectionError(Menu.cMenError)) {
                JOptionPane.showMessageDialog(frame, "Sin Conexion a Internet !\n" + Menu.cMenError, "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);            
            }
            lret = false;
        }
        finally {
            query.close();
            query = null;
            System.gc();
        }
        return lret;
    }

    //public boolean isConnected() throws SQLException {
    public static boolean isConnected() {
        QueryTool query = null;
        if (Menu.lConnected) {
            try {
                TunnelSSHPostgres.main();                
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CFDi?loginTimeout=30&socketTimeout=30", cUser, cClave);
                Menu.lConnected = true;
            } catch ( Exception e ) {
                Menu.lConnected = false;
                Menu.cMenError = e.getMessage().trim().toLowerCase();
                if (DatabasePostgres.lFoundConnectionError(Menu.cMenError)) {
                    JOptionPane.showMessageDialog(frame, "Sin Conexion a Internet !\n" + Menu.cMenError, "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);            
                }
                
            }
        }
        return Menu.lConnected;
    }
    
    public static boolean isConnectedCheck() {
        return isConnectedCheck(true); // Default Halt the System, if an error append
    }
    
    public static boolean isConnectedCheck(boolean lHalt) {
        Connection con = null;
        Boolean lret = false;
        String url = "jdbc:postgresql://localhost:5432/CFDi?loginTimeout=30&socketTimeout=30";
        try {
            Class.forName( driver ).newInstance();
            //con = DriverManager.getConnection(Database.getURL(), Database.getUSERNAME(), Database.getCLAVE());
            con = DriverManager.getConnection(url, Menu.img[2], Menu.img[3]);
            lret = true;
        }
        catch ( Exception e ) {
            e.printStackTrace();
            Menu.cMenError = e.getMessage().trim().toLowerCase();
            if (lHalt) {
                JOptionPane.showMessageDialog(frame, "Sin Conexion a Internet !\n" + Menu.cMenError, "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);            
            }
            lret = false;
        }
        con = null;
        url = null;
        System.gc();
        return lret;
    }

    public static void disconnect() {
        try {
            Menu.lConnected = false;
            con.close();
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public static ResultSet getRS(String cSql) {
        QueryTool query = null;
        try {
            //if (DatabasePostgres.isConnectedCheck()) {
            query = new QueryTool(con);
            String req = cSql;
            PreparedStatement ps = query.setQuery( req );
            ResultSet rs = query.exec();
            if ( rs.next() )
                return (rs);
            //}
        }
        catch ( Exception e ) {
            e.printStackTrace();
            Menu.cMenError = e.getMessage().trim().toLowerCase();
            if (lFoundConnectionError(Menu.cMenError)) {
                JOptionPane.showMessageDialog(frame, "Sin Conexion a Internet !\n" + Menu.cMenError, "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);            
            }
        } 
        return null;
    }
    
    public static Boolean lFoundConnectionError(String cMenError) {
        Boolean lret=false;
        if ((cMenError.contains("i/o")) && (cMenError.contains("backend"))) {
            lret = true;
        } else {
            if ((cMenError.contains("connection")) && (cMenError.contains("refused"))) {
                lret = true;
            }   
        }
        return lret;
    }
    
/*
        DatabasePostgres.disconnect();
        TunnelSSHPostgres.disconnect();  
        Menu.lConnected = true;
        DatabasePostgres.isConnected();              
*/
    
}
