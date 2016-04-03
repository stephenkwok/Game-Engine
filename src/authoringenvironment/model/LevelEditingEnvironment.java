package authoringenvironment.model;

import java.util.List;

import authoringenvironment.controller.Controller;
import authoringenvironment.view.GUILevelEditingEnvironment;
import gameengine.controller.ILevel;
import gameengine.model.IActor;
import javafx.scene.layout.Pane;

/**
 * This class serves as the interface that all level editing environments must implement
 * 
 * @author Stephen
 * @author amyzhao
 */

public class LevelEditingEnvironment {
	private GUILevelEditingEnvironment myLevelEditingGUI;
  
	public LevelEditingEnvironment() {
		initializeEnvironment();
	}
	
	/**
	 * Initializes the Level Editing Environment
	 */
	private void initializeEnvironment(){
		myLevelEditingGUI = new GUILevelEditingEnvironment();
	}
	
	/**
	 * Sets the environment's level to be edited
	 * 
	 * @param level to be edited 
	 */
	public void setLevel(ILevel level){
		
	}
	
	/**
	 * 
	 * @return Editing Environment's Layout
	 */
	public Pane getPane(){
		return myLevelEditingGUI.getPane();
	}

}
