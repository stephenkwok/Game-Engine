package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IPlayActor;
import gameengine.model.IGameElement;

/**
 * @author blakekaplan
 */
public abstract class ActorAction extends Action {

    public ActorAction(Actor assignedActor, Boolean oneTime){
        super((IGameElement)assignedActor, oneTime);
    }

    public IPlayActor getMyActor(){
        return (IPlayActor) getGameElement();
    }

    /**
     * The perform command that will implement the functionality unique to each Action type
     */
    @Override
    public abstract void execute();
}
