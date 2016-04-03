package authoringenvironment.view;

import java.lang.reflect.InvocationTargetException;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

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
	 * Creates the JavaFX Pane, which will be put within a BorderPane through the controller.
	 * @return Scene
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	Scene getScene() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

	Pane getPane();

	/**
	 * This method updates each of the GUI elements' visual representations, based on the
	 * data each refers to. 
	 */
	void updateAllNodes();
}