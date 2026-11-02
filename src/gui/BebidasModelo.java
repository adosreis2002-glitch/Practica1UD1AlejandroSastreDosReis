package gui;

import com.alejandrosdr.practica1ud1.base.Bebida;
import com.alejandrosdr.practica1ud1.base.Ginebra;
import com.alejandrosdr.practica1ud1.base.Ron;
import com.alejandrosdr.practica1ud1.base.Vodka;
import com.alejandrosdr.practica1ud1.base.Whisky;

import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;


public class BebidasModelo {
    // Guardamos las bebidas
    public HashSet<Bebida> listaBebidas;

    public BebidasModelo() {
        listaBebidas = new HashSet<Bebida>();
        importarJSON("inventario.json"); // Cargamos nada mas iniciar los datos guardados la ultima vez
    }

    // Back controlador
    public HashSet<Bebida> getListaBebidas() {
        return listaBebidas;
    }

    public boolean existeBebida(String nombre, String marca) {
        for (Bebida unaBebida : listaBebidas) {
            if (unaBebida.getNombre().equalsIgnoreCase(nombre) &&
                    unaBebida.getMarca().equalsIgnoreCase(marca)) {
                return true;
            }
        }
        return false;
    }

    public void altaWhisky(String nombre, String marca, double precio, int stock, double graduacion, LocalDate fecha) {
        Whisky nuevoWhisky = new Whisky(nombre, marca, precio, stock, graduacion, fecha);
        listaBebidas.add(nuevoWhisky);
    }

    public void altaRon(String nombre, String marca, double precio, int stock, double graduacion, LocalDate fecha) {
        Ron nuevoRon = new Ron(nombre, marca, precio, stock, graduacion, fecha);
        listaBebidas.add(nuevoRon);
    }

    public void altaGinebra(String nombre, String marca, double precio, int stock, double graduacion, LocalDate fecha) {
        Ginebra nuevaGinebra = new Ginebra(nombre, marca, precio, stock, graduacion, fecha);
        listaBebidas.add(nuevaGinebra);
    }

    public void altaVodka(String nombre, String marca, double precio, int stock, double graduacion, LocalDate fecha) {
        Vodka nuevoVodka = new Vodka(nombre, marca, precio, stock, graduacion, fecha);
        listaBebidas.add(nuevoVodka);
    }

    public boolean exportarJSON(String rutaArchivo) {
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(rutaArchivo));
            escritor.println("[");

            boolean primero = true; // Controla la coma separadora

            for (Bebida bebida : listaBebidas) {
                if (!primero) {
                    escritor.println(",");
                }

                // String.format
                String jsonBebida = String.format(java.util.Locale.US,
                        "\t{\n" +
                                "\t\t\"tipo\": \"%s\",\n" +
                                "\t\t\"nombre\": \"%s\",\n" +
                                "\t\t\"marca\": \"%s\",\n" +
                                "\t\t\"precio\": %.2f,\n" +
                                "\t\t\"stock\": %d,\n" +
                                "\t\t\"graduacion\": %.2f\n" +
                                "\t}",
                        bebida.getClass().getSimpleName(),
                        bebida.getNombre(),
                        bebida.getMarca(),
                        bebida.getPrecio(),
                        bebida.getStock(),
                        bebida.getGraduacionAlcoholica()
                );

                escritor.print(jsonBebida);
                primero = false;
            }

            escritor.println("\n]");
            escritor.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error al exportar: " + e.getMessage());
            return false;
        }
    }

    public boolean importarJSON(String rutaArchivo) {
        try {
            // Abrimos el lector de texto para leer el archivo linea por linea
            BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo));
            String linea;

            // Aqui hay unas variables temporales las cuales acumulan los datos de cada bebida mientras se leen
            String tipo = "", nombre = "", marca = "";
            double precio = 0.0, graduacion = 0.0;
            int stock = 0;

            // Bucle que recorre el archivo hasta que no quedan lineas (null)
            while ((linea = lector.readLine()) != null) {
                linea = linea.trim(); // Se limpia los espacios en blanco a los lados para evitar errores

                // Si la linea contiene el tipo, se extrae y se limpia (comillas/comas)
                if (linea.contains("\"tipo\":")) {
                    tipo = linea.split(":")[1].replace("\"", "").replace(",", "").trim();
                }
                // Si contiene nombre se extrae
                else if (linea.contains("\"nombre\":")) {
                    nombre = linea.split(":")[1].replace("\"", "").replace(",", "").trim();
                }
                // Si contiene marca se extrae
                else if (linea.contains("\"marca\":")) {
                    marca = linea.split(":")[1].replace("\"", "").replace(",", "").trim();
                }
                // Si contiene precio, se convierte de texto double
                else if (linea.contains("\"precio\":")) {
                    precio = Double.parseDouble(linea.split(":")[1].replace(",", "").trim());
                }
                // Si contiene stock se convierte a int
                else if (linea.contains("\"stock\":")) {
                    stock = Integer.parseInt(linea.split(":")[1].replace(",", "").trim());
                }
                // Si contiene graduación se convierte a double
                else if (linea.contains("\"graduacion\":")) {
                    graduacion = Double.parseDouble(linea.split(":")[1].replace(",", "").trim());
                }
                // Al llegar al cierre de la llave de un objeto JSON ya estan todos los datos listos
                else if (linea.equals("}") || linea.equals("},")) {
                    LocalDate fechaPorDefecto = LocalDate.now(); // Fecha por defecto al importar

                    // Creamos el objeto correspondiente segun el tipo leido y lo agregamos al HashSet
                    if (tipo.equalsIgnoreCase("Whisky")) {
                        listaBebidas.add(new Whisky(nombre, marca, precio, stock, graduacion, fechaPorDefecto));
                    } else if (tipo.equalsIgnoreCase("Ron")) {
                        listaBebidas.add(new Ron(nombre, marca, precio, stock, graduacion, fechaPorDefecto));
                    } else if (tipo.equalsIgnoreCase("Ginebra")) {
                        listaBebidas.add(new Ginebra(nombre, marca, precio, stock, graduacion, fechaPorDefecto));
                    } else if (tipo.equalsIgnoreCase("Vodka")) {
                        listaBebidas.add(new Vodka(nombre, marca, precio, stock, graduacion, fechaPorDefecto));
                    }
                }
            }
            lector.close(); // Cerramos archivo
            return true;
        } catch (Exception e) {
            System.out.println("Error al importar: " + e.getMessage());
            return false;
        }
    }
}