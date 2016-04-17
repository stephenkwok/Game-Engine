package authoringenvironment.view;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import gui.controller.IScreenController;
import gui.view.ButtonSplash;
import gui.view.GUIFactory;
import gui.view.IGUI;
import gui.view.IGUIElement;
import gui.view.Screen;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Main GUI class. Fixed tool-bar as top pane. 
 * @author AnnieTang, amyzhao
 *
 */
public class GUIMain extends Screen implements IGUI {
    private static final String GUI_RESOURCE = "authoringGUI";
    private static final String TOP_PANE_ELEMENTS = "TopPaneElements";
    private static final int WINDOW_HEIGHT = 700;
	private static final int WINDOW_WIDTH = 1300;
    private static final int PADDING = 10;
    private static final String SPLASH_IMAGE_NAME = "salad.png";
    private Scene myScene;
	private BorderPane myRoot;
	private ResourceBundle myResources;
	private Stage myStage;
	private Controller myController;
	private GUIFactory factory;
	private Scene splashScene;
	
	public GUIMain(Stage s, Scene splash) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		super(s);
		this.splashScene = splash;
		this.myStage = s;
		init();
	}
	
	/**
	 * Initializes resource bundle, controller, and factory class.
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void init() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		myRoot = new BorderPane();
		myScene = new Scene(myRoot, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);
		this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		factory = new GUIFactory(myResources, myController);
		myController = new Controller(myStage, this, this.myResources);
		setTopPane();
		setCenterPane();
	}
	
	/**
	 * Creates the fixed tool-bar and sets up over-arching BorderPane. 
	 * @return Scene
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Scene getScene() {
		return myScene;
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
		myController.goToMainScreen();
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
				Observable observableButton = (Observable) elementToCreate;
				observableButton.addObserver(myController);
				hbox.getChildren().add(elementToCreate.createNode());
			}
			//temp
			ButtonSplash splash = new ButtonSplash(myController, null, SPLASH_IMAGE_NAME);
			hbox.getChildren().add(splash.createNode());
		}catch(Exception e){
			
		}
	}
	/**
	 * Return Pane representation of authoring environment
	 */
	@Override
	public Pane getPane() {
		return myRoot;
	}
	
	@Override
	public IScreenController setController() {			//REMOVE
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Returns user back to splash screen 
	 */
	public void goBackToSplash() {
		myStage.setScene(splashScene);
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
}