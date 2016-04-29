package gameengine.model.Triggers;

import gameengine.model.IPlayActor;
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
    
	public String getMyKey() {
		return myKey;
	}

    public void setMyKey(String myKey) {
        this.myKey = myKey;
    }
    
    public KeyCode getMyKeyCode() {
    	return myKeyCode;
    }

	/**
	 * Checks a boolean condition against the state of an actor
	 *
	 * @param otherTrigger
	 *            A trigger to check information against
	 * @return A boolean that says if the condition is true or false
	 */
	@Override
	public boolean evaluate(ITrigger otherTrigger) {
		return true;
	}
}
