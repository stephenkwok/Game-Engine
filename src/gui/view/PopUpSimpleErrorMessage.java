package gui.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PopUpSimpleErrorMessage extends PopUpParent {

	private String myButtonText;
	private String myErrorMessage;
	private Button myCloseButton;
	private Label myLabel;
	
	public PopUpSimpleErrorMessage(int popUpWidth, int popUpHeight, String buttonText, String errorMessage) {
		super(popUpWidth, popUpHeight);
		myButtonText = buttonText;
		myErrorMessage = errorMessage;
		initializePopUp();
	}
	
	private void initializePopUp() {
		initializeLabel();
		initializeCloseButton();
		addNodesToPopUp();
	}
	
	private void initializeLabel() {
		myLabel = new Label(myErrorMessage);
		myLabel.setAlignment(Pos.CENTER);
		myLabel.prefWidthProperty().bind(getContainer().widthProperty());
	}
	
	private void initializeCloseButton() {
		myCloseButton = new Button(myButtonText);
		myCloseButton.prefWidthProperty().bind(getContainer().widthProperty());
		myCloseButton.setOnAction(e -> closePopUp());
	}
	
	private void addNodesToPopUp() {
		getContainer().getChildren().addAll(myLabel, myCloseButton);
	}

}
