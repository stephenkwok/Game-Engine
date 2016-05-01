package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;

/**
 * @author blakekaplan
 */
public abstract class ActorAction extends Action {

    public ActorAction(Actor assignedActor){
        super((IGameElement)assignedActor);
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
