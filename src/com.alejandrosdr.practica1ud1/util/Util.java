package com.alejandrosdr.practica1ud1.util;

import javax.swing.*;

public class Util {

    public static void confirmacionSalida(JFrame frame) {
        int confirmacion = JOptionPane.showConfirmDialog(frame,
                "Seguro que quieres salir?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            System.exit(0); // Cerramos (xao)
        }
    }
}
