// This entire file is part of my masterpiece.

// Michelle Chen

/**
 * Triggers represent the "cause" part of a rule--when signaled, they execute a corresponding action. For example, when the user presses the right key,
 * the main character on the screen can execute any action (as long as it is associated with the trigger).
 * 
 * Triggers are easily extended--adding a new trigger requires simply implementing the appropriate interface and creating the relevant trigger to be mapped to 
 * an action for the actor. They're also flexible--the keytrigger, for example, supports any key and is part of the reason we can have multiplayer within our game engine.
 * Triggers also include collisions, which make use of reflection in order to carry out relevant methods. Triggers are a key feature of our rule component, embodying
 * principles of flexibility and extensibility, and also reinforces open-closed extension capabilities while maintaining the ability of the  class to employ its 
 * interface's methods.
 */

package gameengine.model.Triggers;

import javafx.scene.input.KeyCode;

public class KeyTrigger extends ITrigger {

    private String myKey;
    private KeyCode myKeyCode;
    
    public KeyTrigger(KeyCode key) {
    	myKeyCode = key;
        setMyKey(key.getName());
    }
    
    @Override
    public Object[] getParameters(){
    	return new Object[]{myKeyCode};
    }
    
    /**
     * Returns key corresponding to key trigger
     */
	public String getMyKey() {
		return myKey;
	}

	/**
	 * Sets key for trigger
	 * @param myKey
	 */
    public void setMyKey(String myKey) {
        this.myKey = myKey;
    }
    
    /**
     * Returns keycode corresponding to key trigger
     * @return
     */
    public KeyCode getMyKeyCode() {
    	return myKeyCode;
    }

	/**
	 * Checks a boolean condition against the state of an actor
	 *
	 * @param otherTrigger a trigger to check information against
	 * @return A boolean that says if the condition is true or false
	 */
	@Override
	public boolean evaluate(ITrigger otherTrigger) {
		return true;
	}
}
