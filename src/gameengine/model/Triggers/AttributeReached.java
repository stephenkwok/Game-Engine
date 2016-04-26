package gameengine.model.Triggers;

import gameengine.model.AttributeType;
import gameengine.model.IGameElement;

public class AttributeReached extends ITrigger {
	private String myKey;
	private AttributeType myType;
	private IGameElement myTarget;
	private int myTriggerValue;
	
	public AttributeReached(IGameElement target, AttributeType type, int triggerValue){
		if(target.getAttribute(type)!=null){
			target.getAttribute(type).addTriggerValue(triggerValue);
		}
		myType = type;
		myTarget = target;
		myTriggerValue = triggerValue;
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
	
	public AttributeType getMyType() {
		return myType;
	}
	
	public IGameElement getMyTarget() {
		return myTarget;
	}
	
	public Integer getMyTriggerValue() {
		return myTriggerValue;
	}
}
