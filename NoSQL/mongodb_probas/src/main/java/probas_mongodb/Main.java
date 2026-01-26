package probas_mongodb;

import java.util.ArrayList;

import javax.print.DocFlavor;

import org.bson.Document;

import com.mongodb.client.model.Filters;

public class Main {
    public static void main(String[] args) {
        
        //Generar el provider y añadir un alumno en la colección alumnado


        try (MongoProvider mp = new MongoProvider()) {
            
            Document doc = new Document();
            doc.append("Nombre", "Xabier").append("Edad", 34).append("Ciclo", "DAM");

            mp.alumnado().insertOne(doc);

            for (Document alumno : mp.alumnado().find()) {
                System.out.println(alumno.toString());
            }
            //Clase Filters práctica para hacer consultas
            mp.alumnado().deleteOne(Filters.eq("Nombre", "Xabier")).getDeletedCount();

            for (Document alumno : mp.alumnado().find()) {
                System.out.println(alumno.toString());
            }

        } catch (Exception e) {
            System.err.println("La conexión a la bd ha fallado");
        }
        

    }
}