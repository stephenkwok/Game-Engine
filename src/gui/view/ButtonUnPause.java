package gui.view;

import gameplayer.controller.BaseScreenController;

public class ButtonUnPause extends ButtonParent {

	private BaseScreenController myControl;

	public ButtonUnPause(String buttonText, String imageName) {
		super(buttonText, imageName);
		setName(this.getClass().getSimpleName());
		setClick();
	}

	@Override
	protected void setButtonAction() {
	}

}
