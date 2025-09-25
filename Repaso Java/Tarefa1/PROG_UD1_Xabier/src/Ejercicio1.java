import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args){

        //Definimos una entrada de texto para hacer las pruebas
        Scanner entrada = new Scanner(System.in);

        //Primer método:
        System.out.println("Vamos a ejecutar el primer método.");
        System.out.println("Introduce un número con decimales: ");
        double double1 = entrada.nextDouble();
        System.out.println("Introduce otro número con decimales: ");
        double double2 = entrada.nextDouble();

        System.out.println("Devolvemos el módulo resultado de la división entre los dos números: ");
        System.out.println(truncar(double1,double2));

        //Segundo método
        System.out.println("Ejecutamos el segundo método.");
        System.out.println("Segundo ejercicio. Introduce una cadena de texto: ");
        String cadea = entrada.nextLine();
        System.out.println("Introduce un caracter: ");
        String cadea2 = entrada.nextLine();
        char caracter = cadea2.charAt(0);

        System.out.println("Comprobando si el caracter introducido está en la cadena: "+existeChar(cadea, caracter));

        //Tercer método
        System.out.println("Ejecutamos el tercer método.");
        System.out.println("Introduce un número con decimales: ");
        double double3 = entrada.nextDouble();
        System.out.println("Introduce otro número con decimales: ");
        double double4 = entrada.nextDouble();
        metodo3(double3, double4);

        //Cerramos la entrada de texto
        entrada.close();
    }

    public static double truncar(double a, double b){
        int c = (int) a;
        int d = (int) b;

        return c%d;
    }

    public static boolean existeChar(String cadena, char a){
        boolean siExiste = false;

        for (int i = 0; i < cadena.length(); i++) {
            if(cadena.charAt(i) == a){
                siExiste = true;
            }
        }
        return siExiste;
    }

    public static void metodo3(double a, double b){
        float suma = (float) a + (float) b;

        String numeroSuma = String.valueOf(suma);
        String [] partes = numeroSuma.split("\\.");

        System.out.println("Resultado suma: "+suma);
        System.out.println("Parte entera del número: "+partes[0]);
        System.out.println("Parte decimal del número: "+partes[1]);
    }
}