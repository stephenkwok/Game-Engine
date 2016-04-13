package gui.view;

import gameengine.model.Actor;
import gameengine.model.Attribute;
import gameengine.model.AttributeType;

public class TextFieldActorPointsEditor extends TextFieldWithButton {

	public TextFieldActorPointsEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> ((Actor) getEditableElement()).addAttribute(new Attribute(AttributeType.POINTS, Integer.parseInt(getTextFieldInput()))));
	}

	@Override
	protected void updateValueBasedOnEditable() {
	}

}
