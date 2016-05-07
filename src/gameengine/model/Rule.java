package gameengine.model;

import gameengine.model.Actions.Action;
import gameengine.model.Triggers.Trigger;

/**
 * Contains a trigger and linked action to be performed when the trigger is
 * signaled
 * 
 * @author blakekaplan
 *
 */

public class Rule implements IRule{

    private Trigger myTrigger;
    private Action myAction;
    
    public Rule(Trigger trigger, Action action){
        setMyTrigger(trigger);
        setMyAction(action);
    }
    
    /**
     * Returns the trigger of the rule 
     */
    @Override
    public Trigger getMyTrigger() {
        return myTrigger;
    }
    
    /**
     * Returns the action of the rule 
     */
    @Override
    public Action getMyAction() {
        return myAction;
    }
    
    /**
     * Sets the trigger for the rule 
     * @param myTrigger
     */
	public void setMyTrigger(Trigger myTrigger) {
		this.myTrigger = myTrigger;
	}

	/**
	 * Sets the action for the rule
	 * 
	 * @param myAction
	 */
	public void setMyAction(Action myAction) {
		this.myAction = myAction;
	}
	
	@Override
	public String toString() {
		return "Trigger: " + myTrigger.getClass().getSimpleName() + "\t causes \t Action:" + myAction.getClass().getSimpleName();
		
	}
	

	@Override
	public boolean equals(Object other){
		return ((Rule)other).getMyAction().equals(getMyAction()) && myTrigger.equals(((Rule)other).getMyTrigger());
	}
}
