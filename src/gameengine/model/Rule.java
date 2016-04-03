package gameengine.model;

import gameengine.controller.Action;
import gameengine.model.IRule;
import gameengine.model.ITrigger;

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
