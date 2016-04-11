package gui.view;

import authoringenvironment.model.IEditableGameElement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

public class TextFieldWithButton implements IGUIElement, IGUIEditingElement {
	private static final int PADDING = 10;
	private static final String GO = "Go";
	private HBox myContainer;
	private Label myTextFieldPrompt;
	private TextField myTextField;
	private Button myButton;
	private String labelText;
	private String promptText;
	private Double textFieldWidth;
	private String buttonText;
	private IEditableGameElement myEditableElement;

	public TextFieldWithButton(String labelText, String promptText, Double textFieldWidth) {
		this.labelText = labelText;
		this.promptText = promptText;
		this.buttonText = GO;
		this.textFieldWidth = textFieldWidth;
		this.myEditableElement = null;
		myButton = new Button(buttonText);
	}
	
	@Override
	public Node createNode() {
		myContainer = new HBox(PADDING);
		myTextFieldPrompt = new Label(labelText);
		myTextField = new TextField();
		myTextField.setPrefWidth(textFieldWidth);
		HBox.setHgrow(myTextField, Priority.ALWAYS);
		myTextField.setPromptText(promptText);
		myContainer.getChildren().addAll(myTextFieldPrompt, myTextField, myButton);
		return myContainer;
	}

	@Override
	public void setEditableElement(IEditableGameElement element) {
		myEditableElement = element;
	}


	protected void setButtonAction(EventHandler<ActionEvent> buttonAction) {
		myButton.setOnAction(buttonAction);
	}
	
	protected IEditableGameElement getEditableElement() {
		return myEditableElement;
	}
	
	protected String getTextFieldInput() {
		return myTextField.getText();
	}
}
