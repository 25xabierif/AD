package com.aplicacion;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        try (Scanner entrada = new Scanner(System.in)) {
            boolean seguir = true;

            /* Dejamos definidas las variables que utilizaremos dentro del bucle while
             * para instanciar y registar los coches.
             */
            String matricula;
            String marca;
            String modelo;
            int kilometraje;
            Coche coche;

            
            while(seguir){
                //Generamos un menú por consola
                System.out.println();
                System.out.println("""
                Elija una de las siguientes opciones:
                    1. Mantenimiento
                    2. Añadir coche
                    3. Modificar coche
                    4. Eliminar coche
                    5. Crear copia de seguridad
                    6. Generar Json vehiculo
                    7. Salir
                """);

                System.out.print("Opcion: ");
                //Solicitamos la opción a elegir en el menú y acto seguido limpiamos el scanner
                int opcion = entrada.nextInt();
                entrada.nextLine();

                //Comprobamos que la opción sea una de las disponibles
                if(opcion>=1 && opcion<=7){
                    switch(opcion){
                        
                        case 1 -> {
                            GestorBuggysDB.createDB();
                            GestorBuggysDB.createTableCoches();
                        }
                        
                        case 2 -> {
                            //Solicitamos los datos para instanciar el coche y almacenarlo en su tabla
                            System.out.println("Introduce los datos del vehículo:\n");
                            System.out.print("-> Matrícula: ");
                            matricula = entrada.nextLine();

                            System.out.print("\n-> Marca: ");
                            marca = entrada.nextLine();

                            System.out.print("\n-> Modelo: ");
                            modelo = entrada.nextLine();

                            System.out.print("\n-> Kilometraje: ");
                            kilometraje = entrada.nextInt();

                            coche = new Coche(matricula, marca, modelo, kilometraje);
                            coche.añadirCoche();
                        }

                        case 3 -> {
                            //Solicitamos la matrícula del coche a modificar
                            System.out.println("Introduzca la matrícula del coche a modificar: ");
                            System.out.print("Matrícula -> ");
                            matricula = entrada.nextLine();
                            System.out.println("\nA continuación introduzca los campos a modificar: ");

                            System.out.print("\n-> Marca: ");
                            marca = entrada.nextLine();

                            System.out.print("\n-> Modelo: ");
                            modelo = entrada.nextLine();

                            System.out.print("\n-> Kilometraje: ");
                            kilometraje = entrada.nextInt();

                            GestorBuggysDB.alterTableCoches(matricula, marca, modelo, kilometraje);
                        }

                        case 4 -> {
                            //Solicitamos la matrícula del coche a eliminar
                            System.out.println("Introduzca la matrícula del coche a eliminar: ");
                            System.out.print("Matrícula -> ");
                            matricula = entrada.nextLine();
                            GestorBuggysDB.deleteCoche(matricula);
                        }

                        case 5 -> {
                            GestorBuggysDB.copiaSeguridad();
                        }

                        case 6 -> {
                            //Solicitamos la matrícula del coche a almarcenar
                            System.out.println("Introduzca la matrícula del coche que exportaremos a JSON: ");
                            System.out.print("Matrícula -> ");
                            matricula = entrada.nextLine();
                            GestorBuggysDB.generarJson(matricula);
                        }
                        
                        case 7 -> {
                            seguir = false;
                        }
                    }
                    
                }else{
                    System.out.println("La opción introducida no existe. Vuelva a introducir opción: ");
                }
            }
        }
    }
}
