package authoringenvironment.controller;

import authoringenvironment.view.GUIMain;
//import gameengine.model.Test;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by AnnieTang on 3/31/16.
 */
public class Main extends Application{
	
	private Stage s;
	GUIMain myGUI;
	
	Controller myMainScreen;
	
    @Override
    public void start(Stage myStage) throws Exception {
    	s = new Stage();
    	myGUI = new GUIMain(s, null);
    	s.setScene(myGUI.getScene());
    	s.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
