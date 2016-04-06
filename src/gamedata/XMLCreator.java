package gamedata;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;

import xmlTest.Level;



public class XMLCreator {
	
	private File myFile;
	private Document myDocument;
	private DocumentBuilderFactory myDocumentBuilderFactory;
	private DocumentBuilder myDocumentBuilder;
	
	public XMLCreator (File file) {
		myFile = file;
		setUpDocument();
	}
	
	protected void setUpDocument () {
		myDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			myDocumentBuilder = myDocumentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        myDocument = myDocumentBuilder.newDocument();
        
        Element root = myDocument.createElement("game");
        myDocument.appendChild(root);
    }

	
	public void writeLevel (Level level) {
		XStream xstream = new XStream();
		xstream.alias("level", Level.class);
		String xml = xstream.toXML(level);
		Document levelDocument;
		try {
			levelDocument = myDocumentBuilder.parse(new InputSource(new StringReader(xml)));
			myDocument.getFirstChild().appendChild(myDocument.importNode(levelDocument.getFirstChild(),true));
		} catch (SAXException | IOException e) {
			// TODO FIX ERROR CATCHING
			e.printStackTrace();
		}
		
	}
	
	
	public void writeInitialFile (File file) {
		Element loader = myDocument.createElement("loader");
		Text loaderText = myDocument.createTextNode(file.getName());
		myDocument.getFirstChild().appendChild(loader).appendChild(loaderText);
	}
	
	public void documentToFile () {
	      try {
	            TransformerFactory transformerFactory =
	                    TransformerFactory.newInstance();
	            Transformer transformer =
	                    transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(myDocument);
	            StreamResult result =
	                    new StreamResult(myFile);
	            transformer.transform(source, result);

	        }
	        catch (Exception e) {
	        	// TODO FIX ERROR CATCHING
	            e.printStackTrace();
	        }
	}

	
	

}
