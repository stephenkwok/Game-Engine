package gameengine.model.Actions;

import gameengine.model.IPlayActor;

/**
 * @author blakekaplan
 */
public class NextImage extends Action {

	public NextImage(IPlayActor assignedActor) {
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
