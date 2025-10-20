import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.zip.*;
import java.util.Iterator;

public class CrearZip {
    
    public static void zipMaker(ArrayList<String> archivos, String zipFileName){
        //Definimos un iterador para recorrer el ArrayList
        /* Iterator<String> iterador = archivos.iterator();
        while(iterador.hasNext()){
            System.out.println(iterador.next());
        } */

        String ficheroAComprimir = archivos.get(0);
        try {

            //Instanciamos el fichero donde se van a comprimir los archivos
            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            Path fileToZip = Paths.get(ficheroAComprimir);
            FileInputStream fis = new FileInputStream(fileToZip.toString());


            ZipEntry newEntry = new ZipEntry(fileToZip.toString());

            zipOut.putNextEntry(newEntry);

            byte[] buffer = new byte[1024];
            int bytesLeidos;
            while((bytesLeidos = fis.read(buffer)) >= 0){
                zipOut.write(buffer, 0, bytesLeidos);
            }

            zipOut.close();
            fis.close();
            fos.close();

            System.out.println("Hemos comprimido un archivo!");

        } catch (IOException e) {
            System.err.println("Error accedendo ó ficheiro: "+e.getMessage()
            );
        }
    }

}
