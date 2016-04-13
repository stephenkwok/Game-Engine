package gameplayer.controller;

import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.util.ResourceBundle;

import gameengine.controller.Game;
import gui.controller.IScreenController;
import gui.view.ComboBoxGame;
import gui.view.Screen;
import gameplayer.view.SplashScreen;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SplashScreenController implements IScreenController {

	private Stage myStage;
	private ResourceBundle myResources;
	private SplashScreen mySplash;
	private Game myGame;
	
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
	
	public void useGame(){
		mySplash.play(myGame);
	}
	
	public void openHighScores(){
		mySplash.openHighScores();
	}
	
	public void edit() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		mySplash.edit();
	}

	@Override
	public void setGame(Game game) {
		this.myGame = game;
	}

	@Override
	public Screen getScreen() {
		return mySplash;
	}

	@Override
	public void chooseGame() {
		Group fileChooseGroup = new Group();
		Scene fileChooseScene = new Scene(fileChooseGroup, this.getScreen().getMyScene().getWidth(), this.getScreen().getMyScene().getHeight());
		ComboBoxGame fileSelector =  new ComboBoxGame("SUP", "gamefiles", this);
		fileChooseGroup.getChildren().add((HBox) fileSelector.createNode());
		this.getStage().setScene(fileChooseScene);
		
	}
	
	

}