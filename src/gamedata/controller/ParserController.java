package gamedata.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import gamedata.XMLParser;
import gameengine.controller.Game;

public class ParserController implements IParserController {

	XMLParser myXMLParser;
	
	public ParserController () {
		this.myXMLParser = new XMLParser();
	}
	
	@Override
	public Game loadForEditing(File file) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		Game playingGame = loadforPlaying(file);
		return this.myXMLParser.extractGame(new File(playingGame.getInitialGameFile()));
	}

	@Override
	public Game loadforPlaying(File file) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		Game game = this.myXMLParser.extractGame(file);
		return game;
	}
	
	public static void main (String [] args) {
		ParserController p = new ParserController ();
		try {
			Game g = p.loadforPlaying(new File ("src/resources/test2.xml"));
			System.out.println(g.toString());
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
