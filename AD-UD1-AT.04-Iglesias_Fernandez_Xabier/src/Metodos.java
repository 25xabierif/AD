import java.io.*;
import java.util.HashMap;

public class Metodos {

    private final HashMap<Character, Character> mapa = new HashMap<>();

    private final HashMap<Character, Character> mapaDes = new HashMap<>();

    public HashMap<Character,Character> getMap(){
        return this.mapa;
    }

    public void setMapa(File fichero){
        try (FileReader file = new FileReader("src\\"+fichero.getName())) {

            BufferedReader buffer = new BufferedReader(file);

            String line1 = buffer.readLine();
            String line2 = buffer.readLine();

            for (int i = 0; i < line1.length(); i++) {
                this.mapa.put(line1.charAt(i), line2.charAt(i));
            }
        } catch (IOException e) {
            System.out.println("No se puede leer el archivo.");
        }
    }

    public void setMapaDes(File fichero){
        try (FileReader file = new FileReader("src\\"+fichero.getName())) {

            BufferedReader buffer = new BufferedReader(file);

            String line1 = buffer.readLine();
            String line2 = buffer.readLine();

            for (int i = 0; i < line1.length(); i++) {
                this.mapaDes.put(line2.charAt(i), line1.charAt(i));
            }
        } catch (IOException e) {
            System.out.println("No se puede leer el archivo.");
        }
    }
    
    public static char valorEncriptado(char c, HashMap<Character, Character> mapa){
        return mapa.get(c);
    }

    public static char valorDesencriptado(char c, HashMap<Character, Character> mapa){
        
        return c;
    }
    
    public static void encriptar(File fichero){

        try (FileReader file = new FileReader("src\\"+fichero.getName())){
            
            BufferedReader buffer = new BufferedReader(file);



        } catch (IOException e) {
            System.out.println("Non se pode ler o ficheiro.");
        }
    }

}