package Modelo;

public class comprobar {
    public static void main(String[] args){
        conexion c = new conexion();
        if(c.conectar() != null){
            System.out.println("Conexión es correcta");
        } else{
            System.out.println("Conexión errónea");
        }
    }
}
