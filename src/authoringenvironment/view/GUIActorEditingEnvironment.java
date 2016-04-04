package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.controller.ILevel;
import gameengine.model.IActor;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
	private IActor myActor;
	
	public GUIActorEditingEnvironment(ResourceBundle myResources) {
		this.myResources = myResources;
		initializeEnvironment();
	}

	@Override
	public void updateAllNodes() { 
	}

	@Override
	public Pane getPane() {
		return myRoot;
	}
	
	private void initializeEnvironment() {
		myRoot = new BorderPane();
		setLeftPane();
		setRightPane();

	}
	
	private void setLeftPane(){
		//left will be hbox of attributes and library
		HBox hbox = new HBox();
		//attributes
		library = new GUILibrary(myResources);
		hbox.getChildren().addAll(library.getPane());
		myRoot.setLeft(hbox);
	}
	
	private void setRightPane(){
		//right will be draggable area thingiemabob
	}
	
	@Override
	public void setEditable(IEditableGameElement editable) {
		myActor = (IActor) editable;
	}
	
	public void setActor(IActor actor){
		myActor = actor;
	}
}
