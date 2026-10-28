package com.alejandrosdr.practica1ud1.base;

import java.time.LocalDate;

     // El abstract hace que no pueda haber un = new Bebida
    // En cambio si un nuevo new R, new W, new V
public abstract class Bebida {

    private String nombre; // Nombre del producto
    private String marca; // Marca del procducto
    private double precio; // Precio en euros
    private int stock; // Unidades disponibles
    private double graduacionAlcoholica; // Cuanta graduacion tendra
    private LocalDate fechaEntradaInv; // Fecha en la que ha entrado al inventario

     // El constructor nos ayudara a la hora de crear una bebida (W, R, V, G)
    // Con esos mismos datos inciales
    public Bebida(String nombre, String marca, double precio, int stock,
                  double graduacionAlcoholica, LocalDate fechaEntradaInv) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
        this.graduacionAlcoholica = graduacionAlcoholica;
        this.fechaEntradaInv = fechaEntradaInv;
    }

    public String getNombre() { // Getters para TODOS los campos
        return nombre;         // Permite consultar el/los campos privados
    }

    public void setNombre(String nombre) { // Setter para TODOS los campos
        this.nombre = nombre;             //  Permite modificar los valores (no todos seran iguales)
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getGraduacionAlcoholica() {
        return graduacionAlcoholica;
    }

    public void setGraduacionAlcoholica(double graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
    }

    public LocalDate getFechaEntradaInv() {
        return fechaEntradaInv;
    }

    public void setFechaEntradaInv(LocalDate fechaEntradaInv) {
        this.fechaEntradaInv = fechaEntradaInv;
    }
}
