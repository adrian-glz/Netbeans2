/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package reportes.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import reportes.Menu;
import reportes.util.QueryTool;
import reportes.util.TunnelSSHMySql;

/**
 *
 * @author jmendoza
 */

public class DatabaseMySql {
    static String driver = "com.mysql.jdbc.Driver";
    public static Connection con = null;
    final Object lock = new Object();
    //static Database db = Database.getInstance();
    static JFrame frame;
    static String cUser;
    static String cClave;

    protected static final DatabaseMySql _INSTANCE = new DatabaseMySql();
    public static DatabaseMySql getInstance() { return _INSTANCE; }

    protected DatabaseMySql() {
        try {

            if ( con == null ) {
                Class.forName( driver ).newInstance();
                cUser = Menu.img[4];
                cClave = Menu.img[5];                
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eCML", cUser, cClave);
                Menu.lConnectedMySql = true;
            }
        }
        catch ( Exception e ) {
            // Database connect failed because it probably doesn't exist
            e.printStackTrace();
            Menu.lConnectedMySql = false;
            Menu.cMenError = e.getMessage().trim().toLowerCase();
            if (lFoundConnectionError(Menu.cMenError)) {
                JOptionPane.showMessageDialog(frame, "Sin Conexion a Internet !", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);            
            }
        }

        if ( con != null ) {
            System.out.println("Database connected");
        } else {
            System.out.println("Database NOT connected");
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
        if (Menu.lConnectedMySql) {
            try {
                TunnelSSHMySql.main();                
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eCML", cUser, cClave);
                Menu.lConnectedMySql = true;
            } catch ( Exception e ) {
                Menu.lConnectedMySql = false;
                Menu.cMenError = e.getMessage().trim().toLowerCase();
                if (DatabaseMySql.lFoundConnectionError(Menu.cMenError)) {
                    JOptionPane.showMessageDialog(frame, "Sin Conexion a Internet !\n" + Menu.cMenError, "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);            
                }
                
            }
        }
        return Menu.lConnectedMySql;
    }
    
    public static boolean isConnectedCheck() {
        return isConnectedCheck(true); // Default Halt the System, if an error append
    }
    
    public static boolean isConnectedCheck(boolean lHalt) {
        Connection con = null;
        Boolean lret = false;
        String url = "jdbc:mysql://localhost:3306/eCML";
        try {
            Class.forName( driver ).newInstance();
            //con = DriverManager.getConnection(Database.getURL(), Database.getUSERNAME(), Database.getCLAVE());
            con = DriverManager.getConnection(url, Menu.img[4], Menu.img[5]);
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
            Menu.lConnectedMySql = false;
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
        Menu.lConnectedMySql = true;
        DatabasePostgres.isConnected();              
*/
    
}
