package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;
import gameengine.model.PhysicsEngine;

import java.util.ArrayList;
import java.util.List;

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
	public MoveRight(Actor actor, Boolean oneTime) {
		super(actor, oneTime);
	}
	
	/**
	 * Moves the Actor to the right by the distance provided in the arguments
	 */
	@Override
	public void execute() {
		getMyActor().getPhysicsEngine().moveRight(getMyActor());
		getMyActor().setHeading(0);
		getMyActor().setDirection();
	}

}
