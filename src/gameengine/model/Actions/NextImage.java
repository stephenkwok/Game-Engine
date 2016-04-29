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
	 * The perform command that will implement the functionality unique to each
	 * Action type
	 */
	@Override
	public void perform() {
		getMyActor().nextImage();
		getMyActor().changed();
	}
}
