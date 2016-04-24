package gui.view;

/**
 * Button to save game
 * @author AnnieTang
 *
 */
public class ButtonSave extends ButtonParent {

	public ButtonSave(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnMouseClicked(e -> notifyObservers("ButtonSave"));
	}
}
