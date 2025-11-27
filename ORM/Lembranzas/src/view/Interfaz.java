package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;

public class Interfaz extends JFrame{

    private JPanel contentPane;
    private JTextField areaTexto;
    private JButton listarTareas;
    private JButton crearTarea;
    private JButton marcarTarea;
    private JButton eliminarTarea;
    private JButton buscarTarea;
    private JButton salir;
    
    
    public Interfaz(){

        JFrame vista = new JFrame("Tareas");
        vista.setBounds(500,500,900,500);
        vista.setLocationRelativeTo(null);
        vista.setDefaultCloseOperation(EXIT_ON_CLOSE);
        vista.setLayout(new BorderLayout());

        contentPane = new JPanel();
        vista.add(contentPane);

        areaTexto = new JTextField();

        listarTareas = new JButton();
        listarTareas.setText("Listar tareas.");
        listarTareas.addActionListener(a ->{

        });

        

        crearTarea = new JButton();
        crearTarea.setText("Crear tarea.");
        crearTarea.addActionListener(a ->{

        });

        marcarTarea = new JButton();
        marcarTarea.setText("Marcar tarea completada.");
        marcarTarea.addActionListener(a -> {

        });

        eliminarTarea = new JButton();
        eliminarTarea.setText("Eliminar tarea.");
        eliminarTarea.addActionListener(a -> {

        });

        buscarTarea = new JButton();
        buscarTarea.setText("Buscar tarea.");
        buscarTarea.addActionListener(a -> {

        });

        salir = new JButton();
        salir.setText("Salir.");
        salir.addActionListener(a -> {

        });
    }

}
