package gui;

import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.*;
import java.awt.*;

public class Ventana {

    // Variables enlazadas automáticamente con el .form
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

    // Aquí están las dos variables del calendario tal y como las tienes en el .form
    public JPanel panelFecha;
    public DatePicker datePicker;

    public JButton btnNuevo;
    public JButton btnExportar;
    public JButton btnImportar;
    public JButton btnCerrar;
    public JTextArea txtAreaListado;

    public JFrame frame;

    public Ventana() {
        frame = new JFrame("Licoreria Managment - Gestion de Catalogo");

        // Panel de fondo con la imagen de madera
        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagen = new ImageIcon("fondo.png");
                g.drawImage(imagen.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelFondo.setLayout(new BorderLayout());

        // Aplicamos los estilos visuales a la interfaz
        Estilo.temaBarril(panel1);

        // Montamos la interfaz transparente sobre el fondo
        panelFondo.add(panel1, BorderLayout.CENTER);
        frame.setContentPane(panelFondo);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}