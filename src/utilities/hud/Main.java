package utilities.hud;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application implements IAuthoringHUDController{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage myStage = primaryStage;
		myStage.setResizable(false);
		Group root = new Group();
		Scene s = new Scene(root, 50, 50);
		Button b = new Button("OPEN");
		b.setOnAction(e->new PopupSelector(this)); //this implements IAuthoringHUDController
		root.getChildren().add(b);
		
		myStage.setScene(s);
		
		myStage.show();
	}
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void setHUDInfoFile(String location) {
		System.out.println(location);
	}

}