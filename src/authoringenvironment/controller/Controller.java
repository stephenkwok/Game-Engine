package authoringenvironment.controller;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import authoringenvironment.model.ImageEditingEnvironment;
import authoringenvironment.model.PresetActorFactory;
import authoringenvironment.view.ActorEditingEnvironment;
import authoringenvironment.view.GUIMain;
import authoringenvironment.view.GUIMainScreen;
import authoringenvironment.view.GameEditingEnvironment;
import authoringenvironment.view.LevelEditingEnvironment;
import authoringenvironment.view.PreviewUnitWithEditable;
import gamedata.controller.ChooserType;
import gamedata.controller.CreatorController;
import gamedata.controller.FileChooserController;
import gameengine.controller.Game;
import gameengine.controller.GameInfo;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gameengine.model.IPlayActor;
import gameengine.model.Rule;
import gameengine.model.Actions.Action;
import gameengine.model.Triggers.ITrigger;
import gameplayer.controller.BranchScreenController;
import gui.view.ButtonFinish;
import gui.view.ButtonHUDOptions;
import gui.view.ButtonHelpPage;
import gui.view.ButtonHome;
import gui.view.ButtonLoad;
import gui.view.ButtonNewActor;
import gui.view.ButtonNewLevel;
import gui.view.ButtonSave;
import gui.view.ButtonSplash;
import gui.view.GUIFactory;
import gui.view.IGUIElement;
import gui.view.PopUpAuthoringHelpPage;
import gui.view.TextFieldActorNameEditor;
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
	private static final int WINDOW_HEIGHT = 700;
	private static final int WINDOW_WIDTH = 1300;
	private static final int PADDING = 10;
	private static final String SPLASH_IMAGE_NAME = "salad.png";
	private static final String EDITING_CONTROLLER_RESOURCE = "editingActions";
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
	private ResourceBundle myButtonResource;
	private Game game;
	private GameInfo gameInfo;
	private Scene myScene;
	private BorderPane myRoot;
	private GUIFactory factory;
	private Scene splashScene;
	private PopUpAuthoringHelpPage helpPage;

	public Controller(Stage myStage) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		super(myStage);
		this.myButtonResource = ResourceBundle.getBundle(EDITING_CONTROLLER_RESOURCE);
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
		myLevels.stream().forEach(level -> mainScreen.createLevelPreviewUnit(level, levelEnvironment));
		myActorMap.keySet().stream().forEach(actor -> mainScreen.createActorPreviewUnit(actor, actorEnvironment));
		initializeGeneralComponents();
	}

	/**
	 * Initializes controller components that remain the same regardless of
	 * whether the Game to be edited is new or previously created
	 */
	public void initializeGeneralComponents() {
		myRoot = new BorderPane();
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
			// temp
			ButtonSplash splash = new ButtonSplash(null, SPLASH_IMAGE_NAME);
			hbox.getChildren().add(splash.createNode());
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
		//TODO implement incomplete game error checking
		System.out.println(myLevels.get(0).getActors().get(0).getRules().size());
		IPlayActor actor = myLevels.get(0).getActors().get(0);
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
		myLevels.add(newLevel);
		newLevel.setPlayPosition(myLevels.size());
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
		System.out.println(newActor.getMyID());
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

//	@Override public void update(Observable o, Object arg) { String button =
//	  (String) arg; String method = myButtonResource.getString(button);
//	  System.out.println(method); try {
//	  this.getClass().getDeclaredMethod(method).invoke(this); } catch
//	  (NoSuchMethodException e) { try {
//	  this.getClass().getSuperclass().getDeclaredMethod(method).invoke(this); }
//	  catch (IllegalAccessException | IllegalArgumentException |
//	  InvocationTargetException | NoSuchMethodException | SecurityException e1)
//	  { // TODO Auto-generated catch block e1.printStackTrace(); } } catch
//	  (IllegalAccessException | IllegalArgumentException |
//	  InvocationTargetException | SecurityException e) { // TODO Auto-generated
//	  catch block e.printStackTrace(); }
//	  
//	  }

//	 Use reflection - properties file linking button name to a method name
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof PreviewUnitWithEditable)
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
			loadGame();
		else if (arg0 instanceof ButtonSave)
			saveGame();
		else if (arg0 instanceof TextFieldActorNameEditor)
			updateActors((IAuthoringActor) arg1);
		else if (arg0 instanceof ButtonHelpPage) {
			helpPage = new PopUpAuthoringHelpPage();
		} else if (arg0 instanceof ButtonHUDOptions) {
			PopupSelector selector = new PopupSelector(this);
		}
	}

	// checking to see if this works with name
	public void updateActors(IAuthoringActor actor) {
		List<IAuthoringActor> listToUpdate = myActorMap.get(actor);
		for (int i = 0; i < listToUpdate.size(); i++) {
			IAuthoringActor toUpdate = listToUpdate.get(i);
			copyActor(toUpdate, actor);
			toUpdate.setName(actor.getName());
		}
	}

	public void updateRefActorSize(IAuthoringActor actor) {
		for (IAuthoringActor refActor : myActorMap.keySet()) {
			if (myActorMap.get(refActor).contains(actor)) {
				refActor.setSize(actor.getSize());
				updateActors(refActor);
			}
		}
	}

	// copy IDs
	private void copyActor(IAuthoringActor toUpdate, IAuthoringActor toCopy) {
		toUpdate.setName(toCopy.getName());
		toUpdate.setFriction(toCopy.getFriction());
		toUpdate.setImageView(toCopy.getImageView());
		toUpdate.setSize(toCopy.getSize());
		toUpdate.setImageViewName(toCopy.getImageViewName());
		toUpdate.setID(toCopy.getMyID());
		copyRules(toUpdate, toCopy.getRules());
		toUpdate.setPhysicsEngine(toCopy.getPhysicsEngine());
		// copyAttributes(toUpdate,)
	}

	private void copyRules(IAuthoringActor toUpdate, Map<String, List<Rule>> rulesToCopy) {
		toUpdate.getRules().clear();
		for (String trigger : rulesToCopy.keySet()) {
			List<Rule> toAdd = rulesToCopy.get(trigger);
			for (int i = 0; i < toAdd.size(); i++) {
				String triggerName = toAdd.get(i).getMyTrigger().getClass().getName();
				Class<?> className;
				try {
					className = Class.forName(triggerName);
					ITrigger triggerToAdd = (ITrigger) className.newInstance();

					String actionName = toAdd.get(i).getMyAction().getClass().getName();
					Class<?> actionClassName = Class.forName(actionName);
					Constructor<?> actionConstructor = actionClassName.getConstructor(Actor.class);
					Action actionToAdd = (Action) actionConstructor.newInstance((Actor) toUpdate);

					// ITrigger triggerToAdd = toAdd.get(i).getMyTrigger();
					// Action action = toAdd.get(i).getMyAction();
					// action.setMyActor((IPlayActor) (toUpdate));
					Rule rule = new Rule(triggerToAdd, actionToAdd);
					rule.setID(toAdd.get(i).getID() + 1);
					toUpdate.addRule(rule);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
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

}