package authoringenvironment.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * This class serves as the base class for all horizontal couplings of a label, text field, and button
 * 
 * @author Stephen
 *
 */

public class TextFieldWithButton implements IGUIElement {

	HBox myContainer;
	Label myTextFieldPrompt;
	TextField myTextField;
	Button myButton;
	
	public TextFieldWithButton(String labelText, String prompt, Double textFieldWidth, String buttonText, EventHandler<ActionEvent> buttonAction) {
		myContainer = new HBox();
		myTextFieldPrompt = new Label(labelText);
		myTextField = new TextField();
		myTextField.setPrefWidth(textFieldWidth);
		HBox.setHgrow(myTextField, Priority.ALWAYS);
		myButton = new Button(buttonText);
		myButton.setOnAction(buttonAction);
		myTextField.setPromptText(prompt);
		myContainer.getChildren().addAll(myTextFieldPrompt, myTextField, myButton);
	}
	
	protected void setContainerPadding(Insets insets) {
		myContainer.setPadding(insets);
	}
	
	protected void setContainerSpacing(Double spacing) {
		myContainer.setSpacing(spacing);
	}
	
	protected void setTextFieldPromptText(String promptText) {
		myTextField.setPromptText(promptText);
	}
	
	public HBox getCoupledNodes() {
		return myContainer;
	}

	@Override
	public Node createNode() {
		return myContainer;
	}

	@Override
	public void updateNode() {
		// TODO Auto-generated method stub
		
	}

}
