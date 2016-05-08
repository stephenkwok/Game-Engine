// This entire file is part of my masterpiece.
// Blake Kaplan

/*
 * The ActorAction subclass adds another level to the Action hierarchy.
 * 
 * I mentioned in the comments in the Action class that Actions are able to handle
 * both Levels and Actors. However, we determined that there are some Actions like NextImage
 * or MoveUp that should only be able to work on Actors. Keeping this in mind, I implemented
 * this abstract class, which, although it only adds one additional method, is incredible important
 * for our design. As we see, Action subclasses can now be written to work exclusively on Actors,
 * specifically IPlayActors that have an API limited to gameplay methods only. Rather than having
 * to worry about acting on a Level in each Action, the casting logic is consolidated in a single
 * location. This serves to minimize repeated code. Additionally, while these ActorActions are
 * slightly different, they are still used in the same way, as all the logic specific to any
 * Action is written in the perform method. See NextImage for an example.
 */

package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;

/**
 * @author blakekaplan
 */
public abstract class ActorAction extends Action {

    public ActorAction(Actor assignedActor){
        super(assignedActor);
    }

    /**
     * Provides the IPlayActor that the Action will be applied to
     * 
     * @return	The IPlayActor that the Action will be applied to
     */
    public IPlayActor getMyActor(){
        return (IPlayActor) getGameElement();
    }

    /**
     * The perform command that will implement the functionality unique to each Action type
     */
    @Override
    public abstract void perform();
}
