package gamedata.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import gamedata.XMLParser;
import xmlTest.Game;
import xmlTest.GameInfo;
import xmlTest.Level;

public class ParserController implements IParserController {

	XMLParser myXMLParser;
	
	public ParserController (File file) {
		try {
			this.myXMLParser = new XMLParser(file);
			loadforPlaying(file);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Level> loadForEditing(File file) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		XMLParser xmlparser = new XMLParser(file);
		return xmlparser.extractLevels();
	}

	@Override
	public Game loadforPlaying(File file) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		XMLParser xmlparser = new XMLParser(file);
		GameInfo myGameInfo = xmlparser.extractGameInfo();
		File loaderFile = xmlparser.extractLoaderFile();
		List<Level> myGameLevels = loadForEditing(loaderFile); 
		Level myCurrentLevel = xmlparser.extractCurrentLevel();
		myGameLevels.set(myGameInfo.getCurrentLevelNum(), myCurrentLevel);
		Game g = new Game(loaderFile.getPath(), myGameInfo, myGameLevels);
		System.out.println(g.toString());
		return g;
	}
	
	public static void main (String [] args) {
		ParserController p = new ParserController (new File ("src/resources/test2.xml"));
		
	}
	

}
