package gui;

import com.alejandrosdr.practica1ud1.base.Bebida;
import com.alejandrosdr.practica1ud1.base.Ginebra;
import com.alejandrosdr.practica1ud1.base.Ron;
import com.alejandrosdr.practica1ud1.base.Vodka;
import com.alejandrosdr.practica1ud1.base.Whisky;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.util.HashSet;


public class BebidasModelo {
    // Guardamos las bebidas
    public HashSet<Bebida> listaBebidas;

    public BebidasModelo() {
        listaBebidas = new HashSet<Bebida>();
        importarXML("inventario.xml"); // Cargamos nada mas iniciar los datos guardados la ultima vez
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

    // Crea <etiqueta>texto</etiqueta>
    private Element crearNodo(Document doc, String etiqueta, String texto) {
        Element nodo = doc.createElement(etiqueta);
        nodo.setTextContent(texto);
        return nodo;
    }

    // Lee texto de etiqueta
    private String leerTexto(Element padre, String etiqueta) {
        return padre.getElementsByTagName(etiqueta).item(0).getTextContent().trim();
    }

    public boolean exportarXML(String rutaArchivo) {
        try {
            // Documento XML vacío
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            // Nodo raíz
            Element raiz = doc.createElement("inventario");
            doc.appendChild(raiz);

            for (Bebida bebida : listaBebidas) {
                // Un nodo por bebida
                Element nodo = doc.createElement("bebida");
                // Tipo como atributo
                nodo.setAttribute("tipo", bebida.getClass().getSimpleName());

                // Campos hijos
                nodo.appendChild(crearNodo(doc, "nombre", bebida.getNombre()));
                nodo.appendChild(crearNodo(doc, "marca", bebida.getMarca()));
                nodo.appendChild(crearNodo(doc, "precio", String.valueOf(bebida.getPrecio())));
                nodo.appendChild(crearNodo(doc, "stock", String.valueOf(bebida.getStock())));
                nodo.appendChild(crearNodo(doc, "graduacion", String.valueOf(bebida.getGraduacionAlcoholica())));
                // Fecha formato ISO
                String fecha = bebida.getFechaEntradaInv() == null ? "" : bebida.getFechaEntradaInv().toString();
                nodo.appendChild(crearNodo(doc, "fecha", fecha));

                raiz.appendChild(nodo);
            }

            // Escribir el fichero
            Transformer transformador = TransformerFactory.newInstance().newTransformer();
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            transformador.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformador.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformador.transform(new DOMSource(doc), new StreamResult(new File(rutaArchivo)));
            return true;
        } catch (Exception e) {
            System.out.println("Error al exportar: " + e.getMessage());
            return false;
        }
    }

    public boolean importarXML(String rutaArchivo) {
        try {
            // Parsear el fichero
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(rutaArchivo));
            doc.getDocumentElement().normalize();

            // Todas las bebidas
            NodeList nodos = doc.getElementsByTagName("bebida");

            for (int i = 0; i < nodos.getLength(); i++) {
                Element nodo = (Element) nodos.item(i);

                // Leer cada campo
                String tipo = nodo.getAttribute("tipo");
                String nombre = leerTexto(nodo, "nombre");
                String marca = leerTexto(nodo, "marca");
                double precio = Double.parseDouble(leerTexto(nodo, "precio"));
                int stock = Integer.parseInt(leerTexto(nodo, "stock"));
                double graduacion = Double.parseDouble(leerTexto(nodo, "graduacion"));

                // Fecha vacía = hoy
                String textoFecha = leerTexto(nodo, "fecha");
                LocalDate fecha = textoFecha.isEmpty() ? LocalDate.now() : LocalDate.parse(textoFecha);

                // Crear según tipo
                if (tipo.equalsIgnoreCase("Whisky")) {
                    listaBebidas.add(new Whisky(nombre, marca, precio, stock, graduacion, fecha));
                } else if (tipo.equalsIgnoreCase("Ron")) {
                    listaBebidas.add(new Ron(nombre, marca, precio, stock, graduacion, fecha));
                } else if (tipo.equalsIgnoreCase("Ginebra")) {
                    listaBebidas.add(new Ginebra(nombre, marca, precio, stock, graduacion, fecha));
                } else if (tipo.equalsIgnoreCase("Vodka")) {
                    listaBebidas.add(new Vodka(nombre, marca, precio, stock, graduacion, fecha));
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error al importar: " + e.getMessage());
            return false;
        }
    }
}