
package Clases;
import java.sql.*;
import javax.swing.*;

public class ConexionMySQL {
    
    Connection conect = null;
    
    //static String scontra = JOptionPane.showInputDialog(null, "Ingreasa la contraseña");
    //int a = 0;
    
    
    
    public Connection ConexionMySQL()
    {
        try 
        {
         Class.forName("com.mysql.jdbc.Driver");
         conect = DriverManager.getConnection("jdbc:mysql://localhost/Cementera","root","brilelo"
                 + "");
         System.out.print("Conexion a Tienda exitosa");
        } catch (Exception error) {
         JOptionPane.showMessageDialog(null,"Error en la conexion: " + error);
         
        }
        return conect;
    }
    
}
