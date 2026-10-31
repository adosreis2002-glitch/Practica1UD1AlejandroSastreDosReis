package gui;

import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.*;
import java.awt.BorderLayout;

public class Ventana {

    public JPanel panel1;
    public JRadioButton rbWhisky;
    public JRadioButton rbRon;
    public JRadioButton rbGinebra;
    public JRadioButton rbVodka;
    public JTextField txtNombre;
    public JTextField txtMarca;
    public JTextField txtPrecio;
    public JTextField txtStock;
    public JTextField txtGraduacion;
    public JPanel panelFecha;
    public JButton btnNuevo;
    public JButton btnExportar;
    public JButton btnImportar;
    public JButton btnCerrar;
    public JTextArea txtAreaListado;

    // Ventana Principal
    public JFrame frame;
    public DatePicker datePicker; // Declaramos el calendario

    // Ponemos el constructor obligatorio que inicializara y muestra la ventana
    public Ventana() {
        // Crea la estructura de la ventana
        frame = new JFrame("Licoreria Managment - Gestion de Catalogo");

        // Este es el puente que conecta .form con la ventana
        frame.setContentPane(panel1);

        //Le decimos a java que salga y cierre la app (Cambiado a DO_NOTHING para poder preguntar antes de salir)
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        initComponents();

        // Ajusta dimensiones
        frame.pack();

        frame.setLocationRelativeTo(null); //Centra la ventana en pantalla
        frame.setVisible(true);
    }

    private void initComponents() {
        // Como pusiste un JPanel ("panelFecha") en el diseño,
        // creamos el calendario y lo metemos dentro de ese hueco.
        datePicker = new DatePicker();
        panelFecha.setLayout(new BorderLayout());
        panelFecha.add(datePicker, BorderLayout.CENTER);
    }
}