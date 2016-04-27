package gameengine.model.Actions;

import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;

/**
 * @author blakekaplan
 */
public class NextImage extends ActorAction {

	public NextImage(IGameElement assignedActor) {
		super((IPlayActor) assignedActor);
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
