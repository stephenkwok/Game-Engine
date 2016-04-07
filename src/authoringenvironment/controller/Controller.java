package authoringenvironment.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import authoringenvironment.view.GUIActorEditingEnvironment;
import authoringenvironment.view.GUILevelEditingEnvironment;
import authoringenvironment.view.GUIMain;
import authoringenvironment.view.GUIMainScreen;
import gameengine.controller.Level;
import gameengine.model.Actor;
import javafx.stage.Stage;

/**
 * This class serves as the interface that all authoring environment main
 * screens must implement
 * 
 * @author Stephen, AnnieTang
 */

public class Controller implements Observer {
	private Stage myStage;
	private List<Level> levels;
	private List<Actor> actors;	
	private GUILevelEditingEnvironment levelEnvironment;
	private GUIActorEditingEnvironment actorEnvironment;
	private GUIMainScreen mainScreen;
	private GUIMain guiMain;
	private ResourceBundle myResources;

	public Controller(Stage myStage, GUIMain guiMain, ResourceBundle myResources) {
		this.myStage = myStage;
		this.guiMain = guiMain;
		this.myResources = myResources;
		init();
	}

	private void init() {
		levels = new ArrayList<>();
		actors = new ArrayList<>();
		levelEnvironment = new GUILevelEditingEnvironment(this, actors);
		actorEnvironment = new GUIActorEditingEnvironment(this, myResources);
		mainScreen = new GUIMainScreen(this, actorEnvironment, levelEnvironment);
	}

	/**
	 * Switches screen to appropriate editing environment
	 * 
	 * @param editable - Level or Actor to edit
	 * @param environment - Editing environment for editable 
	 */
	public void goToEditingEnvironment(IEditableGameElement editable, IEditingEnvironment environment) {
		environment.setEditable(editable);
		clearPanes();
		guiMain.setCenterPane(environment.getPane());
	}

	/**
	 * Switches screen to main screen
	 */
	public void goToMainScreen() {
		mainScreen.updateAllNodes();
		clearPanes();
		guiMain.setCenterPane(mainScreen.getPane());
	}

	/**
	 * Passes Actor and Level info to Game Data to be saved in XML file
	 * 
	 * @param file:
	 *            file to write to.
	 */
	public void saveGame(File file) {

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
		return levels;
	}

	/**
	 * For each level that is created, adds it to the running list in this
	 * class.
	 * 
	 * @param newLevel
	 */
	public void addLevel() {
		Level newLevel = new Level();
		levels.add(newLevel);
		mainScreen.createLevelLabel(newLevel);
		goToEditingEnvironment(newLevel, levelEnvironment);
	}
	
	public void addActor() {
		Actor newActor = new Actor();
		actors.add(newActor);
		mainScreen.createActorLabel(newActor);
		goToEditingEnvironment(newActor, actorEnvironment);
	}

	private void clearPanes() {
		guiMain.setBottomPane(null);
		guiMain.setCenterPane(null);
		guiMain.setLeftPane(null);
		guiMain.setRightPane(null);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
	}

}