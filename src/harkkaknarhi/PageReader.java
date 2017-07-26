/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkaknarhi;

import java.io.InputStream;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author Qnaerhi
 */
public class PageReader {
    
        
        public Document loadDocument() throws Exception {
        URL u = new URL("http://smartpost.ee/fi_apt.xml");
        URLConnection connection;
        connection = u.openConnection();

        Document doc = parseXML(connection.getInputStream());
        NodeList placeNodes = doc.getElementsByTagName("place");
        NodeList idNodes = doc.getElementsByTagName("code");
        NodeList cityNodes = doc.getElementsByTagName("city");
        NodeList addressNodes = doc.getElementsByTagName("address");
        NodeList openingNodes = doc.getElementsByTagName("availability");
        NodeList nameNodes = doc.getElementsByTagName("postoffice");
        NodeList latNodes = doc.getElementsByTagName("lat");
        NodeList lngNodes = doc.getElementsByTagName("lng");
        
        
       
        
        
        
            for(int i=0; i<placeNodes.getLength();i++)
            {
                String id = idNodes.item(i).getTextContent();
                int idn = parseInt(id);
                String a = cityNodes.item(i).getTextContent();
                String b = addressNodes.item(i).getTextContent();
                String c = nameNodes.item(i).getTextContent();
                String d = latNodes.item(i).getTextContent();
                float lat = Float.parseFloat(d);
                String f = lngNodes.item(i).getTextContent();
                float lng = Float.parseFloat(f);
                String g = openingNodes.item(i).getTextContent();
                

                
                Automaatti at = new Automaatti(idn,a,b,c,lat,lng,g);
                dataBase db = new dataBase();
                db.insertPoints(at);
            }
        
        
    return doc;
        
        }     
    

    private static Document parseXML(InputStream stream) throws Exception {
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);
            return doc;
        } catch(Exception ex) {
            throw ex;
        }      
    }
}