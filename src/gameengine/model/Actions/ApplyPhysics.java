package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * This action is used to apply forces such as gravity and friction on actors. 
 * It is most commonly used paired with the TickTrigger
 * @author justinbergkamp
 */
public class ApplyPhysics extends ActorAction {

	public ApplyPhysics(Actor actor) {
		super(actor);
	}

	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().tick(getMyActor());

	}

}
