package gameengine.model;

import gameengine.model.IRule;
import gameengine.model.ITrigger;
import gameengine.model.Actions.Action;

/**
 * @author blakekaplan
 */
public class Rule implements IRule{

    private ITrigger myTrigger;
    private Action myAction;

    public Rule(ITrigger trigger, Action action){
        setMyTrigger(trigger);
        setMyAction(action);
    }

    @Override
    public ITrigger getMyTrigger() {
        return myTrigger;
    }

    @Override
    public Action getMyAction() {
        return myAction;
    }


	public void setMyTrigger(ITrigger myTrigger) {
		this.myTrigger = myTrigger;
	}

	public void setMyAction(Action myAction) {
		this.myAction = myAction;
	}
}
