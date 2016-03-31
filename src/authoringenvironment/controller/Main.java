package authoringenvironment.controller;

import authoringenvironment.view.MainScreenGUI;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by AnnieTang on 3/31/16.
 */
public class Main extends Application{
	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 800;
	
	private Stage s;
	MainScreenGUI myGui;
    @Override
    public void start(Stage myStage) throws Exception {
    	s = new Stage();
    	myGui = new MainScreenGUI(SCREEN_HEIGHT, SCREEN_WIDTH, s);
    	s.setScene(myGui.getScene());
    	s.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
