package gui.view;

import gameplayer.controller.BaseScreenController;

public class ButtonPause extends ButtonParent {

	private BaseScreenController myControl;

	public ButtonPause(String buttonText, String imageName) {
		super(buttonText, imageName);
		setName(this.getClass().getSimpleName());
		setClick();
	}

	@Override
	protected void setButtonAction() {
	}

}
