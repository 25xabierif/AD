package com.conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.aplicacion.ConfigLoader;

public class SQLConnection implements  DBConnection{

    @Override
    public Connection getConnection() {
        
        /* Obtenemos las credenciales de acceso del documento "application.properties"
         * en este caso añadimos a la url el nombre de la BD que vamos a crear
         */
        final String URL = ConfigLoader.get("mysql.url");
        final String BD = ConfigLoader.get("mysql.bd");
        final String USER = ConfigLoader.get("mysql.user");
        final String PASS = ConfigLoader.get("mysql.password");

        //Intentamos realizar la conexión. En caso de que la conexión falle
        // nos avisará con un mensaje de error en la consola
        try {
            return DriverManager.getConnection(URL+BD, USER, PASS);
        } catch (SQLException e) {
            System.err.println("La conexión a la BD "+BD+" no se ha podido realizar: "+e.getMessage());
            return null;
        }
    }

    @Override
    public Connection getConnectionServer() {
        
        /* Obtenemos las credenciales de acceso del documento "application.properties" */
        final String URL = ConfigLoader.get("mysql.url");
        final String USER = ConfigLoader.get("mysql.user");
        final String PASS = ConfigLoader.get("mysql.password");

        try {

            return DriverManager.getConnection(URL, USER, PASS);

        } catch (SQLException e) {
            System.err.println("No se ha podido conectar al servidor MySQL: "+e.getMessage());
            return null;
        }
    }



}

