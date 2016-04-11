package gui.view;

import gameplayer.controller.HighScoreScreenController;
import gui.controller.IScreenController;

public class ButtonReturn extends ButtonParent {

	public HighScoreScreenController myControl;
	
	public ButtonReturn(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
		this.myControl = (HighScoreScreenController) myController;
	}

	@Override
	protected void setButtonAction() {
		button.setOnMouseClicked(e -> myControl.goToSplash());

	}

}
