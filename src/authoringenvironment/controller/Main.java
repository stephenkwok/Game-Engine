package authoringenvironment.controller;

import authoringenvironment.view.GUIMain;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by AnnieTang on 3/31/16.
 */
public class Main extends Application{
	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 800;
	
	private Stage s;
	GUIMain myGUI;
	
	Controller myMainScreen;
	
    @Override
    public void start(Stage myStage) throws Exception {
    	s = new Stage();
    	myGUI = new GUIMain(SCREEN_HEIGHT, SCREEN_WIDTH, s);
    	s.setScene(myGUI.getScene());
    	s.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
