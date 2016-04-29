package gui.view;

public class ButtonHelpRulePanel extends ButtonHelpParent {

	public ButtonHelpRulePanel(String buttonText, String imageName) {
		super(buttonText, imageName);
	}
	
	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> notifyObservers(this.getClass().getSimpleName()));
	}
}
