package com.aplication;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import conexiones.DBConnection;
import conexiones.MySQLConnection;

public class Main {
    public static void main(String[] args) {

        MySQLConnection conexion = new MySQLConnection();

        testConnection(conexion, "MySQL:testdb");

        String consulta = "CREATE TABLE tabla_proba(dato1 INT, dato2 VARCHAR(50), id INT AUTO_INCREMENT PRIMARY KEY);";

        try {
            Connection conecction = conexion.getConnection();
            Statement statement = conecction.createStatement();
        } catch (SQLException e) {
            System.err.println("A sentencia non se puido aplicar: "+e.getMessage());
        }


    }

    private static void testConnection(DBConnection dbConnection, String dbName){
        System.out.println("Probando conexión con "+dbName+"...");
        try (Connection conn = dbConnection.getConnection()) {
            if(conn != null){
                System.out.println("Conexión establecida con "+dbName+"\n");
            }else{
                System.out.println("No se pudo establecer la conexión con "+dbName+"\n");
            }
        } catch (Exception e) {
            System.err.println("Error inesperado: "+e.getMessage());
        }
    }
}