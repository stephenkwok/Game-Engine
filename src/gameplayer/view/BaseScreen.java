package gameplayer.view;

import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;
import gui.view.IGUIElement;
import gui.view.Screen;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * This class provides for a private interface to create a base screen view that
 * will hold the other view components of the gaming program.
 * 
 * @author Carine, Michael
 *
 */
public class BaseScreen extends Screen implements Observer {
	
	private static final String BASE_RESOURCE = "gameGUI";
	private static final String SIDE_BUTTONS = "SideButtons";

	private BorderPane myMasterPane;
	private HUDScreen myHUD;
	private GameScreen myGameScreen;

	
	/**
	 * Adds the auxiliary views, like the HUD display, ToolBar, and GameScreen,
	 * to the BaseScreen
	 * @param stage to change the scene
	 * @param game to initialize the gamescreen with
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public BaseScreen() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		super();
		this.myMasterPane = new BorderPane();
		setUpResourceBundle(BASE_RESOURCE);
		initialize(); //HUD is actually added here
	}

	
	/**
	 * Instantiates the necessary objects to add the game subscene and HUDpane to the base scene
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Override
	protected void initialize() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		addGame();
		addHUD();
		getRoot().getChildren().add(myMasterPane);
	}
	
	//depracated
	private void addHUD() {
		
		notifyObservers("addHUD");
		/*
		ObservableMap<String, Object> status = FXCollections.observableHashMap();
		status.put("health", 20);
		status.put("level", 2);
		HUDScreen myHud = new HUDScreen(SCREEN_WIDTH,SCREEN_WIDTH,status);
		*/
		/*
		HUDScreen myHud = new HUDScreen(SCREEN_WIDTH, SCREEN_WIDTH, 
				myBaseScreenController.getMyGameController().getGame().getHUDData());
		myBaseScreenController.getMyGameController().setHUD(myHud);
		myHud.init();
		myP.getChildren().add(myHud.getScene());
		myMasterPane.setBottom(myP);
		//myMasterPane.setBottom(new Text("HELLO!!!!")); */
	}

	
	/**
	 * Adds the game component in two parts: the toolbar for editing buttons and the subscene for display purposes
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private void addGame() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
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
	private void addButtonPane() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String[] sideButtons = getResources().getString(SIDE_BUTTONS).split(",");
		ToolBar myT = new ToolBar();
		for(int i = 0; i < sideButtons.length; i++){
			IGUIElement newElement = getFactory().createNewGUIObject(sideButtons[i]);
			Button myB = (Button) newElement.createNode();
			Tooltip t = new Tooltip(getResources().getString(sideButtons[i]+ "Text"));
			t.install(myB, t);
			myT.getItems().add(myB);
			myB.setFocusTraversable(false);
		}
		myMasterPane.setTop(myT);
		
	}
	
	/**
	 * Instantiates the necessary game subscene classes to add to the screen
	 */
	private void addGamePane(){
		notifyObservers("addGamePane");
	}
	
	public void setGameScreen(GameScreen screen) {
		this.myGameScreen = screen;
		this.myMasterPane.setCenter(myGameScreen.getScene());
	}
	
	public void setHUDScreen(HUDScreen screen) {
		this.myHUD = screen;
		IGUIElement hudPane = getFactory().createNewGUIObject("hudPane");
		Pane myP = (Pane) hudPane.createNode();
		myP.getChildren().add(myHUD.getScene());
		this.myMasterPane.setBottom(myP);
	}

	@Override
	public void update(Observable o, Object arg) {
		notifyObservers(o.getClass().getName());
	}

}