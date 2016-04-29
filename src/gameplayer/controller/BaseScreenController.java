package gameplayer.controller;

import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import gamedata.controller.ChooserType;
import gamedata.controller.CreatorController;
import gamedata.controller.FileChooserController;
import gameplayer.view.BaseScreen;
import gameplayer.view.IBaseScreen;
import gameplayer.view.TLGCSValueFinder;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import voogasalad.util.hud.source.*;

public class BaseScreenController extends BranchScreenController {

	private static final String BASE_CONTROLLER_RESOURCE = "baseActions";
	@XStreamOmitField
	private ResourceBundle myResources;
	@XStreamOmitField
	private IBaseScreen myScreen;
	@XStreamOmitField
	private IGameController myGameController;
	@XStreamOmitField
	private HUDController myHUDController;
	
	public BaseScreenController(Stage myStage, IGameController gameController) {
		super(myStage, BASE_CONTROLLER_RESOURCE);
		// DEPENDENCY!!
		this.myGameController = gameController;
		((Observable) myGameController).addObserver(this);
		setUpScreen();
		changeScreen(myScreen);
	}

	private void setUpScreen() {
		this.myScreen = new BaseScreen();
		((Observable) this.myScreen).addObserver(this);
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
			((Observable) myGameController.getGame()).deleteObservers();
			myHUDController.linkHandleController(false); //same as .uninit()
			CreatorController c = new CreatorController(myGameController.getGame());
			FileChooser fileChooser = new FileChooser();
			File initialDirectory = new File("gamefiles");
			fileChooser.setInitialDirectory(initialDirectory);
			File file = fileChooser.showSaveDialog(new Stage());
			if (file != null) {
				c.saveForPlaying(file);
			}
			((Observable) myGameController.getGame()).addObserver((Observer) myGameController);
			setUpHUDScreen();
			
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
		myGameController.restartGame();
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
	public void invoke(String method, Class[] parameterTypes, Object[] parameters) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		this.getClass().getDeclaredMethod(method, parameterTypes).invoke(this, parameters);
	}
}