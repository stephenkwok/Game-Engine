package authoringenvironment.controller;

import java.io.File;
import java.io.IOException;
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

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import authoringenvironment.model.ActorCopier;
import authoringenvironment.model.AuthoringEnvironmentRestorer;
import authoringenvironment.model.GamePreviewImageSetter;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import authoringenvironment.model.MainCharacterManager;
import authoringenvironment.model.PresetActorFactory;
import authoringenvironment.view.PopUpAuthoringHelpPage;
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
	private static final String EDITING_CONTROLLER_RESOURCE = "editingActions";
	private static final String REQUIRES_ARG = "RequiresArg";
	private static final String PRESET_ACTORS_RESOURCE = "presetActorsFactory";
	private List<Level> myLevels;
	private Map<IAuthoringActor, List<IAuthoringActor>> myActorMap;
	private LevelEditingEnvironment levelEnvironment;
	private ActorEditingEnvironment actorEnvironment;
	private ResourceBundle myResources, myObservableResource,  myPresetActorsResource;
	private Game game;
	private GameInfo gameInfo;
	private Scene myScene;
	private BorderPane myRoot;
	private GUIFactory factory;
	@SuppressWarnings("unused")
	private PopUpAuthoringHelpPage helpPage;
	private ActorCopier myActorCopier;
	private GameEditingEnvironment gameEditingEnvironment;
	private MainCharacterManager mainCharacterManager;
	private GamePreviewImageSetter myGamePreviewImageSetter;

	public Controller(Stage myStage) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		super(myStage, EDITING_CONTROLLER_RESOURCE);
		this.myObservableResource = ResourceBundle.getBundle(EDITING_CONTROLLER_RESOURCE);
		initNewGame();
	}

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
		myActorMap = new HashMap<>();
		gameInfo = new GameInfo(myActorMap);
		game = new Game(gameInfo, myLevels);
		initializeGeneralComponents();
		initializePresetActors();
		addDefaultLevel();
	}

	/**
	 * Adds a default level to the Game and directs the author to the Main
	 * Screen
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
		myLevels.stream().forEach(level -> gameEditingEnvironment.createLevelPreviewUnit(level));
		myActorMap.keySet().stream().forEach(actor -> gameEditingEnvironment.createActorPreviewUnit(actor));
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
		actorEnvironment = new ActorEditingEnvironment(myResources, getStage(), this);
		gameEditingEnvironment = new GameEditingEnvironment(this, getStage(), myLevels, gameInfo);
		mainCharacterManager = new MainCharacterManager(myLevels);
		myGamePreviewImageSetter = new GamePreviewImageSetter(gameInfo, myLevels);
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
		presetActors.stream().forEach(actor -> gameEditingEnvironment.createActorPreviewUnit(actor));
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
	 * @param editable:
	 *            Level or Actor to edit
	 * @param environment:
	 *            Editing environment for editable
	 */
	public void goToEditingEnvironment(IEditableGameElement editable, IEditingEnvironment environment) {
		environment.setEditableElement(editable);
		setCenterPane(environment.getPane());
	}

	/**
	 * Switches screen to main screen
	 */
	public void goToMainScreen() {
		gameEditingEnvironment.updatePreviewUnits();
		setCenterPane(gameEditingEnvironment.getPane());
	}

	/**
	 * Passes Actor and Level info to Game Data to be saved in XML file
	 */
	public void saveGame() {
		mainCharacterManager.updateMainCharacterListsForEachLevel();
		if (!mainCharacterManager.updateSuccessful()) return;
		myGamePreviewImageSetter.setGameImage();
		if (!myGamePreviewImageSetter.gameImageSetSuccessful()) return;
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
				e.printStackTrace();

			}
		}

	}

	/**
	 * Loads a previously saved game
	 */
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

	/**
	 * 
	 * @return the Game's map of Actors
	 */
	public Map<IAuthoringActor, List<IAuthoringActor>> getActorMap() {
		return myActorMap;
	}

	/**
	 * For each level that is created, adds it to the running list in this
	 * class.
	 * 
	 */
	public void addLevel() {
		Level newLevel = new Level();
		newLevel.setPlayPosition(myLevels.size());
		myLevels.add(newLevel);
		gameEditingEnvironment.createLevelPreviewUnit(newLevel);
		goToEditingEnvironment(newLevel, levelEnvironment);
	}

	/**
	 * Creates a new Actor and places it in the map of all created actors, sets
	 * the Actor's ID, creates a preview unit for that Actor on the Main Screen,
	 * and redirects the author to the Actor Editing Environment to edit that
	 * Actor
	 * 
	 */
	public void addActor() {
		IAuthoringActor newActor = (IAuthoringActor) new Actor();
		myActorMap.put(newActor, new ArrayList<>());
		newActor.setID(myActorMap.size());
		gameEditingEnvironment.createActorPreviewUnit(newActor);
		goToEditingEnvironment(newActor, actorEnvironment);
		actorEnvironment.setActorImage(newActor.getImageView(), newActor.getImageViewName());
	}

	/**
	 * Saves game and returns to splash screen of game player.
	 */

	public void goToSplash() {
		super.goToSplash();
	}

	/**
	 * Creates method to be called and invokes it
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void invoke(String methodName, Class[] parameterTypes, Object[] parameters) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = this.getClass().getDeclaredMethod(methodName, parameterTypes);
		Object parameter = parameters[0] == null ? null : parameters[0];
		System.out.println(method);
		if (parameter == null) method.invoke(this, null);
		else method.invoke(this, parameter);
	}
	
	/**
	 * Calls a method within the Controller depending on the Observable's class
	 */
	@Override
	public void update(Observable o, Object arg) {
		String className = o.getClass().getSimpleName();
		String method = myObservableResource.getString(className);
		Object[] parameters = new Object[] { arg };
		@SuppressWarnings("rawtypes")
		Class[] parameterTypes;
		if (Arrays.asList(myObservableResource.getString(REQUIRES_ARG).split(DELIMITER)).contains(className)) {
			parameterTypes = new Class[] { Object.class };
		} else {
			parameterTypes = new Class[] {};
		}
		try {
			invoke(method, parameterTypes, parameters);
		}
		catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generates pop up allowing user to input HUD Options
	 */
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

	/**
	 * Sets Actor Environment's editable to actor passed in and switches scene
	 * to Actor Environment
	 * 
	 * @param actor:
	 *            actor to be edited
	 */
	@SuppressWarnings("unused")
	private void goToActorEditing(Object actor) {
		goToEditingEnvironment((IEditableGameElement) actor, actorEnvironment);
	}

	/**
	 * Sets Level Environment's editable to level passed in and switches scene
	 * to Level Environment
	 * 
	 * @param level:
	 *            level to be edited
	 */
	@SuppressWarnings("unused")
	private void goToLevelEditing(Object level) {
		goToEditingEnvironment((IEditableGameElement) level, levelEnvironment);
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

	/**
	 * Sets the Game's HUD Info File
	 */
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

}