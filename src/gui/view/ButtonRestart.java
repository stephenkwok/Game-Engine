package gui.view;

import gameplayer.controller.BaseScreenController;
import gui.controller.IScreenController;

public class ButtonRestart extends ButtonParent {

	BaseScreenController myControl;
	
	public ButtonRestart(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
		this.myControl = (BaseScreenController) myController;
	}

	@Override
	protected void setButtonAction() {
		button.setOnMouseClicked(e -> myControl.getMyGameController().restartGame());

	}

}
