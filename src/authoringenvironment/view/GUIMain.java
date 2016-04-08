package authoringenvironment.view;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import gui.controller.IScreenController;
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
 * @author AnnieTang
 *
 */
public class GUIMain extends Screen implements IGUI {
    private static final String GUI_RESOURCE = "authoringGUI";
    private static final String TOP_PANE_ELEMENTS = "TopPaneElements";
    private static final int WINDOW_HEIGHT = 800;
	private static final int WINDOW_WIDTH = 1300;
    private static final int PADDING = 10;
    private Scene myScene;
	private BorderPane myRoot;
	private ResourceBundle myResources;
	private Stage myStage;
	private Controller myController;
	private GUIFactory factory;
//	private Scene splashScene;
	
	public GUIMain(Stage s, Scene splash) {
		super(s);
//		this.splashScene = splash;
		this.myStage = s;
		init();
	}
	
	/**
	 * Initializes resource bundle, controller, and factory class.
	 */
	public void init(){
		this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		myController = new Controller(myStage, this, this.myResources);
		factory = new GUIFactory(myResources, myController);
	}
	
	/**
	 * Creates the fixed tool-bar and sets up over-arching BorderPane. 
	 * @return Scene
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Scene getScene() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		myRoot = new BorderPane();
		setTopPane();
		setCenterPane();
		myScene = new Scene(myRoot, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);
		return myScene;
	}
	
	public void setCenterPane(Pane pane){
		myRoot.setCenter(pane);
	}

	public void setCenterPane(){
		myController.goToMainScreen();
	}
	
	private void setTopPane() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		HBox hbox = new HBox(PADDING);
		hbox.setPadding(new Insets(PADDING,PADDING,PADDING,PADDING));		
		initializeTopPaneElements(hbox);
		hbox.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		myRoot.setTop(hbox);
	}

	private void initializeTopPaneElements(HBox hbox) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String[] topPaneElements = myResources.getString(TOP_PANE_ELEMENTS).split(",");
		for (int i = 0; i < topPaneElements.length; i++) {
			IGUIElement elementToCreate = factory.createNewGUIObject(topPaneElements[i]);
			hbox.getChildren().add(elementToCreate.createNode());
		}
	}
	
	@Override
	public Pane getPane() {
		return myRoot;
	}

	@Override
	public IScreenController setController() {
		// TODO Auto-generated method stub
		return null;
	}
}