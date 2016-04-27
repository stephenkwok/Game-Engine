package authoringenvironment.controller;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import authoringenvironment.model.PresetActorFactory;
import authoringenvironment.view.ActorCopier;
import authoringenvironment.view.ActorEditingEnvironment;
import authoringenvironment.view.GUIMain;
import authoringenvironment.view.GUIMainScreen;
import authoringenvironment.view.GameEditingEnvironment;
import authoringenvironment.view.LevelEditingEnvironment;
import gamedata.controller.ChooserType;
import gamedata.controller.CreatorController;
import gamedata.controller.FileChooserController;
import gameengine.controller.Game;
import gameengine.controller.GameInfo;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gameplayer.controller.BranchScreenController;
import gui.view.GUIFactory;
import gui.view.IGUIElement;
import gui.view.PopUpAuthoringHelpPage;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import voogasalad.util.hud.source.*;

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
	private List<Level> myLevels;
	private List<String> myLevelNames;
	private Map<IAuthoringActor, List<IAuthoringActor>> myActorMap;
	private List<String> myActorNames;
	private LevelEditingEnvironment levelEnvironment;
	private ActorEditingEnvironment actorEnvironment;
	private GameEditingEnvironment gameEnvironment;
	private GUIMainScreen mainScreen;
	private GUIMain guiMain;
	private ResourceBundle myResources;
	private ResourceBundle myObservableResource;
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
		super(myStage);
		this.myObservableResource = ResourceBundle.getBundle(EDITING_CONTROLLER_RESOURCE);
		initNewGame();
	}

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
	}

	// TODO Need a constructor that takes in a game passed by data and sets up
	// Authoring Environment accordingly
	public Controller(Game game, Stage myStage) {
		super(myStage);
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
		myLevelNames = new ArrayList<>();
		myActorMap = new HashMap<>();
		myActorNames = new ArrayList<>();
		gameInfo = new GameInfo();
		gameInfo.setActorMap(myActorMap);
		game = new Game(gameInfo, myLevels);
		initializeGeneralComponents();
		initializePresetActors();
	}

	/**
	 * Initializes controller for previously created game
	 */
	public void initExistingGame() {
		myLevels = game.getLevels();
		gameInfo = game.getInfo();
		myActorMap = gameInfo.getActorMap();
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
		PresetActorFactory presetActorFactory = new PresetActorFactory();
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
		List<IAuthoringActor> refActor = new ArrayList(myActorMap.keySet());
		IAuthoringActor realRefActor = refActor.get(0);
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showSaveDialog(new Stage());
		CreatorController controller = new CreatorController(game, guiMain);
		if (file != null) {
			controller.saveForEditing(file);
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

	public List<String> getLevelNames() {
		return myLevelNames;
	}

	public Map<IAuthoringActor, List<IAuthoringActor>> getActorMap() {
		return myActorMap;
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
		newLevel.setPlayPosition(myLevels.size());
		myLevels.add(newLevel);
		myLevelNames.add(newLevel.getName());
		mainScreen.createLevelPreviewUnit(newLevel, levelEnvironment);
		goToEditingEnvironment(newLevel, levelEnvironment);
	}

	public void addActor() {
		IAuthoringActor newActor = (IAuthoringActor) new Actor();
		myActorMap.put(newActor, new ArrayList<>());
		newActor.setID(myActorMap.size());
		myActorNames.add(newActor.getName());
		mainScreen.createActorPreviewUnit(newActor, actorEnvironment);
		actorEnvironment.setActorImage(newActor.getImageView(), newActor.getImageViewName());
		goToEditingEnvironment(newActor, actorEnvironment);
		System.out.println(newActor.getID());
	}

	public double getSceneWidth() {
		return guiMain.getWidth();
	}

	public double getSceneHeight() {
		return guiMain.getHeight();
	}

	public void useGame() {
		// TODO Auto-generated method stub

	}

	/**
	 * Saves game and returns to splash screen of game player.
	 */
	/*
	 * public void goToSplash() { guiMain.goBackToSplash(); }
	 */

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
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

	public void updateRefActorSize(IAuthoringActor actor) {
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

	private void handleObservableGoToEditingEnvironmentCall(Object notifyObserversArgument) {
		List<Object> arguments = (List<Object>) notifyObserversArgument;
		IEditableGameElement editable = (IEditableGameElement) arguments.get(0);
		IEditingEnvironment environment = (IEditingEnvironment) arguments.get(1);
		goToEditingEnvironment(editable, environment);
	}

	public ActorEditingEnvironment getActorEditingEnvironment() {
		return actorEnvironment;
	}

	public LevelEditingEnvironment getLevelEditingEnvironment() {
		return levelEnvironment;
	}

	@Override
	public void setHUDInfoFile(String location) {
		game.setHUDInfoFile(location);
	}
	
	public Game getGame(){
		return game;
	}

}