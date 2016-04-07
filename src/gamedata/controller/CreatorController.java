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
	
	public CreatorController(Game game) { //, Screen screen) throws ParserConfigurationException { //, BaseScreen screen) {
		this.myGame = game;
		//this.myScreen = screen;
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
	
	
	public static void main(String[] args) {
		List<Level> levels = new ArrayList<>();
		Level levelOne = new Level ();
		Actor actorOne = new Actor();
		actorOne.setID(1);
		ArrayList<Object> bleh = new ArrayList<>();
		bleh.add((double) 90);
		actorOne.addRule(new Rule(new ClickTrigger(), new LoseGame(actorOne)));
		levelOne.addActor(actorOne);
		HashMap<String, List<Actor>> map = new HashMap<>();
		List<Actor> actors = new ArrayList<>();
		actors.add(actorOne);
		map.put("Click", actors);
		levels.add(levelOne);
		Game g = new Game(new GameInfo(), levels);
		CreatorController c;
		c = new CreatorController(g); //, new GUIMain(null, null));
		c.saveForEditing(new File ("src/resources/test.xml"));
	} 
	

}
