package gameplayer.view;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import gameengine.controller.Game;
import gameplayer.controller.BaseScreenController;
import gameplayer.controller.GameController;
import gui.controller.IScreenController;
import gui.view.GUIFactory;
import gui.view.IGUIElement;
import gui.view.Screen;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This class provides for a private interface to create a base screen view that
 * will hold the other view components of the gaming program.
 * 
 * @author Carine, Michael
 *
 */
public class BaseScreen extends Screen {

	private ResourceBundle myResources;
	private static final String GUI_RESOURCE = "gameGUI";
	private BaseScreenController myBaseScreenController;
	private GUIFactory factory;
	private static final String SIDE_BUTTONS = "SideButtons";
	private BorderPane myMasterPane;

	
	/**
	 * Adds the auxiliary views, like the HUD display, ToolBar, and GameScreen,
	 * to the BaseScreen
	 * @param stage to change the scene
	 * @param game to initialize the gamescreen with
	 */
	public BaseScreen(Stage stage, Game game) {
		super(stage);
		this.myMasterPane = new BorderPane();
		init();
		GameController myGameController = myBaseScreenController.getMyGameController();
		myGameController.setGame(game);
		myGameController.setGameView(new GameScreen(new PerspectiveCamera()));
		myGameController.initialize(game.getInfo().getMyCurrentLevelNum()); //note: main actor is define at this line
		addComponents(); //HUD is actually added here
	}
	
	public void init() {
		this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		myBaseScreenController = new BaseScreenController(getStage(), this, this.myResources);
		factory = new GUIFactory(myResources, myBaseScreenController);
	}
	
	/**
	 * Instantiates the necessary objects to add the game subscene and HUDpane to the base scene
	 */
	public void addComponents() {
		try {
			addGame();
			addHUD();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | SecurityException e1) {
			e1.printStackTrace();
		}
		getRoot().getChildren().add(myMasterPane);
	}
	
	//depracated
	public void addHUD() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		IGUIElement hudPane = factory.createNewGUIObject("hudPane");
		Pane myP = (Pane) hudPane.createNode();
		/*
		ObservableMap<String, Object> status = FXCollections.observableHashMap();
		status.put("health", 20);
		status.put("level", 2);
		HUDScreen myHud = new HUDScreen(SCREEN_WIDTH,SCREEN_WIDTH,status);
		*/
		HUDScreen myHud = new HUDScreen(SCREEN_WIDTH, SCREEN_WIDTH, 
				myBaseScreenController.getMyGameController().getGame().getHUDData());
		myBaseScreenController.getMyGameController().setHUD(myHud);
		myHud.init();
		myP.getChildren().add(myHud.getScene());
		myMasterPane.setBottom(myP);
		//myMasterPane.setBottom(new Text("HELLO!!!!"));
	}

	
	/**
	 * Adds the game component in two parts: the toolbar for editing buttons and the subscene for display purposes
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void addGame() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		addButtonPane();
		addGamePane();
	}

	/**
	 * Using reflection adn the GUIFactory, each game and base screen button is created and added to the main toolbar
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void addButtonPane() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String[] sideButtons = myResources.getString(SIDE_BUTTONS).split(",");
		ToolBar myT = new ToolBar();
		for(int i = 0; i < sideButtons.length; i++){
			IGUIElement newElement = factory.createNewGUIObject(sideButtons[i]);
			Button myB = (Button) newElement.createNode();
			Tooltip t = new Tooltip(myResources.getString(sideButtons[i]+ "Text"));
			t.install(myB, t);
			myT.getItems().add(myB);
			myB.setFocusTraversable(false);
		}
		myMasterPane.setTop(myT);
		
	}
	
	/**
	 * Instantiates the necessary game subscene classes to add to the screen
	 */
	public void addGamePane(){
		SubScene gameScene = myBaseScreenController.getMyGameController().getView().getScene();
		myMasterPane.setCenter(gameScene);
	}
	
	@Override
	public Scene getScene()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IScreenController setController() {
		// TODO Auto-generated method stub
		return null;
	}
}