import java.io.*;
import java.util.HashMap;

public final class Metodos {

    private final File codec;

    private final HashMap<Character, Character> clave = new HashMap<>();

    private final HashMap<Character, Character> claveDes = new HashMap<>();

    private final String rutaAbsoluta = "L:\\AD\\AD-UD1-AT.04-Iglesias_Fernandez_Xabier\\src\\";

    public Metodos(File codec){
        this.codec = codec;
        this.setClave();
        this.setClaveDes();
    }

    public HashMap<Character,Character> getClave(){
        return this.clave;
    }

    public HashMap<Character,Character> getClaveDes(){
        return this.claveDes;
    }

    public void setClave(){
        try (FileReader file = new FileReader(rutaAbsoluta+this.codec.getName())) {

            BufferedReader buffer = new BufferedReader(file);

            String line1 = buffer.readLine();
            String line2 = buffer.readLine();

            for (int i = 0; i < line1.length(); i++) {
                this.clave.put(line1.charAt(i), line2.charAt(i));
            }
        } catch (IOException e) {
            System.out.println("No se puede leer el archivo.");
        }
    }

    public void setClaveDes(){
        try (FileReader file = new FileReader(rutaAbsoluta+this.codec.getName())) {

            BufferedReader buffer = new BufferedReader(file);

            String line1 = buffer.readLine();
            String line2 = buffer.readLine();

            for (int i = 0; i < line1.length(); i++) {
                this.claveDes.put(line2.charAt(i), line1.charAt(i));
            }
        } catch (IOException e) {
            System.out.println("No se puede leer el archivo.");
        }
    }
    
    public void valorEncriptado(char c, HashMap<Character, Character> clave){
        System.out.println(clave.get(c));
    }

    public void valorDesencriptado(char c, HashMap<Character, Character> clave){
        System.out.println(claveDes.get(c));
    }
    
    public void encriptar(File fichero){

        //Definimos fichero y lector
        try (FileReader file = new FileReader(rutaAbsoluta+fichero.getName())){
            
            //Creamos el buffer para poder acumular y mostrar la información del fichero
            BufferedReader bufferR = new BufferedReader(file);

            //Creamos el nombre del nuevo fichero
            String regex = "\\.";
            String nombre = fichero.getName();
            String[] nombreComposto = nombre.split(regex);
            String nombreFinal = nombreComposto[0].concat("_codificado.").concat(nombreComposto[1]);

            //Creamos fichero de destino
            File ficheroDestino = new File(rutaAbsoluta, nombreFinal);
            if(!ficheroDestino.exists()){
                boolean creado = ficheroDestino.createNewFile();
                System.out.println(creado);
            }

            //Definimos ahora un writer
            FileWriter writer = new FileWriter(ficheroDestino);
            BufferedWriter bufferW = new BufferedWriter(writer);

            //Creamos una variable String para gestionar la información del fichero
            String line = bufferR.readLine();

            //Hacemos el encriptado del fichero
            while(line!=null){
                String escribir = "";
                line.toLowerCase();
                for(int i = 0; i<line.length();i++){
                    escribir+=clave.get(line.charAt(i));
                }
                bufferW.write(escribir+"\n");
                line = bufferR.readLine();
            }
            //Cerramos el archivo
            bufferR.close();
            file.close();
            bufferW.close();
            writer.close();
        } catch (IOException e) {
            System.out.println("Non se pode ler o ficheiro.");
        }
    }

    public void desencriptar(File fichero){
        //Definimos fichero y lector
        try (FileReader file = new FileReader(rutaAbsoluta+fichero.getName())){
            
            //Creamos el buffer para poder acumular y mostrar la información del fichero
            BufferedReader bufferR = new BufferedReader(file);

            //Creamos el nombre del nuevo fichero
            String regex = "\\.";
            String regex2 = "_";
            String nombre = fichero.getName();
            String[] primeiraDiv = nombre.split(regex2);
            String[] nombreComposto = primeiraDiv[1].split(regex);
            String nombreFinal = primeiraDiv[0].concat("_descodificado.").concat(nombreComposto[1]);

            //Creamos fichero de destino
            File ficheroDestino = new File(rutaAbsoluta, nombreFinal);
            if(!ficheroDestino.exists()){
                boolean creado = ficheroDestino.createNewFile();
            }

            //Definimos ahora un writer
            FileWriter writer = new FileWriter(ficheroDestino);
            BufferedWriter bufferW = new BufferedWriter(writer);

            //Creamos una variable String para gestionar la información del fichero
            String line = bufferR.readLine();

            //Hacemos el desencriptado del fichero
            while(line!=null){
                String escribir = "";
                line.toLowerCase();
                for(int i = 0; i<line.length();i++){
                    escribir+=claveDes.get(line.charAt(i));
                }
                bufferW.write(escribir+"\n");
                line = bufferR.readLine();
            }
            //Cerramos el archivo
            bufferR.close();
            file.close();
            bufferW.close();
            writer.close();
        } catch (IOException e) {
            System.out.println("Non se pode ler o ficheiro.");
        }
    }
}