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
    private boolean oneTime; 
    private boolean performed;

    /**
     * Creates a reference to the Actor that will be changed
     *
     * @param myElement The Actor that will be changed
     */
    public Action(IGameElement myElement, Boolean oneTime) {
        myGameElement = myElement;
        this.oneTime = oneTime;
        this.performed = false;
    }
    
    public Action(IGameElement myElement) {
    	this(myElement,false);
    }
    
    public Object[] getParameters(){
    	return new Object[]{myGameElement,oneTime};
    }
    
    public boolean isOneTime(){
    	return oneTime;
    }
    
    public void perform(){
    	if(!performed){
        	execute();
    	}
    	if(oneTime){
    		performed = true;
    	}
    }
    
    /**
     * The perform command that will implement the functionality unique to each Action type
     */
    @Override
    public abstract void execute();

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

}
