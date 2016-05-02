package gameplayer.controller;

import java.io.File;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import voogasalad.util.hud.source.HUDController;

/**
 * This class serves as the controller for the base screen, which is made up of three parts: the toolbar with button functionality,
 * the game screen, and the HUD screen. Due to this, it encompasses the GameController and handles updating changes for
 * button clicks, Game changes, and HUD updating. 
 * @author mdf15, cmt57
 *
 */
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
		this.myGameController = gameController;
		((Observable) myGameController).addObserver(this);
		setUpScreen();
		changeScreen(myScreen);
	}

	/**
	 * Calls the respective methods to divide the work of instantiating features into the three main parts
	 */
	private void setUpScreen() {
		this.myScreen = new BaseScreen();
		((Observable) this.myScreen).addObserver(this);
		setUpGameScreen();
		setUpHUDScreen();
		setMyScreen(this.myScreen);
	}

	private void toggleSound() {
		this.myGameController.getGame().toggleSound();
	}

	private void toggleMusic() {
		this.myGameController.getGame().toggleMusic();
	}
	
	/**
	 * Uses the CreatorController to take the necessary information from the game (via the GameController) to write
	 * a new xml file with the current status of the game
	 */
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
			myScreen.showError(e.getMessage());
		}
		toggleUnPause();
	}

	/**
	 * Transitions to an alert which has the capacity to initiate screen changes
	 */
	private void switchGame() {
		togglePause();
		this.myScreen.switchAlert();
	}

	/**
	 * Switches scenes to allow the user the opportunity to choose a new game
	 */
	private void chooseGame() {
		togglePause();
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

	/**
	 * Changes the two parts of the screen (the game and the HUD) to switch over their current status to a new game
	 */
	private void restartGame() {
		myGameController.restartGame();
		setUpHUDScreen();
	}

	/**
	 * Uses the game controller to set the front end view of the game screen component
	 */
	private void setUpGameScreen() {
		this.myScreen.setGameScreen(this.myGameController.getView());
	}

	/**
	 * Uses the HUD controller to set the front end view of the HUD screen component
	 */
	private void setUpHUDScreen() {
		myHUDController = new HUDController();
		myHUDController.init(myGameController.getGame().getHUDInfoFile(), myGameController.getGame(), new TLGCSValueFinder());
		myScreen.setHUDScreen(myHUDController.getView());
		
	}
	
	/**
	 * Uses reflection to invoke specific base screen controller-methods in this class
	 */
	@Override
	public void invoke(String method, Class[] parameterTypes, Object[] parameters) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		this.getClass().getDeclaredMethod(method, parameterTypes).invoke(this, parameters);
	}
	
	public void showGameError(Exception e) {
		e.printStackTrace();
		this.myScreen.showError(e.getMessage());
	}
	
	
	
}