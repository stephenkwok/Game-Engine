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
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;




public class XMLCreator {

	private XStream myXStream;

	public XMLCreator () {
		myXStream = new XStream();
		myXStream.autodetectAnnotations(true);
	}

	public void save (Object object, File file) throws SAXException, IOException, TransformerException, ParserConfigurationException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		String xml = this.myXStream.toXML(object);
		System.out.println(xml);
		Document document = documentBuilder.parse(new InputSource(new StringReader(xml)));
		System.out.println("I WAS WONDERING");
		convertDocumentToFile(document, file);
		System.out.println("WHAT THE ERROR IS");
	}

	private void convertDocumentToFile (Document document, File file) throws TransformerException {

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