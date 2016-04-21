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
import authoringenvironment.view.GUIMain;
import authoringenvironment.view.GUIMainScreen;
import authoringenvironment.view.GameEditingEnvironment;
import authoringenvironment.view.HBoxWithEditable;
import authoringenvironment.view.LevelEditingEnvironment;
import gamedata.controller.CreatorController;
import gameengine.controller.Game;
import gameengine.controller.GameInfo;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gui.controller.IScreenController;
import gui.view.ButtonFinish;
import gui.view.ButtonHome;
import gui.view.ButtonLoad;
import gui.view.ButtonNewActor;
import gui.view.ButtonNewLevel;
import gui.view.ButtonSave;
import gui.view.Screen;
import javafx.stage.Stage;

/**
 * This class serves as the main controller for the authoring environment
 * 
 * @author Stephen, AnnieTang
 */

public class Controller implements IScreenController, Observer {
	private Stage myStage;
	private List<Level> myLevels;
	private List<String> myLevelNames;
	private List<IAuthoringActor> myActors;
	private List<String> myActorNames;
	private LevelEditingEnvironment levelEnvironment;
	private ActorEditingEnvironment actorEnvironment;
	private GameEditingEnvironment gameEnvironment;
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
		levelEnvironment = new LevelEditingEnvironment(myActors, myStage);
		gameInfo = new GameInfo();
		game = new Game(gameInfo, myLevels);
		actorEnvironment = new ActorEditingEnvironment(this, myResources, myStage);
		gameEnvironment = new GameEditingEnvironment(gameInfo);
		mainScreen = new GUIMainScreen(gameEnvironment, myStage.widthProperty(), myStage.heightProperty(), myLevels,
				levelEnvironment);
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
		mainScreen.updatePreviewDisplays();
		guiMain.setCenterPane(mainScreen.getPane());
	}

	/**
	 * Passes Actor and Level info to Game Data to be saved in XML file
	 * 
	 * @param file:
	 *            file to write to.
	 */
	public void saveGame(File file) {
		Game g = new Game(new GameInfo(), myLevels); // TODO needs to be game
														// info from AE
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

	public List<String> getLevelNames() {
		return myLevelNames;
	}

	public List<String> getActorNames() {
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
		newLevel.setPlayPosition(myLevels.size());
		myLevelNames.add(newLevel.getMyName());
		mainScreen.createLevelLabel(newLevel, levelEnvironment).addObserver(this);
		goToEditingEnvironment(newLevel, levelEnvironment);
	}

	public void addActor() {
		IAuthoringActor newActor = new Actor();
		newActor.setMyID(myActors.size());
		myActors.add(newActor);
		myActorNames.add(newActor.getMyName());
		mainScreen.createActorLabel(newActor, actorEnvironment).addObserver(this);
		actorEnvironment.setActorImage(newActor.getMyImageView(), newActor.getMyImageViewName());
		goToEditingEnvironment(newActor, actorEnvironment);
	}

	public double getSceneWidth() {
		return guiMain.getWidth();
	}

	public double getSceneHeight() {
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

	/**
	 * Saves game and returns to splash screen of game player.
	 */
	@Override
	public void goToSplash() {
		guiMain.goBackToSplash();
	}

	@Override
	public void switchGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof HBoxWithEditable)
			handleObservableGoToEditingEnvironmentCall(arg1);
		else if (arg0 instanceof ButtonFinish)
			goToSplash();
		else if (arg0 instanceof ButtonHome)
			goToMainScreen();
		else if (arg0 instanceof ButtonNewActor)
			addActor();
		else if (arg0 instanceof ButtonNewLevel)
			addLevel();
		else if (arg0 instanceof ButtonLoad)
			loadGame((File) arg1);
		else if (arg0 instanceof ButtonSave)
			saveGame((File) arg1);
	}

	private void handleObservableGoToEditingEnvironmentCall(Object notifyObserversArgument) {
		List<Object> arguments = (List<Object>) notifyObserversArgument;
		IEditableGameElement editable = (IEditableGameElement) arguments.get(0);
		IEditingEnvironment environment = (IEditingEnvironment) arguments.get(1);
		goToEditingEnvironment(editable, environment);
	}
}