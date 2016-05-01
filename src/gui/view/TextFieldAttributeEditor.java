package gui.view;

import gameengine.model.Actor;
import gameengine.model.Attribute;
import gameengine.model.AttributeType;
import gameengine.model.IGameElement;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class TextFieldAttributeEditor extends TextFieldWithButton {
	private static final double DEFAULT_HEALTH = 0;
	private static final String REMOVE = "X";
	private static final String HEALTH_LABEL = "Health:";
	private static final String NO_ATTRIBUTE_ALERT = "Nothing to remove.";
	private static final String NO_ATTRIBUTE_HEADER = "Attribute not yet set.";
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
		attributeType = AttributeType.valueOf(labelText.toUpperCase());
	}
	
	private void createRemoveButton(){
		Button removeAttributeButton = new Button(REMOVE);
		removeAttributeButton.setOnAction(event->{
			try{
				((Actor) getEditableElement()).removeAttribute(((Actor) getEditableElement()).getAttribute(attributeType));
			}catch(Exception e){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(NO_ATTRIBUTE_HEADER);
				alert.setContentText(NO_ATTRIBUTE_ALERT);
				alert.showAndWait();
			}
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