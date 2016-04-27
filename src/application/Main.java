package application;

import gameplayer.controller.SplashScreenController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	/**
	 * Launches the SplashScreen to begin the program
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage myStage = primaryStage;
		myStage.setResizable(false);
		SplashScreenController splashScreenController = new SplashScreenController(myStage);
		myStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}