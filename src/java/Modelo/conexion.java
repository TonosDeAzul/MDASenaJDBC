package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {
    // Variables final para que su valor no cambie
    private final String baseDatos = "mdasena_db";
    private final String servidor = "jdbc:mysql://localhost/" + baseDatos;
    private final String usuario = "";
    private final String clave = "";
    
    public Connection conectar(){
        Connection cn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(servidor, usuario, clave);
        } catch (Exception e) {
            System.out.println("Error al conectar" + e.getMessage());
        }
        return cn;
    }
    
}
