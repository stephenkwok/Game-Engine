package gui.view;

import authoringenvironment.controller.Controller;
import gui.controller.IScreenController;

public class ButtonFinish extends ButtonParent {

	public ButtonFinish(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setButtonAction() {
		button.setOnAction(e -> {
			((Controller) myController).saveGame(promptForFileName(true));
			((Controller) myController).goBackToGamePlayer();
		});
	}

}
