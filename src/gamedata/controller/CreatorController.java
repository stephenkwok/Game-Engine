package gamedata.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import gamedata.XMLCreator;
import gameplayer.view.BaseScreen;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import xmlTest.Actor;
import xmlTest.GameInfo;
import xmlTest.Level;
import xmlTest.MoveRight;
import xmlTest.Rule;
import xmlTest.Trigger;

public class CreatorController implements ICreatorController {
	
	List<Level> myGameLevels;
	BaseScreen myScreen;
	GameInfo myGameInfo;
	
	public CreatorController(GameInfo gameInfo, List<Level> gameLevels) { //, BaseScreen screen) {
		this.myGameLevels = gameLevels;
		//this.myScreen = screen;
		this.myGameInfo = gameInfo;
		
		//setUpFileChooser();
		
		try {
			saveForEditing(new File ("src/resources/test2.xml"));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void saveForEditing(File file) throws ParserConfigurationException {
		
		File loaderFile = createLoaderFileFromFile(file);
		XMLCreator xmlCreator = new XMLCreator(loaderFile);
		
		for (Level level: this.myGameLevels) {
			xmlCreator.writeLevel(level);
		}
		
		xmlCreator.documentToFile();
		
		saveForPlaying(file,loaderFile);
	}

	@Override
	public void saveForPlaying(File file, File loaderFile) throws ParserConfigurationException {
		XMLCreator xmlCreator = new XMLCreator(file);
		Level currentLevel = myGameLevels.get(myGameInfo.getCurrentLevelNum());
		xmlCreator.writeGameInfo(myGameInfo);
		xmlCreator.writeLevel(currentLevel);
		xmlCreator.writeInitialFile(loaderFile);
		xmlCreator.documentToFile();
	}

	private void setUpFileChooser () {
		FileChooser fileChooser = new FileChooser();
		File file  = fileChooser.showSaveDialog(new Stage());
		if (file != null){
			try {
				saveForEditing(file);
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private File createLoaderFileFromFile (File f) {
		String loaderFileName = f.getName().replace(".xml", "_loader.xml");
		File loaderFile = new File(f.getParent() + "/" + loaderFileName);
		return loaderFile;
	}
	
	
	public static void main (String [] args) {

		List<Level> levels = new ArrayList<>();
		Level levelOne = new Level ();
		Actor actorOne = new Actor();
		actorOne.setMyId(1);
		ArrayList<Object> bleh = new ArrayList<>();
		bleh.add((double) 90);
		actorOne.addRule(new Rule(new Trigger(), new MoveRight(actorOne, bleh)));
		levelOne.addActor(actorOne);
		HashMap<String, List<Actor>> map = new HashMap<>();
		List<Actor> actors = new ArrayList<>();
		actors.add(actorOne);
		map.put("Click", actors);
		levels.add(levelOne);
		GameInfo gameInfo = new GameInfo("My Game", "default.jpg", "This is a test.", 0);
		CreatorController c = new CreatorController (gameInfo , levels);
		
		
		
	}
}
