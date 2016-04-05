package authoringenvironment.view;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TextAreaWithButton extends VBox {
	
	Label myPrompt;
	TextArea myTextArea;
	Button myButton;
	ResourceBundle myResources;
	
	public TextAreaWithButton(String promptKey, String buttonText, EventHandler<ActionEvent> handler) {
		myResources = ResourceBundle.getBundle("resources/guiStrings/guiStrings");
		myPrompt = new Label(myResources.getString(promptKey));
		myPrompt.setAlignment(Pos.CENTER);
		myPrompt.setWrapText(true);
		myTextArea = new TextArea();
		myButton = new Button(buttonText);
		myButton.setOnAction(handler);
		myButton.prefWidthProperty().bind(this.widthProperty());
		this.getChildren().addAll(myPrompt, myTextArea, myButton);
	}

}
