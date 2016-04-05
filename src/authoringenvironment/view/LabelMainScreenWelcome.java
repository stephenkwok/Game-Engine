package authoringenvironment.view;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class LabelMainScreenWelcome extends Label {

	private static final double LABEL_PADDING = 10.0;
	
	public LabelMainScreenWelcome(String welcomeMessage) {
		this.setText(welcomeMessage);
		this.setWrapText(true);
		this.setPadding(new Insets(LABEL_PADDING));
	}

}
