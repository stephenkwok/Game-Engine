package gui.view;

import gameengine.model.Actor;
import gameengine.model.Attribute;
import gameengine.model.AttributeType;
import gameengine.model.IGameElement;

public class TextFieldActorHealthEditor extends TextFieldWithButton {
	private static final double DEFAULT_HEALTH = 0;

	public TextFieldActorHealthEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> {
			((Actor) getEditableElement()).addAttribute(new Attribute(AttributeType.HEALTH, Integer.parseInt(getTextFieldInput()), (IGameElement) getEditableElement()));
		});
	}

	/**
	 * Sets the textfield's value to reflect the current actor's health.
	 */
	@Override
	protected void updateValueBasedOnEditable() {
		try {
			setTextFieldValue(
					Double.toString(((Actor) getEditableElement()).getAttribute(AttributeType.HEALTH).getMyValue()));
		} catch (Exception e) {
			setTextFieldValue(Double.toString(DEFAULT_HEALTH));
		}
	}

}