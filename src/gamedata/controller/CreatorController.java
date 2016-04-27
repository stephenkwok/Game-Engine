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

	public CreatorController(Game game) {
		this.myGame = game;
		this.myXMLCreator = new XMLCreator();
	}

	@Override
	public void saveForEditing(File file) throws SAXException, IOException, TransformerException, ParserConfigurationException {
		File loaderFile = createLoaderFileFromFile(file);
		myGame.setInitialGameFile(loaderFile.getPath());
		myGame.getInfo().setMyFile(loaderFile.getName());
		this.myXMLCreator.save(myGame, loaderFile);
		myGame.setInitialGameFile(loaderFile.getPath());
		myGame.getInfo().setMyFile(file.getName());
		saveForPlaying(file);

	}
	
	public void saveForPreviewing(File file) throws SAXException, IOException, TransformerException, ParserConfigurationException {
		this.myXMLCreator.save(myGame, file);
	}

	@Override
	public void saveForPlaying(File file) throws ParserConfigurationException, SAXException, IOException, TransformerException {
			myGame.getInfo().setMyFile(file.getName());
			this.myXMLCreator.save(myGame, file);
	}

	private File createLoaderFileFromFile(File f) {
		String loaderFileName = f.getName().replace(".xml", "_loader.xml");
		File loaderFile = new File(f.getParent() + "/loaders/" + loaderFileName);
		return loaderFile;
	}

}