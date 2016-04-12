package gamedata.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import gamedata.XMLParser;
import gameengine.controller.Game;
import gui.view.Screen;

public class ParserController implements IParserController {

	private XMLParser myXMLParser;
	private Screen myScreen;
	
	
	public ParserController () {
		this.myXMLParser = new XMLParser();
		
	}
	public ParserController (Screen screen) {
		this.myXMLParser = new XMLParser();
		this.myScreen = screen;
	}
	
	@Override
	public Game loadForEditing(File file) {
		Game editingGame;
		try {
			Game playingGame = loadforPlaying(file);
			editingGame = this.myXMLParser.extractGame(new File(playingGame.getInitialGameFile()));
			editingGame.initCurrentActors();
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			myScreen.showError(e.getMessage());
			editingGame = null;
		}
		return editingGame;
	}

	@Override
	public Game loadforPlaying(File file) {
		Game game;
		try {
			game = this.myXMLParser.extractGame(file);
			game.initCurrentActors();
			game.initTimeline();
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			myScreen.showError(e.getMessage());
			game = null;
		}
		return game;
	}
	
	
	public static void main (String [] args) {
		ParserController p = new ParserController ();
		Game g = p.loadforPlaying(new File ("gamefiles/test.xml"));
		System.out.println(g.toString());

		
	}
	

}
