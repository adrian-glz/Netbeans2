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
public class TunnelSSHPostgres {

    private static Session sessionPg;
        
    public static void disconnect(){
        if (Menu.lsessionPg) {
            if (sessionPg.isConnected()) {
                Menu.lsessionPg = false;
                sessionPg.disconnect();
            }
        }
    }
    
    //public static void main(String[] arg) {
    public static void main() {
        
        // Local address and port
        String lhost = "localhost";
        String rhost="";
        int lport = 5432;
        
        // Remote address and port
        //if (Database.getftpURL()!=null) {
        
        rhost = "cfdi.sounds.mx";
        int rport = 5432;
        
        try {
            if (!Menu.lsessionPg) {
                Menu.lsessionPg = true;
                JSch jsch = new JSch();
                
                sessionPg = jsch.getSession(Menu.img[0], rhost,7822);
                sessionPg.setTimeout(60000); 
                sessionPg.setServerAliveInterval(60000); 
                sessionPg.setPassword(Menu.img[1]);

                Properties config = new Properties();
                config.put("StrictHostKeyChecking","no");
                sessionPg.setConfig(config);
            }
            if (!sessionPg.isConnected()) {
                sessionPg.connect(60000);
                int assigned_port=sessionPg.setPortForwardingL(lport, lhost, rport);
                System.out.println("localhost:"+assigned_port+"  ->  "+rhost+":"+rport);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
    }
}
