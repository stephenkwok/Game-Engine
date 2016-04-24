package gui.view;

/**
 * Button to load game.
 * @author AnnieTang
 *
 */
public class ButtonLoad extends ButtonParent {
	
	public ButtonLoad(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnAction(event -> {
			notifyObservers("ButtonLoad");
		});
	}

}
