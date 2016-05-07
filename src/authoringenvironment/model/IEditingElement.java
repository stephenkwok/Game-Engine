// This entire file is part of my masterpiece.
// Amy Zhao

// This is an interface implemented by all view elements (e.g. ComboBoxes, TextFields, etc.) that edit some attribute of a game, a level, or an actor.
// In a similar fashion, all games, levels, and actors that can be edited implement IEditableGameElement, and thus this allows for us to easily re-use the 
// same view elements to edit different levels or different actors. Our ActorEditingEnvironment, LevelEditingEnvironment, and GameEditingEnvironments are 
// designed such that when their setEditableElement() method is called, it updates the editable element for all of the IEditingElements contained within it,
// and those do the same for any of their child IEditingElements. This cascade allows for a particular actor or level's attributes (e.g. name, size, image) 
// to be reflected in the view elements of the editing environment so that the user can easily see the attributes they've already chosen. This interface 
// is good code because it is simple and clearly demonstrates a unique functionality of all elements that implement this interface, while also enhancing user friendliness 

package authoringenvironment.model;

/**
 * Interface for classes that edit an IEditableGameElement.
 * 
 * @author amyzhao
 *
 */

public interface IEditingElement {
	
	/**
	 * Sets the class's IEditableGameElement to a different IEditableGameElement.
	 * @param element: the class' new IEditableGameElement.
	 */
	public void setEditableElement(IEditableGameElement element);

}
