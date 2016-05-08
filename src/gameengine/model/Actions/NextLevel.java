// This entire file is part of my masterpiece.
// Michelle Chen

/**
 * This class is another example of an action, the component of a rule that represents the "effect" from a triggering "cause". It is different from
 * HorizontalBounceCollision in that it is not necessarily an "actor action"--it doesn't affect the actor but rather causes a change within the game. 
 * NextLevel makes use of the observable/observer design pattern, and I include it here to showcase not only the flexibility but also the depth of types of
 * actions within our program. More specifically, when this action is triggered, it notifies its observers to launch the next level of the game. There
 * are a few benefits to implementing this type of action in this manner--the observable/observer interface allows us to encapsulate and delegate
 * specific jobs to other classes, making it clear which component does what. The action does not need to know what elements are observing it; this sort of dependency
 * allows both the Game and Action to do their respective jobs without having explicit access to each other. This sort of separation makes for a clear division--
 * these action objects are thus completely self contained and work without reference to the observers.  
 * 
 * Much of my analysis in the HorizontalBounceCollision class applies here--for example, the inheritance heirarchy for our action reduces convoluted 
 * dependencies and duplicated code, and makes for a cleaner implementation with a workflow that is much easier to understand and makes for overall
 * better design.
 */
package gameengine.model.Actions;

import java.util.ArrayList;
import java.util.Observable;

import gameengine.model.Actor;
import gameengine.model.IGameElement;

/**
 * This Action calls the next level of the game
 * 
 * @author michellechen
 *
 */

public class NextLevel extends Action {
	private Actor assignedActor;
	
	public NextLevel(IGameElement assignedElement) {
		super(assignedElement);
	}

	/**
	 * Signals for next level of the game
	 */
	@Override
	public void perform() {
		getGameElement().changed();
        ArrayList<String> myList = new ArrayList<String>();
        myList.add("nextLevel");
        ((Observable) getGameElement()).notifyObservers(myList);
	}
	
	/**
	 * Returns actor being referenced/acted upon
	 * @return
	 */
	public Actor getAssignedActor() {
		return assignedActor;
	}

	/**
	 * Sets actor being referenced/acted upon
	 * @param assignedActor
	 */
	public void setAssignedActor(Actor assignedActor) {
		this.assignedActor = assignedActor;
	}

}
