package model;

import java.util.Date;

public class Empleado {
    
    private int id;
    private String nombre;
    private int depNum;
    private int salario;
    private Date fechaAlta;
    private String oficio;
    private int comision;
    
    public Empleado(int id, String nombre, int depNum, int salario, Date fechaAlta, String oficio, int comision) {
        this.id = id;
        this.nombre = nombre;
        this.depNum = depNum;
        this.salario = salario;
        this.fechaAlta = fechaAlta;
        this.oficio = oficio;
        this.comision = comision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDepNum() {
        return depNum;
    }

    public void setDepNum(int depNum) {
        this.depNum = depNum;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public int getComision() {
        return comision;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }  

}
