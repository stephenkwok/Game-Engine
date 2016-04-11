package gui.view;

import authoringenvironment.model.IEditableGameElement;
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

public abstract class TextAreaParent implements IGUIEditingElement, IGUIElement {
	
	private VBox myContainer;
	private Label myPrompt;
	private TextArea myTextArea;
	private Button myButton;
	private IEditableGameElement myEditableElement;
	private String myPromptText;
	private String myButtonText;
	private int textAreaPreferredRows;
	
	public TextAreaParent(String promptText, String buttonText, int preferredRows) {
		myPromptText = promptText;
		myButtonText = buttonText;
		textAreaPreferredRows = preferredRows;
	}
	
	@Override
	public void setEditableElement(IEditableGameElement element) {
		myEditableElement = element;
	}
	
	@Override 
	public Node createNode() {
		myContainer = new VBox();
		myPrompt = new Label(myPromptText);
		myPrompt.setWrapText(true);
		myTextArea = new TextArea();
		myTextArea.setPrefRowCount(textAreaPreferredRows);
		myButton = new Button(myButtonText);
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
