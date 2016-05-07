//This entire file is part of my masterpiece.
// Michael Figueiras, The Loops Goat Cheese Salad
/**
 * This is the interface for the superclass Screen.java in the gui.view package. Its purpose is more
 * clearly outlined in the other descriptions of the code masterpiece.
 */
package gui.view;

import javafx.scene.Scene;

public interface IScreen {

	/**
	 * Returns the scene of the current screen
	 * @return scene
	 */
	public Scene getScene();

	/**
	 * Catch and displays error messages to the user in the form of an alert
	 * @param message
	 */
	public void showError(String message);
	
	/**
	 * Sets up the basic functionality of a screen with the necessary user input buttons
	 * @param buttons
	 */
	public void initialize(String buttons);

}
