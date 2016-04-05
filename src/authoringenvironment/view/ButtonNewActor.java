package authoringenvironment.view;

import authoringenvironment.controller.Controller;

public class ButtonNewActor extends ButtonParent {

	public ButtonNewActor(Controller myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
	}

	@Override
	protected void setButtonAction() {
		button.setOnAction(e -> myController.addActor());
	}

}
