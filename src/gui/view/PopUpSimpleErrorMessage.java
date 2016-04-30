package gui.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * This class generates a simple error notification in the form of a pop up
 * 
 * @author Stephen
 *
 */

public class PopUpSimpleErrorMessage extends PopUpParent {

	private static final double CONTAINER_SPACING = 10.0;
	private String myButtonText;
	private String myErrorMessage;
	private Button myCloseButton;
	private VBox myContainer;
	private Label myLabel;

	public PopUpSimpleErrorMessage(int popUpWidth, int popUpHeight, String buttonText, String errorMessage) {
		super(popUpWidth, popUpHeight);
		myContainer = new VBox(CONTAINER_SPACING);
		myButtonText = buttonText;
		myErrorMessage = errorMessage;
		initializePopUp();
		bindChildrenWidthsToContainerWidth();
		
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
	}

	/**
	 * Initializes the Button that will close the pop up on click
	 */
	private void initializeCloseButton() {
		myCloseButton = new Button(myButtonText);
		myCloseButton.setOnAction(e -> closePopUp());
	}

	/**
	 * Adds nodes created to parent container
	 */
	private void addNodesToPopUp() {
		myContainer.prefHeightProperty().bind(getContainer().heightProperty());
		myContainer.getChildren().addAll(myLabel, myCloseButton);
		myContainer.setAlignment(Pos.CENTER);
		getContainer().getChildren().add(myContainer);
	}

}
