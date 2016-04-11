package gui.view;

import gameplayer.controller.BaseScreenController;
import gameplayer.controller.SplashScreenController;
import gui.controller.IScreenController;

public class ButtonSound extends ButtonParent{
	
	private BaseScreenController myControl;

	public ButtonSound(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
		this.myControl = (BaseScreenController) myController;
	}

	@Override
	protected void setButtonAction() {
		button.setOnMouseClicked(e -> myControl.toggleSound());
		
	}

}
