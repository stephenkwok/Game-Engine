package authoringenvironment.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import gamedata.controller.ChooserType;
import gamedata.controller.CreatorController;
import gamedata.controller.FileChooserController;
import gameengine.controller.Game;
import gameengine.controller.GameInfo;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gui.view.ButtonFinish;
import gui.view.ButtonHome;
import gui.view.ButtonLoad;
import gui.view.ButtonNewActor;
import gui.view.ButtonNewLevel;
import gui.view.ButtonSave;
import gui.view.ButtonSplash;
import gui.view.GUIFactory;
import gui.view.IGUIElement;
import gameplayer.controller.BranchScreenController;
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
import gui.view.Screen;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This class serves as the main controller for the authoring environment
 * 
 * @author Stephen, AnnieTang
 */

public class Controller extends BranchScreenController implements Observer {
	private static final String GUI_RESOURCE = "authoringGUI";
	private static final String TOP_PANE_ELEMENTS = "TopPaneElements";
	private static final int WINDOW_HEIGHT = 700;
	private static final int WINDOW_WIDTH = 1300;
	private static final int PADDING = 10;
	private static final String SPLASH_IMAGE_NAME = "salad.png";
	private static final String EDITING_CONTROLLER_RESOURCE = "editingActions";
	private List<Level> myLevels;
	private List<String> myLevelNames;
	private Map<IAuthoringActor, List<IAuthoringActor>> myActors;
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

	public Controller(Stage myStage) {
		super(myStage);
		this.myButtonResource = ResourceBundle.getBundle(EDITING_CONTROLLER_RESOURCE);
		init();
	}

	//TODO This constructor is not parallel with other BranchScreenController subclasses
	//Create a resource bundle for the Controller's actions associated to buttons it handles, but the resource bundle for the GUIFactory should be stored in and set up in the GUIMain
	//Stage should not be contained in the subclass (already in BranchScreenController parent)
	public Controller(Stage myStage, GUIMain guiMain) {
		super(myStage);
		this.guiMain = guiMain;
		init();
	}

	//TODO Need a constructor that takes in a game passed by data and sets up Authoring Environment accordingly 

	public void init() {
		myRoot = new BorderPane();
		myScene = new Scene(myRoot, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);
		getStage().setScene(myScene);
		this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		factory = new GUIFactory(myResources);
		myLevels = new ArrayList<>();
		myLevelNames = new ArrayList<>();
		myActors = new HashMap<>();
		myActorNames = new ArrayList<>();
		levelEnvironment = new LevelEditingEnvironment(myActors, getStage());
		gameInfo = new GameInfo();
		game = new Game(gameInfo, myLevels);
		actorEnvironment = new ActorEditingEnvironment(myResources, getStage(), myActors, myLevels);
		gameEnvironment = new GameEditingEnvironment(gameInfo, getStage());
		mainScreen = new GUIMainScreen(gameEnvironment, getStage().widthProperty(), getStage().heightProperty(), myLevels,
				levelEnvironment);
		setTopPane();
		setCenterPane();
	}

	/**
	 * Set center section of screen to given Pane
	 * @param pane
	 */
	public void setCenterPane(Pane pane){
		myRoot.setCenter(pane);
	}
	/**
	 * Set center screen to default, the home screen 
	 */
	public void setCenterPane(){
		goToMainScreen();
	}
	/**
	 * Sets top section of screen to fixed toolbar 
	 */
	private void setTopPane() {
		HBox hbox = new HBox(PADDING);
		hbox.setPadding(new Insets(PADDING,PADDING,PADDING,PADDING));		
		initializeTopPaneElements(hbox);
		hbox.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		myRoot.setTop(hbox);
	}
	/**
	 * Initialize elements to be in toolbar
	 * @param hbox
	 */
	private void initializeTopPaneElements(HBox hbox) {
		try{
			String[] topPaneElements = myResources.getString(TOP_PANE_ELEMENTS).split(",");
			for (int i = 0; i < topPaneElements.length; i++) {
				IGUIElement elementToCreate = factory.createNewGUIObject(topPaneElements[i]);
				((Observable) elementToCreate).addObserver(this);
				hbox.getChildren().add(elementToCreate.createNode());
				
			}
			//temp
			ButtonSplash splash = new ButtonSplash(null, SPLASH_IMAGE_NAME);
			hbox.getChildren().add(splash.createNode());
		}catch(Exception e){
			
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
	 * @return
	 */
	public double getWidth() {
		return myScene.getWidth();
	}
	/**
	 * Return height of authoring environment Scene
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
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showSaveDialog(new Stage());
		Game g = new Game(gameInfo, myLevels); 
		CreatorController controller;
		try {
			controller = new CreatorController(game, guiMain);
			controller.saveForEditing(file);
		} catch (ParserConfigurationException e) {
			guiMain.showError(e.getMessage());
		}

	}

	public void loadGame() {
		promptForFileName(false);
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
		myLevelNames.add(newLevel.getName());
		mainScreen.createLevelLabel(newLevel, levelEnvironment).addObserver(this);
		goToEditingEnvironment(newLevel, levelEnvironment);
	}

	public void addActor() {
		IAuthoringActor newActor = (IAuthoringActor) new Actor();
		newActor.setID(myActors.size());
		myActors.put(newActor, new ArrayList<>());
		myActorNames.add(newActor.getName());
		mainScreen.createActorLabel(newActor, actorEnvironment).addObserver(this);;
		actorEnvironment.setActorImage(newActor.getImageView(), newActor.getImageViewName());
		goToEditingEnvironment(newActor, actorEnvironment);
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
	/*public void goToSplash() {
		guiMain.goBackToSplash();
	}*/

	public void switchGame() {
		// TODO Auto-generated method stub
	}
	
/*	@Override
	public void update(Observable o, Object arg) {
		String button = (String) arg;
		String method = myButtonResource.getString(button);
		System.out.println(method);
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
*/
	// Use reflection - properties file linking button name to a method name
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
			loadGame();
		else if (arg0 instanceof ButtonSave)
			saveGame();
		else if (arg0 instanceof TextFieldActorNameEditor) 
			updateActors((IAuthoringActor) arg1);
	}

	// checking to see if this works with name
	private void updateActors(IAuthoringActor actor) {
		List<IAuthoringActor> listToUpdate = myActors.get(actor);
		for (int i = 0; i < listToUpdate.size(); i++) {
			listToUpdate.get(i).setName(actor.getName());
		}
	}
	
	private void handleObservableGoToEditingEnvironmentCall(Object notifyObserversArgument) {
		List<Object> arguments = (List<Object>) notifyObserversArgument;
		IEditableGameElement editable = (IEditableGameElement) arguments.get(0);
		IEditingEnvironment environment = (IEditingEnvironment) arguments.get(1);
		goToEditingEnvironment(editable, environment);
	}
	
	/**
     * Creates a file picker to get a file name
     * @return returns the file
     */
	
    protected File promptForFileName(boolean isSaving){
        FileChooser myFileChooser = new FileChooser();
        FileChooser.ExtensionFilter myFilter = new FileChooser.ExtensionFilter("XML Files (.xml)", "*.xml");
        myFileChooser.getExtensionFilters().add(myFilter);
        File fileName;
        if (isSaving){
            fileName = myFileChooser.showSaveDialog(getStage());
        }
        else{
            fileName = myFileChooser.showOpenDialog(getStage());
        }
        return fileName;
    }
}