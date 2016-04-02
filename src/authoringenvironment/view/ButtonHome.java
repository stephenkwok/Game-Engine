package authoringenvironment.view;

import authoringenvironment.controller.Controller;

public class ButtonHome extends ButtonParent{
	public ButtonHome(Controller myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
	}

	@Override
	void setButtonAction() {
		button.setOnAction(event -> {
			myController.goToMainScreen();
		});
	}
}
