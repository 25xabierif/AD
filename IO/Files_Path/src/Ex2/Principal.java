package Ex2;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Principal {
    
    public static void main(String[] args) {
        Persona p1 = new Persona("Xabier", 34, 'm');
        Persona p2 = new Persona("Sandy", 27, 'f');
        Persona p3 = new Persona("Santiago", 21, 'q');
        Persona p4 = new Persona("Estrela", 21, 'f');

        Path fichero = Paths.get("personas.bin");

        /* p1.escribirPersona(fichero);
        p2.escribirPersona(fichero);
        p3.escribirPersona(fichero);
        p4.escribirPersona(fichero); */

        Persona.leerFichero(fichero);
    }
}
