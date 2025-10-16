import java.io.*;

public class FicheroTexto {
    private File fichero;

    public FicheroTexto(File fichero){
        this.fichero = fichero;
        try {
                fichero.createNewFile();
            } catch (IOException e) {
                System.out.println("El fichero no se ha podido crear.");
            }
    }

    public File getFichero() {
        return fichero;
    }

    public void setFichero(File fichero) {
        this.fichero = fichero;
    }

    public void escribir(String texto){
        try {
            try (FileWriter file = new FileWriter(this.fichero); BufferedWriter buffer = new BufferedWriter(file)) {
                
                buffer.write(texto);
                
            }

        } catch (IOException e) {
            System.out.println("No se puede escribir en el archivo.");
        }
    }
    public void leer(){
        try {
            BufferedReader buffer;
            try (FileReader file = new FileReader(this.fichero)) {
                buffer = new BufferedReader(file);
                String line = buffer.readLine();
                while(line != null){
                    System.out.println(line);
                    line = buffer.readLine();
                }
            }
            buffer.close();
        } catch (IOException e) {
            System.out.println("Error en la lectura del documento.");
        }
    }
}
