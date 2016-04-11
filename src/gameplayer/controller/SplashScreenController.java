package gameplayer.controller;

import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.util.ResourceBundle;

import gameengine.controller.Game;
import gui.controller.IScreenController;
import gui.view.Screen;
import gameplayer.view.SplashScreen;
import javafx.stage.Stage;

public class SplashScreenController implements IScreenController {

	private Stage myStage;
	private ResourceBundle myResources;
	private SplashScreen mySplash;
	
	public SplashScreenController(Stage myStage, SplashScreen mySplash, ResourceBundle myResources) {
		this.myStage = myStage;
		this.myResources = myResources;
		this.mySplash = mySplash;
		init();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Stage getStage() {
		return myStage;
	}
	
	public void play(){
		mySplash.play();
	}
	
	public void openHighScores(){
		mySplash.openHighScores();
	}
	
	public void edit() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		mySplash.edit();
	}

	@Override
	public void createGameFromFile(Game game) {
		// TODO Auto-generated method stub
	}

	@Override
	public Screen getScreen() {
		return mySplash;
	}
	
	

}