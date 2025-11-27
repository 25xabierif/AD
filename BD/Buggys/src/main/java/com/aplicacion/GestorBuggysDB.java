package com.aplicacion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.conexiones.SQLConnection;
import com.google.gson.Gson;

public class GestorBuggysDB {

    //En esta clase realizaremos la creación de la base de datos Buggys

    private static final SQLConnection CONEXION = new SQLConnection();

    public static void createDB(){


        try (Connection conn = CONEXION.getConnectionServer();
        Statement stmt = conn.createStatement();) {

            String createDB = """
                    CREATE DATABASE IF NOT EXISTS Buggys;
                    """;
            
            stmt.executeUpdate(createDB);
            stmt.close();
            System.out.println("\nLa base de datos Buggys se ha creado con éxito.");
            
        } catch (SQLException e) {
            System.err.println("No se ha podido crear la BD: "+e.getMessage());
        }

    }

    //===============================================================
    //Creamos un método que permita eliminar la DB para hacer pruebas
    //===============================================================
    public static void dropDB(){

        try (Connection conn = CONEXION.getConnectionServer();
            Statement stmt = conn.createStatement();) {

            String dropDB = """
                    DROP DATABASE `Buggys`;
                    """;
            
            stmt.executeUpdate(dropDB);
            stmt.close();
            System.out.println("\nLa base de datos ha sido eliminada con éxito.");

        } catch (SQLException e) {
            System.err.println("No se ha podido eliminar la BD: "+e.getMessage());
        }

    }
    //============================================================
    //Aquí añadimos los métodos para crear la estructura de tablas
    //============================================================
    public static void createTableCoches(){

        try (Connection conn = CONEXION.getConnection();
        Statement stmt = conn.createStatement()) {
            
            String createTableCoches = """
                    CREATE TABLE IF NOT EXISTS `Coches` (`matricula` VARCHAR(7) NOT NULL,
                    `marca` VARCHAR(15) NOT NULL,
                    `modelo` VARCHAR(15) NOT NULL,
                    `kilometraje` INT NOT NULL,
                    PRIMARY KEY (`matricula`)); 
                    """;

            stmt.executeUpdate(createTableCoches);
            stmt.close();
            System.out.println("\nLa tabla coches se ha creado con éxito!");

        } catch (SQLException e) {
            System.err.println("No se ha podido crear la tabla coches: "+e.getMessage());
        }

    }

    //=============================================================
    //Método para eliminar la tabla, con finalidad de hacer pruebas
    //=============================================================
    public static void dropTableCoches(){

        try (Connection conn = CONEXION.getConnection();
        Statement stmt = conn.createStatement()) {
            
            String dropTableCoches = """
                    DROP TABLE IF EXISTS Coches;
                    """;

            stmt.executeUpdate(dropTableCoches);
            stmt.close();
            System.out.println("\nLa tabla coches se ha eliminado con éxito!");

        } catch (SQLException e) {
            System.err.println("No se ha podido crear la tabla coches: "+e.getMessage());
        }
    }

    //===================================================================
    //Creamos los métodos para alterar y eliminar registros de las tablas
    //===================================================================

    //Modificar coches
    public static void alterTableCoches(String matricula, String marca, String modelo, int kilometraje){
       
        /* Voy a poner una única opción de hacer el Update de momento.
         * La idea que tengo es utilizar el array para poder modificar los campos que 
         * el usuario elija desde la consola.
         */
        String updateCoche = """
                UPDATE `Coches` SET `marca` = ?,
                `modelo` = ?, `kilometraje` = ?
                WHERE `matricula` = ?
                """;
        
        try (Connection conn = CONEXION.getConnection();
        PreparedStatement ps = conn.prepareStatement(updateCoche)) {

            ps.setString(1, marca);
            ps.setString(2, modelo);
            ps.setInt(3, kilometraje);
            ps.setString(4, matricula);
            ps.executeUpdate();
            System.out.println("\nLa modificación del coche ha sido realizada con éxito.");

            
        } catch (SQLException e) {
            System.err.println("\nNo se han podido modificar los datos de coche.");
        }

    }

    //Eliminar coches
    public static void deleteCoche(String matricula){
        String insertCoche = """
                DELETE FROM `Coches` WHERE `matricula` = ?;
                """;

        try (Connection conn = CONEXION.getConnection();
        PreparedStatement ps = conn.prepareStatement(insertCoche)) {
            
            ps.setString(1, matricula);
            ps.executeUpdate();
            ps.close();
            System.out.println("\nEl coche ha sido eliminado con éxito!");

        } catch (SQLException e) {
            System.err.println("\nNo se ha podido registar el coche: "+e.getMessage());
        }
    }

    //=======================================================================
    //Método para generar el volcado de coches de la BD al archivo buggys.dat
    //=======================================================================
    public static void copiaSeguridad(){

        Path fichero = Paths.get("buggys.dat");
        File archivo = fichero.toFile();
        
        if(!archivo.exists()){
            try  {
                archivo.createNewFile();
            } catch (IOException e) {
                System.err.println("No se ha podido crear el archivo: "+e.getMessage());
            }
        }

        /* Instanciamos todos los recursos que vamos a utilizar para el volcado de datos 
         * al nuevo archivo "buggys.dat"
        */
        try (Connection conn = CONEXION.getConnection();
        Statement stmt = conn.createStatement();
        FileWriter fr = new FileWriter(archivo);
        BufferedWriter br = new BufferedWriter(fr)) {
            
            String select = "SELECT * FROM Coches";
            ResultSet rs = stmt.executeQuery(select);

            while(rs.next()){
                String escribir = "Matricula: "+rs.getString(1)+", marca: "+rs.getString(2)+", modelo: "+rs.getString(3)+", kilometraje: "+rs.getInt(4)+"\n";
                br.write(escribir);
            }

            System.out.println("\nEl volcado de datos ha finalizado con éxito!");

        } catch (SQLException e) {
            System.err.println("El acceso a datos de la BD ha fallado: "+e.getMessage());
        } catch (IOException e) {
            System.err.println("El volcado de datos al archivo ha fallado: "+e.getMessage());
        }

    }

    //========================
    //MÉTODO para generar JSON
    //========================
    public static void generarJson(String matricula){

        String selectMatricula = "SELECT * FROM Coches WHERE matricula = ?";

        //Instanciamos las clases que vamos a utilizar para exportar el JSON
        try (Connection conn = CONEXION.getConnection();
        PreparedStatement ps = conn.prepareStatement(selectMatricula)){

            //Creamos un objeto coche para poder utilizar la clase Gson
            ps.setString(1, matricula);
            ResultSet rs = ps.executeQuery();
            Coche coche = null;
            while(rs.next()){
                if(rs.getString(1)!=null){
                    coche = new Coche(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                }
            }

            //Comprobamos que el coche tiene registro en la BD. En caso afirmativo generamos el JSON.
            if(coche != null){
                Gson archivoJson = new Gson();
                String jsonString = archivoJson.toJson(coche);

                File archivo = new File(matricula+".json");

                try (FileWriter fw = new FileWriter(archivo);
                BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write(jsonString);
                } catch (IOException e) {
                    System.err.println("No se ha podido generar el archivo .json: "+e.getMessage());
                }
                System.out.println("\n El archivo .JSON ha sido creado con éxito!");
            }else{
                System.out.println("\n Escriba una matrícula existente.");
            }

        } catch (SQLException e) {
            System.err.println("No se ha podido realizar el volcado al archivo JSON: "+e.getMessage());
        } 
    }
}