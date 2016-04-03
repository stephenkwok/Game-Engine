package authoringenvironment.view;

import java.util.ResourceBundle;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
/**
 * Returns BorderPane to represent Actor Editing Environment. 
 * @author AnnieTang
 *
 */
public class GUIActorEditingEnvironment implements IGUI {
	private BorderPane myRoot;
	private GUILibrary library;
	private ResourceBundle myResources;
	
	public GUIActorEditingEnvironment(ResourceBundle myResources) {
		this.myResources = myResources;
	}

	@Override
	public void updateAllNodes() { 
	}

	@Override
	public Pane getPane() {
		myRoot = new BorderPane();
		library = new GUILibrary(myResources);
		myRoot.setLeft(library.getPane());
		//left will be hbox of attributes and library
		//right will be draggable area thingiemabob
		return myRoot;
	}
}
