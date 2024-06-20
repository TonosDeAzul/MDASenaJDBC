package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {
    // Variables final para que su valor no cambie
    private final String baseDatos = "mdasena_db";
    private final String servidor = "jdbc:mysql://localhost:3306/" + baseDatos;
    private final String usuario = "root";
    private final String clave = "";
    
    public Connection conectar(){
        Connection cn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(servidor, usuario, clave);
        } catch (Exception e) {
            System.out.println("Error al conectar 1: " + e.getMessage());
        }
        return cn;
    }
    
}
