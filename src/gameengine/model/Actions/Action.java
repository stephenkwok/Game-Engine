package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IAction;
import gameengine.model.IPlayActor;
import gameengine.model.PhysicsEngine;
import gameengine.controller.IGame;
import gameengine.model.*;

/**
 * This class defines the generalized behavior of an Action object. Since the
 * Action class is abstract, the perform function will have to be implemented in
 * a manner specific to the type of action.
 *
 * @author blakekaplan
 */
public abstract class Action implements IAction {

    private IGameElement myGameElement;

    /**
     * Creates a reference to the Actor that will be changed
     *
     * @param myElement The Actor that will be changed
     */
    public Action(IGameElement myElement) {
        myGameElement = myElement;
    }
    
    public Object[] getParameters(){
    	return new Object[]{myGameElement};
    }
    
    /**
     * The perform command that will implement the functionality unique to each Action type
     */
    @Override
    public abstract void perform();

    /**
     * Provides the Actor linked to the Action
     *
     * @return The Actor that the Action references
     */
    public IGameElement getGameElement() {
        return myGameElement;
    }

    public void setMyActor(IPlayActor actor) {
    	myGameElement = actor;
    }
    
    @Override
    public boolean equals(Object other){
    	System.out.println("this "+this.getClass());
    	System.out.print("other: ");
    	System.out.println(other.getClass());
    	return this.getClass().equals(other.getClass());
    }

}
