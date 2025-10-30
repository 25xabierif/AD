package bolechas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
    
    private String nombre;
    private String dni;

    public void setDni(String dni){
        this.dni = dni;
    }

    public String getDni(){
        return this.dni;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return this.nombre;
    }

    public Cliente(String nombre, String dni){
        this.nombre = nombre;
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Cliente [getDni()=" + getDni() + ", getNombre()=" + getNombre() + "]";
    }
}
