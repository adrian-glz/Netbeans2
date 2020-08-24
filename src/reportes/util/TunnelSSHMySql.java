/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//import com.jcraft.*;
package reportes.util;

import com.jcraft.jsch.*;
import java.util.Properties;
import reportes.Menu;

/**
 *
 * @author jmendoza
 */
public class TunnelSSHMySql {

    private static Session sessionMySql;
        
    public static void disconnect(){
        if (Menu.lsessionMySql) {
            if (sessionMySql.isConnected()) {
                Menu.lsessionMySql = false;
                sessionMySql.disconnect();
            }
        }
    }
    
    //public static void main(String[] arg) {
    public static void main() {
        
        // Local address and port
        String lhost = "localhost";
        String rhost="";
        int lport = 3306;
        
        // Remote address and port
        //if (Database.getftpURL()!=null) {
        
        rhost = "www0.sounds.mx";
        int rport = 3306;
        
        try {
            if (!Menu.lsessionMySql) {
                Menu.lsessionMySql = true;
                JSch jsch = new JSch();
                
                sessionMySql = jsch.getSession(Menu.img[0], rhost,7822);
                sessionMySql.setTimeout(60000); 
                sessionMySql.setServerAliveInterval(60000); 
                sessionMySql.setPassword(Menu.img[1]);

                Properties config = new Properties();
                config.put("StrictHostKeyChecking","no");
                sessionMySql.setConfig(config);
            }
            if (!sessionMySql.isConnected()) {
                sessionMySql.connect(60000);
                int assigned_port=sessionMySql.setPortForwardingL(lport, lhost, rport);
                System.out.println("localhost:"+assigned_port+"  ->  "+rhost+":"+rport);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
    }
}
