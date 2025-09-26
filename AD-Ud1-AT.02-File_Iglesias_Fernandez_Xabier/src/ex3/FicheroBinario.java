package ex3;

import java.io.*;

public class FicheroBinario{

    private File archivo;

    public FicheroBinario(String entrada){
        this.archivo = new File("src\\ex3\\",entrada);
        if(!archivo.exists()){
            try {
                boolean created = archivo.createNewFile();
                System.out.println(created);
            } catch (IOException e) {
                System.out.println("El archivo binario no se ha podido crear");
            }
        }
    }

    public void setFile(File archivo){
        this.archivo = archivo;
    }
    public File getFile(){
        return this.archivo;
    }

    public void escribir(String texto){
        try {
            FileOutputStream entrada = new FileOutputStream(archivo);
            BufferedOutputStream buffer = new BufferedOutputStream(entrada);

            buffer.write(texto.getBytes());

            buffer.close();
            entrada.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero.");
        } catch(IOException e){
            System.out.println("No está escribiendo el archivo correctamente.");
        }
    }

    public void leer(){
        try {
            FileInputStream salida = new FileInputStream(archivo);
            BufferedInputStream buffer = new BufferedInputStream(salida);

            byte b;
            while((b = (byte)buffer.read())!=-1){
                System.out.print(b+" ");
            }

            buffer.close();
            salida.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero.");
        }catch(IOException e){
            System.out.println("No se está leyendo el archivo correctamente.");
        }
    }

    public void copiar(FicheroBinario ficheroDestino){
        try {
            FileInputStream origen = new FileInputStream(archivo);
            BufferedInputStream bufferOrigen = new BufferedInputStream(origen);

            FileOutputStream destino = new FileOutputStream(ficheroDestino.getFile());
            BufferedOutputStream bufferDestino = new BufferedOutputStream(destino);

            //Leemos el primer archivo
            byte[] b = new byte[bufferOrigen.read()];

            //Escribimos en el fichero de destino
            bufferDestino.write(b);

            origen.close();
            bufferOrigen.close();
            destino.close();
            bufferDestino.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo referenciado no existe.");
        }catch(IOException e){
            System.out.println("Error na entrada ou saída de información.");
        }
    }
}
