package authoringenvironment.controller;

import javafx.scene.image.Image;

/**
 * 
 * Interface for all game elements that can be edited in Game Authoring Environment (actors and levels)
 * 
 * @author Stephen
 *
 */

public interface IEditableGameElement {

	public String getName();
	
	public Image getImage();
	
}
