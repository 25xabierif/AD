package com.example;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String titulo;
    private String autor;
    private int paginas;

    public Libro(){}

    public Libro(String titulo, String autor, int paginas){
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getPaginas() {
        return paginas;
    }
    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

}
