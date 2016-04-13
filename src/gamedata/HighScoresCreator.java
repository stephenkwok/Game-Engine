package gamedata;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.thoughtworks.xstream.XStream;

import gameengine.controller.HighScoresKeeper;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class HighScoresCreator {
	private XStream myXStream;
	
	public HighScoresCreator () {
		myXStream = new XStream();
		myXStream.autodetectAnnotations(true);
	}
	
	public void saveScore (HighScoresKeeper scoresKeeper, File file) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		String xml = this.myXStream.toXML(scoresKeeper);
		Document document = documentBuilder.parse(new InputSource(new StringReader(xml)));
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
