import java.sql.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JDBC_pagos {
    
    // Configuración de la conexión
    private static final String URL = "jdbc:mysql://localhost:3306/db_pagos";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Si tienes contraseña, cámbiala aquí
    
    // Método para obtener la conexión
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Error: Driver MySQL no encontrado");
            e.printStackTrace();
            throw new SQLException("Driver no encontrado");
        }
    }
    
    // ================ CREATE ================
    public static void insertarPago(int idFactura, Integer idMetodo, BigDecimal monto, 
                                     LocalDateTime fechaPago, String referencia) {
        String sql = "INSERT INTO pagos (id_factura, id_metodo, monto, fecha_pago, referencia) "
                   + "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, idFactura);
            if (idMetodo != null) {
                pstmt.setInt(2, idMetodo);
            } else {
                pstmt.setNull(2, Types.INTEGER);
            }
            pstmt.setBigDecimal(3, monto);
            pstmt.setTimestamp(4, Timestamp.valueOf(fechaPago));
            pstmt.setString(5, referencia);
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("✅ Pago insertado con ID: " + rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar pago");
            e.printStackTrace();
        }
    }
    
    // ================ READ (todos los pagos) ================
    public static void mostrarTodosLosPagos() {
        String sql = "SELECT * FROM pagos ORDER BY fecha_pago DESC";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n📋 LISTA DE TODOS LOS PAGOS:");
            System.out.println("================================================================");
            System.out.printf("%-8s %-12s %-10s %-12s %-20s %-20s\n", 
                            "ID_PAGO", "ID_FACTURA", "ID_METODO", "MONTO", "FECHA_PAGO", "REFERENCIA");
            System.out.println("----------------------------------------------------------------");
            
            while (rs.next()) {
                int idPago = rs.getInt("id_pago");
                int idFactura = rs.getInt("id_factura");
                Integer idMetodo = rs.getInt("id_metodo");
                if (rs.wasNull()) idMetodo = null;
                BigDecimal monto = rs.getBigDecimal("monto");
                Timestamp ts = rs.getTimestamp("fecha_pago");
                String fecha = (ts != null) ? ts.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "NULL";
                String referencia = rs.getString("referencia");
                
                System.out.printf("%-8d %-12d %-10s %-12.2f %-20s %-20s\n", 
                                idPago, idFactura, 
                                (idMetodo != null ? idMetodo : "NULL"), 
                                monto, fecha, referencia);
            }
            System.out.println("================================================================");
            
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener pagos");
            e.printStackTrace();
        }
    }
    
    // ================ READ (pagos por ID de factura) ================
    public static void mostrarPagosPorFactura(int idFactura) {
        String sql = "SELECT * FROM pagos WHERE id_factura = ? ORDER BY fecha_pago DESC";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idFactura);
            ResultSet rs = pstmt.executeQuery();
            
            System.out.println("\n📋 PAGOS DE LA FACTURA #" + idFactura + ":");
            System.out.println("----------------------------------------------------------------");
            
            boolean hayResultados = false;
            while (rs.next()) {
                hayResultados = true;
                System.out.println("  ID Pago: " + rs.getInt("id_pago") +
                                 " | Monto: $" + rs.getBigDecimal("monto") +
                                 " | Fecha: " + rs.getTimestamp("fecha_pago") +
                                 " | Ref: " + rs.getString("referencia"));
            }
            
            if (!hayResultados) {
                System.out.println("  No hay pagos para la factura #" + idFactura);
            }
            
        } catch (SQLException e) {
            System.out.println("❌ Error al buscar pagos por factura");
            e.printStackTrace();
        }
    }
    
    // ================ UPDATE ================
    public static void actualizarMontoPago(int idPago, BigDecimal nuevoMonto) {
        String sql = "UPDATE pagos SET monto = ? WHERE id_pago = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setBigDecimal(1, nuevoMonto);
            pstmt.setInt(2, idPago);
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("✅ Pago ID " + idPago + " actualizado a $" + nuevoMonto);
            } else {
                System.out.println("⚠️ No se encontró el pago con ID " + idPago);
            }
            
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar pago");
            e.printStackTrace();
        }
    }
    
    // ================ DELETE ================
    public static void eliminarPago(int idPago) {
        String sql = "DELETE FROM pagos WHERE id_pago = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idPago);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("✅ Pago ID " + idPago + " eliminado correctamente");
            } else {
                System.out.println("⚠️ No se encontró el pago con ID " + idPago);
            }
            
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar pago");
            e.printStackTrace();
        }
    }
    
    // ================ CONSULTAS ADICIONALES ================
    
    // Total recaudado
    public static void mostrarTotalRecaudado() {
        String sql = "SELECT SUM(monto) AS total FROM pagos";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                BigDecimal total = rs.getBigDecimal("total");
                System.out.println("💰 Total recaudado: $" + (total != null ? total : "0.00"));
            }
            
        } catch (SQLException e) {
            System.out.println("❌ Error al calcular total");
            e.printStackTrace();
        }
    }
    
    // Contar pagos por método
    public static void mostrarResumenPorMetodo() {
        String sql = "SELECT id_metodo, COUNT(*) AS cantidad, SUM(monto) AS total " +
                    "FROM pagos GROUP BY id_metodo ORDER BY id_metodo";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n📊 RESUMEN POR MÉTODO DE PAGO:");
            System.out.println("----------------------------------------");
            System.out.printf("%-12s %-10s %-12s\n", "ID_METODO", "CANTIDAD", "TOTAL");
            System.out.println("----------------------------------------");
            
            while (rs.next()) {
                Integer idMetodo = rs.getInt("id_metodo");
                if (rs.wasNull()) idMetodo = null;
                int cantidad = rs.getInt("cantidad");
                BigDecimal total = rs.getBigDecimal("total");
                
                System.out.printf("%-12s %-10d $%-11.2f\n", 
                                (idMetodo != null ? idMetodo : "NULL"), 
                                cantidad, total);
            }
            
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener resumen");
            e.printStackTrace();
        }
    }
    
    // ================ METODO MAIN PARA PROBAR ================
    public static void main(String[] args) {
        
        System.out.println("=== SISTEMA DE GESTIÓN DE PAGOS ===\n");
        
        // 1. Insertar un nuevo pago
        System.out.println("1. Insertando nuevo pago...");
        insertarPago(106, 1, new BigDecimal("99000.99"), LocalDateTime.now(), "TRX-NUEVO-001");
        
        // 2. Mostrar todos los pagos
        mostrarTodosLosPagos();
        
        // 3. Mostrar pagos de una factura específica
        mostrarPagosPorFactura(101);
        
        // 4. Actualizar un monto
        System.out.println("\n4. Actualizando monto del pago ID 1...");
        actualizarMontoPago(1, new BigDecimal("175000.00"));
        
        // 5. Mostrar total recaudado
        mostrarTotalRecaudado();
        
        // 6. Mostrar resumen por método de pago
        mostrarResumenPorMetodo();
        
        System.out.println("\n=== FIN DEL EJEMPLO ===");
    }
}