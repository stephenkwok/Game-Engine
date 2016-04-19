package gameplayer.controller;

import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import gamedata.controller.CreatorController;
import gamedata.controller.FileChooserController;
import gamedata.controller.ParserController;
import gameengine.controller.Game;
import gameplayer.view.BaseScreen;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class BaseScreenController extends BranchScreenController{

	private static final String BASE_CONTROLLER_RESOURCE = "baseActions";
	
	private ResourceBundle myResources;
	private BaseScreen myScreen;
	private GameController myGameController;
	
	public BaseScreenController(Stage myStage, GameController gameController) {
		super(myStage);
		try {
			this.myScreen = new BaseScreen();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			this.myScreen.showError(e.getMessage());
		}
		this.myGameController = gameController;
		this.myResources = ResourceBundle.getBundle("baseActions");
		changeScreen(myScreen);
	}

	private void toggleSound() {
		System.out.println("toggle sound");
	}
	
	private void toggleMusic(){
		System.out.println("toggle music");
	}

	private void saveGame(){
		this.myGameController.togglePause();
		try {
			CreatorController c = new CreatorController(myGameController.getGame(), this.myScreen);
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showSaveDialog(new Stage());
			c.saveForPlaying(file);
		} catch (ParserConfigurationException e) {
			myScreen.showError(e.getMessage());
		}
		
	}
	
	private void switchGame(){
		this.myGameController.togglePause();
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, myResources.getString("SwitchConfirmation"), ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            this.saveGame();
            this.chooseGame();
        }
        else if (result.get() == ButtonType.NO) {
        	this.chooseGame();
        }
        else {
        	this.myGameController.toggleUnPause();
        }
	}
	
	private void chooseGame() {
		FileChooserController fileChooserController = new FileChooserController(getStage(), "play");
	}

	private void togglePause() {
		this.myGameController.togglePause();
	}
	
	private void toggleUnPause() {
		this.myGameController.toggleUnPause();
	}

	private void restartGame(){
		myGameController.getView().clearGame();
		ParserController parserController = new ParserController(myScreen);
		Game initialGame = parserController.loadforPlaying(new File(myGameController.getGame().getInitialGameFile()));
		myGameController.setGame(initialGame);
		myGameController.initialize(0);
	}
	
	private void setUpGameScreen(){
		this.myScreen.setGameScreen(this.myGameController.getView());
	}
	
	
	private void setUpHUDScreen() {
		//TODO FIX THIS BOBBY
		//this.myScreen.setHUDScreen(new HUDScreen(Screen.SCREEN_WIDTH, Screen.SCREEN_WIDTH, this.myGameController.getGame().getHUDData()));
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
