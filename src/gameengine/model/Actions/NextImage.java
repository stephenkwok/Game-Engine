// This entire file is part of my masterpiece.
// Blake Kaplan

/*
 * This Action is the Action that creates the animation functionality. Each
 * time that NextImage performs its logic, the Actor tells its instance of Sprite
 * to move the next image.
 * 
 * I believe that this class shows good design. We see that it only takes a single line
 * of code to create such a significant change on the front end of the project. This further
 * indicates the power in encapsulating the animation logic into the Sprite class. As I
 * mention in my comments in the Sprite class, the Actor simply needs to tell the Sprite
 * to go to the next Sprite image in order to get the new image to display. This
 * class also shows the extent of the Action hierarchy, as Actions can make changes related
 * to graphics in addition to just movement. It's also worth noting that this is an
 * ActorAction, meaning that it acts exclusively on IPlayActors. See the ActorAction class
 * for additional explanation.
 */

package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * @author blakekaplan
 */
public class NextImage extends ActorAction {

	public NextImage(Actor assignedActor) {
		super(assignedActor);
	}

	/**
	 * Tells the Actor to change the Sprite to the next image
	 */
	@Override
	public void perform() {
		getMyActor().nextImage();
	}
}
