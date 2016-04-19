package gameplayer.controller;

import java.lang.reflect.InvocationTargetException;

import gameplayer.view.SplashScreen;
import gui.controller.ScreenController;
import javafx.stage.Stage;

/**
 * This abstract controller class will allow returning to splash functionality for all screens that are branches off the splash screen
 * @author Carine
 *
 */
public abstract class BranchScreenController extends ScreenController {
	
	/**
	 * Instantiates BranchScreenController
	 * @param myStage
	 */
	public BranchScreenController(Stage myStage) {
		super(myStage);
	}

	/**
	 * Creates a splash screen and swaps the stage scene for the splash screen stage
	 */
	public void goToSplash(){
		//TODO create a splash screen controller
		SplashScreen mySplash = new SplashScreen(getStage());
		try {
			getStage().setScene(mySplash.getScene());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			//GET RID OF THIS STACK TRACE DO NOT FORGET OR YOU WILL BE CURSED!
			e.printStackTrace();
		}
	}


}
