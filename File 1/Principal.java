public class Principal {
    public static void main(String[] args) {

        ManejoFicheros.crearFichero("proba.txt");
        
        InfoFichero nuevoFichero = new InfoFichero("proba.txt");

        nuevoFichero.mostrarInfo();

        ManejoFicheros.crearDirectorio("Proba");
        ManejoFicheros.crearDirectorio("Proba\\Cousa");
        ManejoFicheros.crearDirectorio("Proba\\Cousa\\Mais");
        ManejoFicheros.crearFichero("Proba\\Cousa\\Mais\\Fichero.txt");
        
        ManejoFicheros.borrarDirectorio("Proba");

        ManejoFicheros.listarDirectorio("Proba");

    }
}
