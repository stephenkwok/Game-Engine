package gameplayer.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

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
		try {
			this.myScreen = new SplashScreen();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			this.myScreen.showError(e.getMessage());
		}
		this.myStage.setScene(myScreen.getScene());
		this.myResources = ResourceBundle.getBundle(SPLASH_CONTROLLER_RESOURCE);
	}
	
	private void play() {
		FileChooserController fileChooserController = new FileChooserController(myStage, "play");
	}
	
	
	private void openHighScores() {
		FileChooserController fileChooserController = new FileChooserController(myStage, "scores");
	}
	
	private void edit() {
		Controller GUIMainController = new Controller(myStage);
	}

	private SplashScreen getScreen() {
		return this.myScreen;
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