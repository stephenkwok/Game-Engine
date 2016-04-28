package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * Moves Actor in the direction of its heading
 *
 * @author justinbergkamp
 */
public class MoveBackward extends MovingAction {

	/**
	 * Takes in reference to the Actor it will change along with the argument it
	 * will require to do so
	 *
	 * @param actor1
	 *            The Actor that will be changed
	 * @param args
	 *            The arguments required to perform the change
	 */
	public MoveBackward(Actor actor, Boolean oneTime) {
		super(actor, oneTime);
	}

	/**
	 * Moves the Actor forwards
	 */
	@Override
	public void execute() {
		getMyActor().getPhysicsEngine().moveBackward(getMyActor());
	}

}