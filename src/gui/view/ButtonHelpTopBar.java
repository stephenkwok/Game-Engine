package gui.view;

/**
 * Help Button for Top Bar.
 * @author amyzhao
 *
 */
public class ButtonHelpTopBar extends ButtonHelpParent {

	/**
	 * Constructor for ButtonHelpTopBar.
	 * @param buttonText: text for button.
	 * @param imageName: image name for button.
	 */
	public ButtonHelpTopBar(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> notifyObservers(this.getClass().getSimpleName()));
	}
}
