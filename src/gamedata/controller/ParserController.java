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

	public ParserController() {
		this.myXMLParser = new XMLParser();

	}

	public ParserController(Screen screen) {
		this.myXMLParser = new XMLParser();
		this.myScreen = screen;
	}

	@Override
	public Game loadForEditing(File file) {
		Game playingGame = loadforPlaying(file);
		if (playingGame == null) {
			return null;
		}
		else {
			File editingFile = new File(playingGame.getInitialGameFile());
			return loadforPlaying(editingFile);
		}
	}

	@Override
	public Game loadforPlaying(File file) {
		Game game = null;
		try {
			game = this.myXMLParser.extractGame(file);
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//myScreen.showError(e.getMessage());
		}
		return game;
	}

}
