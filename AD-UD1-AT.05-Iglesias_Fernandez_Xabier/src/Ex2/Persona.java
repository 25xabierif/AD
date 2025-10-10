package Ex2;

import java.nio.file.Path;

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

    public void escribirPersona(Persona p){

    }

    public Persona leerPersona(Path archivo){
        Persona p = new Persona();

        return p;
    }
}
