import gui.BebidasControlador;
import gui.BebidasModelo;
import gui.Ventana;

public class Principal {

    public static void main(String[] args) {

        // Interfaz grafica
        Ventana vista = new Ventana();

        // Gestion datos
        BebidasModelo modelo = new BebidasModelo();

        // Conector
        BebidasControlador controlador = new BebidasControlador(vista, modelo);

    }
}
