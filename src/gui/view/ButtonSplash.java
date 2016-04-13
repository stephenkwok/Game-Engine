package gui.view;

import gameplayer.controller.BaseScreenController;
import gui.controller.IScreenController;
import gui.controller.ScreenController;

public class ButtonSplash extends ButtonParent {
	
	private ScreenController myController;

	public ButtonSplash(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
		this.myController  = (ScreenController) myController;
	}

	@Override
	protected void setButtonAction() {
		button.setOnMouseClicked(e -> myController.goToSplash());

	}

}
