package com.ferrados.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Sorts;

import model.Empleado;

public class EmpleadoController {
    
    public boolean addEmpleado(Empleado empleado){
        boolean added = false;

        try (MongoProvider mp = MongoProvider.getInstance()) {



        } catch (Exception e) {
            System.err.println("No se ha podido conectar a la BD.");
        }

        return added;
    }

    public List<Document> getEmpleadoSalarioDept(){
        List<Document> lista = new ArrayList<>();
        List<Bson> pipeline = List.of(
            Aggregates.group("$dep", 
                Accumulators.sum("numEmpleados", 1),
                Accumulators.avg("salarioMedio", "$salario"),
                Accumulators.max("salarioMaximo", "$salario")
            ),
            Aggregates.sort(Sorts.ascending("dep"));
        );

        try (MongoProvider mp = MongoProvider.getInstance()) {
            mp.getCollection("empleados").aggregate(pipeline).into(lista);
            //Aquí enviamos todo a la vista+
        } catch (Exception e) {
            // TODO: handle exception
        }


        return lista;
    }

}

