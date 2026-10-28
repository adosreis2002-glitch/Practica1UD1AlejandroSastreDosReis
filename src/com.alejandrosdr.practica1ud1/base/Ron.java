package com.alejandrosdr.practica1ud1.base;

import java.time.LocalDate;

// Ron forma parte de bebida entonces extends
public class Ron extends Bebida {

    public Ron(String nombre, String marca, double precio, int stock, double graduacionAlcoholica, LocalDate fechaEntradaInv) {
        super(nombre, marca, precio, stock, graduacionAlcoholica, fechaEntradaInv);
    }
}
