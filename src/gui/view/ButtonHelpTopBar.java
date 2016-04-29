package gui.view;

public class ButtonHelpTopBar extends ButtonHelpParent {

	public ButtonHelpTopBar(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> notifyObservers(this.getClass().getSimpleName()));
	}
}
