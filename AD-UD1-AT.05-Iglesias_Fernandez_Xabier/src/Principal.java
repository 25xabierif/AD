import java.nio.file.*;

public class Principal {
    public static void main(String[] args) throws Exception {
        
        Path orixe = Paths.get("AD-UD1-AT.05-Iglesias_Fernandez_Xabier/src/dir/origen.txt");
        Path copia = Paths.get("AD-UD1-AT.05-Iglesias_Fernandez_Xabier/src/dir/copia.txt");

        CopiaFichero.copiar(orixe, copia);
        /* System.out.println(System.getProperty("user.dir")); */
    }
}
