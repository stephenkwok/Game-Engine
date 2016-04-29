package gui.view;

public class ButtonHelpLevelAttributes extends ButtonHelpParent {

	public ButtonHelpLevelAttributes(String buttonText, String imageName) {
		super(buttonText, imageName);
	}
	
	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> notifyObservers(this.getClass().getSimpleName()));
	}
}
