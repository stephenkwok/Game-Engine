package gameplayer.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import gamedata.controller.ChooserType;
import gamedata.controller.FileChooserController;
import gameplayer.view.ISplashScreen;
import gameplayer.view.SplashScreen;
import javafx.stage.Stage;

/**
 * This controller handles the necessary transitions to different parts of the program, including the game authoring environment, the high score screen,
 * and the base screen where the game is played. It uses reflection to initiate the necessary changes enacted by button clicks and reflects these changes
 * by passing the stage to the next controller so scenes can be swapped out.
 * @author mdf15, cmt57
 *
 */
public class SplashScreenController implements Observer {

	private static final String SPLASH_CONTROLLER_RESOURCE = "splashActions";

	private ResourceBundle myResources;
	private ISplashScreen myScreen;
	private Stage myStage;

	public SplashScreenController(Stage stage) {
		this.myStage = stage;
		setUpScreen();
		this.myStage.setScene(myScreen.getScene());
		this.myResources = ResourceBundle.getBundle(SPLASH_CONTROLLER_RESOURCE);
	}

	/**
	 * Instantiates and observers the front end screen
	 */
	private void setUpScreen() {
		this.myScreen = new SplashScreen();
		((Observable) this.myScreen).addObserver(this);
	}

	/**
	 * Instantiates an intermediary controller to allow the user to pick a game to play
	 */
	private void play() {
		FileChooserController fileChooserController = new FileChooserController(myStage, ChooserType.PLAY);
	}

	/**
	 * Instantiates an intermediary controller to allow the user to pick a game to view scores from
	 */
	private void openHighScores() {
		FileChooserController fileChooserController = new FileChooserController(myStage, ChooserType.SCORES);
	}

	/**
	 * Instantiates the controller to transition to the Game Authoring Environment
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private void edit() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Controller GUIMainController = new Controller(myStage);
	}

	@Override
	public void update(Observable o, Object arg) {
		List<Object> myList = (List<Object>) arg;
		String methodName = (String) myList.get(0);
		try {
			if(myResources.getString(methodName).equals("null")){
				this.getClass().getDeclaredMethod(methodName).invoke(this);
			}
			else{
				Class<?> myClass = Class.forName(myResources.getString(methodName));
				Object arg2 = myClass.cast(myList.get(1));
				this.getClass().getDeclaredMethod(methodName).invoke(this, arg2);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			this.myScreen.showError(e.getMessage());
		}
	}

}