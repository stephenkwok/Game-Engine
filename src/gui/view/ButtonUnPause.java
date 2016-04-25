package gui.view;

import gameplayer.controller.BaseScreenController;

public class ButtonUnPause extends ButtonParent {

	private BaseScreenController myControl;

	public ButtonUnPause(String buttonText, String imageName) {
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
