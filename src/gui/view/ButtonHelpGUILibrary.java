package gui.view;

public class ButtonHelpGUILibrary extends ButtonHelpParent {

	public ButtonHelpGUILibrary(String buttonText, String imageName) {
		super(buttonText, imageName);
	}
	
	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> notifyObservers(this.getClass().getSimpleName()));
	}
}
