package authoringenvironment.model;

// This class represents the actors that the user will create. It holds the relevant data that must be saved in the XML
// and will implement the IActor interface, and it will also extend the GestureSource class so that the user can interact
// with it from the GUI

public interface CreatedActor {

	/**
	 * Sets the image of the actor by accessing the image through the ImageLibrary.
	 * @param filename: name of image file.
	 */
	public void setImage(String filename);
	
	/**
	 * Sets the name of the actor.
	 * @param name: name of actor.
	 */
	public void setName(String name);
	
	/**
	 * Sets whether or not the actor responds to a trigger (mouse click, key press, or collision).
	 * @param isReactive: true if the actor responds to a trigger event, false o.w.
	 */
	public void setReactive(boolean isReactive);
	
	/**
	 * Sets the position of the actor.
	 * @param x: x-position.
	 * @param y: y-position.
	 */
	public void setPosition(double x, double y);
	
	/**
	 * Adds a rule to the actor.
	 * @param trigger: event that triggers the action (e.g. MouseClick, KeyPress, Collision).
	 * @param action: action that the actor carries out in response (e.g. MoveUp, MoveDown, Die).
	 */
	public void addRule(String trigger, String action);
	
	/**
	 * Returns the info necessary to create the actor in the game engine in a specified order and format.
	 * @return: actor's info in a specified order and format.
	 */
	public String getInfo();
}
