package usecases;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import gamedata.controller.IHighScoresController;

/**
 * See description of use case found on SampleSplash for further comments
 * @author michaelfigueiras
 *
 */
public class HighScoresController implements IHighScoresController {

	String myFile;
	
	public HighScoresController(String fileName) {
		myFile = fileName;
	}

	@Override
	public Map<String, Integer> getHighScores() {
		//TO BE IMPLEMENTED LATER
		return null;
	}

	@Override
	public void viewHighScores(Map<String, Integer> highScores) {
		//TO BE IMPLEMENTED LATER
		
	}

	@Override
	public void clearHighScores() throws SAXException, IOException {
		File file = new File(myFile);
		
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Document doc = docBuilder.parse (file);
        
		Node gameNode = doc.getElementsByTagName("game").item(0);
		NodeList myList = gameNode.getChildNodes();
		for(int x = 0; x < myList.getLength(); x++){
			Element name = (Element) myList.item(x);
			name.getParentNode().removeChild(name);
			
		}
		
		
	}

}
