package Controlador;

import Modelo.DAOUsuario;
import Modelo.usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "svUsuario", urlPatterns = {"/svUsuario"})
public class svUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "verificar":
//                        System.out.println("Acción: " + accion);
                        verificar(request, response);
                        break;
                    case "cerrar":
                        cerrarSession(request, response);
                        break;
                    default:
                        response.sendRedirect("/ingreso.jsp");
                }
            } else {
                response.sendRedirect("/ingreso.jsp");
            }
        } catch (Exception e) {
            try {
                this.getServletConfig().getServletContext().getRequestDispatcher("/ingreso.jsp").forward(request, response);
            } catch (Exception er) {
                System.out.println("Error 3: " + er.getMessage());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void verificar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session;
        DAOUsuario dao;
        usuario usuario;

        // Pasar el objeto response a obtenerUsuario
        usuario = this.obtenerUsuario(request, response);

        // Si usuario es null, salir del método
        if (usuario == null) {
            return;
        }

        dao = new DAOUsuario();
        usuario = dao.identificar(usuario);

//        System.out.println("Ingreso");
//        System.out.println("Usuario: " + usuario.getId_rol_fk().getNombre_rol());
//        if(usuario.getId_rol_fk().getNombre_rol().equals("aprendiz")){
//            session = request.getSession();
//            this.getServletConfig().getServletContext().getRequestDispatcher("/views/inicio.jsp").forward(request, response);
//        }
        if (usuario != null) {
            String rolNombre = usuario.getId_rol_fk().getNombre_rol();
            switch (rolNombre) {
                case "instructor":
                    session = request.getSession();
                    session.setAttribute("instructor", usuario);
                    request.setAttribute("msje", "Bienvenido instructor");
                    response.sendRedirect("views/inicio.jsp");        
//                    this.getServletConfig().getServletContext().getRequestDispatcher("/views/inicio.jsp").forward(request, response);
                    break;
                case "aprendiz":
                    session = request.getSession();
                    session.setAttribute("aprendiz", usuario);
                    request.setAttribute("msje", "Bienvenido aprendiz");
                    response.sendRedirect("views/inicio.jsp");
//                    this.getServletConfig().getServletContext().getRequestDispatcher("/views/inicio.jsp").forward(request, response);
                    break;
                case "monitor":
                    session = request.getSession();
                    session.setAttribute("monitor", usuario);
                    request.setAttribute("msje", "Bienvenido monitor");
                    response.sendRedirect("views/inicio.jsp");
//                    this.getServletContext().getRequestDispatcher("/views/inicio.jsp").forward(request, response);
                    break;
                default:
                    System.out.println("Usuario: " + usuario.getId_rol_fk().getNombre_rol());
                    request.setAttribute("msje", "Credenciales incorrectas");
                    request.getRequestDispatcher("/ingreso.jsp").forward(request, response);
            }
        } else {
            System.out.println("Usuario no encontrado");
            request.getRequestDispatcher("/ingreso.jsp").forward(request, response);
        }

    }

    private void cerrarSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        session.setAttribute("usuario", null);
        session.invalidate();
        response.sendRedirect("views/ingreso.jsp");
    }

    private usuario obtenerUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        usuario usu = new usuario();

        String correo = request.getParameter("correoInstitucional");
        System.out.println("Correo: " + correo);

        String regexAprendiz = "\\b[A-Za-z0-9._%+-]+@soy\\.sena\\.edu\\.co\\b";
        Pattern pAprendiz = Pattern.compile(regexAprendiz);
        Matcher mAprendiz = pAprendiz.matcher(correo);

        if (!mAprendiz.matches()) {
            response.sendRedirect("ingreso.jsp");
            return null; // Return null to avoid further processing
        }

        usu.setCorreo_inst(correo);
        usu.setContrasena(request.getParameter("password"));
        return usu;
    }

}
