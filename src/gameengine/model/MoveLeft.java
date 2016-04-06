package gameengine.model;

import java.util.List;

import gameengine.controller.Action;

/**
 * Created by Michelle on 3/31/16.
 */

public class MoveLeft extends Action {

    private static final int LEFT_ANGLE = 0;
    private double distance;

    /**
     * Takes in reference to the Actor it will change along with the argument it will require to do so
     *
     * @param assignedActor The Actor that will be changed
     * @param args          The arguments required to perform the change
     */
    public MoveLeft(Actor assignedActor, List<Object> args) {
        super(assignedActor);
        distance = (Double) args.get(0);
    }

    /**
     * Moves the Actor to the left by the distance provided in the arguments
     */
    @Override
    public void perform() {
        getActor().move(distance, LEFT_ANGLE);
    }

	@Override
	public void performOn(Actor a) {
		// TODO Auto-generated method stub
		
	}
}
