package authoringenvironment.model;

import authoringenvironment.controller.Controller;
import authoringenvironment.view.GUIMainScreen;
import javafx.scene.layout.Pane;

public class MainScreen {
	GUIMainScreen guiMainScreen;
	
	public void initializeEnvironment(Controller controller){
		guiMainScreen = new GUIMainScreen(controller);
	}
	
	public Pane getPane(){
		return guiMainScreen.getPane();
	}
	
	public void update() {
		guiMainScreen.updateAllNodes();
	}
}
