package com.biblioteca;

public class Main {
    public static void main(String[] args) {
        
        GestorLibros.createDB("Cousa");

        GestorLibros.dropT();

        GestorLibros.createT();

        Libro libro1 = new Libro("Olvidado Rey Gudú", "Ana María Matute", 1996, "9788423974993");
        Libro libro2 = new Libro("El nombre del viento", "Patrick Rothfuss", 2001, "9788423974000");
        Libro libro3 = new Libro("El temor de un hombre sabio", "Patrick Rothfuss", 2007, "9788423975044");
        Libro libro4 = new Libro("Las puertas de piedra", "Patrick Rothfuss", 2050, "9788423975001");
        Libro libro5 = new Libro("La historiadora", "Elizabeth Kostova", 1999, "9788423956998");
        Libro libro6 = new Libro("El señor de los anillos", "JRR Tolkien", 1954, "9788423956566");
        Libro libro7 = new Libro("Eragon", "Cristopher Paolini", 1999, "9788423956933");
        Libro libro8 = new Libro("La vida de Lazarillo de Tormes", "Anónimo", 1554, "9788423956777");

        

        GestorLibros.addLibro(libro1);
        GestorLibros.addLibro(libro2);
        GestorLibros.addLibro(libro3);
        GestorLibros.addLibro(libro4);
        GestorLibros.addLibro(libro5);
        GestorLibros.addLibro(libro6);
        GestorLibros.addLibro(libro7);
        GestorLibros.addLibro(libro8);

        GestorLibros.obtenerLibros();

        System.out.println("\n");

        GestorLibros.obtenerLibrosA("Elizabeth Kostova");

        System.out.println("\n");

        GestorLibros.obtenerLibrosFrom(2000);

        System.out.println("\n");

        GestorLibros.updateName("Olvidado Rey Gudú", "El cuento de la bicicleta");

        System.out.println("\n");

        GestorLibros.obtenerLibros();

    }
}