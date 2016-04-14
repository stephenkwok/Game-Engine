package authoringenvironment.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import authoringenvironment.view.GUIActorEditingEnvironment;
import authoringenvironment.view.GUILevelEditingEnvironment;
import authoringenvironment.view.GUIMain;
import authoringenvironment.view.GUIMainScreen;
import gamedata.controller.CreatorController;
import gameengine.controller.Game;
import gameengine.controller.GameInfo;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gameengine.model.IAuthoringActor;
import gui.controller.IScreenController;
import gui.view.Screen;
import javafx.stage.Stage;

/**
 * This class serves as the interface that all authoring environment main
 * screens must implement
 * 
 * @author Stephen, AnnieTang
 */

public class Controller implements IScreenController {
	private Stage myStage;
	private List<Level> myLevels;
	private List<String> myLevelNames;
	private List<IAuthoringActor> myActors;
	private List<String> myActorNames;
	private GUILevelEditingEnvironment levelEnvironment;
	private GUIActorEditingEnvironment actorEnvironment;
	private GUIMainScreen mainScreen;
	private GUIMain guiMain;
	private ResourceBundle myResources;
	private Game game;
	private GameInfo gameInfo;

	public Controller(Stage myStage, GUIMain guiMain, ResourceBundle myResources) {
		this.myStage = myStage;
		this.guiMain = guiMain;
		this.myResources = myResources;
		init();
	}

	public void init() {
		myLevels = new ArrayList<>();
		myLevelNames = new ArrayList<>();
		myActors = new ArrayList<>();
		myActorNames = new ArrayList<>();
		levelEnvironment = new GUILevelEditingEnvironment(this, myActors);		
		gameInfo = new GameInfo();
		game = new Game(gameInfo, myLevels);
		actorEnvironment = new GUIActorEditingEnvironment(this, myResources);
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
		environment.setEditable(editable);
		try{
			actorEnvironment.updateRules();
			System.out.println(((IAuthoringActor)actorEnvironment.getEditable()).getActorRules());
		}catch(ConcurrentModificationException e){}
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
			controller = new CreatorController(g, this.getScreen());
			controller.saveForEditing(file);
		} catch (ParserConfigurationException e) {
			getScreen().showError(e.getMessage());
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
		newLevel.setMyID(myLevels.size());
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
		actorEnvironment.setActorImage(newActor.getImageView(), newActor.getMyImageViewName());
		goToEditingEnvironment(newActor, actorEnvironment);
	}
	/**
	 * Saves game and returns to splash screen of game player.
	 */
	public void goBackToGamePlayer() {
		guiMain.goBackToSplash();
	}
	
	public double getSceneWidth(){
		return guiMain.getWidth();
	}
	
	public double getSceneHeight(){
		return guiMain.getHeight();
	}

	@Override
	public void setGame(Game game) {
		// TODO Auto-generated method stub
	}

	@Override
	public Screen getScreen() {
		return guiMain;
	}

	@Override
	public void chooseGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToSplash() {
		guiMain.goBackToSplash();
	}

	@Override
	public void switchGame() {
		// TODO Auto-generated method stub
		
	}
}