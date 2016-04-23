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
 * This class serves as the private interface that sets up the framework for what a screen in our project will consist of (i.e. a stage, a group, etc.).
 * @author cmt57
 */

public abstract class Screen extends Observable implements IScreen {

	private Scene myScene;
	private Stage myStage;
	private Group myRoot;
	private ResourceBundle myResources;
	private GUIFactory myFactory;
	public static final int SCREEN_WIDTH = 1000;
	public static final int SCREEN_HEIGHT = 700;
	
	public Screen(){
		this.myRoot = new Group();
		this.myScene = new Scene(myRoot, SCREEN_WIDTH, SCREEN_HEIGHT);
		// Title
	}
	
	protected void setUpResourceBundle(String resource) {
		this.myResources = ResourceBundle.getBundle(resource);
		setUpGUIFactory();
	}
	
	private void setUpGUIFactory() {
		this.myFactory = new GUIFactory(myResources);
	}
	
	protected Stage getStage(){
		return myStage;
	}
	
	protected ResourceBundle getResources() {
		return this.myResources;
	}
	
	protected GUIFactory getFactory() {
		return this.myFactory;
	}
	
	protected Group getRoot(){
		return (Group) myScene.getRoot();
	}
	
	public Scene getScene(){
		return this.myScene;
	}
	
	protected void addToScene(Node object) {
		this.myRoot.getChildren().add(object);
	}

	public void showError(String message) {
		  Alert alert = new Alert(Alert.AlertType.ERROR);
		  if(message == null) message = "Hmm, something went wrong! ¯\\_(ツ)_/¯";
	      alert.setContentText(message);
	      alert.showAndWait();
	}
	
	protected abstract void initialize() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	
	protected ToolBar addToolbar(String buttonList){
		String[] sideButtons = getResources().getString(buttonList).split(",");
		ToolBar myT = new ToolBar();
		myT.setMinWidth(SCREEN_WIDTH);
		myT.setOrientation(Orientation.HORIZONTAL);
		for(int i = 0; i < sideButtons.length; i++){
			IGUIElement newElement = getFactory().createNewGUIObject(sideButtons[i]);
			newElement.addNodeObserver((Observer) this);
			Button myB = (Button) newElement.createNode();
			Tooltip t = new Tooltip(getResources().getString(sideButtons[i]+ "Text"));
			t.install(myB, t);
			myT.getItems().add(myB);
			myB.setFocusTraversable(false);
		}
		return myT;
	}
	
		
	
}