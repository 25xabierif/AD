import java.nio.file.*;

public class Principal {
    public static void main(String[] args){
        
        String raiz = "/media/a25xabierif/a25xabierif_documentos/AD/AD-UD1-AT.05-Iglesias_Fernandez_Xabier/";

        Path orixe = Paths.get(raiz+"src/dir/origen.txt");
        Path copia = Paths.get(raiz+"src/dir/copia.txt");

        CopiaFichero.copiar(orixe, copia);
        /* System.out.println(System.getProperty("user.dir")); */
    }
}
