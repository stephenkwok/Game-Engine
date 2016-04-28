package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * @author blakekaplan
 */
public class NextImage extends ActorAction {

	public NextImage(Actor assignedActor, Boolean oneTime) {
		super(assignedActor, oneTime);
	}

	/**
	 * The perform command that will implement the functionality unique to each
	 * Action type
	 */
	@Override
	public void execute() {
		getMyActor().nextImage();
		getMyActor().changed();
	}
}
