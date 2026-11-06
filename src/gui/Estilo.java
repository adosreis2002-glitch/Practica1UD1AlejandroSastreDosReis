package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Estilo {

    // Paleta de colores
    public static final Color MADERA_CAJAS = new Color(30, 20, 15);
    public static final Color TEXTO_DORADO = new Color(220, 180, 110);
    public static final Color FONDO_PERGAMINO = new Color(222, 195, 145);
    public static final Color TEXTO_PERGAMINO = new Color(60, 30, 15);

    // Tipografiaa
    public static final Font FUENTE_RUSTICA = new Font("Serif", Font.BOLD, 14);
    public static final Font FUENTE_BOTONES = new Font("Serif", Font.BOLD, 15);
    public static final Font FUENTE_LISTADO = new Font("Monospaced", Font.BOLD, 13);

    // Bordes estandarizados
    public static final Border BORDE_CAJAS = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(TEXTO_DORADO, 1),
            BorderFactory.createEmptyBorder(3, 5, 3, 5)
    );

    public static void temaBarril(Container contenedor) {

        // Paneles/contenedores de scroll transparentes
        if (contenedor instanceof JPanel || contenedor instanceof JScrollPane || contenedor instanceof JViewport) {
            ((JComponent) contenedor).setOpaque(false);

            if (contenedor instanceof JScrollPane) {
                ((JScrollPane) contenedor).setBorder(BorderFactory.createEmptyBorder()); // Quita el borde blanco del scroll
            }
        }

        for (Component c : contenedor.getComponents()) {
            // Feunte general
            c.setFont(FUENTE_RUSTICA);
            if (c instanceof JTextField) {
                c.setBackground(MADERA_CAJAS);
                c.setForeground(TEXTO_DORADO);
                ((JComponent) c).setBorder(BORDE_CAJAS);
                ((JTextField) c).setCaretColor(TEXTO_DORADO);
                ((JComponent) c).setOpaque(true);
            }
            // Comprueba area de Text
            else if (c instanceof JTextArea) {
                c.setBackground(FONDO_PERGAMINO);
                c.setForeground(TEXTO_PERGAMINO);
                c.setFont(FUENTE_LISTADO);
                ((JComponent) c).setBorder(BorderFactory.createLoweredBevelBorder());
                ((JComponent) c).setOpaque(true);
            }

            else if (c instanceof JButton) {
                c.setBackground(MADERA_CAJAS);
                c.setForeground(TEXTO_DORADO);
                c.setFont(FUENTE_BOTONES);
                c.setCursor(new Cursor(Cursor.HAND_CURSOR));
                ((JComponent) c).setBorder(BorderFactory.createRaisedBevelBorder());
                ((JButton) c).setFocusPainted(false);
                ((JComponent) c).setOpaque(true);
            }
            else if (c instanceof JLabel || c instanceof JRadioButton) {
                c.setForeground(TEXTO_DORADO);
                if (c instanceof JComponent) {
                    ((JComponent) c).setOpaque(false);
                }
            }
            // identifica si un componente es un panel o un contenedor de desplazamiento
            if (c instanceof JPanel || c instanceof JScrollPane || c instanceof JViewport) {
                temaBarril((Container) c);
            }
        }
    }
}