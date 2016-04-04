package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.model.IActor;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
/**
 * Returns BorderPane to represent Actor Editing Environment. 
 * @author AnnieTang
 *
 */
public class GUIActorEditingEnvironment implements IGUI, IEditingEnvironment {
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
	
	public void setActor(IActor actor){
		
	}

	@Override
	public void initializeEnvironment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEditable(IEditableGameElement editable) {
		// TODO Auto-generated method stub
		
	}
}
