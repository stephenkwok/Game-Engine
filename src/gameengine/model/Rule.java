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
        myTrigger = trigger;
        myAction = action;
    }

    @Override
    public ITrigger getTrigger() {
        return myTrigger;
    }

    @Override
    public Action getAction() {
        return myAction;
    }
}
