package gameengine.model;

import gameengine.model.Actions.Action;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Attribute {
	private IntegerProperty myInitialValue;
	private IntegerProperty myTriggerValue;
	private Action myAction;
	private BooleanBinding isActionTriggered;

	public Attribute(int initialValue, int triggerValue, Action action) {
		myInitialValue = new SimpleIntegerProperty(initialValue);
		myTriggerValue = new SimpleIntegerProperty(triggerValue);
		myAction = action;
		isActionTriggered = myInitialValue.isEqualTo(myTriggerValue);
		
		initAttribute();
	}
	
	public void setAttribute(int x){
		myInitialValue.set(x);
	}

	private void initAttribute() {
		//BooleanBinding triggerAction = myInitialValue.isEqualTo(myTriggerValue);
		isActionTriggered.addListener(new ChangeListener(){
	        @Override public void changed(ObservableValue o,Object oldVal, 
	                 Object newVal){
	             myAction.perform();
	        }
        });
	}
	

}
