package gui.controller;

import java.lang.reflect.InvocationTargetException;

import gameplayer.view.SplashScreen;
import javafx.stage.Stage;

public abstract class ScreenController implements IScreenController{

	private Stage myStage;
	
	public ScreenController(Stage myStage) {
		this.myStage = myStage;
	}
	
	public void goToSplash(){
		//TODO create a splash screen controller
		SplashScreen mySplash = new SplashScreen(myStage);
		try {
			myStage.setScene(mySplash.getScene());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			//GET RID OF THIS STACK TRACE DO NOT FORGET OR YOU WILL BE CURSED!
			e.printStackTrace();
		}
	}
	
	public Stage getStage(){
		return myStage;
	}
	

}
