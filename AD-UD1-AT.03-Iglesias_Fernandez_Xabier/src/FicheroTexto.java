import java.io.*;

public class FicheroTexto {
    private File fichero;
    private String ruta;

    public FicheroTexto(String ruta){
        this.fichero = new File("src\\",ruta);
        try {
                boolean created = fichero.createNewFile();
                System.out.println(created);
            } catch (IOException e) {
                System.out.println("El fichero no se ha podido crear.");
            }
    }

    public File getFichero() {
        return fichero;
    }

    public String getRuta() {
        return ruta;
    }

    public void setFichero(File fichero) {
        this.fichero = fichero;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public void escribir(String texto){
        try {
            FileWriter file = new FileWriter(this.getRuta());
            BufferedWriter buffer = new BufferedWriter(file);

            

        } catch (IOException e) {
        }
    }
    public void leer(){
        try {
            FileReader file = new FileReader(this.getRuta());
            BufferedReader buffer = new BufferedReader(file);

            String line = buffer.readLine();
            while(line != null){
                System.out.println(line);
                line = buffer.readLine();
            }

            file.close();
            buffer.close();
        } catch (IOException e) {
            System.out.println("Error en la lectura del documento.");
        }
    }
}
