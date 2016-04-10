package gui.view;

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

public class TextFieldWithButton implements IGUIElement {
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
	private EventHandler<ActionEvent> buttonAction;
	

	public TextFieldWithButton(String labelText, String promptText, Double textFieldWidth,
			EventHandler<ActionEvent> buttonAction) {
		this.labelText = labelText;
		this.promptText = promptText;
		this.textFieldWidth = textFieldWidth;
		this.buttonAction = buttonAction;
	}
	
	public HBox getCoupledNodes() {
		return myContainer;
	}

	@Override
	public Node createNode() {
		myContainer = new HBox(PADDING);
		myTextFieldPrompt = new Label(labelText);
		myTextField = new TextField();
		myTextField.setPrefWidth(textFieldWidth);
		HBox.setHgrow(myTextField, Priority.ALWAYS);
		myButton = new Button(buttonText);
		myButton.setOnAction(buttonAction);
		myTextField.setPromptText(promptText);
		myContainer.getChildren().addAll(myTextFieldPrompt, myTextField, myButton);
		return myContainer;
	}
}
