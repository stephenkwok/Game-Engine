package gui.view;

import gameplayer.controller.BaseScreenController;
import gui.controller.IScreenController;

public class ButtonSaveGame extends ButtonParent {
	
	private BaseScreenController myControl;

	public ButtonSaveGame(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
		this.myControl = (BaseScreenController) myController;
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnMouseClicked(e -> myControl.saveGame());
	}

}
