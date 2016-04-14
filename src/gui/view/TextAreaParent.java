package gui.view;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingElement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 * 
 * Abstract class generating a VBox containing a Label prompting user to do something, a TextArea in which the author
 * can enter text, and a Button that can perform any defined action
 * game's description
 * 
 * @author Stephen
 *
 */

public abstract class TextAreaParent implements IEditingElement, IGUIElement {
	
	private VBox myContainer;
	private Label myPrompt;
	private TextArea myTextArea;
	private Button myButton;
	private IEditableGameElement myEditableElement;
	private String myButtonText;
	private int textAreaPreferredRows;
	
	public TextAreaParent(String promptText, String buttonText, int preferredRows) {
		myButtonText = buttonText;
		textAreaPreferredRows = preferredRows;
		myContainer = new VBox();
		myPrompt = new Label(promptText);
		myTextArea = new TextArea();
		myButton = new Button(myButtonText);
	}
	
	@Override
	public void setEditableElement(IEditableGameElement element) {
		myEditableElement = element;
	}
	
	@Override 
	public Node createNode() {
		myPrompt.setWrapText(true);
		myTextArea.setPrefRowCount(textAreaPreferredRows);
		myButton.prefWidthProperty().bind(myContainer.widthProperty());
		myContainer.getChildren().addAll(myPrompt, myTextArea, myButton);
		return myContainer;
	}
	
	protected void setButtonAction(EventHandler<ActionEvent> buttonAction) {
		myButton.setOnAction(buttonAction);
	}
	
	protected void setContainerPadding(Insets insets) {
		myContainer.setPadding(insets);
	}
	
	protected void setTextAreaPromptText(String prompt) {
		myTextArea.setPromptText(prompt);
	}
	
	protected IEditableGameElement getEditableElement() {
		return myEditableElement;
	}
	
	protected String getTextAreaInput() {
		return myTextArea.getText();
	}

}
