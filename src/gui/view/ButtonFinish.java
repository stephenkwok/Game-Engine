package gui.view;

/**
 * On click, saves the game and returns user to the game player splash screen.
 * @author AnnieTang
 *
 */
public class ButtonFinish extends ButtonParent {

	public ButtonFinish(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	/**
	 * Returns to the splash screen once the user is finished editing.
	 */
	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> notifyObservers("ButtonFinish"));
	}

}
