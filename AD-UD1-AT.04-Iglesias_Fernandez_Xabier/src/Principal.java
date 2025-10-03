import java.io.File;

public class Principal {
    public static void main(String[] args) throws Exception {

        File codec = new File("src\\","codec.txt");
        File fichero1 = new File("src\\","cod1.txt");
        File fichero2 = new File("src\\","cod2.txt");
        File ficheroEnc = new File("src\\","cod1_codificado.txt");

        Metodos metodo = new Metodos(codec);

        metodo.valorEncriptado('a', metodo.getClave());

        metodo.valorDesencriptado('q', metodo.getClaveDes());

        metodo.encriptar(fichero1);
        metodo.encriptar(fichero2);

        metodo.desencriptar(ficheroEnc);

    }
}