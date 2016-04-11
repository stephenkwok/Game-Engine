package gui.view;

import gameplayer.controller.BaseScreenController;
import gui.controller.IScreenController;

public class ButtonSplash extends ButtonParent {
	
	private BaseScreenController myController;

	public ButtonSplash(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
		this.myController  = (BaseScreenController) myController;
	}

	@Override
	protected void setButtonAction() {
		button.setOnMouseClicked(e -> myController.goToSplash());

	}

}
