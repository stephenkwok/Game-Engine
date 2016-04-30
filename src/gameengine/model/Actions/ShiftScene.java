package gameengine.model.Actions;


import gameengine.controller.Level;

public class ShiftScene extends LevelAction {
	private Double myShiftAmount;
	private String myDirection;
	
	public ShiftScene(Level myElement, String direction, Double shiftAmount) {
		super(myElement);
		myShiftAmount = shiftAmount;
		myDirection = direction;
	}

	@Override
	public void perform() {
		getMyLevel().shiftScene(myDirection, myShiftAmount);
	}

}
