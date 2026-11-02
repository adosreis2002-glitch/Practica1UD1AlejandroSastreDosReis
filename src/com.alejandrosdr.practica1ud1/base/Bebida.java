package com.alejandrosdr.practica1ud1.base;

import java.time.LocalDate;
import java.util.Objects; // Necesario para el hashCode

// El abstract hace que no pueda haber un = new Bebida
// En cambio si un nuevo new R, new W, new V
public abstract class Bebida {

    private String nombre;
    private String marca;
    private double precio;
    private int stock; // Unidades disponibles
    private double graduacionAlcoholica;
    private LocalDate fechaEntradaInv; // Fecha en la que ha entrado al inventario

    // (W, R, V, G)
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
        this.nombre = nombre;             // Permite modificar los valores (no todos seran iguales)
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

    // --- MÉTODOS AÑADIDOS PARA LA VISTA Y EL HASHSET ---

    @Override
    public String toString() {
        return "Bebida: " + nombre + " | Marca: " + marca +
                " | Precio: " + precio + "€ | Stock: " + stock +
                " | Graduación: " + graduacionAlcoholica + "% | Fecha: " + fechaEntradaInv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bebida bebida = (Bebida) o;
        return nombre.equalsIgnoreCase(bebida.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase());
    }
}
