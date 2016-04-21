package gui.view;

import authoringenvironment.controller.Controller;
/**
 * Button to return to main screen
 * @author AnnieTang
 *
 */
public class ButtonHome extends ButtonParent{
	public ButtonHome(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	/**
	 * Returns to the main screen of the authoring environment.
	 */
	@Override
	protected void setButtonAction() {
		getButton().setOnAction(event -> {
			setChanged();
			notifyObservers();
		});
	}
}