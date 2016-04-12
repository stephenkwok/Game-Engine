package gamedata;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Observable;
import java.util.Observer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;

import gameengine.controller.Game;



public class XMLCreator {

	private XStream myXStream;

	public XMLCreator () {
		myXStream = new XStream();
		myXStream.autodetectAnnotations(true);
	}

	public void saveGame (Game game, File file) throws SAXException, IOException, TransformerException, ParserConfigurationException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		String xml = this.myXStream.toXML(game);
		//System.out.println(xml);
		Document document = documentBuilder.parse(new InputSource(new StringReader(xml)));
		//document.getDocumentElement().normalize();
		convertDocumentToFile(document, file);
	}

	public void convertDocumentToFile (Document document, File file) throws TransformerException {

		TransformerFactory transformerFactory =
				TransformerFactory.newInstance();
		Transformer transformer =
				transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result =
				new StreamResult(file);
		transformer.transform(source, result);
	}




}
