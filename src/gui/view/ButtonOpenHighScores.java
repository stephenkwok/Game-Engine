package gui.view;

import gameplayer.controller.SplashScreenController;

public class ButtonOpenHighScores extends ButtonParent{

	public ButtonOpenHighScores(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnMouseClicked(e -> notifyObservers("ButtonOpenHighScores"));
		
	}

}