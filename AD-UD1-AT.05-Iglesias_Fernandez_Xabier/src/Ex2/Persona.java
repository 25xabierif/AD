package Ex2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Persona {

    private String nombre;
    private int edad;
    private String dni;
    private char sexo;
    private double peso;
    private double altura;

    public Persona() {
        this.nombre = "";
        this.edad = 0;
        this.dni = generarDNI();
        this.sexo = 'H';
        this.peso = 0;
        this.altura = 0;
    }

    public Persona(String nombre, int edad, char sexo) {
        this.nombre = nombre;
        this.edad = edad;
        this.dni = generarDNI();
        this.sexo = sexo;
        this.peso = 0;
        this.altura = 0;
    }

    public Persona(String nombre, int edad, String dni, char sexo, double peso, double altura) {
        this.nombre = nombre;
        this.edad = edad;
        this.dni = dni;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
    }

    private String generarDNI() {
        int numero = (int) (Math.random() * 100000000);
        char letra = "TRWAGMYFPDXBNJZSQVHLCKE".charAt(numero % 23);
        return String.format("%08d%c", numero, letra);
    }

    public String toString() {
        return "Nombre: " + nombre + "\nEdad: " + edad + "\nDNI: " + dni + "\nSexo: " + sexo + "\nPeso: " + peso + "\nAltura: " + altura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void escribirPersona(Path archivo){

        if(!Files.exists(archivo)){
            try {
                Files.createFile(archivo);
            } catch (IOException e) {
                System.out.println("El archivo no se ha podido crear.");
            }
        }
        try {
            String contenido = this.toString();
            Files.write(archivo, contenido.getBytes(),StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("No se ha podido escribir la persona en el archivo.");
        }
    }

    public static void leerFichero(Path archivo){
        if(Files.exists(archivo)){
            Charset charset = StandardCharsets.UTF_8;
            byte[] bytes;
            try {
                bytes = Files.readAllBytes(archivo);
                String cadea = new String(bytes, charset);
                System.out.println(cadea);
            } catch (Exception e) {
                System.out.println("No se ha podido acceder al fichero de lectura.");
            }
        }else{
            System.out.println("El archivo especificad no existe.");
        }
    }
}
