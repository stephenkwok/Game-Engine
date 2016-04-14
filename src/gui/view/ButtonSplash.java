package gui.view;

import gui.controller.IScreenController;

public class ButtonSplash extends ButtonParent {
	
	//private ScreenController myController;

	public ButtonSplash(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
		//this.myController  = (ScreenController) myController;
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnMouseClicked(e -> getController().goToSplash());
	}

}
