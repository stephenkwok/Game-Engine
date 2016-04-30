package gameengine.model.Actions;

import gameengine.model.Actor;

public class HorizontalHeadingSwitch extends MovingAction {
	
	public HorizontalHeadingSwitch(Actor actor) {
		super(actor);
	}

	@Override
	public void perform() {		
		if (getMyActor().getHeading() <= 180) {
			
			getMyActor().setHeading( 180-getMyActor().getHeading() );
			getMyActor().setDirection();
			
		} else { 
			getMyActor().setHeading(180+(360-getMyActor().getHeading()));
			getMyActor().setDirection();
		}
	}
	
	
}