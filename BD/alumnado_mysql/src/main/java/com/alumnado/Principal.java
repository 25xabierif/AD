package com.alumnado;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        boolean seguir = true;
        int option = 0;
        Alumnado alumno = null;
        String nombre = "";
        int id = 0;
        int edad = 0;
        String email = "";

        try (Scanner entrada = new Scanner(System.in)) {
            
            while(seguir){

                System.out.println("""
                Elija una de las siguientes opciones: 

                1. Mantenimiento.
                2. Añadir alumno.
                3. Comprobar alumnos.
                4. Modificar alumno.
                5. Eliminar alumno.
                6. Salir.
                """);

                System.out.print("\nOpción: ");
                option = Integer.parseInt(entrada.nextLine());

                switch (option) {
                    case 1 -> {

                        GestorDAM2.createDB();
                        GestorDAM2.createTableAlumnado();

                    }
                    case 2 -> {
                        System.out.print("\nNombre: ");
                        nombre = entrada.nextLine();

                        System.out.print("\nEdad: ");
                        edad = Integer.parseInt(entrada.nextLine());

                        System.out.print("\nEmail: ");
                        email = entrada.nextLine();

                        alumno = new Alumnado(nombre, edad, email);
                        
                        alumno.añadirAlumno();
                        id++;
                        alumno.setId(id);

                        System.out.println("Registrado satisfactoriamente: \n"+alumno.toString());
                    }
                    case 3 -> {
                        GestorDAM2.selectAlumnos();
                    }
                    case 4 -> {
                        System.out.println("Introduzca el id del alumno a modificar: ");
                        System.out.print("Id: ");
                        id = Integer.parseInt(entrada.nextLine());
                        
                        System.out.println("Introduzca los nuevos valores: ");
                        System.out.print("\nNombre: ");
                        nombre = entrada.nextLine();

                        System.out.print("\nEdad: ");
                        edad = Integer.parseInt(entrada.nextLine());

                        System.out.print("\nEmail: ");
                        email = entrada.nextLine();

                        GestorDAM2.modificarAlumno(id, nombre, edad, email);
                    }
                    case 5 -> {
                        System.out.println("Introduzca el id del alumno a eliminar: ");
                        System.out.print("Id: ");
                        id = Integer.parseInt(entrada.nextLine());
                        GestorDAM2.eliminarAlumno(id);
                    }
                    case 6 -> {
                        seguir = false;
                    }
                }

            }

        } catch (Exception e) {
            System.err.println("Ha habido algún problema con el uso del scanner: "+e.getMessage());
        }

    }



}


/*
 * Alumnado:
 * 
 * id
 * nome
 * edad
 * email
 * 
 */