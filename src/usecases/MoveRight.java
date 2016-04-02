package usecases;

import java.util.List;

/**
 * An example of an Action to move an Actor right by a given distance.
 *
 * @author blakekaplan
 */
public class MoveRight extends Action {

    private static final int RIGHT_ANGLE = 90;
    private double distance;

    /**
     * Takes in reference to the Actor it will change along with the argument it will require to do so
     *
     * @param assignedActor The Actor that will be changed
     * @param args          The arguments required to perform the change
     */
    public MoveRight(Actor assignedActor, List<Object> args) {
        super(assignedActor);
        distance = (Double) args.get(0);
    }

    /**
     * Moves the Actor to the right by the distance provided in the arguments
     */
    @Override
    public void perform() {
        getActor().move(distance, RIGHT_ANGLE);
    }
}
