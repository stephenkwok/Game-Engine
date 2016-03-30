package usecases;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AuthoringEnvironmentMain extends Application {
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 600;

	private Stage s;

	@Override
	public void start(Stage myStage) throws Exception {
		s = new Stage();
		Group root = new Group();
        Scene theScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        s.setScene(theScene);
        
		CreatedActorInstance actor = new CreatedActorInstance();
		root.getChildren().add(actor);
		
		s.show();

	}

	public static void main(String[] args){
		launch(args);
	}
}