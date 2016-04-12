package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.ITrigger;
import javafx.scene.input.KeyCode;

public class KeyTrigger implements ITrigger {
	private String myKey;
	
	public KeyTrigger(KeyCode key){
		setMyKey(key.getName());
	}
	
	public String getMyKey(){
		return myKey;
	}

	@Override
	public boolean evaluate(Actor myActor) {
		return true;
	}


	public void setMyKey(String myKey) {
		this.myKey = myKey;
	}

}
