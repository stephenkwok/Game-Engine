package gui.view;

import gameplayer.controller.SplashScreenController;
import gui.controller.IScreenController;

public class ButtonOpenHighScores extends ButtonParent{
	
	private SplashScreenController myControl;

	public ButtonOpenHighScores(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
		this.myControl = (SplashScreenController) myController;
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnMouseClicked(e -> myControl.openHighScores());
		
	}

}