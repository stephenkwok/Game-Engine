package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.ITrigger;
import javafx.scene.input.KeyCode;

public class KeyTrigger implements ITrigger {
	String myKey;
	
	public KeyTrigger(KeyCode key){
		myKey = key.getName();
	}
	
	public String getTriggerName(){
		return myKey;
	}

	@Override
	public boolean evaluate(Actor myActor) {
		return true;
	}

}
