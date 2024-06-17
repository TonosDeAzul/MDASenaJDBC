package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAOUsuario extends conexion {
    
    public usuario identificar(usuario user) throws Exception{
        usuario usu = null;
        conexion con;
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT u.id_usuario, r.nombre_rol FROM tb_usuario u \n"
                     + "INNER JOIN tb_rol r on u.id_rol_fk = r.id_rol " 
                     + "WHERE u.correo_inst =  '"+ user.getCorreo_inst() +"' "
                     + "AND u.contrasena = '"+ user.getContrasena() +"' ";
        con = new conexion();
        try {
            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            if( rs.next() == true ){
                usu = new usuario();
                usu.setId_usuario(rs.getInt("id_usuario"));
                usu.setContrasena(user.getCorreo_inst());
                usu.setId_rol_fk(new rol());
                usu.getId_rol_fk().setNombre_rol(rs.getString("nombre_rol"));
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            if(rs != null && rs.isClosed() == false){
                rs.close();
            }
            rs = null;
            if(st != null && st.isClosed() == false){
                st.close();
            }
            st = null;
        }
    }
    
}
