package authoringenvironment.controller;

//import gameengine.model.Test;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Class to test Authoring Environment Created by AnnieTang on 3/31/16.
 */
public class Main extends Application {
	private static final String SPLASH_MESSAGE = "TEMPORARY. Will go back to splash screen.";
	private Stage s;
	Controller myMainScreen;

	@Override
	public void start(Stage myStage) throws Exception {
		s = new Stage();
		// test splash screen
		Group root = new Group();
		Scene scene = new Scene(root, 300, 300, Color.THISTLE);
		Label label = new Label(SPLASH_MESSAGE);
		root.getChildren().add(label);
		//

		Controller c = new Controller(s);
		/*
		 * myGUI = new GUIMain(s, scene); s.setScene(myGUI.initializeScreen());
		 */
		s.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}