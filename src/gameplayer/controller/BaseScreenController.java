package gameplayer.controller;

import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import gamedata.controller.ChooserType;
import gamedata.controller.CreatorController;
import gamedata.controller.FileChooserController;
import gamedata.controller.ParserController;
import gameengine.controller.Game;
import gameplayer.view.BaseScreen;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class BaseScreenController extends BranchScreenController{

	private static final String BASE_CONTROLLER_RESOURCE = "baseActions";
	
	private ResourceBundle myResources;
	private BaseScreen myScreen;
	private GameController myGameController;
	
	public BaseScreenController(Stage myStage, GameController gameController) {
		super(myStage);
		//DEPENDENCY!!
		this.myGameController = gameController;
		setUpScreen();
		this.myResources = ResourceBundle.getBundle(BASE_CONTROLLER_RESOURCE);
		changeScreen(myScreen);
	}
	
	private void setUpScreen() {
		this.myScreen = new BaseScreen();
		this.myScreen.addObserver(this);
		setUpGameScreen();
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
		this.myScreen.switchAlert();
	}
	
	private void chooseGame() {
		FileChooserController fileChooserController = new FileChooserController(getStage(), ChooserType.PLAY);
	}

	private void togglePause() {
		this.myGameController.togglePause();
	}
	
	private void toggleUnPause() {
		this.myGameController.toggleUnPause();
	}

	private void restartGame(){
		//TODO fix this ish
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
		List<Object> myList = (List<Object>) arg;
		String methodName = (String) myList.get(0);
		try {
			if (myResources.getString(methodName).equals("null")) {
				this.getClass().getDeclaredMethod(methodName).invoke(this);
			} else {
				Class<?> myClass = Class.forName(myResources.getString(methodName));
				Object arg2 = myClass.cast(myList.get(1));
				Class[] parameterTypes = { myClass };
				this.getClass().getDeclaredMethod(methodName, parameterTypes).invoke(this, arg2);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			try {
				this.getClass().getSuperclass().getDeclaredMethod(methodName).invoke(this);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				this.myScreen.showError(e.getMessage());
			}
		}
	}

}
