
import java.io.File;



public class InfoFichero {

    private String ruta;

    //Getters y setters

    public void setRuta(String ruta){
        this.ruta = ruta;
    }
    public String getRuta(){
        return this.ruta;
    }

    //Constructor

    public InfoFichero(String ruta){
        this.ruta = ruta;
    }

    //Métodos

    public void mostrarInfo(){
        try {
            File archivo = new File(this.getRuta());
            if(archivo.exists()){
                System.out.println("Nombre fichero: "+archivo.getName());
                System.out.println("Ruta relativa: "+archivo.getPath());
                System.out.println("Ruta absoluta: "+archivo.getAbsolutePath());
                System.out.println("Permiso de lectura: "+archivo.canRead());
                System.out.println("Permiso de escritura: "+archivo.canWrite());
                System.out.println("Tamaño: "+archivo.length());
                System.out.println("Es un directorio?: "+archivo.isDirectory());
                System.out.println("Es un fichero? "+archivo.isFile());
            }else{
                System.out.println("La ruta indicada no existe.");
            }
        } catch (Exception e) {
            System.out.println("Algo ha ido mal.");
        }
    }
}