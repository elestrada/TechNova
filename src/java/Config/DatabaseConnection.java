package Config;  // ← Debe coincidir con el paquete

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/db_login";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";
    
    private static Connection conexion = null;
    
    public static Connection obtenerConexion() throws SQLException {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                System.out.println("✅ Conectado exitosamente a db_login");
            }
            return conexion;
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Error: No se encontró el driver de MySQL");
            throw new SQLException("Driver no encontrado");
        }
    }
    
    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("🔌 Conexión cerrada");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}