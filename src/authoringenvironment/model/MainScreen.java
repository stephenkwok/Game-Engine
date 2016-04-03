package authoringenvironment.model;

import authoringenvironment.controller.Controller;
import authoringenvironment.view.GUIMainScreen;
import javafx.scene.layout.Pane;

public class MainScreen {
	private GUIMainScreen guiMainScreen;
	private Controller myController;
	
	public MainScreen(Controller controller){
		this.myController = controller;
		initializeEnvironment();
	}
	
	private void initializeEnvironment(){
		guiMainScreen = new GUIMainScreen(myController);
	}
	
	public Pane getPane(){
		return guiMainScreen.getPane();
	}
	
	public void update() {
		guiMainScreen.updateAllNodes();
	}
}
