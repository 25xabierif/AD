package bolechas;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        boolean bdCreada = false;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Quiere crear la base de datos? S/N");
        if(entrada.next().equals("S")){
            GestorBD.createDB();
            bdCreada = true;
        }else{
            System.exit(0);
        }
        if(bdCreada){
            System.out.println("Creando las tablas...");
            System.out.println("...");
            System.out.println("...");
            GestorBD.createTableCliente();
            GestorBD.createTableProducto();
            GestorBD.createTablePedido();
            GestorBD.createTableProductoPedido();
        }

        System.out.println("Introducimos unos clientes/productos/pedidos de prueba...");

        Cliente xabier = new Cliente("Xabier", "45699040M");
        Cliente estrela = new Cliente("Estrela", "34456776Y");
        Cliente olivia = new Cliente("Olivia", "35553332J");
        Cliente alvaro = new Cliente("Alvaro", "45055403Y");

        GestorBD.altaCliente(xabier);
        GestorBD.altaCliente(alvaro);
        GestorBD.altaCliente(estrela);
        GestorBD.altaCliente(olivia);
        System.out.println("Clientes añadidos con éxito. Clientes: \n"+xabier.toString());

        Producto gaita = new Producto("Gaita", "Gaiga galega", 1800);
        Producto tambor = new Producto("Tambor", "Tamboril galego", 800);
        Producto pandeireta = new Producto("Pandeireta", "Trozo de pel con ferro", 10);
        Producto flautin = new Producto("Flautín", "Flautin en do", 360);

        GestorBD.registrarProducto(gaita);
        GestorBD.registrarProducto(tambor);
        GestorBD.registrarProducto(pandeireta);
        GestorBD.registrarProducto(flautin);



        entrada.close();
    }
}