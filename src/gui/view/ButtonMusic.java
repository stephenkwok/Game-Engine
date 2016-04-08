package gui.view;

import gameplayer.controller.BaseScreenController;
import gui.controller.IScreenController;

public class ButtonMusic extends ButtonParent{
	
	private BaseScreenController myControl;

	public ButtonMusic(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
		this.myControl = (BaseScreenController) myController;
	}

	@Override
	protected void setButtonAction() {
		button.setOnAction(e -> myControl.toggleMusic());
		
	}

}
