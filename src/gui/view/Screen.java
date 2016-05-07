//This entire file is part of my masterpiece.
// Michael Figueiras, The Loop's Goat Cheese Salad
/*
 * This code's purpose is to set up and maintain the visual front end of our program. It is the super class to all screens
 * in our voogasalad, except the game authoring environment (which implemented their own GUI)
 * and the game screen (which is a subscene).
 *  
 * This class is well designed for several reasons. First, it serves as a clearly defined inheritance hierarchy starting point that allows
 * programmers to separate the interface from the implementation. By being selective in how much change can occur with our APIs,
 * we avoided fragile subclasses that are subject to breaking if this class were to augment its functionality.
 * 
 * In addition, it employs open-closed principles in its position with our program's design structure. 
 * The screens are all closed in terms of baseline requirements, which are further distinguished in responsibility
 * by the splash screen and the branch screen controllers. However, being open for extension allows the screen subclasses
 * to take on different roles in the program, and each take advantage of the polymorphism in this class (in specific reference to
 * the  to error checking and button instantiations) to employ these methods 
 * while handling user input and notifying proper program components of change.
 * 
 * See BaseScreen in the gameplayer.view package for an example of how this class is extended.
 */
package gui.view;

import java.util.Arrays;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This class serves as the parent abstract class that sets up the framework for
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
	private BorderPane myPane;

	public Screen() {
		this.myRoot = new Group();
		this.myScene = new Scene(myRoot, SCREEN_WIDTH, SCREEN_HEIGHT);
		this.myPane = new BorderPane();
		addToScene(myPane);
	}

	/**
	 * Assigns myResources to a new ResourceBundle and invokes the method to
	 * instantiate the GUIFactory
	 * 
	 * @param resource
	 */
	public void setUpResourceBundle(String resource) {
		this.myResources = ResourceBundle.getBundle(resource);
		setUpGUIFactory();
	}

	/**
	 * Adds a node to the scene's group
	 * 
	 * @param object
	 */
	public void addToScene(Node object) {
		this.myRoot.getChildren().add(object);
	}

	/**
	 * Creates an alert on the screen to notify the user of an error and display
	 * its relevant information
	 */
	public void showError(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(message);
		alert.showAndWait();
	}

	/**
	 * Uses the GUI factory to create and implement the buttons relevant to a
	 * specific screen on the toolbar
	 * 
	 * @param buttonList,
	 *            a string of button names to be instantiated
	 * @return ToolBar
	 */
	public void initialize(String buttonList) {
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
		myPane.setTop(myT);
	}

	/**
	 * Notifies the observer of the screen with what changes have occurred and
	 * passes the data necessary to put those changes into effect
	 * 
	 * @param msg
	 * @param arg
	 */
	public void sendChange(String msg, Object arg) {
		setChanged();
		Object[] methodArg = { msg, arg };
		notifyObservers(Arrays.asList(methodArg));
	}

	/**
	 * Assigns the GUIFactory to a new instance of GUIFactory
	 */
	private void setUpGUIFactory() {
		this.myFactory = new GUIFactory(myResources);
	}

	/**
	 * Returns the stage
	 * 
	 * @return myStage
	 */
	private Stage getStage() {
		return myStage;
	}

	/**
	 * Returns the ResourceBundle
	 * 
	 * @return myResources
	 */
	public ResourceBundle getResources() {
		return this.myResources;
	}

	/**
	 * Returns the GUI Factory
	 * 
	 * @return myFactory
	 */
	public GUIFactory getFactory() {
		return this.myFactory;
	}

	/**
	 * Returns the Scene
	 */
	public Scene getScene() {
		return this.myScene;
	}

	public BorderPane getPane() {
		return myPane;
	}

}