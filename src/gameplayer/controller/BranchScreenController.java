package gameplayer.controller;


import gui.view.Screen;

import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import gameplayer.controller.SplashScreenController;
import gameplayer.view.BaseScreen;
import javafx.stage.Stage;

/**
 * This abstract controller class will allow returning to splash functionality for all screens that are branches off the splash screen
 * @author Carine
 *
 */
public abstract class BranchScreenController implements Observer {
	private ResourceBundle myResources;
	private BaseScreen myScreen;
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

	@Override
	public void update(Observable o, Object arg) {
		String method = myResources.getString((String)arg);
		try {
			try {
				this.getClass().getDeclaredMethod(method).invoke(this);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| SecurityException e) {
				e.printStackTrace();
				this.myScreen.showError(e.getMessage());
			}
		} catch (NoSuchMethodException e) {
			try {
				this.getClass().getSuperclass().getDeclaredMethod(method).invoke(this);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e1) {
				e.printStackTrace();
				this.myScreen.showError(e.getMessage());
			}
		}
		
		
	}

}
