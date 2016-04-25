package gameengine.model;

import gameengine.model.IRule;
import gameengine.model.Actions.Action;
import gameengine.model.Triggers.ITrigger;

/**
 * Contains a trigger and linked action to be performed when the trigger is
 * signaled
 * 
 * @author blakekaplan
 *
 */
public class Rule implements IRule {

	private ITrigger myTrigger;
	private Action myAction;

	public Rule(ITrigger trigger, Action action) {
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
	 * 
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
}
