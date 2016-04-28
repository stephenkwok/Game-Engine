package authoringenvironment.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import authoringenvironment.model.*;
import authoringenvironment.view.*;
import gamedata.controller.*;
import gameengine.controller.*;
import gameengine.model.Actor;
import gameplayer.controller.BranchScreenController;
import gui.view.*;
import gui.view.PopUpAuthoringHelpPage;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import voogasalad.util.hud.source.IAuthoringHUDController;
import voogasalad.util.hud.source.PopupSelector;

/**
 * This class serves as the main controller for the authoring environment
 * 
 * @author Stephen, AnnieTang
 */

public class Controller extends BranchScreenController implements Observer, IAuthoringHUDController {
	private static final String GUI_RESOURCE = "authoringGUI";
	private static final String TOP_PANE_ELEMENTS = "TopPaneElements";
	private static final String DELIMITER = ",";
	private static final int WINDOW_HEIGHT = 700;
	private static final int WINDOW_WIDTH = 1300;
	private static final int PADDING = 10;
	private static final String SPLASH_IMAGE_NAME = "salad.png";
	private static final String EDITING_CONTROLLER_RESOURCE = "editingActions";
	private static final String REQUIRES_ARG = "RequiresArg";
	private static final String PRESET_ACTORS_RESOURCE = "presetActorsFactory";
	private List<Level> myLevels;
//	private List<String> myLevelNames;
	private Map<IAuthoringActor, List<IAuthoringActor>> myActorMap;
//	private List<String> myActorNames;
	private LevelEditingEnvironment levelEnvironment;
	private ActorEditingEnvironment actorEnvironment;
	private GameEditingEnvironment gameEnvironment;
	private GUIMainScreen mainScreen;
	private ResourceBundle myResources;
	private ResourceBundle myObservableResource;
	private ResourceBundle myPresetActorsResource;
	private Game game;
	private GameInfo gameInfo;
	private Scene myScene;
	private BorderPane myRoot;
	private GUIFactory factory;
	private Scene splashScene;
	private PopUpAuthoringHelpPage helpPage;
	private ActorCopier myActorCopier;

	public Controller(Stage myStage) throws NoSuchMethodException, SecurityException, IllegalAccessException,
	IllegalArgumentException, InvocationTargetException {
		super(myStage, EDITING_CONTROLLER_RESOURCE);
		this.myObservableResource = ResourceBundle.getBundle(EDITING_CONTROLLER_RESOURCE);
		initNewGame();
	}
/*
	// TODO This constructor is not parallel with other BranchScreenController
	// subclasses
	// Create a resource bundle for the Controller's actions associated to
	// buttons it handles, but the resource bundle for the GUIFactory should be
	// stored in and set up in the GUIMain
	// Stage should not be contained in the subclass (already in
	// BranchScreenController parent)
	public Controller(Stage myStage, GUIMain guiMain) {
		super(myStage);
		this.guiMain = guiMain;
		// initNewGame();
	}*/

	// TODO Need a constructor that takes in a game passed by data and sets up
	// Authoring Environment accordingly
	public Controller(Game game, Stage myStage) {
		super(myStage, EDITING_CONTROLLER_RESOURCE);
		this.game = game;
		initExistingGame();
	}

	/**
	 * Initializes Controller for newly created game
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public void initNewGame() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		myLevels = new ArrayList<>();
//		myLevelNames = new ArrayList<>();
		myActorMap = new HashMap<>();
//		myActorNames = new ArrayList<>();
		gameInfo = new GameInfo(myActorMap);
		game = new Game(gameInfo, myLevels);
		initializeGeneralComponents();
		initializePresetActors();
		addDefaultLevel();
	}
	
	/**
	 * Adds a default level to the Game and directs the author to the Main Screen
	 */
	private void addDefaultLevel() {
		addLevel();
		goToMainScreen();
	}

	/**
	 * Initializes controller for previously created game
	 */
	public void initExistingGame() {
		myLevels = game.getLevels();
		gameInfo = game.getInfo();
		myActorMap = gameInfo.getActorMap();
		AuthoringEnvironmentRestorer restorer = new AuthoringEnvironmentRestorer(myActorMap, myLevels);
		restorer.restoreActorsAndLevels();
		initializeGeneralComponents();
		myLevels.stream().forEach(level -> mainScreen.createLevelPreviewUnit(level, levelEnvironment));
		myActorMap.keySet().stream().forEach(actor -> mainScreen.createActorPreviewUnit(actor, actorEnvironment));
	}

	/**
	 * Initializes controller components that remain the same regardless of
	 * whether the Game to be edited is new or previously created
	 */
	public void initializeGeneralComponents() {
		myRoot = new BorderPane();
		myActorCopier = new ActorCopier();
		myScene = new Scene(myRoot, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);
		getStage().setScene(myScene);
		this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		this.myObservableResource = ResourceBundle.getBundle(EDITING_CONTROLLER_RESOURCE);
		this.myPresetActorsResource = ResourceBundle.getBundle(PRESET_ACTORS_RESOURCE);
		factory = new GUIFactory(myResources);
		levelEnvironment = new LevelEditingEnvironment(myActorMap, getStage(), this);
		gameEnvironment = new GameEditingEnvironment(gameInfo, getStage());
		actorEnvironment = new ActorEditingEnvironment(myResources, getStage(), this);
		mainScreen = new GUIMainScreen(gameEnvironment, this, getStage(), myLevels, levelEnvironment);
		setTopPane();
		setCenterPane();
	}

	/**
	 * Creates and displays preset actors
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private void initializePresetActors() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		PresetActorFactory presetActorFactory = new PresetActorFactory(myPresetActorsResource);
		List<Actor> presetActors = presetActorFactory.getPresetActors();
		presetActors.stream().forEach(actor -> myActorMap.put(actor, new ArrayList<>()));
		presetActors.stream().forEach(actor -> mainScreen.createActorPreviewUnit(actor, actorEnvironment));
	}

	/**
	 * Set center section of screen to given Pane
	 * 
	 * @param pane
	 */
	public void setCenterPane(Pane pane) {
		myRoot.setCenter(pane);
	}

	/**
	 * Set center screen to default, the home screen
	 */
	public void setCenterPane() {
		goToMainScreen();
	}

	/**
	 * Sets top section of screen to fixed toolbar
	 */
	private void setTopPane() {
		HBox hbox = new HBox(PADDING);
		hbox.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
		initializeTopPaneElements(hbox);
		hbox.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		myRoot.setTop(hbox);
	}

	/**
	 * Initialize elements to be in toolbar
	 * 
	 * @param hbox
	 */
	private void initializeTopPaneElements(HBox hbox) {
		try {
			String[] topPaneElements = myResources.getString(TOP_PANE_ELEMENTS).split(",");
			for (int i = 0; i < topPaneElements.length; i++) {
				IGUIElement elementToCreate = factory.createNewGUIObject(topPaneElements[i]);
				((Observable) elementToCreate).addObserver(this);
				hbox.getChildren().add(elementToCreate.createNode());
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Return Pane representation of authoring environment
	 */
	public Pane getPane() {
		return myRoot;
	}

	/**
	 * Return width of authoring environment Scene
	 * 
	 * @return
	 */
	public double getWidth() {
		return myScene.getWidth();
	}

	/**
	 * Return height of authoring environment Scene
	 * 
	 * @return
	 */
	public double getHeight() {
		return myScene.getHeight();
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
		setCenterPane(environment.getPane());
	}

	/**
	 * Switches screen to main screen
	 */
	public void goToMainScreen() {
		mainScreen.updatePreviewUnits();
		setCenterPane(mainScreen.getPane());
	}

	/**
	 * Passes Actor and Level info to Game Data to be saved in XML file
	 * 
	 * @param file:
	 *            file to write to.
	 */
	public void saveGame() {
		// TODO implement incomplete game error checking
		gameInfo.setMyImageName(myLevels.get(0).getMyBackgroundImgName());
		List<IAuthoringActor> refActor = new ArrayList(myActorMap.keySet());
		IAuthoringActor realRefActor = refActor.get(0);
		FileChooser fileChooser = new FileChooser();
		File initialDirectory = new File("gamefiles");
		fileChooser.setInitialDirectory(initialDirectory);
		File file = fileChooser.showSaveDialog(new Stage());
		CreatorController controller = new CreatorController(new Game(gameInfo, myLevels));
		if (file != null) {
			try {
				controller.saveForEditing(file);
			} catch (SAXException | IOException | TransformerException | ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}

	}

	public void loadGame() {
		FileChooserController fileChooserController = new FileChooserController(getStage(), ChooserType.EDIT);
	}

	/**
	 * Returns list of created levels.
	 * 
	 * @return
	 */
	public List<Level> getLevels() {
		return myLevels;
	}

//	public List<String> getLevelNames() {
//		return myLevelNames;
//	}

	public Map<IAuthoringActor, List<IAuthoringActor>> getActorMap() {
		return myActorMap;
	}

//	public List<String> getActorNames() {
//		return myActorNames;
//	}

	/**
	 * For each level that is created, adds it to the running list in this
	 * class.
	 * 
	 * @param newLevel
	 */
	public void addLevel() {
		Level newLevel = new Level();
		newLevel.setPlayPosition(myLevels.size());
		myLevels.add(newLevel);
//		myLevelNames.add(newLevel.getName());
		mainScreen.createLevelPreviewUnit(newLevel, levelEnvironment);
		goToEditingEnvironment(newLevel, levelEnvironment);
	}

	/**
	 * Creates a new Actor and places it in the map
	 * of all created actors, sets the Actor's ID, creates a preview unit
	 * for that Actor on the Main Screen, and redirects the author to
	 * the Actor Editing Environment to edit that Actor
	 * 
	 */
	public void addActor() {
		IAuthoringActor newActor = (IAuthoringActor) new Actor();
		myActorMap.put(newActor, new ArrayList<>());
		newActor.setID(myActorMap.size());
//		myActorNames.add(newActor.getName());
		mainScreen.createActorPreviewUnit(newActor, actorEnvironment);
		actorEnvironment.setActorImage(newActor.getImageView(), newActor.getImageViewName());
		goToEditingEnvironment(newActor, actorEnvironment);
//		System.out.println(newActor.getID());
	}

	public void useGame() {
		// TODO Auto-generated method stub

	}

	/**
	 * Saves game and returns to splash screen of game player.
	 */
	
	  public void goToSplash() {
		  super.goToSplash();
	  }
	 

	public void switchGame() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Observable o, Object arg) {
		String className = o.getClass().getSimpleName();
		Method method;
		try {
			if (Arrays.asList(myObservableResource.getString(REQUIRES_ARG).split(DELIMITER)).contains(className)) {
				method = this.getClass().getDeclaredMethod(myObservableResource.getString(className), Object.class);
				method.invoke(this, arg);
			} else {
				Class noparams[] = {};
				method = this.getClass().getDeclaredMethod(myObservableResource.getString(className), noparams);
				method.invoke(this, null);
			}
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private void displayHUDOptions() {
		PopupSelector selector = new PopupSelector(this);
	}

	private void displayHelp(Object arg) {
		helpPage = new PopUpAuthoringHelpPage((String) arg);
	}

	public void updateActors(Actor actor) {
		myActorCopier.setReferenceActor(actor);
		List<IAuthoringActor> listToUpdate = myActorMap.get(actor);
		for (int i = 0; i < listToUpdate.size(); i++) {
			Actor toUpdate = (Actor) listToUpdate.get(i);
			myActorCopier.copyActor(toUpdate, actor);
		}
	}

	public void updateRefActor(IAuthoringActor actor) {
		for (IAuthoringActor refActor : myActorMap.keySet()) {
			if (myActorMap.get(refActor).contains(actor)) {
				refActor.setSize(actor.getSize());
				refActor.setRotate(actor.getRotate());
				refActor.setOpacity(actor.getOpacity());
				refActor.setScaleX(actor.getScaleX());
				refActor.setScaleY(actor.getScaleY());
				updateActors((Actor) refActor);
			}
		}
	}

	@SuppressWarnings("unused")
	private void handleObservableGoToEditingEnvironmentCall(Object notifyObserversArgument) {
		@SuppressWarnings("unchecked")
		List<Object> arguments = (List<Object>) notifyObserversArgument;
		IEditableGameElement editable = (IEditableGameElement) arguments.get(0);
		IEditingEnvironment environment = (IEditingEnvironment) arguments.get(1);
		goToEditingEnvironment(editable, environment);
	}

	/**
	 * 
	 * @return the Authoring Environment's ActorEditingEnvironment
	 */
	public ActorEditingEnvironment getActorEditingEnvironment() {
		return actorEnvironment;
	}

	/**
	 * 
	 * @return @return the Authoring Environment's LevelEditingEnvironment
	 */
	public LevelEditingEnvironment getLevelEditingEnvironment() {
		return levelEnvironment;
	}

	@Override
	public void setHUDInfoFile(String location) {
		game.setHUDInfoFile(location);
	}

	/**
	 * @return the Game
	 */
	public Game getGame() {
		return game;
	}

	@Override
	public void invoke(String method, Class[] parameterTypes, Object[] parameters) {
		// TODO Auto-generated method stub
		
	}

}