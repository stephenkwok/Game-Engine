package gameengine.model.Triggers;

import gameengine.model.AttributeType;
import gameengine.model.IAttributable;

public class AttributeReached extends ITrigger {
	String myKey;
	
	public AttributeReached(AttributeType type,IAttributable target, int triggerValue){
		target.getAttribute(type).addTriggerValue(triggerValue);
		myKey = target.getName()+type.toString()+triggerValue;
	}
	
	@Override
    public String getMyKey() {
        return myKey;
    }
    
    @Override
    public boolean evaluate(ITrigger otherTrigger) {
		return true;
	}
}
