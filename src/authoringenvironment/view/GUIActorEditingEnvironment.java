package authoringenvironment.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * Returns BorderPane to represent Actor Editing Environment. 
 * @author AnnieTang
 *
 */
public class GUIActorEditingEnvironment implements IGUI {
	Stage myStage;
	BorderPane myRoot;
	Scene myScene;
	int windowHeight;
	int windowWidth;
	
	public GUIActorEditingEnvironment(int height, int width, Stage myStage) {
		this.myStage = myStage; 
		this.windowHeight = height;
		this.windowWidth = width;
	}

	@Override
	public Scene getScene() {
		myRoot = new BorderPane();
		myScene = new Scene(myRoot, windowHeight, windowWidth, Color.WHITE);
		return myScene;
	}

	@Override
	public void updateAllNodes() {
		// TODO 
	}

}
