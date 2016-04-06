package xmlTest;

import java.util.List;

public class MoveRight extends Action {
	private static final int RIGHT_ANGLE = 90;
	
	private double myDistance;
	
	public MoveRight (Actor actor, List<Object> args) {
		setMyDistance((double) args.get(0));
	}

	public double getMyDistance() {
		return myDistance;
	}

	public void setMyDistance(double myDistance) {
		this.myDistance = myDistance;
	}
	
}
