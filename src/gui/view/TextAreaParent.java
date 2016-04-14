package gui.view;

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

public abstract class TextAreaParent extends EditingElementParent {	
	private VBox myContainer;
	private Label myPrompt;
	private TextArea myTextArea;
	private Button myButton;
	private int textAreaPreferredRows;
	
	public TextAreaParent(String promptText, String buttonText, int preferredRows) {
		super(buttonText);
		textAreaPreferredRows = preferredRows;
		myButton = getButton();
		myContainer = new VBox();
		myPrompt = new Label(promptText);
		myTextArea = new TextArea();
	}
	
	@Override 
	public Node createNode() {
		myPrompt.setWrapText(true);
		myTextArea.setPrefRowCount(textAreaPreferredRows);
		myButton.prefWidthProperty().bind(myContainer.widthProperty());
		myContainer.getChildren().addAll(myPrompt, myTextArea, myButton);
		return myContainer;
	}
	
	protected void setContainerPadding(Insets insets) {
		myContainer.setPadding(insets);
	}
	
	protected void setTextAreaPromptText(String prompt) {
		myTextArea.setPromptText(prompt);
	}
	
	protected String getTextAreaInput() {
		return myTextArea.getText();
	}

}
