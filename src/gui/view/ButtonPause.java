package gui.view;

import gameplayer.controller.BaseScreenController;

public class ButtonPause extends ButtonParent {

	private BaseScreenController myControl;

	public ButtonPause(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> {
			setChanged();
			notifyObservers();
		});

	}

}
