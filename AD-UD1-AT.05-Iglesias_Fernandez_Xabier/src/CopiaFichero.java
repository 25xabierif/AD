import java.nio.file.*;
import java.io.IOException;

public class CopiaFichero {
    
    static void copiar(Path origen, Path copia){
        if(Files.exists(origen) && Files.exists(copia)){
            try {
                String contenido = Files.readString(origen);
                Files.writeString(copia, contenido);
            } catch (IOException e) {
                System.out.println("La copia no ha podido completarse.");
            }
        }
    }
}