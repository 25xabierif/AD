package model;

import java.util.ArrayList;

public class RepositorioTareas{
    
    private static RepositorioTareas instance;

    private ArrayList<Tarea> tareas;

    /**
     * Definimos un constructor privado para la clase
     * @param tareas
     */
    private RepositorioTareas(ArrayList<Tarea> tareas){
        this.tareas = new ArrayList<Tarea>();
    }

    /**
     * Método que nos permitirá trabajar siempre con la misma instancia de la clase.
     * @return
     */
    public static RepositorioTareas getInstance(){

        if(instance == null){
            synchronized(RepositorioTareas.class){
                if(instance == null) {
                    instance = new RepositorioTareas(null);
                }
            }
        }
        
        return instance;
        
    }

    /**
     * Método para obtener todas las tareas contenidas en el ArrayList.
     * @return
     */
    public ArrayList<Tarea> obtenerTodas(){
        return tareas;
    }

    /**
     * Método que nos permite buscar una tarea concreta según su id.
     * @param id
     * @return
     */
    public Tarea obtenerPorId(int id){
        return tareas.get(id);
    }

    /**
     * Método que nos permite enviar una nueva tarea al ArrayList de tareas.
     * @param t
     */
    public void agregar(Tarea t){
        tareas.add(t);
    }

    /**
     * Método que nos permite eliminar una tarea.
     * Devuelve true si ha conseguido eliminarla y false en el caso contrario.
     * @param id
     * @return
     */
    public boolean eliminar(int id){
        if(tareas!=null && tareas.get(id)!= null){
            tareas.remove(id);
            return true;
        }else{
            return false;
        }
    }

    /**
     * Método que nos permite acceder al atributo "Completada" de la clase tarea y modificarlo a true.
     * @param id
     */
    public void marcarCompletada(int id){
        tareas.get(id).setCompletada(true);
    }
}
