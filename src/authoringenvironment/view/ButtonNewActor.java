package authoringenvironment.view;

import authoringenvironment.controller.Controller;
import gui.controller.IScreenController;
import gui.view.ButtonParent;

public class ButtonNewActor extends ButtonParent {

	public ButtonNewActor(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
	}

	@Override
	protected void setButtonAction() {
		button.setOnAction(e -> ((Controller) myController).addActor());
	}

}
