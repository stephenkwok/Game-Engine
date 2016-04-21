package gui.view;

import gui.controller.IScreenController;

/**
 * Button for adding a new actor.
 * @author amyzhao
 *
 */
public class ButtonNewActor extends ButtonParent {

	/**
	 * Constructs a button for adding a new actor.
	 * @param myController: environment controller.
	 * @param buttonText: text for the button.
	 * @param imageName: image for the button.
	 */
	public ButtonNewActor(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
	}

	/**
	 * On click, add a new actor via the controller.
	 */
	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> notifyObservers(null));
	}

}
