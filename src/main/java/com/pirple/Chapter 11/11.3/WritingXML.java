//package writingxml;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class WritingXML {
    public static void main(String[] args) {
        File xmlFile = new File("C:\\Users\\Ol\\IdeaProjects\\Pirple\\src\\main\\java\\com\\pirple\\Chapter 11\\11.3\\cars.xml");
        Document dom = LoadXMLDocument(xmlFile);
        
        NodeList owners = dom.getElementsByTagName("owner");
        for(int i = 0; i < owners.getLength(); i++)
        {
            Element owner = (Element)owners.item(i);
            owner.setAttribute("name", "Mike222");
        }
        
        WriteXMLDocument(dom, xmlFile);
    }
    
    private static void WriteXMLDocument(Document doc, File destination)
    {
        try{
            // Write doc to destination file here...
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            StreamResult result = new StreamResult(destination);
            DOMSource source = new DOMSource(doc);
            
            transformer.transform(source, result);
        }
        catch(TransformerConfigurationException e)
        {
            System.err.println("XML writing failed.");
        }
        catch(TransformerException e)
        {
            System.err.println("XML writing failed.");
        }
    }
    
    private static Document LoadXMLDocument(File source)
    {
        Document dom = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            dom = builder.parse(source);
        }
        catch (ParserConfigurationException e) {
             System.err.println("XML loading failed.");
        } 
        catch (SAXException e) {
             System.err.println("XML loading failed.");
        } 
        catch (IOException e) {
            System.err.println("XML loading failed.");
        }
        
        return dom;
    }
}
