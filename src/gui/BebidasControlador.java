package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// Lo que une la vista con el modelo
public class BebidasControlador implements ActionListener, WindowListener {

    private Ventana vista;
    private BebidasModelo modelo;

    public BebidasControlador(Ventana vista, BebidasModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        addActionListener(this);
        addWindowListener(this);

    }

    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    private void addActionListener(ActionListener listener) {
        vista.btnNuevo.addActionListener(listener);

        // Controlador pueda detectar cambios en "bebida"
        vista.rbWhisky.addActionListener(listener);
        vista.rbRon.addActionListener(listener);
        vista.rbGinebra.addActionListener(listener);
        vista.rbVodka.addActionListener(listener);
    }

    // Comprobaciones
    // Comprobaciones
    private boolean hayCamposVacios() {
        if (vista.txtNombre.getText().isEmpty() ||
                vista.txtMarca.getText().isEmpty() ||
                vista.txtPrecio.getText().isEmpty() ||
                vista.txtStock.getText().isEmpty() ||
                vista.txtGraduacion.getText().isEmpty() ||
                vista.datePicker.getText().isEmpty()) {
            return true;
        }
        return false;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Limpiamos los campos
    private void limpiarCampos() {
        vista.txtNombre.setText("");
        vista.txtMarca.setText("");
        vista.txtPrecio.setText("");
        vista.txtStock.setText("");
        vista.txtGraduacion.setText("");
        vista.datePicker.setText("");
        vista.txtNombre.requestFocus();
    }

    public void refrescar() {
        vista.txtAreaListado.setText("");

        for (Bebida unaBebida : modelo.obtenerBebidas()) {
            vista.txtAreaListado.append(unaBebida.toString() + "\n");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Nuevo":
                if (hayCamposVacios()) {
                    System.out.println("ERROR: No puedes dejar campos vacios.");
                    break;
                }
                if (modelo.existeBebida(vista.txtNombre.getText())) {
                    System.out.println("ERROR: Ya existe una bebida con este nombre.");
                    break;
                }
            // Cogemos datos para convertirlos
                String nombre = vista.txtNombre.getText();
                String marca = vista.txtMarca.getText();
                double precio = Double.parseDouble(vista.txtPrecio.getText());
                int stock = Integer.parseInt(vista.txtStock.getText());
                double graduacion = Double.parseDouble(vista.txtGraduacion.getText());
                java.time.LocalDate fecha = vista.datePicker.getDate();

                if (vista.rbWhisky.isSelected()) {
                    modelo.altaWhisky(nombre, precio, marca, stock, fecha, graduacion);
                } else if (vista.rbRon.isSelected()) {
                    modelo.altaRon(nombre, precio, marca, stock, fecha, graduacion);
                } else if (vista.rbGinebra.isSelected()) {
                    modelo.altaGinebra(nombre, precio, marca, stock, fecha, graduacion);
                } else if (vista.rbVodka.isSelected()) {
                    modelo.altaVodka(nombre, precio, marca, stock, fecha, graduacion);
                }
                // Limpiamos y refrescamos IMPORTANTE
                limpiarCampos();
                refrescar();
                break;
        }

    }

    @Override public void windowOpened(WindowEvent e) {}
    @Override public void windowClosing(WindowEvent e) {

    }
////////////////////////////////////////////////////////////////////////////////////////////
    @Override public void windowClosed(WindowEvent e) {}
    @Override public void windowIconified(WindowEvent e) {}
    @Override public void windowDeiconified(WindowEvent e) {}
    @Override public void windowActivated(WindowEvent e) {}
    @Override public void windowDeactivated(WindowEvent e) {}
}

