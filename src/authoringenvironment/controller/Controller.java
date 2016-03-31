package authoringenvironment.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import authoringenvironment.model.ICreatedActor;
import authoringenvironment.model.ICreatedLevel;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class serves as the interface that all authoring environment main screens must implement
 * 
 * @author Stephen, AnnieTang
 */

public class Controller {
	Stage myStage;	
	List<ICreatedLevel> levels;
	
	
	public Controller(Stage myStage){
		this.myStage = myStage;
		levels = new ArrayList<>();
	}
	/**
	 * Displays the Main Screen giving user option to create/edit actors and levels or save 
	 * data from created game into XML file 
	 */
	public void show(){
		myStage.setScene(null);
	}
	
	/**
	 * Switches screen to Level Editing Environment
	 * 
	 * @param level - level to be edited 
	 * @param createdActors - list of created Actors that can be placed into the level 
	 */
	public void goToLevelEditing(ICreatedLevel level, List<ICreatedActor> createdActors){
		
	}
	
	/**
	 * Switches screen to Actor Editing Environment
	 * 
	 * @param actor - Actor to edit
	 */
	public void goToActorEditing(ICreatedActor actor){
		
	}
	
	/**
	 * Passes Actor and Level info to Game Data to be saved in XML file
     * @param file: file to write to.
     */
	public void saveGame(File file){
		
	}
	
	/**
     * Gets the current workspace's stage.
     * @return current workspace's stage.
     */
    public Stage getStage() {
        return myStage;
    }
    
    /**
     * Returns list of created levels.
     * @return
     */
    public List<ICreatedLevel> getLevels(){
    	return levels;
    }
    
    /**
     * For each level that is created, adds it to the running list in this class. 
     * @param newLevel
     */
    public void addLevel(ICreatedLevel newLevel){
    	levels.add(newLevel);
    }
	
}
