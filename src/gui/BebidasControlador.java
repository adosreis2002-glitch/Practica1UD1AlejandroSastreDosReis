package gui;

import com.alejandrosdr.practica1ud1.base.Bebida;
import com.alejandrosdr.practica1ud1.util.Util;

import javax.swing.*; // importa clases de la biblioteca Swing de Java
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


    // Gestiona las acciones.
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

    // listerner = cambio
    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    private void addActionListener(ActionListener listener) {
        vista.btnNuevo.addActionListener(listener);

        vista.btnExportar.addActionListener(listener);
        vista.btnImportar.addActionListener(listener);
        vista.btnCerrar.addActionListener(listener);

        // Controlador pueda detectar cambios en "bebida"
        vista.rbWhisky.addActionListener(listener);
        vista.rbRon.addActionListener(listener);
        vista.rbGinebra.addActionListener(listener);
        vista.rbVodka.addActionListener(listener);
    }

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
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Limpiamos los campos
    private void limpiarCampos() {
        vista.txtNombre.setText("");
        vista.txtMarca.setText("");
        vista.txtPrecio.setText("");
        vista.txtStock.setText("");
        vista.txtGraduacion.setText("");
        vista.datePicker.setText("");
        vista.txtNombre.requestFocus(); // Pone seleccionado el ultimo campo
    }

    public void refrescar() {
        vista.txtAreaListado.setText("");

        for (Bebida unaBebida : modelo.getListaBebidas()) {
            vista.txtAreaListado.append(unaBebida.toString() + "\n"); // Jump
        }
    }

    @Override
    //se ejecuta cuando se realiza una accion en un componente conectado al listener.
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Nuevo":
                if (hayCamposVacios()) {
                    // V. Emergentes
                    JOptionPane.showMessageDialog(vista.frame, "No puedes dejar campos vacíos.",
                            "Atención", JOptionPane.WARNING_MESSAGE);
                    break;
                }
                if (modelo.existeBebida(vista.txtNombre.getText(), vista.txtMarca.getText())) {
                    JOptionPane.showMessageDialog(vista.frame, "Ya existe una bebida con este nombre y marca.",
                            "Bebida duplicada", JOptionPane.ERROR_MESSAGE);
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
                    modelo.altaWhisky(nombre, marca, precio, stock, graduacion, fecha);
                } else if (vista.rbRon.isSelected()) {
                    modelo.altaRon(nombre, marca, precio, stock, graduacion, fecha);
                } else if (vista.rbGinebra.isSelected()) {
                    modelo.altaGinebra(nombre, marca, precio, stock, graduacion, fecha);
                } else if (vista.rbVodka.isSelected()) {
                    modelo.altaVodka(nombre, marca, precio, stock, graduacion, fecha);
                }
                // Limpiamos y refrescamos IMPORTANTE
                limpiarCampos();
                refrescar();
                break;
            case "Exportar":
                // Exportar a XML
                if (modelo.exportarXML("inventario.xml")) {
                    JOptionPane.showMessageDialog(vista.frame, "Inventario exportado a XML correctamente.",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(vista.frame, "Error al crear el archivo XML.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "Importar":
                // Importar desde XML
                if (modelo.importarXML("inventario.xml")) {
                    refrescar(); // Actualiza el listado visual inferior
                    JOptionPane.showMessageDialog(vista.frame, "Inventario importado correctamente.",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(vista.frame, "Error al leer el archivo XML.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Cerrar":
                Util.confirmacionSalida(vista.frame);
                break;
        }
    }

    @Override public void windowOpened(WindowEvent e) {}
    @Override public void windowClosing(WindowEvent e) {}

////////////////////////////////////////////////////////////////////////////////////////////
    @Override public void windowClosed(WindowEvent e) {}
    @Override public void windowIconified(WindowEvent e) {} // Minimiza V.
    @Override public void windowDeiconified(WindowEvent e) {} // Restaura V.
    @Override public void windowActivated(WindowEvent e) {}
    @Override public void windowDeactivated(WindowEvent e) {}
}

