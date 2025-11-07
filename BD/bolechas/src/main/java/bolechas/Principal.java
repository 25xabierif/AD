package bolechas;

import java.time.LocalDate;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Quiere crear la base de datos? S/N");
        if(entrada.next().equals("S")){
            GestorBD.createDB();
        }else{
            System.exit(0);
        }

        System.out.println("Creando las tablas...");
        System.out.println("...");
        System.out.println("...");
        GestorBD.createTableCliente();
        GestorBD.createTableProducto();
        GestorBD.createTablePedido();
        GestorBD.createTableProductoPedido();

        System.out.println("Introducimos unos clientes/productos/pedidos de prueba...");

        Cliente xabier = new Cliente("Xabier", "45699040M");
        Cliente estrela = new Cliente("Estrela", "34456776Y");
        Cliente olivia = new Cliente("Olivia", "35553332J");
        Cliente alvaro = new Cliente("Alvaro", "45055403Y");

        GestorBD.altaCliente(xabier);
        GestorBD.altaCliente(alvaro);
        GestorBD.altaCliente(estrela);
        GestorBD.altaCliente(olivia);
        System.out.println("Clientes añadidos con éxito. Clientes: \n"+xabier.toString()+"\n"+alvaro.toString()+"\n"+estrela.toString()+"\n"+olivia.toString());

        Producto gaita = new Producto("Gaita", "Gaiga galega", 1800);
        Producto tambor = new Producto("Tambor", "Tamboril galego", 800);
        Producto pandeireta = new Producto("Pandeireta", "Trozo de pel con ferro", 10);
        Producto flautin = new Producto("Flautín", "Flautin en do", 360);


        GestorBD.registrarProducto(gaita);
        GestorBD.registrarProducto(tambor);
        GestorBD.registrarProducto(pandeireta);
        GestorBD.registrarProducto(flautin);
        System.out.println("Productos añadidos con éxito. Productos: \n"+gaita.toString()+"\n"+tambor.toString()+"\n"+pandeireta.toString()+"\n"+flautin.toString());

        Pedido pedidoX1 = new Pedido(LocalDate.now(), "45699040M");
        Pedido pedidoX2 = new Pedido(LocalDate.now(), "45699040M");
        Pedido pedidoX3 = new Pedido(LocalDate.now(), "45699040M");
        Pedido pedidoX4 = new Pedido(LocalDate.of(2023, 9, 27), "45699040M");
        Pedido pedidoE1 = new Pedido(LocalDate.of(2024, 10, 10), "34456776Y");
        Pedido pedidoE2 = new Pedido(LocalDate.of(2025, 1, 23), "34456776Y");
        Pedido pedidoE3 = new Pedido(LocalDate.of(2023, 2, 12), "34456776Y");
        Pedido pedidoA1 = new Pedido(LocalDate.of(2024, 10, 29), "45055403Y");
        Pedido pedidoA2 = new Pedido(LocalDate.of(2024, 10, 29), "45055403Y");
        Pedido pedidoA3 = new Pedido(LocalDate.of(2024, 10, 29), "45055403Y");

        System.out.println("""
                ...
                Añadiendo pedidos...
                ...
                """);
        

        GestorBD.crearPedido(pedidoX1);
        GestorBD.crearPedido(pedidoX2);
        GestorBD.crearPedido(pedidoX3);
        GestorBD.crearPedido(pedidoX4);
        GestorBD.crearPedido(pedidoE1);
        GestorBD.crearPedido(pedidoE2);
        GestorBD.crearPedido(pedidoE3);
        GestorBD.crearPedido(pedidoA1);
        GestorBD.crearPedido(pedidoA2);
        GestorBD.crearPedido(pedidoA3);
        System.out.println("Pedidos añadidos con éxito!");

        System.out.println();
        System.out.println("Registramos los pedidos con las cantidades...");
        System.out.println("...");

        ProductoPedido prodP1 = new ProductoPedido(3, gaita.getId(), pedidoX1.getId());
        ProductoPedido prodP2 = new ProductoPedido(1, tambor.getId(), pedidoX2.getId());
        ProductoPedido prodP3 = new ProductoPedido(4, flautin.getId(), pedidoX3.getId());
        ProductoPedido prodP4 = new ProductoPedido(1, gaita.getId(), pedidoX4.getId());
        ProductoPedido prodP5 = new ProductoPedido(2, tambor.getId(), pedidoE1.getId());
        ProductoPedido prodP6 = new ProductoPedido(3, pandeireta.getId(), pedidoE2.getId());
        ProductoPedido prodP7 = new ProductoPedido(1, gaita.getId(), pedidoE3.getId());
        ProductoPedido prodP8 = new ProductoPedido(3, tambor.getId(), pedidoA1.getId());
        ProductoPedido prodP9 = new ProductoPedido(1, pandeireta.getId(), pedidoA2.getId());
        ProductoPedido prodP10 = new ProductoPedido(4, flautin.getId(), pedidoA3.getId());

        GestorBD.altaProductoPedido(prodP1);
        GestorBD.altaProductoPedido(prodP2);
        GestorBD.altaProductoPedido(prodP3);
        GestorBD.altaProductoPedido(prodP4);
        GestorBD.altaProductoPedido(prodP5);
        GestorBD.altaProductoPedido(prodP6);
        GestorBD.altaProductoPedido(prodP7);
        GestorBD.altaProductoPedido(prodP8);
        GestorBD.altaProductoPedido(prodP9);
        GestorBD.altaProductoPedido(prodP10);

        

        GestorBD.cerrarConexion();

        entrada.close();
    }
}