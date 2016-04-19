package gui.view;

import gameplayer.controller.BaseScreenController;
import gui.controller.IScreenController;

public class ButtonSound extends ButtonParent{
	
	private BaseScreenController myControl;

	public ButtonSound(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
		this.myControl = (BaseScreenController) myController;
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnMouseClicked(e -> myControl.toggleSound());
	}

}
