
package Clases;
import java.sql.*;
import javax.swing.*;

public class ConexionMySQL {
    
    Connection conect = null;
    
    public Connection ConexionMySQL()
    {
        try 
        {
         Class.forName("com.mysql.jdbc.Driver");
         conect = DriverManager.getConnection("jdbc:mysql://localhost/Cementera","root","Polo76K$");
         System.out.print("Conexion a Tienda exitosa");
        } catch (Exception error) {
         JOptionPane.showMessageDialog(null,"Error en la conexion: " + error);
         
        }
        return conect;
    }
    
}
