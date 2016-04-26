package gameengine.model.Actions;

import gameengine.model.IPlayActor;

/**
 * @author blakekaplan
 */
public abstract class ActorAction extends Action {

    public ActorAction(IPlayActor assignedActor){
        super(assignedActor);
    }

    public IPlayActor getMyActor(){
        return (IPlayActor) getGameElement();
    }

    /**
     * The perform command that will implement the functionality unique to each Action type
     */
    @Override
    public abstract void perform();
}
