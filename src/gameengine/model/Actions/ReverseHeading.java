package gameengine.model.Actions;

import gameengine.model.Actor;

public class ReverseHeading extends MovingAction {

	//heading is 0 if movng right and 180 if moving left
	
	public ReverseHeading(Actor actor) {
		super(actor);
	}

	@Override
	public void perform() {
		if (getMyActor().getHeading() == 0.0) {
			getMyActor().setHeading(180.0);
			getMyActor().setDirection();
		} else { 
			getMyActor().setHeading(0.0);
			getMyActor().setDirection();
		}
	}
	
	
}
