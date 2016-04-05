package authoringenvironment.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public abstract class TextFieldWithButton {

	HBox myContainer;
	Label myTextFieldPrompt;
	TextField myTextField;
	Button myButton;
	
	public TextFieldWithButton(String prompt, Double textFieldWidth, String buttonText, EventHandler<ActionEvent> buttonAction) {
		myContainer = new HBox();
		myTextFieldPrompt = new Label(prompt);
		myTextField = new TextField();
		myTextField.setPrefWidth(textFieldWidth);
		HBox.setHgrow(myTextField, Priority.ALWAYS);
		myButton = new Button(buttonText);
		myButton.setOnAction(buttonAction);
		myContainer.getChildren().addAll(myTextFieldPrompt, myTextField, myButton);
	}
	
	protected void setContainerPadding(Insets insets) {
		myContainer.setPadding(insets);
	}
	
	protected void setContainerSpacing(Double spacing) {
		myContainer.setSpacing(spacing);
	}
	
	public HBox getCoupledNodes() {
		return myContainer;
	}

}
