package com.alejandrosdr.practica1ud1.base;

import java.time.LocalDate;


public class Vodka extends Bebida {


    public Vodka(String nombre, String marca, double precio, int stock, double graduacionAlcoholica, LocalDate fechaEntradaInv) {
        super(nombre, marca, precio, stock, graduacionAlcoholica, fechaEntradaInv);
    }
}
