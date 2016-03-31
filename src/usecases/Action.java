package usecases;

import gameengine.model.IAction;

import java.util.List;
import java.util.Objects;

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
