import java.io.File;
import java.io.IOException;

public class ManejoFicheros{
    

    //Métodos

    public static void crearFichero(String fichero){
        try {
            File nuevoFichero = new File(fichero);
            boolean created = nuevoFichero.createNewFile();
            System.out.println("Fichero "+fichero+" creado= "+created);
        } catch (IOException e) {
            System.out.println("El archivo no se ha podido crear. "+e.getMessage());
        }
    }

    public static void borrarFichero(String fichero){
        try {
            File nuevoFichero = new File(fichero);
            if(nuevoFichero.exists() && nuevoFichero.isFile()){
                nuevoFichero.delete();
            }else{
                System.out.println("El fichero no existe.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void crearDirectorio(String ruta){
        try {
            File nuevoFichero = new File(ruta);
            boolean created = nuevoFichero.mkdir();
            System.out.println("Directorio "+ruta+" creado= "+created);
        } catch (Exception e) {
            System.out.println("El directorio no se ha podido crear.");
        }
    }

    public static void borrarDirectorio(String ruta){
        try {
            File nuevoFichero = new File(ruta);

            if(!nuevoFichero.exists()){
                System.out.println("El directorio no existe.");
            }else{
                File[] lista = nuevoFichero.listFiles();

                if(lista != null){
                    for (File archivo : lista) {
                        if(archivo.isDirectory()){
                            ManejoFicheros.borrarDirectorio(archivo.getAbsolutePath());
                            archivo.delete();
                            System.out.println("Directorio "+archivo.getAbsolutePath()+" eliminado.");
                        }else{
                            archivo.delete();
                            System.out.println("Fichero "+archivo.getAbsolutePath()+" eliminado.");
                        }
                    }
                }

                nuevoFichero.delete();

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void listarDirectorio(String ruta){
        try {
            File fichero = new File(ruta);

            if(!fichero.exists()){
                System.out.println("El directorio no existe.");
            }else{

                File[] lista = fichero.listFiles();

                File novoFichero;

                if(lista != null && lista.length>0){
                    for (File nombre : lista) {
                        novoFichero = new File(nombre.getAbsolutePath());
                        if(novoFichero.isDirectory()){
                            ManejoFicheros.listarDirectorio(novoFichero.getAbsolutePath());
                            System.out.println("Directorio: "+novoFichero.getName());
                        }else{
                            System.out.println("Fichero: "+novoFichero.getName());
                        }
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("Algo no funciona");
        }
    }
}