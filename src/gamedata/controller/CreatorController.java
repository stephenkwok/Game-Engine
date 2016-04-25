package gamedata.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import gamedata.XMLCreator;
import gameengine.controller.Game;
import gameengine.controller.GameInfo;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gameengine.model.Rule;
import gameengine.model.Actions.LoseGame;
import gameengine.model.Triggers.ClickTrigger;
import gui.view.Screen;

public class CreatorController implements ICreatorController {

	private Game myGame;
	private XMLCreator myXMLCreator;
	private Screen myScreen;

	public CreatorController(Game game, Screen screen) throws ParserConfigurationException {
		this.myGame = game;
		this.myScreen = screen;
		this.myXMLCreator = new XMLCreator();
	}

	public CreatorController(Game game) {
		this.myGame = game;
		this.myXMLCreator = new XMLCreator();
	}

	@Override
	public void saveForEditing(File file) {
		if (file == null) {
			return;
		}
		File loaderFile = createLoaderFileFromFile(file);
		try {
			myGame.setInitialGameFile(loaderFile.getPath());
			myGame.getInfo().setMyFile(loaderFile.getName());
			this.myXMLCreator.saveGame(myGame, loaderFile);
			myGame.setInitialGameFile(loaderFile.getPath());
			myGame.getInfo().setMyFile(file.getName());
			saveForPlaying(file);
		} catch (SAXException | IOException | TransformerException | ParserConfigurationException e) {
			myScreen.showError(e.getMessage());
		}

	}

	@Override
	public void saveForPlaying(File file) throws ParserConfigurationException {
		if (file == null) {
			return;
		}
		try {
			myGame.getInfo().setMyFile(file.getName());
			this.myXMLCreator.saveGame(myGame, file);
		} catch (SAXException | IOException | TransformerException e) {
			myScreen.showError(e.getMessage());
		}
	}

	private File createLoaderFileFromFile(File f) {
		String loaderFileName = f.getName().replace(".xml", "_loader.xml");
		File loaderFile = new File(f.getParent() + "/loaders/" + loaderFileName);
		return loaderFile;
	}

}