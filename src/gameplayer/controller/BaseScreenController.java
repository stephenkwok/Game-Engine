package gameplayer.controller;

import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.util.ResourceBundle;

import gameengine.controller.Game;
import gameplayer.view.BaseScreen;
import gameplayer.view.SplashScreen;
import gui.controller.IScreenController;
import gui.view.Screen;
import javafx.stage.Stage;

public class BaseScreenController implements IScreenController{

	private Stage myStage;
	private ResourceBundle myResources;
	private BaseScreen myScreen;
	
	public BaseScreenController(Stage myStage, BaseScreen myBase, ResourceBundle myResources) {
		this.myStage = myStage;
		this.myResources = myResources;
		this.myScreen = myBase;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Stage getStage() {
		return myStage;
	}

	public void toggleSound() {
		System.out.println("toggle sound");
	}
	
	public void toggleMusic(){
		System.out.println("toggle music");
	}

	public void togglePause() {
		System.out.println("pause the game");
	}

	public void toggleUnPause() {
		System.out.println("un pause game");
	}
	
	public void goToSplash(){
		SplashScreen mySplash = new SplashScreen(myStage);
		try {
			myStage.setScene(mySplash.getScene());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public void saveGame(){
		System.out.println("User wants to save game");
	}
	
	public void switchGame(){
		System.out.println("User wants to switch games");
	}

	@Override
	public void createGameFromFile(Game file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Screen getScreen() {
		return myScreen;
	}

}