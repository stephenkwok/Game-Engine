// This entire file is part of my masterpiece.
// Blake Kaplan

/*
 * This Action is used to place a copy of an Actor at a prechosen or randomly
 * selected point on the screen.
 * 
 * I believe that this class also highlights our project.
 * We see that in the perform method uses the ActorCopier to make a copy of the
 * provided Actor, determines the Actor's spawning coordinates, sets 
 * the Actor's spawning coordinates, then notifies the Actor's observers.
 * This Action, in particular, showcases our use of the Observer/Observable
 * design pattern. Each Actor extends the Observable class and the GameController,
 * which regulates interactions between our project's model and view, implements the
 * Observer interface. When the CreateActor Action tells its associated Actor to notify
 * its observers, the copied Actor is sent up the Observer chains all the way to the GameController.
 * Even though the Action has no reference to the GameController, we see that such
 * a significant change is able to be reflected at a higher level. We designed our
 * project such that the Observer/Observable design pattern is central to interclass
 * communication and logic. Actions have limited access to the GameController via
 * the update method in the Actor, which passes in information up to the Game. The Game, in turn,
 * passes the information up to the GameController, which executes the appropriate course of action.
 */

package gameengine.model.Actions;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import authoringenvironment.model.ActorCopier;
import authoringenvironment.model.IAuthoringActor;
import gameengine.model.Actor;
import gameengine.model.IGameElement;

public class CreateActor extends Action {

    private static final String ADD_ACTOR = "addActor";
    private Actor cloneActor;
	private Double myX;
	private Double myY;
	private Actor myActorToCopy;
    private Double myMinX;
    private Double myMaxX;
    private Double myMinY;
    private Double myMaxY;
    private boolean isRandom;
	
    /**
     * Constructor used when spawn location is non-random
     * 
     * @param element	The IGameElement that contains the Action
     * @param toCopy	The Actor to be copied
     * @param x			The desired X coordinate for spawning
     * @param y			The desired Y coordinate for spawning
     */
	public CreateActor(IGameElement element, Actor toCopy, Double x, Double y) {
		super(element);
		myActorToCopy = toCopy;
		myX = x;
		myY = y;
	}

	/**
	 * Constructor used when the spawn location is random within a provided range
	 * 
	 * @param element	The IGameElement that contains the Action
	 * @param toCopy	The Actor to copy
	 * @param minX		The minimum X coordinate
	 * @param maxX		The maximum X coordinate
	 * @param minY		The minimum Y coordinate
	 * @param maxY		The maximum Y coordinate
	 */
    public CreateActor(IGameElement element, Actor toCopy, Double minX, Double maxX, Double minY, Double maxY){
        super(element);
        myActorToCopy = toCopy;
        isRandom = true;
        myMaxX = maxX;
        myMaxY = maxY;
        myMinX = minX;
        myMinY = minY;
    }

    /**
     * Provides the Action's parameters
     * 
     * @return	The Action's parameters
     */
    @Override
    public Object[] getParameters(){
    	if(isRandom){
    		return new Object[]{getGameElement(),myActorToCopy,myMinY,myMaxX,myMinY,myMaxY};
    	}
    	return new Object[]{getGameElement(),myActorToCopy,myX,myY};
    }
    
    /**
     * Creates a copy of the desired Actor at either predetermined coordinates or random
     * coordinates within a particular range
     */
	@Override
	public void perform() {
		ActorCopier copier = new ActorCopier(myActorToCopy);
		cloneActor = copier.makeCopy();
        if (isRandom){
        	myX = getRandom(myMinX, myMaxX);
        	myY = getRandom(myMinY, myMaxY);
        }
        cloneActor.setX(myX);
		cloneActor.setY(myY);
		getGameElement().changed();
		((Observable) getGameElement()).notifyObservers(Arrays.asList(new Object[]{ADD_ACTOR, cloneActor}));
	}
	
	/**
	 * Provides the Actor that is being copied
	 * 
	 * @return	The Actor that is being copied
	 */
	public IAuthoringActor getMyActorToCopy() {
		return (IAuthoringActor) myActorToCopy;
	}
	
	/**
	 * States if the created Actor is being placed randomly or not
	 * 
	 * @return	Whether or not the created Actor is being placed randomly
	 */
	public boolean isRandom(){
		return isRandom;
	}
	
	/**
	 * Provides the CreateActor's coordinates
	 * 
	 * @return	The CreateActor's coordinates
	 */
	public List<Double> getCoordinates(){
		return Arrays.asList(myX, myY, myMinX, myMaxX, myMinY, myMaxY);
	}
	
	/**
	 * Calculates a random coordinate within a provided range
	 * 
	 * @param min	The minimum coordinate
	 * @param max	The maximum coordinate
	 * @return	The randomly calculated coordinate within the range
	 */
	private Double getRandom(Double min, Double max){
		return min + (max - min) * Math.random();
	}
}
