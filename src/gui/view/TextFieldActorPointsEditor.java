package gui.view;

import gameengine.model.Actor;
import gameengine.model.Attribute;
import gameengine.model.AttributeType;
import gameengine.model.IGameElement;

public class TextFieldActorPointsEditor extends TextFieldWithButton {
	private static final double DEFAULT_POINTS = 0;
	public TextFieldActorPointsEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		Actor a = (Actor) getEditableElement();
		setButtonAction(e -> a.addAttribute(new Attribute(AttributeType.POINTS, Integer.parseInt(getTextFieldInput()), (IGameElement) a)));
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
