// This entire file is part of my masterpiece
// Stephen Kwok

// This class is included in my masterpiece to demonstrate how I am able to keep the PreviewUnitWithEditable
// class as reusable as possible. By simply having all classes to be preview implement this interface,
// I can ensure that the PreviewUnitWithEditable class can support the previewing of any class it wants,
// even those written by other members of my team or even by other programmers working on other projects.
// To prove the utility of this class, I had the Turtle class in my slogo project implement this class
// in order to dispaly Turtle images for the front-end extension.

package authoringenvironment.model;

import javafx.scene.image.ImageView;

/**
 * 
 * Interface for all game elements that can be edited in Game Authoring
 * Environment (actors, levels, and game itself)
 * 
 * @author Stephen
 *
 */

public interface IEditableGameElement {

	/**
	 * sets the IEditableGameElement's name to the given name
	 * 
	 * @param name
	 *            to be set as IEditableGameElement's name
	 */
	public void setName(String name);

	/**
	 * 
	 * @return the IEditableGameElement's name
	 */
	public String getName();

	/**
	 * 
	 * @return the IEditableGameElement's ImageView
	 */
	public ImageView getImageView();

	/**
	 * sets the IEditableGameElement's ImageView to the given ImageView
	 * 
	 * @param imageView
	 *            to be set as IEditableGameElement's ImageView
	 */
	public void setImageView(ImageView imageView);

}
