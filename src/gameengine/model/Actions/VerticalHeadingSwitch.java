package gameengine.model.Actions;

import gameengine.model.Actor;


/**
 * 
 * This action calculates a new heading following a collision. 
 * This new heading is based on its current heading. 
 * It allows for angled bounces. 
 * @author justinbergkamp
 *
 */
public class VerticalHeadingSwitch extends MovingAction {
	
	public VerticalHeadingSwitch(Actor actor) {
		super(actor);
	}

	@Override
	public void perform() {
		double newAngle =  0;
		
		if (getMyActor().getHeading() >= 270) {
			newAngle = 360 - getMyActor().getHeading();
		}
		else if (getMyActor().getHeading() >= 180) {
			newAngle = 180 - (getMyActor().getHeading()-180);
		}
		else if (getMyActor().getHeading() >= 90) {
			newAngle = 270 - (getMyActor().getHeading()-90);	
		} else { 
			newAngle = 360 - getMyActor().getHeading();
		}
		getMyActor().setHeading(newAngle);
		getMyActor().setDirection();
	}
	
	
}