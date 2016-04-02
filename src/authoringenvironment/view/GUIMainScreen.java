package authoringenvironment.view;

import java.util.List;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GUIMainScreen implements IGUI {
	
	Scene myScene;
	private List<IEditableGameElement> createdActors;
	private List<IEditableGameElement> createdLevels;
	private VBox actorLabelContainer;
	private VBox levelLabelContainer;
	private ScrollPane actorScrollPane;
	private ScrollPane levelScrollPane;
	private HBox scrollPaneContainer;
	private BorderPane borderPane;
	private IEditingEnvironment levelEditor;
	private IEditingEnvironment actorEditor;
	
	public GUIMainScreen() {
		actorLabelContainer = new VBox();
		levelLabelContainer = new VBox();
	}
	
	public void initializeEnvironment(){
		//create instance of GUI class that represents main screen 
	}
	
	public Pane getPane(){
		return borderPane;
	}

	@Override
	public void updateAllNodes() {
		// TODO Auto-generated method stub
		
	}
}
