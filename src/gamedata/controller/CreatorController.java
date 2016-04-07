package gamedata.controller;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import gamedata.XMLCreator;
import gameengine.controller.Game;
import gui.view.Screen;


public class CreatorController implements ICreatorController {
	
	private Game myGame;
	private XMLCreator myXMLCreator;
	private Screen myScreen;
	
	public CreatorController(Game game, Screen screen) throws ParserConfigurationException { //, BaseScreen screen) {
		this.myGame = game;
		this.myScreen = screen;
		this.myXMLCreator = new XMLCreator();
	}

	@Override
	public void saveForEditing(File file) {
		File loaderFile = createLoaderFileFromFile(file);
		try {
			this.myXMLCreator.saveGame(myGame, loaderFile);
			myGame.setInitialGameFile(loaderFile.getPath());
			saveForPlaying(file);
		} catch (SAXException | IOException | TransformerException | ParserConfigurationException e) {
			myScreen.showError(e.getMessage());
		} 
		
		

	}

	@Override
	public void saveForPlaying(File file) throws ParserConfigurationException {
		try {
			this.myXMLCreator.saveGame(myGame, file);
		} catch (SAXException | IOException | TransformerException e) {
			myScreen.showError(e.getMessage());
		}
	}

	private File createLoaderFileFromFile (File f) {
		String loaderFileName = f.getName().replace(".xml", "_loader.xml");
		File loaderFile = new File(f.getParent() + "/" + loaderFileName);
		return loaderFile;
	}
	

}
