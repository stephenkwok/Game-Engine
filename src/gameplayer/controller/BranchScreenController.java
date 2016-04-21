package gameplayer.controller;


import gui.view.Screen;

import java.util.Observable;
import java.util.Observer;

import gameplayer.controller.SplashScreenController;
import javafx.stage.Stage;

/**
 * This abstract controller class will allow returning to splash functionality for all screens that are branches off the splash screen
 * @author Carine
 *
 */
public abstract class BranchScreenController implements Observer {
	
	private Stage myStage;
	
	public BranchScreenController(Stage stage) {
		this.myStage = stage;
	}
	
	/**
	 * Creates a splash screen and swaps the stage scene for the splash screen stage
	 */
	protected void goToSplash(){
		//TODO create a splash screen controller
		SplashScreenController splashScreenController = new SplashScreenController(myStage);
	}
	
	protected void changeScreen (Screen newScreen) {
		this.myStage.setScene(newScreen.getScene());
	}
	
	protected Stage getStage() {
		return this.myStage;
	}
	
	


}
