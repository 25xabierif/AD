import java.util.Scanner;

public class UD2 {
    public static void main(String[] args) throws Exception {
        
        Scanner entrada = new Scanner(System.in);

        boolean seguir = true;
        while(seguir){
            System.out.println("Elija una opción: ");
            System.out.println("1. Intremento sueldo");
            System.out.println("2. Alumnado mayor de edad");
            System.out.println("3. Alumnado alto");
            System.out.println("4. Salir\n");
            int opcion = entrada.nextInt();

            switch(opcion){
                case 1 -> {
                    System.out.println("Introduce el sueldo del trabajador: ");
                    int sueldo = entrada.nextInt();
                    System.out.println("El nuevo salario será: "+incrementoSueldo(sueldo)+"\n");
                }
                case 2 -> {
                    int edades[] = new int[5];
                    int contador = 0;
                    for (int i = 0; i < edades.length; i++) {
                        System.out.println("Introduce una edad: ");
                        edades[i] = entrada.nextInt();
                        if(edades[i]>=18){
                            contador++;
                        }
                    }
                    System.out.println("Hay "+contador+" alumnos mayores de edad.\n");
                }
                case 3 -> {
                    int contador = 0;
                    int altos = 0;
                    float alturas[] = new float[5];
                    while(contador < alturas.length){
                        System.out.println("Introduce una altura: ");
                        alturas[contador] = entrada.nextFloat();
                        if(alturas[contador] > 1.75){
                            altos++;
                        }
                        contador++;
                    }
                    System.out.println("Hay "+altos+" alumnos que superan el 1,75.\n");
                }
                case 4 -> {
                    seguir = false;
                }
                default -> {
                    System.out.println("El número elegido para la opción debe ser uno de los contemplados.\n");
                }
            }

        }

        entrada.close();
    }

    //Definimos metodos
    public static int incrementoSueldo(int sueldo){

        if(sueldo<1000){
            sueldo = sueldo + (int)(sueldo*0.15);
        }else{
            sueldo = sueldo + (int)(sueldo*0.12);
        }

        return sueldo;
    }
}
