package gui;

import javax.swing.*;

public class Ventana {

    private JPanel panel1;
    private JRadioButton rbWhisky;
    private JRadioButton rbRon;
    private JRadioButton rbGinebra;
    private JRadioButton rbVodka;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtMarca;
    private JTextField txtStock;
    private JTextField txtGraduacion;
    private JPanel panelFecha;
    private JButton btnNuevo;
    private JButton btnExportar;
    private JButton btnImportar;
    private JButton btnCerrar;
    private JTextArea txtAreaListado;

    // Ventana Principal
    public JFrame frame;

    // Ponemos el constructor obligatorio que inicializara y muestra la ventana
    public Ventana() {
        // Crea la estructura de la ventana
        frame = new JFrame("Licoreria Managment - Gestion de Catalogo");
        // Este es el puente que conecta .form con la ventana
        frame.setContentPane(panel1);
        //Le decimos a java que salga y cierre la app
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Ajusta dimensiones
        frame.pack();
        frame.setLocationRelativeTo(null); //Centra la ventana en pantalla
        frame.setVisible(true);
    }
}