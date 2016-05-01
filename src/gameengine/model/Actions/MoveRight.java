package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * An example of an Action to move an Actor right by a given distance.
 *
 * @author blakekaplan
 */
public class MoveRight extends MovingAction {

	private static final int RIGHT_ANGLE = 0;

	/**
	 * Takes in reference to the Actor it will change along with the argument it
	 * will require to do so
	 *
	 * @param actor1
	 *            The Actor that will be changed
	 * @param args
	 *            The arguments required to perform the change
	 */
	public MoveRight(Actor actor) {
		super(actor);
	}
	
	/**
	 * Moves the Actor to the right by the distance provided in the arguments
	 */
	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().moveRight(getMyActor());
		getMyActor().setHeading(0);
		getMyActor().setDirection();
	}

}
