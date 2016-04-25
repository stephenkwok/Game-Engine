package gamedata;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
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

import gameengine.controller.Game;

public class XMLParser {

	private XStream myXStream;

	public XMLParser() {
		myXStream = new XStream();
		myXStream.autodetectAnnotations(true);
	}

	public Game extractGame(File file)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
		String xml = convertFileToString(file);
		try {
			return (Game) myXStream.fromXML(xml);
		} catch (Exception e) {
			return null;
		}

	}

	private String convertFileToString(File file)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
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

}
