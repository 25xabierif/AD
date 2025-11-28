package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class Interfaz extends JFrame{

    private JPanel botonera;
    private JScrollPane resultados;
    private JButton listarTareas;
    private JButton crearTarea;
    private JButton marcarTarea;
    private JButton eliminarTarea;
    private JButton buscarTarea;
    private JButton salir;
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    
    
    public Interfaz(){

        JFrame vista = new JFrame("Tareas");
        vista.setBounds(500,500,700,500);
        vista.setLocationRelativeTo(null);
        vista.setDefaultCloseOperation(EXIT_ON_CLOSE);
        vista.setLayout(new BorderLayout());

        botonera = new JPanel();
        modeloTabla = new DefaultTableModel();
        tabla = new JTable(modeloTabla);

        resultados = new JScrollPane(tabla);


        listarTareas = new JButton();
        listarTareas.setText("Listar tareas");


        crearTarea = new JButton();
        crearTarea.setText("Crear tarea");

        marcarTarea = new JButton();
        marcarTarea.setText("Marcar tarea completada");

        eliminarTarea = new JButton();
        eliminarTarea.setText("Eliminar tarea");

        buscarTarea = new JButton();
        buscarTarea.setText("Buscar tarea");

        salir = new JButton();
        salir.setText("Salir");

        vista.add(botonera, BorderLayout.NORTH);
        vista.add(resultados, BorderLayout.CENTER);
        botonera.add(listarTareas);
        botonera.add(crearTarea);
        botonera.add(marcarTarea);
        botonera.add(eliminarTarea);
        botonera.add(buscarTarea);
        botonera.add(salir);

        vista.setVisible(true);
    }

    public void addFuncListar(ActionListener listener){
        listarTareas.addActionListener(listener);
    }
    public void addFuncCrear(ActionListener listener){
        crearTarea.addActionListener(listener);
    }
    public void addFuncMarcar(ActionListener listener){
        marcarTarea.addActionListener(listener);
    }
    public void addFuncEliminar(ActionListener listener){
        eliminarTarea.addActionListener(listener);
    }
    public void addFuncBuscar(ActionListener listener){
        buscarTarea.addActionListener(listener);
    }
    public void addFuncSalir(ActionListener listener){
        salir.addActionListener(listener);
    }
}
