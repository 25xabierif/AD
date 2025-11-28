package controller;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.RepositorioTareas;
import model.Tarea;
import view.Interfaz;

public class AppTareas{

    private final RepositorioTareas MODEL;
    
    private final Interfaz INTERFAZ;

    public AppTareas(Interfaz interfaz){
        this.MODEL = RepositorioTareas.getInstance();
        this.INTERFAZ = interfaz;

        this.INTERFAZ.addFuncListar(e -> listar());
        this.INTERFAZ.addFuncCrear(e -> crear());
        this.INTERFAZ.addFuncMarcar(e -> marcar());
        this.INTERFAZ.addFuncEliminar(e -> eliminar());
        this.INTERFAZ.addFuncBuscar(e -> buscar());
        this.INTERFAZ.addFuncSalir(e -> salir());
        
    }

    public DefaultTableModel crearTabla() {
        
        ArrayList<Tarea> tareas = RepositorioTareas.getInstance().obtenerTodas();

        String[] nombresColumnas = {"ID","Título","Descripción","Completada"};

        Object[][] datosFilas = new Object[tareas.size()][4];
        for(int i = 0; i < tareas.size(); i++){
            Tarea t = tareas.get(i);
            datosFilas[i][0] = t.getId();
            datosFilas[i][1] = t.getTitulo();
            datosFilas[i][2] = t.getDescripcion();
            datosFilas[i][3] = t.isCompletada();
        }

        return new DefaultTableModel(datosFilas, nombresColumnas);

    }

    private void listar(){
        this.INTERFAZ.repaint();
        this.INTERFAZ.revalidate();
    }

    private void crear(){
        JPanel panel = new JPanel(new GridLayout(0,1));
        JTextField nombreField = new JTextField(10);
        JTextField descripcionField = new JTextField(30);
        panel.add(new JLabel("Nombre: "));
        panel.add(nombreField);
        panel.add(new Label("Descripción: "));
        panel.add(descripcionField);

        int result = JOptionPane.showOptionDialog(null, panel, "Introduce tus datos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if(result == JOptionPane.OK_OPTION){
            String nombre = nombreField.getText();
            String descripcion = descripcionField.getText();
            Tarea t = new Tarea(nombre, descripcion);
            RepositorioTareas.getInstance().agregar(t);
        }
    }

    private void marcar(){

    }

    private void eliminar(){}


    private void buscar(){

    }

    private void salir(){
        System.exit(0);
    }

    
}