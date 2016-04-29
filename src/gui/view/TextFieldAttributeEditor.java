package gui.view;

import gameengine.model.Actor;
import gameengine.model.Attribute;
import gameengine.model.AttributeType;
import gameengine.model.IGameElement;
import javafx.scene.control.Button;

public class TextFieldAttributeEditor extends TextFieldWithButton {
	private static final double DEFAULT_HEALTH = 0;
	private static final String REMOVE = "X";
	private static final String HEALTH_LABEL = "Health:";
	private AttributeType attributeType;
	
	public TextFieldAttributeEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		determineAttributeType(labelText);
		setButtonAction(e -> {
			((Actor) getEditableElement()).addAttribute(new Attribute(attributeType, (int) Double.parseDouble((getTextFieldInput())), (IGameElement) getEditableElement()));
		});
		createRemoveButton();
	}
	
	private void determineAttributeType(String labelText){
		if(labelText==HEALTH_LABEL) attributeType = AttributeType.HEALTH;
		attributeType = AttributeType.POINTS;
	}
	
	private void createRemoveButton(){
		Button removeAttributeButton = new Button(REMOVE);
		removeAttributeButton.setOnAction(event->{
			((Actor) getEditableElement()).removeAttribute(((Actor) getEditableElement()).getAttribute(attributeType));
		});
		getMyHBox().getChildren().add(removeAttributeButton);
	}

	/**
	 * Sets the textfield's value to reflect the current actor's health.
	 */
	@Override
	protected void updateValueBasedOnEditable() {
		try {
			setTextFieldValue(
					Double.toString(((Actor) getEditableElement()).getAttribute(attributeType).getMyValue()));
		} catch (Exception e) {
			setTextFieldValue(Double.toString(DEFAULT_HEALTH));
		}
	}


}