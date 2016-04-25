package gameengine.model.Triggers;

import gameengine.model.AttributeType;
import gameengine.model.IGameElement;

public class AttributeReached extends ITrigger {
	String myKey;
	
	public AttributeReached(AttributeType type,IGameElement target, int triggerValue){
		if(target.getAttribute(type)!=null){
			target.getAttribute(type).addTriggerValue(triggerValue);
		}
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
