package com.biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorLibros {

    public static void createDB(String dBName){

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=root")) {

            String createDB = """
                    CREATE DATABASE IF NOT EXISTS bibliotecaXabierI
                        CHARACTER SET utf8mb4
                        COLLATE utf8mb4_general_ci;
                    """;

            String createUser = """
                    CREATE USER IF NOT EXISTS 'usuario'@'%' IDENTIFIED BY 'usuario123';
                    """;
            String grantPriv = """
                    GRANT ALL PRIVILEGES ON bibliotecaXabierI.* TO 'usuario'@'%';
                    """;
            String flushPriv = """
                    FLUSH PRIVILEGES;
                    """;

            String useDB = "use bibliotecaXabierI;";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createDB);
            stmt.executeUpdate(createUser);
            stmt.executeUpdate(grantPriv);
            stmt.executeUpdate(flushPriv);
            stmt.executeUpdate(useDB);

            //Comprobamos que a BD existe
            /* String sentencia = "SHOW DATABASES";

            ResultSet rs = stmt.executeQuery(sentencia);

            System.out.println("BD: ");
            while (rs.next()) {
                String nomeDB = rs.getString(1);
                System.out.println(" - "+nomeDB);
            } */


        } catch (SQLException e) {
            System.err.println("La creación de la BD ha fallado: "+e.getMessage());
        }

    }
    
    public static void createT(){

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=usuario&password=usuario123")) {
            

            String useDB = "use bibliotecaXabierI;";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(useDB);

            String createT = """
                    CREATE TABLE IF NOT EXISTS Libros (
                    Titulo varchar(255),
                    Autor varchar(255),
                    Anho_P int,
                    ISBN varchar(255) PRIMARY KEY)
                    """;
            stmt.executeUpdate(createT);

            //Comprobamos que existen las tablas
            /* String showT = "SHOW TABLES;";

            ResultSet rs = stmt.executeQuery(showT);
            while(rs.next()){
                String table = rs.getString(1);
                System.out.println("Tabla: "+table);
            } */
            
        } catch (SQLException e) {
            System.err.println("Error inesperado en conexion para creación de tabla: "+e.getMessage());
        }

    }

    public static void dropT(){

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=usuario&password=usuario123")) {
            
            String useDB = "use bibliotecaXabierI;";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(useDB);

            String dropT = "DROP TABLE Libros";
            stmt.executeUpdate(dropT);
            
        } catch (SQLException e) {
            System.err.println("Error inesperado en conexion para borrado de tabla: "+e.getMessage());
        }

    }

    public static void addLibro(Libro libro){

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=usuario&password=usuario123")) {
            
            String useDB = "use bibliotecaXabierI";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(useDB);

            String insertL = """
                    INSERT INTO Libros(Titulo, Autor, Anho_P, ISBN)
                    VALUES (?,?,?,?);
                    """;

            PreparedStatement pst = conn.prepareStatement(insertL);
            pst.setString(1, libro.getTitle());
            pst.setString(2, libro.getAuthor());
            pst.setInt(3, libro.getYearOfP());
            pst.setString(4, libro.getISBN());
            pst.executeUpdate();
            pst.close();

        } catch (SQLException e) {
            System.err.println("Error inesperado al añadir libro: "+e.getMessage());
        }

    }
}
