package gui.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * This class generates a simple error notficaiton in the form of a pop up
 * 
 * @author Stephen
 *
 */

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
	
	/**
	 * Initializes the pop up
	 */
	private void initializePopUp() {
		initializeLabel();
		initializeCloseButton();
		addNodesToPopUp();
	}
	
	/**
	 * Initializes the Label that will display the error message
	 */
	private void initializeLabel() {
		myLabel = new Label(myErrorMessage);
		myLabel.setAlignment(Pos.CENTER);
		myLabel.prefWidthProperty().bind(getContainer().widthProperty());
	}
	
	/**
	 * Initializes the Button that will close the pop up on click
	 */
	private void initializeCloseButton() {
		myCloseButton = new Button(myButtonText);
		myCloseButton.prefWidthProperty().bind(getContainer().widthProperty());
		myCloseButton.setOnAction(e -> closePopUp());
	}
	
	/**
	 * Adds nodes created to parent container
	 */
	private void addNodesToPopUp() {
		getContainer().getChildren().addAll(myLabel, myCloseButton);
	}

}
