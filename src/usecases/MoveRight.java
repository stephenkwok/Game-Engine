package usecases;

import java.util.List;

/**
 * @author blakekaplan
 */
public class MoveRight extends Action {

    private static final int RIGHT_ANGLE = 90;
    private double distance;

    public MoveRight(Actor assignedActor, List<Object> args){
        super(assignedActor);
        distance = (Double) args.get(0);
    }

    @Override
    public void perform() {
        getActor().move(distance, RIGHT_ANGLE);
    }
}
