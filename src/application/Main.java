package application;

import gameplayer.view.SplashScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage myStage = primaryStage;
		myStage.setResizable(false);
		SplashScreen myScreen = new SplashScreen(myStage);
		myStage.setScene(myScreen.getScene());
		
		myStage.show();
		
	}
	
	public static void main(String[] args){
		launch(args);
	}

}