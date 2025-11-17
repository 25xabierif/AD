package com.alumnado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexiones.MySQLConnection;

public class Alumnado {
    
    private int id;
    private String nome;
    private int edad;
    private String email;

    public Alumnado(String nome, int edad, String email){
        this.nome = nome;
        this.edad = edad;
        this.email = email;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "Alumnado --> id: " + getId() + ", nombre: " + getNome() + ", edad: " + getEdad()
                + ", email: " + getEmail() + ".";
    }

    public void añadirAlumno(){

        String insertInto = """
                INSERT INTO Alumnado (nome, edad, email)
                VALUES (?,?,?);
                """;

        try (Connection conn = new MySQLConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(insertInto)) {
           
            ps.setString(1, this.getNome());
            ps.setInt(2, this.getEdad());
            ps.setString(3, this.getEmail());
            ps.executeUpdate();
            System.out.println("\nAlumno agregado con éxito!");

        } catch (SQLException e) {
            System.err.println("El alumno "+this.getNome()+" no se ha podido registrar con éxito en la tabla: "+e.getMessage());
        }

    }

}
