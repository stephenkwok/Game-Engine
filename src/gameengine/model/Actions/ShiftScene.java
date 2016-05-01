package gameengine.model.Actions;

import gameengine.model.IGameElement;

public class ShiftScene extends Action {
	private Double myShiftAmount;
	private String myDirection;
	
	public ShiftScene(IGameElement myElement, String direction, Double shiftAmount) {
		super(myElement);
		myShiftAmount = shiftAmount;
		myDirection = direction;
	}

	@Override
	public void perform() {
		getGameElement().getGame().getCurrentLevel().shiftScene(myDirection, myShiftAmount);
	}

}
