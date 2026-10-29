package gui;

    // Lo que une la vista con el modelo
public class BebidasControlador {

    private Ventana ventana;
    private BebidasModelo modelo;

    public BebidasControlador(Ventana ventana, BebidasModelo modelo) {
        this.ventana = ventana;
        this.modelo = modelo;
    }
}
