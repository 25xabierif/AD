import java.io.File;

public class Principal {
    public static void main(String[] args) throws Exception {

        Metodos metodo = new Metodos();

        File fichero = new File("src\\","codec.txt");

        metodo.setMapa(fichero);

        System.out.println(Metodos.valorEncriptado('a', metodo.getMap()));

    }
}
