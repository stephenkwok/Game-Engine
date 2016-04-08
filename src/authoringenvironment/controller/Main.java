package authoringenvironment.controller;

import authoringenvironment.view.GUIMain;
//import gameengine.model.Test;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    	//test scene
    	Group root = new Group();
    	Scene scene = new Scene(root, 300, 300, Color.THISTLE);
    	Label label = new Label("TEMPORARY. Will go back to splash screen.");
    	root.getChildren().add(label);
    	//
    	
    	myGUI = new GUIMain(s, scene);
    	s.setScene(myGUI.getScene());
    	s.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
