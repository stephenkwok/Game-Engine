package gui.view;

public class ButtonHelpActorsTab extends ButtonHelpParent {

	public ButtonHelpActorsTab(String buttonText, String imageName) {
		super(buttonText, imageName);
	}
	
	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> notifyObservers(this.getClass().getSimpleName()));
	}

}
