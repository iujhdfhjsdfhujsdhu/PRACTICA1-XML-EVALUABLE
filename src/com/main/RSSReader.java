package com.main;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class RSSReader {
    static final private Logger LOGGER = Logger.getLogger("mx.com.hash.pruebaxml.PruebaXML");

    public static void main(String[] args) {
        String rssUrl = "https://www.youtube.com/feeds/videos.xml?channel_id=UCrBzBOMcUVV8ryyAU_c6P5g";

        try {
            // Cargar el documento XML desde la URL
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new URL(rssUrl).openStream());

            // Normalizar XML
            doc.getDocumentElement().normalize();

            // Extraer las entradas (tags <entry>)
            NodeList entryNodes = doc.getElementsByTagName("entry");
            List<VideoEntry> videos = new ArrayList<>();

            for (int i = 0; i < entryNodes.getLength(); i++) {
                Node node = entryNodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Element authorElement = (Element) element.getElementsByTagName("author").item(0);
                    // Extraer detalles del video
                    String title = element.getElementsByTagName("title").item(0).getTextContent();
                    String link = element.getElementsByTagName("link").item(0).getAttributes()
                            .getNamedItem("href").getNodeValue();
                    String publishedDate = element.getElementsByTagName("published").item(0).getTextContent();

                    String author = authorElement.getElementsByTagName("name").item(0).getTextContent();    

                    videos.add(new VideoEntry(title, link, publishedDate, author));
                }
            }

            // Generar XML con la lista de videos
            EjemploXML ejemploXML = new EjemploXML();
            Document documento = ejemploXML.crearDocumento(videos);

            // Convertir el documento a String y mostrarlo
            System.out.println(ejemploXML.convertirString(documento));

            // Escribir el archivo XML
            ejemploXML.escribirArchivo(documento, "25-11-2024.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
