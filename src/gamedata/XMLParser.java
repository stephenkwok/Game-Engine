package gamedata;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;

import xmlTest.GameInfo;
import xmlTest.Level;

public class XMLParser {
	
	private Document myDocument;
	private DocumentBuilder myDocumentBuilder;
	private DocumentBuilderFactory myDocumentBuilderFactory;
	private XStream myXStream;

	public XMLParser (File file) throws ParserConfigurationException, SAXException, IOException {
		myDocument = getDocument(file);
		myXStream = new XStream();
		myXStream.autodetectAnnotations(true);
	}
	
	public GameInfo extractGameInfo () throws TransformerException {
		Node gameInfoNode = myDocument.getElementsByTagName("xmlTest.GameInfo").item(0);
		GameInfo gameInfo = (GameInfo) myXStream.fromXML(convertNodeToString(gameInfoNode));
		return gameInfo;
	}	
	
	private String convertNodeToString (Node node) throws TransformerException {
		Document nodeDocument = myDocumentBuilder.newDocument();
		nodeDocument.appendChild(nodeDocument.importNode(node, true));
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(nodeDocument), new StreamResult(writer));
		String xml = writer.getBuffer().toString();
		return xml;
	}

	public List<Level> extractLevels () throws TransformerException {
		List<Level> myGameLevels = new ArrayList<>();

		NodeList levelsNodes = myDocument.getElementsByTagName("xmlTest.Level");
		for (int i=0; i<levelsNodes.getLength(); i++) {
			Node levelNode = levelsNodes.item(i);
			Level level = extractLevel(convertNodeToString(levelNode));
			myGameLevels.add(level);
		}
		
		return myGameLevels;
	}
	
	public Level extractCurrentLevel () throws TransformerException {
		Level currentLevel = extractLevel(convertNodeToString(myDocument.getElementsByTagName("xmlTest.Level").item(0)));
		return currentLevel;
	}
	
	public File extractLoaderFile () {
		Node filePathNode = myDocument.getElementsByTagName("loader").item(0);
		String filePath = filePathNode.getTextContent();
		return new File(filePath);
	}

	private Document getDocument(File file) throws ParserConfigurationException, SAXException, IOException {
		myDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
		myDocumentBuilder = myDocumentBuilderFactory.newDocumentBuilder();
		Document document = myDocumentBuilder.parse(file);
		document.getDocumentElement().normalize();
		return document;
	}
	
	public Level extractLevel(String xml) {
		Level level = (Level) myXStream.fromXML(xml);
		return level;
	}

	public void setFile(File file) throws ParserConfigurationException, SAXException, IOException {
		this.myDocument = getDocument(file);
		
	}
}
