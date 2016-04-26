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
import gameplayer.view.TLGCSValueFinder;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import voogasalad.util.hud.source.*;

public class BaseScreenController extends BranchScreenController {

	private static final String BASE_CONTROLLER_RESOURCE = "baseActions";

	private ResourceBundle myResources;
	private BaseScreen myScreen;
	private GameController myGameController;
	private HUDController myHUDController;
	
	public BaseScreenController(Stage myStage, GameController gameController) {
		super(myStage);
		// DEPENDENCY!!
		this.myGameController = gameController;
		myGameController.addObserver(this);
		setUpScreen();
		this.myResources = ResourceBundle.getBundle(BASE_CONTROLLER_RESOURCE);
		changeScreen(myScreen);
	}

	private void setUpScreen() {
		this.myScreen = new BaseScreen();
		this.myScreen.addObserver(this);
		setUpGameScreen();
		setUpHUDScreen();
	}

	private void toggleSound() {
		System.out.println("toggle sound");
	}

	private void toggleMusic() {
		System.out.println("toggle music");
	}

	private void saveGame() {
		togglePause();
		try {
			CreatorController c = new CreatorController(myGameController.getGame(), this.myScreen);
			FileChooser fileChooser = new FileChooser();
			File initialDirectory = new File("gamefiles");
			fileChooser.setInitialDirectory(initialDirectory);
			File file = fileChooser.showSaveDialog(new Stage());
			if (file != null) {
				c.saveForPlaying(file);
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			myScreen.showError(e.getMessage());
		}
		toggleUnPause();
	}

	private void switchGame() {
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

	private void restartGame() {
		// TODO fix this ish
		myGameController.getView().clearGame();
		ParserController parserController = new ParserController(myScreen);
		Game initialGame = parserController.loadforPlaying(new File(myGameController.getGame().getInitialGameFile()));
		myGameController.setGame(initialGame);
		myGameController.initialize(0);
		setUpHUDScreen();
	}

	private void setUpGameScreen() {
		this.myScreen.setGameScreen(this.myGameController.getView());
	}

	private void setUpHUDScreen() {
		myHUDController = new HUDController();
		myHUDController.init(myGameController.getGame().getHUDInfoFile(), myGameController.getGame(), new TLGCSValueFinder());
		myScreen.setHUDScreen(myHUDController.getView());
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
				Object[] parameters = { arg2 };
				this.getClass().getDeclaredMethod(methodName, parameterTypes).invoke(this, parameters);
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
