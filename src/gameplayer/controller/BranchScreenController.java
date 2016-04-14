package gameplayer.controller;

import java.lang.reflect.InvocationTargetException;

import gameplayer.view.SplashScreen;
import gui.controller.ScreenController;
import javafx.stage.Stage;

public abstract class BranchScreenController extends ScreenController {
	
	public BranchScreenController(Stage myStage) {
		super(myStage);
	}

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
