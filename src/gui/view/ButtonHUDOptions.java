package gui.view;

/**
 * 
 * @author Stephen
 *
 */

public class ButtonHUDOptions extends ButtonParent {

	public ButtonHUDOptions(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	/**
	 * Returns to the main screen of the authoring environment.
	 */
	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> notifyObservers());
	}
	
}
