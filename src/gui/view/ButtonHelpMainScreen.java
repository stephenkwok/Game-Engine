package gui.view;

public class ButtonHelpMainScreen extends ButtonHelpParent {

	public ButtonHelpMainScreen(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	
	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> notifyObservers(this.getClass().getSimpleName()));
	}
}
