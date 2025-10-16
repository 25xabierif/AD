package ex3;

public class ManejoFicherosBinarios {
    public static void main(String[] args){
        
        FicheroBinario archivo = new FicheroBinario("origen.bin");
        FicheroBinario salida = new FicheroBinario("salida.bin");

        archivo.escribir("ESTE ES EL TEXTO DE ORIGEN");

        archivo.leer();

        System.out.println();

        archivo.copiar(salida);

        System.out.println();
        salida.leer();

    }
}
