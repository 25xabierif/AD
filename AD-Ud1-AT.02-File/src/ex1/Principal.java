package ex1;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws Exception {
        
        Scanner entrada = new Scanner(System.in);

        System.out.println("Introduce un formato válido de ruta: ");
        String ruta = entrada.nextLine();
        
        File directorio = new File(ruta);

        System.out.println("Escribe una extensión válida: ");
        String extension = entrada.nextLine();

        Filtrar.filtrar(directorio.getAbsolutePath(), extension);

        FilenameFilter filtro = new FiltrarNombre(extension);

        String[] lista = directorio.list(filtro);

        if(lista!=null && lista.length>0){
            for (String nome : lista) {
                System.out.println(nome);
            }
        }

        entrada.close();

    }
}
