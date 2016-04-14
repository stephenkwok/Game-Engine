package gui.view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * This class serves as the base class for all horizontal couplings of a label,
 * text field, and button
 * 
 * @author Stephen
 *
 */

public abstract class TextFieldWithButton extends EditingElementParent {
	private static final int PADDING = 10;
	private static final String GO = "Go";
	private HBox myContainer;
	private Label myTextFieldPrompt;
	private TextField myTextField;
	private String labelText;
	private String promptText;
	private Double textFieldWidth;

	public TextFieldWithButton(String labelText, String promptText, Double textFieldWidth) {
		super(GO);
		this.labelText = labelText;
		this.promptText = promptText;
		this.textFieldWidth = textFieldWidth;
		myTextField = new TextField();
	}
	
	/**
	 * Creates and returns the container containing this class' Label, TextField, and Button
	 */
	@Override
	public Node createNode() {
		myContainer = new HBox(PADDING);
		myTextFieldPrompt = new Label(labelText);
		myTextField.setPrefWidth(textFieldWidth);
		myTextField.setPromptText(promptText);
		myContainer.getChildren().addAll(myTextFieldPrompt, myTextField, getButton());
		return myContainer;
	}
	
	/**
	 * Updates the text that will be displayed by the text field depending 
	 * on the the IEditableGameElement's current value for the attribute
	 * that is being edited
	 */
	protected abstract void updateValueBasedOnEditable();
	
	/**
	 * Sets the text that is displayed by the text field to the given value
	 * 
	 * @param value that text field's text will be set to
	 */
	protected void setTextFieldValue(String value) {
		myTextField.setText(value);
	}
	
	/**
	 * 
	 * @return the input in the text field
	 */
	protected String getTextFieldInput() {
		return myTextField.getText();
	}
	
	/**
	 * Enables the TextField to expand to fill the width of the HBox container
	 */
	protected void setTextFieldHGrow() {
		HBox.setHgrow(myTextField, Priority.ALWAYS);
	}
}
