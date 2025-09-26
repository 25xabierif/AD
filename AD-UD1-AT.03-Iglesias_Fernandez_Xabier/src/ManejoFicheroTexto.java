import java.util.Scanner;

public class ManejoFicheroTexto {
    public static void main(String[] args){
        
        try (Scanner entrada = new Scanner(System.in)) {
            FicheroTexto fichero = new FicheroTexto("destino.txt");

            boolean seguir = true;
            int valor;
            while(seguir){
                //Menú de opciones
                System.out.println("1. Escribir en el fichero.");
                System.out.println("2. Leer fichero.");
                System.out.println("3. Salir.");
                //Elegimos opción
                valor = entrada.nextInt();
                
                switch (valor) {
                    case 1 -> {
                        System.out.println("Introduce un texto: ");
                        String texto = entrada.nextLine();
                        fichero.escribir(texto);
                    }
                    case 2 -> {
                        fichero.leer();
                    }
                    case 3 -> seguir = false;
                    default -> System.out.println("Introduce un número do 1 ó 3.");
                }
            }
        }
    }
}
