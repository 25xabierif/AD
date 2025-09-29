import java.io.File;
import java.util.Scanner;

public class ManejoFicheroTexto {
    public static void main(String[] args){
        
        try (Scanner entrada = new Scanner(System.in)) {
            File arch = new File("src\\", "destino.txt");
            FicheroTexto fichero = new FicheroTexto(arch);

            boolean seguir = true;
            int valor;
            while(seguir){
                //Menú de opciones
                System.out.println("Elije una opción: ");
                System.out.println("1. Escribir en el fichero.");
                System.out.println("2. Leer fichero.");
                System.out.println("3. Salir.");
                System.out.print("Opción: ");
                //Elegimos opción
                valor = entrada.nextInt();
                entrada.nextLine();
                
                switch (valor) {
                    case 1 -> {
                        System.out.println("\nIntroduce un texto: ");
                        String texto = entrada.nextLine();
                        fichero.escribir(texto);
                        System.out.println("");
                    }
                    case 2 -> {
                        System.out.println("");
                        fichero.leer();
                        System.out.println("");
                    }
                    case 3 -> seguir = false;
                    default -> System.out.println("Introduce un número do 1 ó 3.");
                }
            }
        }
    }
}
