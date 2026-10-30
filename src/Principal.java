import gui.BebidasControlador;
import gui.BebidasModelo;
import gui.Ventana;

public class Principal {

    public static void main(String[] args) {

        Ventana vista = new Ventana();

        BebidasModelo modelo = new BebidasModelo();

        BebidasControlador controlador = new BebidasControlador(vista, modelo);

    }
}
