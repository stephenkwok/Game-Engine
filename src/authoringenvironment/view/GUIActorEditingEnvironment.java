package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.model.IActor;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
/**
 * Returns BorderPane to represent Actor Editing Environment. 
 * @author AnnieTang
 *
 */
public class GUIActorEditingEnvironment implements IGUI, IEditingEnvironment {
	private static final String ACTOR_OPTIONS_RESOURCE = "actorEditorOptions";
	private static final String ACTOR_ATTRIBUTES = "Actor Attributes";
	private BorderPane myRoot;
	private GUILibrary library;
	private TabAttributes attributes;
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
		VBox vbox = new VBox();
		//attributes
		library = new GUILibrary(myResources);
		attributes = new TabAttributes(myResources,ACTOR_ATTRIBUTES,ACTOR_OPTIONS_RESOURCE);
		TabPane attributeTP = new TabPane();
		attributeTP.getTabs().add(attributes.getTab());
		vbox.getChildren().addAll(attributeTP, library.getPane());
		myRoot.setLeft(vbox);
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
