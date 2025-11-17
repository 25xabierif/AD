package com.alumnado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import conexiones.MySQLConnection;

public class GestorDAM2 {


    public static void createDB(){
        try (Connection conn = new MySQLConnection().getConnectionServer();
        Statement stmt = conn.createStatement()) {
            
            String createDB = """
                    CREATE DATABASE IF NOT EXISTS dam2
                        CHARACTER SET utf8mb4
                        COLLATE utf8mb4_general_ci;
                    """;
            
            stmt.executeUpdate(createDB);

            System.out.println("Base de datos DAM2 creada satisfactoriamente.");
  
        } catch (SQLException e) {
            System.err.println("La creación de la BD ha fallado: "+e.getMessage());
        }

    }

    public static void createTableAlumnado(){

        try(Connection conn = new MySQLConnection().getConnection();
        Statement stmt = conn.createStatement()){

            String createT = """
                    CREATE TABLE IF NOT EXISTS `Alumnado` (
                    `id` INT NOT NULL AUTO_INCREMENT ,
                    `nome` VARCHAR(40) NOT NULL ,
                    `edad` TINYINT NOT NULL ,
                    `email` VARCHAR(60) NOT NULL ,
                    PRIMARY KEY (`id`)
                    );
                    """;
            
            stmt.executeUpdate(createT);
            System.out.println("Tabla Alumnado creada de manera satisfactoria!");

        } catch (SQLException e){
            System.err.println("La creación de la tabla Alumnado ha fallado: "+e.getMessage());
        }

    }

    public static void selectAlumnos(){

        try (Connection conn = new MySQLConnection().getConnection();
        Statement stmt = conn.createStatement()) {
           
            String selectA = "SELECT * FROM Alumnado;";
            ResultSet rs = stmt.executeQuery(selectA);

            String consulta = "";
            System.out.println("========================");
            while(rs.next()){
                consulta = rs.getInt(1)+" || "+rs.getString(2)+" || "+rs.getInt(3)+" || "+rs.getString(4);
                System.out.println(consulta);
            }
            System.out.println("========================");

        } catch (SQLException e) {
            System.err.println("El select de la tabla Alumnado no se ha realizado con éxito: "+e.getMessage());
        }

    }

    public static void modificarAlumno(int id, String nome, int edad, String email){
        String updateT = "UPDATE Alumnado SET nome = ?, edad = ?, email = ? WHERE id = ?;";

        try (Connection conn = new MySQLConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(updateT)) {
            
            ps.setString(1, nome);
            ps.setInt(2, edad);
            ps.setString(3, email);
            ps.setInt(4, id);
            ps.executeUpdate();
            System.out.println("El registro ha sido actualizado con éxito!");

        } catch (SQLException e) {
            System.err.println("No se ha podido modificar el registro: "+e.getMessage());
        }
    }

    public static void eliminarAlumno(int id){

        String deleteA = "DELETE FROM Alumnado WHERE id = ?;";
        try (Connection conn = new MySQLConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(deleteA)) {
           
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Alumno eliminado con éxito.");

        } catch (SQLException e) {
            System.err.println("El alumno no se ha podido eliminar con éxito: "+e.getMessage());
        }
    }

}

/*
 * Alumnado:
 * 
 * id
 * nome
 * email
 * 
 */