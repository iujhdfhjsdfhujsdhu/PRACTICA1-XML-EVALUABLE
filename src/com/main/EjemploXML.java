package com.main;


import java.io.File;
import java.io.StringWriter;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author david
 */
public class EjemploXML {
    
    public Document inicializarDocumento() throws ParserConfigurationException{
        Document documento;
        // Creamos los objectos de creacion de Documentos XML
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = docFactory.newDocumentBuilder();
        
        documento = constructor.newDocument();
        
        return documento;        
    }
    
    public String convertirString(Document documento) throws TransformerConfigurationException, TransformerException {
        // Creamos el objecto transformador
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        
        // Indicamos que queremos que idente el XML con 2 espacios
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        
        // Creamos el escritor a cadena de texto
        StringWriter writer = new StringWriter();
        // Fuente de datos, en este caso el documento XML
        DOMSource source = new DOMSource(documento);
        // Resultado, el cual se almacenara en el objecto writer
        StreamResult result = new StreamResult(writer);
        // Efectuamos la transformacion a lo que indica el objecto resultado, writer apuntara a el resultado
        transformer.transform(source, result);
        // Convertimos el buffer de writer en cadena de texto
        String output = writer.getBuffer().toString();

        return output;
    }
    
    public void escribirArchivo(Document documento, String fileName) throws TransformerConfigurationException, TransformerException {
        // Creamos el objecto transformador
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        
        // Indicamos que queremos que idente el XML con 2 espacios
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        // Archivo donde almacenaremos el XML
        File archivo = new File(fileName);

        // Fuente de datos, en este caso el documento XML
        DOMSource source = new DOMSource(documento);
        // Resultado, el cual almacena en el archivo indicado
        StreamResult result = new StreamResult(archivo);
        // Transformamos de ña fuente DOM a el resultado, lo que almacena todo en el archivo
        transformer.transform(source, result);
    }
    
    public Document crearDocumento(List<VideoEntry> videos) throws ParserConfigurationException {
        Document documento = this.inicializarDocumento();

        // Creamos el elemento raíz <videos>
        Element root = documento.createElement("videos");
        documento.appendChild(root);

        // Recorremos la lista de videos y los agregamos al XML
        for (VideoEntry video : videos) {
            // Creamos el elemento <video>
            Element videoElement = documento.createElement("video");
            root.appendChild(videoElement);

            // Creamos el Elemento de <titulo>
            Element titulo = documento.createElement("titulo");
            titulo.setTextContent(video.getTitle());
            videoElement.appendChild(titulo);

            // Creamos el Elemento de <link>
            Element link = documento.createElement("link");
            link.setTextContent(video.getLink());
            videoElement.appendChild(link);

            // Creamos el Elemento de <fecha>
            Element fecha = documento.createElement("fecha");
            fecha.setTextContent(video.getPublishedDate());
            videoElement.appendChild(fecha);

            // Creamos el Elemento de <autor>
            Element autor = documento.createElement("autor");
            autor.setTextContent(video.getAuthor());
            videoElement.appendChild(autor);
        }

        return documento;
    }
    
}