package gui.view;

import java.lang.reflect.InvocationTargetException;

import java.util.ResourceBundle;

import gui.controller.IScreenController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/** 
 * This class serves as the private interface that sets up the framework for what a screen in our project will consist of (i.e. a stage, a group, etc.).
 * @author cmt57
 */

public abstract class Screen {

	private Scene myScene;
	private Stage myStage;
	private ResourceBundle myResources;
	private String GUI_RESOURCE;
	private IScreenController myController;
	private Group myRoot;
	private GUIFactory factory;
	public static final int SCREEN_WIDTH = 1000;
	public static final int SCREEN_HEIGHT = 700;
	
	public Screen(Stage stage){
		myRoot = new Group();
		this.myScene = new Scene(myRoot, SCREEN_WIDTH, SCREEN_HEIGHT);
		myStage = stage;
	}
	
	/**
	 * Will instantiate the basic components of a visualized screen.
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void init() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		myController = setController();
		factory = new GUIFactory(myResources, myController);
		myStage.setTitle("Welcome to Vooga Salad!");
	}
	
	public abstract Scene getScene() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	public abstract IScreenController setController();
	
	public Stage getStage(){
		return myStage;
	}
	
	public Group getRoot(){
		return (Group) myScene.getRoot();
	}
	
	public Scene getMyScene(){
		return myScene;
	}

	public void showError(String message) {
		  Alert alert = new Alert(Alert.AlertType.ERROR);
	      alert.setContentText(message);
	      alert.showAndWait();
		
	}
		
	
}