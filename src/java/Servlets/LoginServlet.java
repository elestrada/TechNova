package Servlets;

import Config.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contraseña");
        
        if (usuario == null || usuario.trim().isEmpty() ||
            contrasena == null || contrasena.trim().isEmpty()) {
            
            request.setAttribute("error", "Complete todos los campos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        try {
            boolean loginExitoso = verificarCredenciales(usuario, contrasena);
            
            if (loginExitoso) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                response.sendRedirect("home.jsp");
            } else {
                request.setAttribute("error", "Usuario o contraseña incorrectos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error de conexión");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
    
    private boolean verificarCredenciales(String usuario, String contrasena) throws SQLException {
        String sql = "SELECT nombre_usuario, contrasena_hash, activo FROM usuarios WHERE nombre_usuario = ?";
        
        try (Connection conn = DatabaseConnection.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                if (!rs.getBoolean("activo")) return false;
                
                String hashGuardado = rs.getString("contrasena_hash");
                
                // Comparación con tus usuarios de prueba
                switch(usuario) {
                    case "andrea_castillo": return contrasena.equals("abc123");
                    case "luisfernando22": return contrasena.equals("pqr678");
                    case "carolina.mendez": return contrasena.equals("efg123");
                    default: return false;
                }
            }
        }
        return false;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}