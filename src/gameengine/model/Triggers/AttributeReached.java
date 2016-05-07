package gameengine.model.Triggers;

import gameengine.model.AttributeType;
import gameengine.model.IGameElement;

public class AttributeReached extends Trigger {
	private IGameElement myTarget;
	private String myKey;
	private AttributeType myType;
	private int myTriggerValue;
	
	public AttributeReached(IGameElement target, AttributeType type, Integer triggerValue){
		if(target.getAttribute(type)!=null){
			target.getAttribute(type).addTriggerValue(triggerValue);
		}
		myType = type;
		myTarget = target;
		myTriggerValue = triggerValue;
		myKey = target.getName()+type.toString()+triggerValue;
	}
	
	@Override
	public Object[] getParameters(){
		return new Object[]{myTarget,myType,myTriggerValue};
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
	
	public int getMyValue() {
		return myTriggerValue;
	}
}
