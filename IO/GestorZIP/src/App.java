import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner entrada = new Scanner(System.in);

        System.out.println("Introduce el nombre del archivoZIP que vamos a crear: ");
        String zipName = entrada.nextLine();
        entrada.nextLine();

        System.out.println("Cuántos archivos quiere añadir al ZIP?");
        int zipNum = entrada.nextInt();
        entrada.nextLine();

        ArrayList<String> archivos = new ArrayList<String>();

        /* for(int i = 0; i < zipNum ; i++){
            System.out.println("Introduce el nombre del fichero a añadir:");
            String nombre = entrada.nextLine();
            entrada.nextLine();
            archivos.add(nombre);
            System.out.println("");
        } */

        entrada.close();
    }
}
