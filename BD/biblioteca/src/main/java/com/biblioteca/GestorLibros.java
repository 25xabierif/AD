package com.biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

import com.mysql.cj.protocol.Resultset;

import conexiones.DBConnection;
import conexiones.MySQLConnection;

public class GestorLibros {

    final static String DBNAME = ConfigLoader.get("mysql.db");

    public static void createDB(String dBName){

        MySQLConnection conexion = new MySQLConnection();

        try (Connection conn = conexion.getConnection()) {

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

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createDB);
            stmt.executeUpdate(createUser);
            stmt.executeUpdate(grantPriv);
            stmt.executeUpdate(flushPriv);

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

        MySQLConnection conexion = new MySQLConnection();

        try (Connection conn = conexion.getConnectionName(DBNAME)) {

            Statement stmt = conn.createStatement();

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

        MySQLConnection conexion = new MySQLConnection();

        try (Connection conn = conexion.getConnectionName(DBNAME)) {

            Statement stmt = conn.createStatement();

            String dropT = "DROP TABLE IF EXISTS Libros";
            stmt.executeUpdate(dropT);
            
        } catch (SQLException e) {
            System.err.println("Error inesperado en conexion para borrado de tabla: "+e.getMessage());
        }

    }

    public static void addLibro(Libro libro){

        MySQLConnection conexion = new MySQLConnection();

        try (Connection conn = conexion.getConnectionName(DBNAME)) {

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

    public static void obtenerLibros(){

        MySQLConnection conexion = new MySQLConnection();

        try (Connection conn = conexion.getConnectionName(DBNAME)) {
            
            String getL = """
                    SELECT * FROM Libros;
                    """;

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(getL);

            String libro;
            int iter;
            while (rs.next()) {
                libro = "";
                iter = rs.getMetaData().getColumnCount();
                for(int i = 1; i <= iter ; i++ ){
                    libro+=rs.getString(i)+(i==iter?".":", ");
                }
                System.out.println(libro);
            }

        } catch (SQLException e) {
            System.err.println("No se ha podido realizar el SELECT * FROM Libros: "+e.getMessage());
        }
    }

    public static void obtenerLibrosA(String autor){

        MySQLConnection conexion = new MySQLConnection();

        try (Connection conn = conexion.getConnectionName(DBNAME)) {


            String selecTAut = "SELECT * FROM Libros WHERE Autor = ?;";

            PreparedStatement pst = conn.prepareStatement(selecTAut);
            pst.setString(1, autor);
            pst.executeQuery();
            String libro;
            int iter;
            while(pst.getResultSet().next()){
                iter = pst.getResultSet().getMetaData().getColumnCount();
                libro = "";
                for(int i = 1; i <= iter ; i++ ){
                    libro+=pst.getResultSet().getString(i)+(i==iter?".":", ");
                }
                System.out.println(libro);
            }

            pst.close();

            
        } catch (SQLException e) {
            System.err.println("No se han podido filtrar los libros por autor: "+e.getMessage());
        }
    }

    public static void obtenerLibrosFrom(int anho){
        MySQLConnection conexion = new MySQLConnection();

        try (Connection conn = conexion.getConnectionName(DBNAME)) {


            String selecTAut = "SELECT * FROM Libros WHERE Anho_P >= ?;";

            PreparedStatement pst = conn.prepareStatement(selecTAut);
            pst.setInt(1, anho);
            pst.executeQuery();
            String consulta;
            int iter;
            while(pst.getResultSet().next()){
                iter = pst.getResultSet().getMetaData().getColumnCount();
                consulta = "";
                for(int i = 1; i <= iter ; i++ ){
                    consulta+=pst.getResultSet().getString(i)+(i==iter?".":", ");
                }
                System.out.println(consulta);
            }

            pst.close();

            
        } catch (SQLException e) {
            System.err.println("No se han podido filtrar los libros por año: "+e.getMessage());
        }
    }

    public static void updateName(String title, String newTitle){

        MySQLConnection conexion = new MySQLConnection();

        try (Connection conn = conexion.getConnectionName(DBNAME)) {

            String update = """
                    UPDATE Libros
                    SET Titulo = ?
                    WHERE Titulo = ?;
                    """;
            PreparedStatement ps = conn.prepareStatement(update);
            ps.setString(1, newTitle);
            ps.setString(2, title);
            ps.executeUpdate();
        
            System.out.println("El título se ha modificado con éxito.");
        
            ps.close();
            
        } catch (SQLException e) {
            System.err.println("No se ha podido actualizar el nombre: "+e.getMessage());
        }

    }

    public static void updateAuthor(String author, String newAuthor){

        MySQLConnection conexion = new MySQLConnection();

        try (Connection conn = conexion.getConnectionName(DBNAME)) {

            String update = """
                    UPDATE Libros
                    SET Autor = ?
                    WHERE Autor = ?;
                    """;
            PreparedStatement ps = conn.prepareStatement(update);
            ps.setString(1, newAuthor);
            ps.setString(2, author);
            ps.executeUpdate();
        
            System.out.println("El autor se ha modificado con éxito.");
        
            ps.close();
            
        } catch (SQLException e) {
            System.err.println("No se ha podido actualizar el nombre del autor: "+e.getMessage());
        }

    }

    public static void updateYearOfP(int yearOfP, int newYearOfP){

        MySQLConnection conexion = new MySQLConnection();

        try (Connection conn = conexion.getConnectionName(DBNAME)) {

            String update = """
                    UPDATE Libros
                    SET Anho_P = ?
                    WHERE Anho_P = ?;
                    """;
            PreparedStatement ps = conn.prepareStatement(update);
            ps.setInt(1, yearOfP);
            ps.setInt(2, newYearOfP);
            ps.executeUpdate();
        
            System.out.println("El año de publicación se ha modificado con éxito.");
        
            ps.close();
            
        } catch (SQLException e) {
            System.err.println("No se ha podido actualizar el año de publicación: "+e.getMessage());
        }

    }

    public static void deleteBook(String isbm){

        MySQLConnection conexion = new MySQLConnection();

        try (Connection conn = conexion.getConnectionName(DBNAME)) {

            String delete = """
                    DELETE FROM Libros
                    WHERE ISBM = ?;
                    """;
            PreparedStatement ps = conn.prepareStatement(delete);
            ps.setString(1, isbm);
            ps.executeUpdate();
        
            System.out.println("El libro se ha borrado con éxito.");
        
            ps.close();
            
        } catch (SQLException e) {
            System.err.println("No se ha podido borrar el registro: "+e.getMessage());
        }

    }

    public static void deleteBookTo(int year){

        MySQLConnection conexion = new MySQLConnection();

        try (Connection conn = conexion.getConnectionName(DBNAME)) {

            String delete = """
                    DELETE FROM Libros
                    WHERE Anho_P <= ?;
                    """;
            PreparedStatement ps = conn.prepareStatement(delete);
            ps.setInt(1, year);
            ps.executeUpdate();
        
            System.out.println("El libro se ha borrado con éxito.");
        
            ps.close();
            
        } catch (SQLException e) {
            System.err.println("No se ha podido borrar el registro: "+e.getMessage());
        }

    }

    public static void cleanLibros(){

        MySQLConnection conexion = new MySQLConnection();
        try (Connection conn = conexion.getConnectionName(DBNAME)) {
            
            String delete = """
                    DELETE FROM Libros;
                    """;

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(delete);

            System.out.println("El borrado de registros se ha realizado con éxito.");

        } catch (SQLException e) {
            System.err.println("El borrado de registros ha fallado: "+e.getMessage());
        }

    }
}
