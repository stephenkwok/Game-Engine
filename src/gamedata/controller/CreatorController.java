package gamedata.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.InputSource;

import gamedata.EditXMLCreator;
import gamedata.GameXMLCreator;
import gamedata.XMLCreator;
import gameengine.model.ClickTrigger;
import gameplayer.view.BaseScreen;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import xmlTest.Actor;
import xmlTest.Level;
import xmlTest.MoveRight;
import xmlTest.Rule;
import xmlTest.Trigger;

public class CreatorController implements ICreatorController {
	
	List<Level> myGameLevels;
	BaseScreen myScreen;
	int myCurrentLevelNum;
	
	public CreatorController(int currentLevelNum, List<Level> gameLevels) { //, BaseScreen screen) {
		this.myGameLevels = gameLevels;
		//this.myScreen = screen;
		this.myCurrentLevelNum = currentLevelNum;
		
		//setUpFileChooser();
		
		try {
			saveForEditing(new File ("src/resources/test1.xml"));
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
		
		Level currentLevel = myGameLevels.get(myCurrentLevelNum);
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
		CreatorController c = new CreatorController (0 , levels);
		
		
		
	}
}
