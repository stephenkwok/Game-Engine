package gui.view;

import gameplayer.controller.SplashScreenController;
import gui.controller.IScreenController;

public class ButtonEdit extends ButtonParent{

	private SplashScreenController myControl;
	
	public ButtonEdit(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
		this.myControl = (SplashScreenController) myController;
	}

	@Override
	protected void setButtonAction() {
		button.setOnAction(e -> myControl.edit());
		System.out.println("testing");
		
	}

}
