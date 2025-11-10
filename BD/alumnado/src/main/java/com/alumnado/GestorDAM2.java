package com.alumnado;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import conexiones.PostgreSQLConnection;

public class GestorDAM2 {


    public static void createDB(){
        try (Connection conn = new PostgreSQLConnection().getConnectionServer();
        Statement stmt = conn.createStatement()) {

            String BD = ConfigLoader.get("postgresql.bd");
            String USER = ConfigLoader.get("postgresql.user");

            String check = "SELECT 1 FROM pg_database WHERE datname = '"+BD+"'";
            ResultSet rs = stmt.executeQuery(check);
            if(!rs.next()){
                stmt.executeUpdate("CREATE DATABASE "+BD+" WITH OWNER = "+USER+" ENCODING 'UTF8' TEMPLATE template0;");
                System.out.println("Base de datos DAM2 creada satisfactoriamente.");
            }
  
        } catch (SQLException e) {
            System.err.println("La creación de la BD ha fallado: "+e.getMessage());
        }

    }

}
