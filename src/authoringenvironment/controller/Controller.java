package authoringenvironment.controller;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
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
import gamedata.controller.ChooserType;
import gamedata.controller.CreatorController;
import gamedata.controller.FileChooserController;
import gameengine.controller.Game;
import gameengine.controller.GameInfo;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gameplayer.controller.BranchScreenController;
import gui.view.Screen;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This class serves as the main controller for the authoring environment
 * 
 * @author Stephen, AnnieTang
 */

public class Controller extends BranchScreenController {
	
	private static final String EDITING_CONTROLLER_RESOURCE = "editingActions";
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
		this.guiMain = new GUIMain();
		this.myResources = ResourceBundle.getBundle(EDITING_CONTROLLER_RESOURCE);
		changeScreen(guiMain);
	}
	
	
	private void setUpScreen(){
		this.guiMain = new GUIMain();
		this.guiMain.addObserver(this);
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
	public void saveGame() {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showSaveDialog(new Stage());
		Game g = new Game(gameInfo, myLevels); 
		CreatorController controller;
		try {
			controller = new CreatorController(g, guiMain);
			controller.saveForEditing(file);
		} catch (ParserConfigurationException e) {
			guiMain.showError(e.getMessage());
		}

	}

	public void loadGame() {
		FileChooserController fileChooserController = new FileChooserController(myStage,ChooserType.EDIT);
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
		myLevelNames.add(newLevel.getName());
		mainScreen.createLevelLabel(newLevel);
		goToEditingEnvironment(newLevel, levelEnvironment);
	}

	public void addActor() {
		IAuthoringActor newActor = (IAuthoringActor) new Actor();
		newActor.setID(myActors.size());
		myActors.add(newActor);
		myActorNames.add(newActor.getName());
		mainScreen.createActorLabel(newActor);
		actorEnvironment.setActorImage(newActor.getImageView(), newActor.getImageViewName());
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
		String method = (String) arg;
		try {
			this.getClass().getDeclaredMethod(method).invoke(this);
		} catch (NoSuchMethodException e) {
			try {
				this.getClass().getSuperclass().getDeclaredMethod(method).invoke(this);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}


}