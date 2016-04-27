package gameplayer.controller;

import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

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
	@XStreamOmitField
	private ResourceBundle myResources;
	@XStreamOmitField
	private BaseScreen myScreen;
	private GameController myGameController;
	@XStreamOmitField
	private HUDController myHUDController;
	
	public BaseScreenController(Stage myStage, GameController gameController) {
		super(myStage, BASE_CONTROLLER_RESOURCE);
		// DEPENDENCY!!
		this.myGameController = gameController;
		myGameController.addObserver(this);
		setUpScreen();
		changeScreen(myScreen);
	}

	private void setUpScreen() {
		this.myScreen = new BaseScreen();
		this.myScreen.addObserver(this);
		setUpGameScreen();
		setUpHUDScreen();
		setMyScreen(this.myScreen);
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
			myGameController.getGame().deleteObservers();
			CreatorController c = new CreatorController(myGameController.getGame());
			FileChooser fileChooser = new FileChooser();
			File initialDirectory = new File("gamefiles");
			fileChooser.setInitialDirectory(initialDirectory);
			File file = fileChooser.showSaveDialog(new Stage());
			if (file != null) {
				c.saveForPlaying(file);
			}
			
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			e.printStackTrace();
			myScreen.showError(e.getMessage());
		}
		toggleUnPause();
	}

	private void switchGame() {
		togglePause();
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
	
	@Override
	protected void goToSplash() {
		togglePause();
		super.goToSplash();
	}

	private void restartGame() {
		// TODO fix this ish
		togglePause();
		myGameController.getView().clearGame();
		ParserController parserController = new ParserController();
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
	public void invoke(String method, Class[] parameterTypes, Object[] parameters) {
		try {
			this.getClass().getDeclaredMethod(method, parameterTypes).invoke(this, parameters);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
