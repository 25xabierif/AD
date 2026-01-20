package com.lambonadas;

import com.lambonadas.controller.ProductoController;
import com.lambonadas.model.Categoria;

public class Main {
    public static void main(String[] args) {
        
        ProductoController pc = new ProductoController();

        Categoria gominolas = new Categoria("Gominolas");

        pc.addProducto("Ositos Haribo", 100, 1.5f, gominolas);

    }
}