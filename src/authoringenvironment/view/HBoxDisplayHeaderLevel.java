package authoringenvironment.view;

import javafx.beans.binding.DoubleExpression;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

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
		ImageView myButtonImage = new ImageView("reorder.png");
		myButtonImage.setFitHeight(20.0);
		myButtonImage.setPreserveRatio(true);
		myReorderLevelsButton = new Button(BUTTON_TEXT, myButtonImage);
		myReorderLevelsButton.setOnAction(e -> notifyObservers(null));
		myReorderLevelsButton.prefHeightProperty().bind(getHBox().heightProperty().subtract(30.0));
		addToHBox(myReorderLevelsButton);
	}

}
