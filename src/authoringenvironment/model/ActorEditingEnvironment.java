package authoringenvironment.model;

import java.util.ResourceBundle;

import authoringenvironment.view.GUIActorEditingEnvironment;
import gameengine.model.IActor;
import javafx.scene.layout.Pane;

/**
 * This class serves as the interface that all actor editing environments must implement
 * 
 * @author Stephen
 */

public class ActorEditingEnvironment {
	private GUIActorEditingEnvironment myActorEditingGUI;
	private Pane myPane;
	private ResourceBundle myResources;
	
	public ActorEditingEnvironment(ResourceBundle myResources){
		this.myResources = myResources;
		initializeEnvironment();
	}
	/**
	 * Initializes the Actor Editing Environment
	 */
	private void initializeEnvironment(){
		myActorEditingGUI = new GUIActorEditingEnvironment(myResources);
	}
	
	/**
	 * Sets the environment's actor to be edited
	 * 
	 * @param actor to be edited 
	 */
	public void setActor(IActor actor){
		
	}
	
	/**
	 * 
	 * @return Editing Environment's Layout
	 */
	public Pane getPane(){
		return myPane;
	}
	
}
