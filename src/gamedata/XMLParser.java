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

public class XMLParser {
	
	private XStream myXStream;

	public XMLParser() {
		myXStream = new XStream();
		myXStream.autodetectAnnotations(true);
	}
	
	//TODO throw exceptions?
	
	public Object load(File file) {
		if (file == null || empty(file)) {
			return null;
		}
		else {
			String xml = convertFileToString(file);
			try {
				return myXStream.fromXML(xml);
			} catch (Exception e) {
				return null;
			}
		}
	}
	
	private String convertFileToString(File file) {
		Document document = convertFileToDocument(file);
		if (document == null) {
			return "";
		}
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer;
			transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(document), new StreamResult(writer));
			return writer.getBuffer().toString();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	private Document convertFileToDocument(File file) {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			document.getDocumentElement().normalize();
			return document;
		} catch (IOException | SAXException | ParserConfigurationException e) {
			return null;
		}
	}

	private boolean empty(File file) {
		String xml = convertFileToString(file);
		return xml.length() == 0;
	}

}
