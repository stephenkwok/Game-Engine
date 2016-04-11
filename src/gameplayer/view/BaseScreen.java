package gameplayer.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.ResourceBundle;

import gameengine.controller.Game;
import gameplayer.controller.BaseScreenController;
import gameplayer.controller.GameController;
import gui.controller.IScreenController;
import gui.view.GUIFactory;
import gui.view.IGUIElement;
import gui.view.Screen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Camera;
import javafx.scene.Node;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class provides for a private interface to create a base screen view that
 * will hold the other view components of the gaming program.
 * 
 * @author Carine
 *
 */
public class BaseScreen extends Screen {
	/**
	 * Adds the auxiliary views, like the HUD display, MenuBar, and GameScreen,
	 * to the BaseScreen
	 */

	private ResourceBundle myResources;
	private static final String GUI_RESOURCE = "gameGUI";
	private BaseScreenController myController;
	private GUIFactory factory;
	private static final String MENU_ITEMS = "MenuBarMenus";
	private static final String SIDE_BUTTONS = "SideButtons";
	private static final Integer BUTTON_X = 50;
	private static final Integer BUTTON_Y = 10;
	private BorderPane myMasterPane;
	private GameController myGameController;

	public BaseScreen(Stage stage, Game game) {
		super(stage);
		this.myMasterPane = new BorderPane();
		init();
		GameController gameController = new GameController();
		gameController.setGame(game);
		gameController.setGameView(new GameScreen(new ParallelCamera()));
		gameController.initialize(game.getInfo().getCurrentLevelNum());
		addComponents();
	}
	
	public void init() {
		this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		myController = new BaseScreenController(getStage(), this, this.myResources);
		factory = new GUIFactory(myResources, myController);
	}
	

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
	
	public void addHUD() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		IGUIElement hudPane = factory.createNewGUIObject("hudPane");
		Pane myP = (Pane) hudPane.createNode();
		ObservableMap<String, Object> status = FXCollections.observableHashMap();
		status.put("health", 20);
		status.put("level", 2);
		HUDScreen myHud = new HUDScreen(SCREEN_WIDTH,SCREEN_WIDTH,status);
		myHud.init();
		myP.getChildren().add(myHud.getRoot());
		myMasterPane.setBottom(myP);
	}

	public void addGame() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		addButtonPane();
		addGamePane();
	}

	public void addButtonPane() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String[] sideButtons = myResources.getString(SIDE_BUTTONS).split(",");
		ToolBar myT = new ToolBar();
		for(int i = 0; i < sideButtons.length; i++){
			IGUIElement newElement = factory.createNewGUIObject(sideButtons[i]);
			Button myB = (Button) newElement.createNode();
			Tooltip t = new Tooltip(myResources.getString(sideButtons[i]+ "Text"));
			t.install(myB, t);
			myT.getItems().add(myB);
		}
		myMasterPane.setTop(myT);
		
	}
	
	public void addGamePane(){
		SubScene gameScene = myGameController.getView().getScene();
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