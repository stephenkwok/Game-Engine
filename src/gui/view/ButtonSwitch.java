package gui.view;

import gui.controller.IScreenController;
import gui.controller.ScreenController;

public class ButtonSwitch extends ButtonParent {
	
	private ScreenController myController;

	public ButtonSwitch(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
		this.myController = (ScreenController) myController;
	}

	@Override
	protected void setButtonAction() {
		button.setOnMouseClicked(e -> myController.switchGame());

	}

}