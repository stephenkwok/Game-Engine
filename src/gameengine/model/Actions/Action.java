// This entire file is part of my masterpiece.
// Blake Kaplan

/*
 * This class contains the core functionality for all Actions in our project.
 * 
 * I believe that this class follows good design principles because it generalizes
 * most of the logic for all Actions. All Action subclasses simply need to
 * implement the perform method, which defines the logic specific to that Action.
 * The fact that Actions only differ by a single method shows that most of their logic is shared.
 * I chose to use an abstract class exactly for that reason. I was able to put all of the
 * shared functionality in a single location to be used by many subclasses. Actions are extremely
 * important for our project's functionality so it was important that we were able to treat them
 * all in a uniform manner using the methods written below.
 * 
 * It is also important to point out that Actions can apply to both Levels and Actors.
 * As we developed our project we discovered that it would make sense for both Levels and Actors
 * to be able to hold the cause and effect behaviors that are central to our project's design.
 * We realized that some Actions would be useful for both Actors and Levels such as WinGame, LoseGame,
 * Create Actor, and others. We generalized Actors and Levels to both be IGameElements.
 * We see in the code below that Actions take in IGameElements rather than Actors or Levels since
 * Rules should work uniformly for both.
 */

package gameengine.model.Actions;

import gameengine.model.IAction;
import gameengine.model.IGameElement;

/**
 *
 * @author blakekaplan
 */
public abstract class Action implements IAction {

    private IGameElement myGameElement;

    /**
     * Creates a reference to the IGameElement that will be changed
     *
     * @param myElement The IGameElement that will be changed
     */
    public Action(IGameElement myElement) {
        myGameElement = myElement;
    }
    
    /**
     * Provides the Action's parameters
     * 
     * @return	The Action's parameters
     */
    public Object[] getParameters(){
    	return new Object[]{myGameElement};
    }
    
    /**
     * The perform command that will implement the functionality unique to each Action type
     */
    @Override
    public abstract void perform();

    /**
     * Provides the IGameElement linked to the Action
     *
     * @return The IGameElement that the Action references
     */
    public IGameElement getGameElement() {
        return myGameElement;
    }

    /**
     * Determines if the Action is equal to another Action
     * 
     * @param other	The Action being compared against
     * @return	Whether or not the Actions are equal
     */
    @Override
    public boolean equals(Object other){
    	return this.getClass().equals(other.getClass());
    }

}
