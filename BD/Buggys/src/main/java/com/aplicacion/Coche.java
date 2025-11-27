package com.aplicacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.conexiones.SQLConnection;

public class Coche{
    
    /* Creamos la clase coche para estructurar y almacenar los distintos vehículos que
    iremos instanciando en nuestro programa y BD. */

    private static SQLConnection conexion = new SQLConnection();

    private String matricula;
    private String marca;
    private String modelo;
    private int kilometraje;

    public Coche(String matricula, String marca, String modelo, int kilometraje){
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
    }

    //Creamos getters and setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    //Sobreescribimos el método toString() por si fuera necesario
    @Override
    public String toString() {
        return "Coche --> \nMatrícula = " + getMatricula() + ",\nmarca = " + getMarca() + ",\nmodelo = " + getModelo()
                + ",\nkilometraje = " + getKilometraje() +".";
    }

    public void añadirCoche(){

        String insertCoche = """
                INSERT INTO `Coches`(`matricula`, `marca`, `modelo`, `kilometraje`) 
                VALUES (?,?,?,?);
                """;

        try (Connection conn = conexion.getConnection();
        PreparedStatement ps = conn.prepareStatement(insertCoche)) {
            
            ps.setString(1, getMatricula());
            ps.setString(2, getMarca());
            ps.setString(3, getModelo());
            ps.setInt(4, getKilometraje());
            ps.executeUpdate();
            ps.close();
            System.out.println("\nEl coche ha sido registrado con éxito!");

        } catch (SQLException e) {
            System.err.println("No se ha podido registar el coche: "+e.getMessage());
        }

    }

}
