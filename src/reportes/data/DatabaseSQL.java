/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package reportes.data;
import java.sql.*;
import reportes.util.QueryTool;
//import simo.data.DatabasePostgresAccesos;

/**
 *
 * @author jmendoza
 */

public class DatabaseSQL {
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static Connection con = null;
    final Object lock = new Object();

    protected static final DatabaseSQL _INSTANCE = new DatabaseSQL();
    public static DatabaseSQL getInstance() { return _INSTANCE; }

    protected DatabaseSQL() {
        try {

            if ( con == null ) {
                //System.setProperty("derby.database.forceDatabaseLock", "true");
                Class.forName( driver ).newInstance();
                //con = DriverManager.getConnection(url, usr, pwd );
                //con = DriverManager.getConnection("jdbc:sqlserver://192.168.0.80:1169;databaseName=CML", "usounds", "madljda");
                con = DriverManager.getConnection("jdbc:sqlserver://192.168.1.80:56704;databaseName=CML", "usounds", "madljda");
            }
        }
        catch ( Exception e ) {
            // Database connect failed because it probably doesn't exist
            e.printStackTrace();
            System.exit(0);
        }

        if ( con != null )
            System.out.println("Database connected");
        else
            System.out.println("Database NOT connected");
    }

    private void createTables(String cSql) {
        execQuery(cSql);
        System.out.println("Database tables created");
    }

    public static boolean execQuery(String queryStr) {
        QueryTool query = null;
        try {
            query = new QueryTool(con);
            query.setQuery( queryStr );
            query.exec();
        }
        catch ( Exception e ) {
            e.printStackTrace();
            return false;
        }
        finally {
            query.close();
        }
        return true;
    }

    public boolean isConnected() {
        if ( con != null )
            return true;
        return false;
    }

    public void disconnect() {
        try {
            // Close the active connection
            if ( isConnected() ) {
                //con.commit();
                con.close();
            }

            // Shutdown Derby - the exception contains the shutdown message
            try {
                //con = DriverManager.getConnection( "jdbc:postgresql:;shutdown=true", usr, pwd );
                con = DriverManager.getConnection( "jdbc:sqlserver:;shutdown=true",  "usounds", "madljda" );
            }
            catch ( SQLException sqle ) {
                System.out.println(sqle.getMessage());
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    static public ResultSet getRS(String cSql) {
        QueryTool query = null;
        try {
            query = new QueryTool(con);
            String req = cSql;
            PreparedStatement ps = query.setQuery( req );
            ResultSet rs = query.exec();
            if ( rs.next() )
                return (rs);
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        finally {
            //query.close();
        }
        return null;
    }


}
