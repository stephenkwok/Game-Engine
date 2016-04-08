package gui.view;

import gui.controller.IScreenController;
import gameplayer.controller.SplashScreenController;

public class ButtonPlay extends ButtonParent{

	private SplashScreenController myControl; 
	
	public ButtonPlay(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
		this.myControl = (SplashScreenController) myController;
	}

	@Override
	protected void setButtonAction() {
		button.setOnAction(e ->  myControl.play());
		
	}

}