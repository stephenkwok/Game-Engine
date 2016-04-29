package gui.view;

public class ButtonHelpActorAttributes extends ButtonHelpParent {

	public ButtonHelpActorAttributes(String buttonText, String imageName) {
		super(buttonText, imageName);
	}
	
	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> notifyObservers(this.getClass().getSimpleName()));
	}
}
