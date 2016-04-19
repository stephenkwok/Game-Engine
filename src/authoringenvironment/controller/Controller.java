package authoringenvironment.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
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
import javafx.stage.Stage;

/**
 * This class serves as the main controller for the authoring environment
 * 
 * @author Stephen, AnnieTang
 */

public class Controller extends BranchScreenController implements Observer {
	private static final String EDITING_CONTROLLER_RESOURCE = "editingActions";
	
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
		this(stage, new GUIMain()); // pass GUIMain);
    	//TODO instantiate new GUIMain
		
	}
	
	public Controller(Stage stage, GUIMain guiMain) {
		super(stage);
		this.guiMain = guiMain;
		this.myResources = ResourceBundle.getBundle(EDITING_CONTROLLER_RESOURCE);
		changeScreen(guiMain);
		init();
	}
	
	//roughly what loading will look like
	public Controller(Stage stage, Game game) {
		this(stage);
		this.myLevels = game.getLevels();
		gameInfo = game.getInfo();
		this.game = game;
		mainScreen = new GUIMainScreen(this, actorEnvironment, levelEnvironment, gameInfo, myActors);
	}

	public void init() {
		myLevels = new ArrayList<>();
		myLevelNames = new ArrayList<>();
		myActors = new ArrayList<>();
		myActorNames = new ArrayList<>();
		levelEnvironment = new LevelEditingEnvironment(this, myActors);		
		gameInfo = new GameInfo();
		game = new Game(gameInfo, myLevels);
		actorEnvironment = new ActorEditingEnvironment(this, myResources); //change this resource to be not controller's resource
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
		Game g = new Game(new GameInfo(), myLevels);  //TODO needs to be game info from AE
		CreatorController controller;
		try {
			controller = new CreatorController(g, this.guiMain);
			controller.saveForEditing(file);
		} catch (ParserConfigurationException e) {
			guiMain.showError(e.getMessage());
		}

	}

	public void loadGame(File file) {

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
		
	}
}