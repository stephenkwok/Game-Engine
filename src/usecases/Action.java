package usecases;

import gameengine.model.IAction;

/**
 * @author blakekaplan
 */
public abstract class Action implements IAction{

    private Actor myActor;

    public Action(Actor assignedActor){
        myActor = assignedActor;
    }

    @Override
    public abstract void perform();

    public Actor getActor(){
        return myActor;
    }
}
