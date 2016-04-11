package gameplayer.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.ResourceBundle;

import gameplayer.controller.BaseScreenController;
import gui.controller.IScreenController;
import gui.view.GUIFactory;
import gui.view.IGUIElement;
import gui.view.Screen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Border;
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
	public BaseScreen(Stage stage) {
		super(stage);
		init();
		addComponents();
	}

	public void init() {
		this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		myController = new BaseScreenController(getStage(), this, this.myResources);
		factory = new GUIFactory(myResources, myController);
	}
	

	public void addComponents() {
		VBox myBox = new VBox(20);
		try {
			addTopBar(myBox);
			addGame(myBox);
			addHUD(myBox);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | ClassNotFoundException | NoSuchMethodException | SecurityException e1) {
			e1.printStackTrace();
		}
		getRoot().getChildren().add(myBox);
	}

	public void addTopBar(VBox myV)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException {
		String[] topButtons = myResources.getString("TopPane").split(",");
		HBox smallH = new HBox();
		for (int i = 0; i < topButtons.length; i++) {
			IGUIElement newElement = factory.createNewGUIObject(topButtons[i]);
			Button myB = (Button) newElement.createNode();
			myB.setMinSize(10, 10);
			smallH.getChildren().add(myB);
		}
		myV.getChildren().add(smallH);
	}
	
	public void addHUD(VBox myV) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		IGUIElement hudPane = factory.createNewGUIObject("hudPane");
		Pane myP = (Pane) hudPane.createNode();
		myP.setMinWidth(SCREEN_WIDTH);
		ObservableMap<String, Object> status = FXCollections.observableHashMap();
		status.put("health", 20);
		status.put("level", 2);
		HUDScreen myHud = new HUDScreen(SCREEN_WIDTH,SCREEN_WIDTH,status);
		myHud.init();
		myP.getChildren().add(myHud.getRoot());
		myV.getChildren().add(myP);
	}

	public void addGame(VBox myV) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Pane myPane = new Pane();
		addButtonPane(myPane);
		addGamePane(myPane);
		myV.getChildren().add(myPane);
	}

	public void addButtonPane(Pane myP) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String[] sideButtons = myResources.getString(SIDE_BUTTONS).split(",");
		VBox smallV = new VBox();
		for(int i = 0; i < sideButtons.length; i++){
			IGUIElement newElement = factory.createNewGUIObject(sideButtons[i]);
			Button myB = (Button) newElement.createNode();
			myB.setMaxSize(1, 1);
			smallV.getChildren().add(myB);
		}
		myP.getChildren().add(smallV);
	}
	
	public void addGamePane(Pane myP){
		GameDisplay myGD = new GameDisplay(myP);
		myGD.init();
		//System.out.println(myP.getChildren());
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