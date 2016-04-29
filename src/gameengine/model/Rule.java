package gameengine.model;

import gameengine.model.IRule;
import gameengine.model.Triggers.BottomCollision;
import gameengine.model.Triggers.TopCollision;
import gameengine.model.Actions.Action;
import gameengine.model.Triggers.ITrigger;

/**
 * Contains a trigger and linked action to be performed when the trigger is
 * signaled
 * 
 * @author blakekaplan
 *
 */

public class Rule implements IRule{

    private ITrigger myTrigger;
    private Action myAction;
    private int ID;
    
    public Rule(ITrigger trigger, Action action){
        setMyTrigger(trigger);
        setMyAction(action);
    }
    
    /**
     * Returns the trigger of the rule 
     */
    @Override
    public ITrigger getMyTrigger() {
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
	public void setMyTrigger(ITrigger myTrigger) {
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
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public int getID() {
		return ID;
	}
	
	public String toString() {
		String id = Integer.toString(ID);
		return ("id: " + id);
	}
	
	@Override
	public boolean equals(Object other){
		return ((Rule)other).getMyAction().equals(getMyAction()) && myTrigger.equals(((Rule)other).getMyTrigger());
	}
}
