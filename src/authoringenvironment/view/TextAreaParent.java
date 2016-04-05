package authoringenvironment.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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

public abstract class TextAreaParent extends VBox {
	
	private Label myPrompt;
	private TextArea myTextArea;
	private Button myButton;
	
	public TextAreaParent(String promptText, String buttonText, int prefRows, EventHandler<ActionEvent> handler) {
		myPrompt = new Label(promptText);
		myPrompt.setWrapText(true);
		myTextArea = new TextArea();
		myTextArea.setPrefRowCount(prefRows);
		myButton = new Button(buttonText);
		myButton.setOnAction(handler);
		myButton.prefWidthProperty().bind(this.widthProperty());
		this.getChildren().addAll(myPrompt, myTextArea, myButton);
	}
	
	protected void setVBoxPadding(Insets insets) {
		this.setPadding(insets);
	}

}
