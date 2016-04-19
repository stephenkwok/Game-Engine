package authoringenvironment.view;

import javafx.beans.binding.DoubleExpression;
import javafx.scene.control.Button;

/**
 * This class acts as the header for the display of created Levels on the Main Screen
 * 
 * @author Stephen
 *
 */
public class HBoxDisplayHeaderLevel extends HBoxDisplayHeader {

	private static final String LABEL_TEXT = "Levels";
	private static final String BUTTON_TEXT = "Reorder Levels";
	private Button myReorderLevelsButton;
	
	public HBoxDisplayHeaderLevel(DoubleExpression bindWidth) {
		super(LABEL_TEXT, bindWidth);
		initializeReorderLevelsButton();
	}
	
	private void initializeReorderLevelsButton() {
		myReorderLevelsButton = new Button(BUTTON_TEXT);
		myReorderLevelsButton.setOnAction(e -> notifyObservers(null));
		myReorderLevelsButton.prefHeightProperty().bind(getHBox().heightProperty());
		addToHBox(myReorderLevelsButton);
	}

}
