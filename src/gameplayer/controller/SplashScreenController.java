package gameplayer.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import gamedata.controller.ChooserType;
import gamedata.controller.FileChooserController;
import gameplayer.view.SplashScreen;
import javafx.stage.Stage;
import authoringenvironment.controller.Controller;

public class SplashScreenController implements Observer {
	
	private static final String SPLASH_CONTROLLER_RESOURCE = "splashActions";
	
	private ResourceBundle myResources;
	private SplashScreen myScreen;
	private Stage myStage;
	

	public SplashScreenController(Stage stage) {
		this.myStage = stage;
		setUpScreen();
		this.myStage.setScene(myScreen.getScene());
		this.myResources = ResourceBundle.getBundle(SPLASH_CONTROLLER_RESOURCE);
	}
	
	private void setUpScreen() {
		this.myScreen = new SplashScreen();
		this.myScreen.addObserver(this);
	}
	
	private void play() {
		FileChooserController fileChooserController = new FileChooserController(myStage, ChooserType.PLAY);
	}
	
	
	private void openHighScores() {
		FileChooserController fileChooserController = new FileChooserController(myStage, ChooserType.SCORES);
	}
	
	private void edit() {
		Controller GUIMainController = new Controller(myStage);
	}


	@Override
	public void update(Observable o, Object arg) {
		String method = myResources.getString((String) arg);
		try {
			this.getClass().getDeclaredMethod(method).invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			this.myScreen.showError(e.getMessage());
		}
	}



}