package Modelo;

public class usuario {
    private int id_usuario;
    private String correo_inst;
    private String contrasena;
    private rol id_rol_fk;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getCorreo_inst() {
        return correo_inst;
    }

    public void setCorreo_inst(String correo_inst) {
        this.correo_inst = correo_inst;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public rol getId_rol_fk() {
        return id_rol_fk;
    }

    public void setId_rol_fk(rol id_rol_fk) {
        this.id_rol_fk = id_rol_fk;
    }
    
}
