package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * Moves Actor in the direction of its heading
 *
 * @author justinbergkamp
 */
public class MoveForward extends MovingAction {

	/**
	 * Takes in reference to the Actor it will change along with the argument it
	 * will require to do so
	 *
	 * @param actor1
	 *            The Actor that will be changed
	 * @param args
	 *            The arguments required to perform the change
	 */
	public MoveForward(Actor actor) {
		super(actor);
	}

	/**
	 * Moves the Actor forwards
	 */
	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().moveForward(getMyActor());

	}

}