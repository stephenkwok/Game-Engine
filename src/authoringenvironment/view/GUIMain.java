package authoringenvironment.view;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
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
public class GUIMain extends Screen implements IGUI, Observer {
    private static final String EDITING_RESOURCE = "authoringGUI";
    private static final String TOP_PANE_ELEMENTS = "TopPaneElements";
    private static final int WINDOW_HEIGHT = 700;
	private static final int WINDOW_WIDTH = 1300;
    private static final int PADDING = 10;
    private static final String SPLASH_IMAGE_NAME = "salad.png";
    private Scene myScene;
	private BorderPane myRoot;
	
	public GUIMain() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		super();
		setUpResourceBundle(EDITING_RESOURCE);
		initialize();
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
			String[] topPaneElements = getResources().getString(TOP_PANE_ELEMENTS).split(",");
			for (int i = 0; i < topPaneElements.length; i++) {
				IGUIElement elementToCreate = getFactory().createNewGUIObject(topPaneElements[i]);
				hbox.getChildren().add(elementToCreate.createNode());
			}
			//temp
			//ButtonSplash splash = new ButtonSplash(null, SPLASH_IMAGE_NAME);
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

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initialize()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		myRoot = new BorderPane();
		myScene = new Scene(myRoot, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);
		setTopPane();
		setCenterPane();
		
	}
}