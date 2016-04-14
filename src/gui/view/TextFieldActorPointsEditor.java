package gui.view;

import gameengine.model.Actor;
import gameengine.model.Attribute;
import gameengine.model.AttributeType;

public class TextFieldActorPointsEditor extends TextFieldWithButton {
	private static final double DEFAULT_POINTS = 0;
	public TextFieldActorPointsEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> ((Actor) getEditableElement()).addAttribute(new Attribute(AttributeType.POINTS, Integer.parseInt(getTextFieldInput()))));
	}
	/**
	 * Sets the textfield's value to reflect the current actor's points.
	 */
	@Override
	protected void updateValueBasedOnEditable() {
		try{setTextFieldValue(Double.toString(((Actor) getEditableElement()).getAttribute(AttributeType.POINTS).getMyValue()));}
		catch(Exception e){
			setTextFieldValue(Double.toString(DEFAULT_POINTS));
		}
	}

}
