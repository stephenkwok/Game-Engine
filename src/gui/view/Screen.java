package gui.view;

import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

/**
 * This class serves as the private interface that sets up the framework for
 * what a screen in our project will consist of (i.e. a stage, a group, etc.).
 * 
 * @author cmt57, mdf15
 */

public abstract class Screen extends Observable implements IScreen {

	private Scene myScene;
	private Stage myStage;
	private Group myRoot;
	private ResourceBundle myResources;
	private GUIFactory myFactory;
	public static final int SCREEN_WIDTH = 1000;
	public static final int SCREEN_HEIGHT = 700;
	public static final String NULL_ERROR= "Hmm, something went wrong! ¯\\_(ツ)_/¯";


	public Screen() {
		this.myRoot = new Group();
		this.myScene = new Scene(myRoot, SCREEN_WIDTH, SCREEN_HEIGHT);
		// Title
	}

	/**
	 * Assigns myResources to a new ResourceBundle and invokes the method to instantiate the GUIFactory
	 * @param resource
	 */
	protected void setUpResourceBundle(String resource) {
		this.myResources = ResourceBundle.getBundle(resource);
		setUpGUIFactory();
	}

	/**
	 * Assigns the GUIFactory to a new instance of GUIFactory
	 */
	private void setUpGUIFactory() {
		this.myFactory = new GUIFactory(myResources);
	}

	/**
	 * Returns the stage
	 * @return myStage
	 */
	protected Stage getStage() {
		return myStage;
	}

	/**
	 * Returns the ResourceBundle
	 * @return myResources
	 */
	protected ResourceBundle getResources() {
		return this.myResources;
	}

	/**
	 * Returns the GUI Factory
	 * @return myFactory
	 */
	protected GUIFactory getFactory() {
		return this.myFactory;
	}

	/**
	 * Returns the scene's root
	 * @return myRoot
	 */
	protected Group getRoot() {
		return (Group) myScene.getRoot();
	}

	/**
	 * Returns the Scene
	 */
	public Scene getScene() {
		return this.myScene;
	}

	/**
	 * Adds a node to the scene's group
	 * @param object
	 */
	protected void addToScene(Node object) {
		this.myRoot.getChildren().add(object);
	}

	/**
	 * Creates an alert on the screen to notify the user of an error and display its relevant information
	 */
	public void showError(String message) {
		  Alert alert = new Alert(Alert.AlertType.ERROR);
		  if(message == null) message = NULL_ERROR;
	      alert.setContentText(message);
	      alert.show();
	}

	/**
	 * Initializes features that all screens share, like a toolbar for example
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	protected abstract void initialize()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

	/**
	 * Uses the GUI factory to create and implement the buttons relevant to a specific screen on the toolbar
	 * @param buttonList
	 * @return toolbar
	 */
	protected ToolBar addToolbar(String buttonList) {
		String[] sideButtons = getResources().getString(buttonList).split(",");
		ToolBar myT = new ToolBar();
		myT.setMinWidth(SCREEN_WIDTH);
		myT.setOrientation(Orientation.HORIZONTAL);
		for (int i = 0; i < sideButtons.length; i++) {
			IGUIElement newElement = getFactory().createNewGUIObject(sideButtons[i]);
			newElement.addNodeObserver((Observer) this);
			Button myB = (Button) newElement.createNode();
			Tooltip t = new Tooltip(getResources().getString(sideButtons[i] + "Text"));
			t.install(myB, t);
			myT.getItems().add(myB);
			myB.setFocusTraversable(false);
		}
		return myT;
	}

}