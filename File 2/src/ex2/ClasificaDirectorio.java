package ex2;

import java.io.File;

public class ClasificaDirectorio {
    
    void segunExtension(String dir){

        File archivo = new File(dir);

        if(archivo.exists() && archivo.isDirectory()){
            //Creamos lista de nombres de directorios

            File[] archivos = archivo.listFiles();

            String[] extensiones = new String[archivos.length];

            String regex = "\\.";

            if(archivos.length>0){

                //Comprobar tipo de archivo
                for (int i = 0; i<archivos.length;i++) {
                    if(archivos[i].getName().split(regex).length == 2){
                        extensiones[i] = archivos[i].getName().split(regex)[1];
                    }
                    System.out.println(archivos[i].getName());
                }
                //Creacion directorios según las extensiones
                for (int i = 0; i<extensiones.length; i++) {
                    if(extensiones[i] != null){

                        File newDirectory = new File(archivo, extensiones[i].toUpperCase());

                        if(!newDirectory.exists()){
                            newDirectory.mkdir();
                        }

                        //Mover archivos a los nuevos directorios
                        
                        File destino = new File(newDirectory, archivos[i].getName());

                        String extension = "";
                        if(archivos[i].getName().split(regex)[1]!=null){
                            extension = archivos[i].getName().split(regex)[1];
                            System.out.println(extension.toUpperCase());
                        }
                        if(archivos[i].isFile()){
                            if(extension.equals(extensiones[i]) && newDirectory.exists()){
                                boolean move = archivos[i].renameTo(destino);
                                System.out.println(move);
                            }
                        }
                    }
                }
            }
        }else{
            System.out.println("El directorio indicado no existe.");
        }
    }
}
