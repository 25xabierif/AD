package com.biblioteca;

public class Main {
    public static void main(String[] args) {
        
        GestorLibros.createDB("Cousa");

        GestorLibros.dropT();

        GestorLibros.createT();

        Libro libro1 = new Libro("Olvidado Rey Gudú", "Ana María Matute", 1996, "9788423974993");

        GestorLibros.addLibro(libro1);

    }
}