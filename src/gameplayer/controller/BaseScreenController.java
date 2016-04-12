package gameplayer.controller;

import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import gamedata.controller.CreatorController;
import gameengine.controller.Game;
import gameplayer.view.BaseScreen;
import gameplayer.view.SplashScreen;
import gui.controller.IScreenController;
import gui.view.Screen;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class BaseScreenController implements IScreenController{

	private Stage myStage;
	private ResourceBundle myResources;
	private BaseScreen myScreen;
	private GameController myGameController;
	
	public BaseScreenController(Stage myStage, BaseScreen myBase, ResourceBundle myResources) {
		this.myStage = myStage;
		this.myResources = myResources;
		this.myScreen = myBase;
		this.setMyGameController(new GameController());
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
		//TODO: stop the step(), thx michael!!!!!! :)
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
		try {
			CreatorController c = new CreatorController(getMyGameController().getGame(), this.myScreen);
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showSaveDialog(new Stage());
			c.saveForPlaying(file);
		} catch (ParserConfigurationException e) {
			myScreen.showError(e.getMessage());
		}
		
	}
	
	public void switchGame(){
		System.out.println("User wants to switch games");
	}

	@Override
	public void useGame(Game game) {
		
	}

	@Override
	public Screen getScreen() {
		return myScreen;
	}

	public GameController getMyGameController() {
		return myGameController;
	}

	public void setMyGameController(GameController myGameController) {
		this.myGameController = myGameController;
	}
	
	public void restartGame(){
		System.out.print("user wants to restart game");
	}

}