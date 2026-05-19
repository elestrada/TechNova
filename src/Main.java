import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Probando conexión a la base de datos ===\n");
        
        try {
            Connection conexion = DatabaseConnection.obtenerConexion();
            
            if (conexion != null) {
                System.out.println("¡Conexión exitosa! La base de datos db_pagos está funcionando.");
                System.out.println("Tu proyecto TechNova puede trabajar con los pagos.");
            }
            
            DatabaseConnection.cerrarConexion();
            
        } catch (Exception e) {
            System.out.println("❌ Error al conectar: " + e.getMessage());
            e.printStackTrace();
        }
    }
}