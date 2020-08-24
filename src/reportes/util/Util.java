/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes.util;

/**
 *
 * @author jmendoza
 */
public class Util {
    
    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);  
    }

    public static String padLeft(String s, int n) {
        return String.format("%1$#" + n + "s", s);  
    }   
    
    public static boolean isWindows() {
 
        String os = System.getProperty("os.name").toLowerCase();
        // windows
        return (os.indexOf("win") >= 0);
 
    }
 
    public static boolean isMac() {

        String os = System.getProperty("os.name").toLowerCase();
        // Mac
        return (os.indexOf("mac") >= 0);

    }

    public static boolean isUnix() {

        String os = System.getProperty("os.name").toLowerCase();
        // linux or unix
        return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);

    }

    public static String cFixString(String cCad) {
        if (cCad == null) {
            cCad = "";
        }
        String cRet=cCad.trim();
        cRet = cRet.replaceAll("'", "");
        return cRet;
    }

    
}
