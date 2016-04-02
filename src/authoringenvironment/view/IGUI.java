package authoringenvironment.view;

import javafx.scene.Scene;

/**
 * This interface contains the information that the frontend (of the authoring environment)
 *  will pass to the backend (of the authoring environment). The model will call these methods 
 *  in order to change the data representation of the authoring environment, after receiving
 *  input from the user using the environment.
 *
 * @author Annie
 */
public interface IGUI {
	/**
	 * Creates the JavaFX Scene, which will be put on a Stage in the controller.
	 * @return Scene
	 */
	Scene getScene();
    
	/**
     * This method updates each of the GUI elements' visual representations, based on the
     * data each refers to. 
     */
    void updateAllNodes();
}