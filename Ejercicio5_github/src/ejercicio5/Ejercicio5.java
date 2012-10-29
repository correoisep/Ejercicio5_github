/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Ejercicio5 {

    public static void main(String[] args) {

        String nombreAtomo = args[0];

        try {

            elemento(nombreAtomo);


        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println("ERROR EN MAIN");
        }

    }

    public static void elemento(String nombreAtomo) throws ParserConfigurationException, SAXException, IOException {
        boolean encontrado = false;

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File("tabla_periodica.xml"));

            NodeList tablaPeriodica = doc.getElementsByTagName("ATOM");

            Element elementoTablaPeriodica = null;

            for (int i = 0; i < tablaPeriodica.getLength(); i++) {

                elementoTablaPeriodica = (Element) tablaPeriodica.item(i);

                if (nombreAtomo.equals(getPropiedadAtomo(elementoTablaPeriodica, "NAME"))) {

                    encontrado = true;
                    break;
                }

            }

            if (!encontrado) {
                System.otrytrut.println("No se ha encontrado el elemento");
            } else {
                System.out.println("Elemento: " + nombreAtomo);
                System.out.println("\n  -El Peso atómico es: " + getPropiedadAtomo(elementoTablaPeriodica, "ATOMIC_WEIGHT"));
                System.out.println("\n  -El Estado de Oxidacion atómico es: " + getPropiedadAtomo(elementoTablaPeriodica, "OXIDATION_STATES"));
                System.out.println("\n  -El Electronegatividad atómico es: " + getPropiedadAtomo(elementoTablaPeriodica, "ELECTRONEGATIVITY"));
                System.out.println("\n  -El Volumen atómico es: " + getPropiedadAtomo(elementoTablaPeriodica, "ATOMIC_VOLUME"));
                System.out.println(getAtributeAtomo(elementoTablaPeriodica, "ATOMIC_VOLUME", "UNITS"));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected static String getPropiedadAtomo(Element elementoTablaPeriodica, String nombrePropiedad) {

        NodeList propiedades = elementoTablaPeriodica.getChildNodes();

        for (int i = 0; i < propiedades.getLength(); i++) {

            if (propiedades.item(i).getNodeName().equals(nombrePropiedad)) {

                return propiedades.item(i).getFirstChild().getNodeValue();
            }

        }

        return null;
    }

//    protected static String getAtributeAtomo(Element elementoTablaPeriodica, String nombreAtributo)
//    {
//        return elementoTablaPeriodica.getAttribute(nombreAtributo);
//
//    }
    protected static String getAtributeAtomo(Element elementoTablaPeriodica, String nombrePropiedad, String nombreAtributo) {

        NodeList propiedades = elementoTablaPeriodica.getChildNodes();

        Node propiedad = null;
        for (int i = 0; i < propiedades.getLength(); i++) {

            if (propiedades.item(i).getNodeName().equals(nombrePropiedad)) {

                propiedad = propiedades.item(i);
                
                NamedNodeMap atributos = propiedad.getAttributes();
                
                System.out.println(atributos.getNamedItem(nombreAtributo));
            }

        }

        return propiedad.getNodeValue();
    }
}
