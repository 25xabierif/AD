package main;

import java.sql.Connection;
import java.sql.SQLException;

import conexiones.OracleDBConnection;

public class GestorDB {
    
    private static OracleDBConnection  conexion = new OracleDBConnection();

    public static void createDB(){
        // Para Oracle la sentencia "CREATE DATABASE" tal como en MySQL no es válida.
        // Aquí hacemos una comprobación segura de la conexión al servidor y mostramos información de la BD.
        try (Connection conn = conexion.getConnectionServer()) {
            if (conn == null) {
                System.err.println("No se pudo obtener conexión al servidor de BD.");
                return;
            }
            String product = conn.getMetaData().getDatabaseProductName();
            String version = conn.getMetaData().getDatabaseProductVersion();
            System.out.println("Conexión al servidor establecida con éxito.");
            System.out.println("Producto BD: " + product + " - " + version);

        } catch (SQLException e) {
            System.err.println("La comprobación de la BD ha fallado: "+e.getMessage());
        }

    }

}
