package gui.view;

public class ButtonHelpLevelPreview extends ButtonHelpParent {

	public ButtonHelpLevelPreview(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	
	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> notifyObservers(this.getClass().getSimpleName()));
	}
}
