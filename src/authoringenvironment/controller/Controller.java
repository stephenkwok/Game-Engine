package authoringenvironment.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import authoringenvironment.view.ActorEditingEnvironment;
import authoringenvironment.view.LevelEditingEnvironment;
import authoringenvironment.view.GUIMain;
import authoringenvironment.view.GUIMainScreen;
import gamedata.controller.CreatorController;
import gameengine.controller.Game;
import gameengine.controller.GameInfo;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gameplayer.controller.BranchScreenController;
import gui.view.Screen;
import javafx.stage.Stage;

/**
 * This class serves as the main controller for the authoring environment
 * 
 * @author Stephen, AnnieTang
 */

public class Controller extends BranchScreenController {
	private Stage myStage;
	private List<Level> myLevels;
	private List<String> myLevelNames;
	private List<IAuthoringActor> myActors;
	private List<String> myActorNames;
	private LevelEditingEnvironment levelEnvironment;
	private ActorEditingEnvironment actorEnvironment;
	private GUIMainScreen mainScreen;
	private GUIMain guiMain;
	private ResourceBundle myResources;
	private Game game;
	private GameInfo gameInfo;

	public Controller(Stage stage) {
		super(stage);
	}
	
	//TODO This constructor is not parallel with other BranchScreenController subclasses
	//Create a resource bundle for the Controller's actions associated to buttons it handles, but the resource bundle for the GUIFactory should be stored in and set up in the GUIMain
	//Stage should not be contained in the subclass (already in BranchScreenController parent)
	public Controller(Stage myStage, GUIMain guiMain, ResourceBundle myResources) {
		super(myStage);
		this.myStage = myStage;
		this.guiMain = guiMain;
		this.myResources = myResources;
		init();
	}

	//TODO Need a constructor that takes in a game passed by data and sets up Authoring Environment accordingly 

	public void init() {
		myLevels = new ArrayList<>();
		myLevelNames = new ArrayList<>();
		myActors = new ArrayList<>();
		myActorNames = new ArrayList<>();
		levelEnvironment = new LevelEditingEnvironment(this, myActors);		
		gameInfo = new GameInfo();
		game = new Game(gameInfo, myLevels);
		actorEnvironment = new ActorEditingEnvironment(this, myResources);
		mainScreen = new GUIMainScreen(this, actorEnvironment, levelEnvironment, gameInfo, myActors);
	}
	
	/**
	 * Switches screen to appropriate editing environment
	 * 
	 * @param editable
	 *            - Level or Actor to edit
	 * @param environment
	 *            - Editing environment for editable
	 */
	public void goToEditingEnvironment(IEditableGameElement editable, IEditingEnvironment environment) {
		environment.setEditableElement(editable);
		guiMain.setCenterPane(environment.getPane()); 
	}

	/**
	 * Switches screen to main screen
	 */
	public void goToMainScreen() {
		mainScreen.updateAllNodes();
		guiMain.setCenterPane(mainScreen.getPane());
	}

	/**
	 * Passes Actor and Level info to Game Data to be saved in XML file
	 * 
	 * @param file:
	 *            file to write to.
	 */
	public void saveGame(File file) {
		Game g = new Game(gameInfo, myLevels); 
		CreatorController controller;
		try {
			controller = new CreatorController(g, guiMain);
			controller.saveForEditing(file);
		} catch (ParserConfigurationException e) {
			guiMain.showError(e.getMessage());
		}

	}

	public void loadGame(File file) {

	}

	/**
	 * Gets the current workspace's stage.
	 * 
	 * @return current workspace's stage.
	 */
	public Stage getStage() {
		return myStage;
	}

	/**
	 * Returns list of created levels.
	 * 
	 * @return
	 */
	public List<Level> getLevels() {
		return myLevels;
	}
	
	public List<String> getLevelNames(){
		return myLevelNames;
	}
	
	public List<String> getActorNames(){
		return myActorNames;
	}

	/**
	 * For each level that is created, adds it to the running list in this
	 * class.
	 * 
	 * @param newLevel
	 */
	public void addLevel() {
		Level newLevel = new Level();
		myLevels.add(newLevel);
		myLevelNames.add(newLevel.getMyName());
		mainScreen.createLevelLabel(newLevel);
		goToEditingEnvironment(newLevel, levelEnvironment);
	}

	public void addActor() {
		IAuthoringActor newActor = new Actor();
		newActor.setMyID(myActors.size());
		myActors.add(newActor);
		myActorNames.add(newActor.getMyName());
		mainScreen.createActorLabel(newActor);
		actorEnvironment.setActorImage(newActor.getMyImageView(), newActor.getMyImageViewName());
		goToEditingEnvironment(newActor, actorEnvironment);
	}
	
	public double getSceneWidth(){
		return guiMain.getWidth();
	}
	
	public double getSceneHeight(){
		return guiMain.getHeight();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		//Needs to handle buttons, comboboxes, tabs, etc.
		
	}


}