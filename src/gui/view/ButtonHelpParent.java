package gui.view;

public abstract class ButtonHelpParent extends ButtonParent {
	public ButtonHelpParent(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	/**
	 * Opens up the Authoring Environment Help Page
	 */
	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> notifyObservers(this.getClass().getSimpleName()));
	}

}
