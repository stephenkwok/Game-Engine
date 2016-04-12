package gameplayer.controller;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import gameengine.controller.Game;
import gameplayer.view.HighScoreScreen;
import gameplayer.view.SplashScreen;
import gui.controller.IScreenController;
import gui.view.Screen;
import javafx.stage.Stage;

public class HighScoreScreenController implements IScreenController {

	private Stage myStage;
	private ResourceBundle myResources;
	private HighScoreScreen myScreen;
	
	public HighScoreScreenController(Stage myStage, HighScoreScreen myBase, ResourceBundle myResources) {
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

	@Override
	public void setGame(Game game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Screen getScreen() {
		return myScreen;
	}

	public void goToSplash() {
		SplashScreen mySplash = new SplashScreen(getStage());
		try {
			getStage().setScene(mySplash.getScene());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void chooseGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useGame() {
		// TODO Auto-generated method stub
		
	}

}
