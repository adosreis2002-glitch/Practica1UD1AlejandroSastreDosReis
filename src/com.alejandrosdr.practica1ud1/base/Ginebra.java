package com.alejandrosdr.practica1ud1.base;

import java.time.LocalDate;

    // Extends ya que Ginebra heredara todos los atributos y metetodos de Bebida
public class Ginebra extends Bebida {

    public Ginebra(String nombre, String marca, double precio, int stock,
                   double graduacionAlcoholica, LocalDate fechaEntradaInv) {

        // Llamamos al constructor de la clase padre mediante super();
        super(nombre, marca, precio, stock, graduacionAlcoholica, fechaEntradaInv);
    }
}
