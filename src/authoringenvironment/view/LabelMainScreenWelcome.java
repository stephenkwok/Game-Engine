package authoringenvironment.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

/**
 * This class creates a Label containing a welcome message for the Game Editing Environment. More 
 * generally, it is a Label with a pre-defined padding and the wrap text property initialized to true.
 * The actual text of the Label is determined by the constructor and need not be a welcome message;
 * 
 * @author Stephen
 *
 */

public class LabelMainScreenWelcome extends Label {

	private static final double LABEL_PADDING = 10.0;
	
	public LabelMainScreenWelcome(String welcomeMessage) {
		this.setText(welcomeMessage);
		this.setWrapText(true);
		this.setPadding(new Insets(LABEL_PADDING));
	}

}
