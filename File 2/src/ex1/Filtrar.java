package ex1;
import java.io.File;

public class Filtrar {
    
    //Listar archivos con X extensión
    static void filtrar(String ruta, String extension){
        
        File directorio = new File(ruta);
        
        File[] lista = directorio.listFiles();

        if(directorio.exists()){
            if(lista != null && lista.length > 0){
                for (File carpeta : lista) {
                    if(carpeta.isDirectory()){
                        Filtrar.filtrar(carpeta.getAbsolutePath(), extension);
                    }else{
                        if(carpeta.getName().endsWith(extension)){
                        System.out.println(carpeta.getName());
                    }
                    }
                }
            }
        }else{
            System.out.println("Introduce un nombre de directorio válido.");
        }
    }

}
