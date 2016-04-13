package gamedata;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;

import gameengine.controller.HighScoresKeeper;

/**
 * The purpose of this class is to serve as the public interface for the HighScoresController, which will take information from an XML file to display high scores.
 * @author cmt57
 *
 */


public class HighScoresParser {
	
	private XStream myXStream;
	
	public HighScoresParser () { 
		myXStream = new XStream();
		myXStream.autodetectAnnotations(true);
	}
	
	/**
	 * Will read through an XML file of the form highScores.XML and produce a map linking a game type to a map of that game's users linked to their high scores.
	 * @param filename a string name representing the file of high scores to read from
	 * @return a map of games to their high scores maps (linking user to score)
	 * @throws TransformerException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public Map<String, Map<String, Integer>> getHighScoreInfo (File file) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		HighScoresKeeper highScoresKeeper;
		if (checkIfFileEmpty(file)) {
			highScoresKeeper = new HighScoresKeeper();
		}
		else {
			String xml = convertFileToString(file);
			highScoresKeeper = (HighScoresKeeper) myXStream.fromXML(xml);
		}
		return highScoresKeeper.getMyScores();
	}
	
	private String convertFileToString(File file) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		Document document = convertFileToDocument(file);
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(document), new StreamResult(writer));
		String xml = writer.getBuffer().toString();
		return xml;
	}
	
	private Document convertFileToDocument(File file) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(file);
		document.getDocumentElement().normalize();
		return document;
	}
	
	private boolean checkIfFileEmpty(File file) throws ParserConfigurationException, SAXException, IOException, TransformerException{
		String xml = convertFileToString(file);
		if (xml.length() == 0) {
			return true;
		}
		else {
			return false;
		}
	}

}
