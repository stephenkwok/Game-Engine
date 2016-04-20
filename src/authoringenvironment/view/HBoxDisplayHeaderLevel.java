package authoringenvironment.view;

import javafx.beans.binding.DoubleExpression;
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
	private static final String IMAGE_URL = "reorder.png";
	private static final double BUTTON_IMAGE_HEIGHT = 20.0;
	private static final double BUTTON_TOP_BOTTOM_PADDING = 30.0;
	private Button myReorderLevelsButton;
	
	public HBoxDisplayHeaderLevel(DoubleExpression bindWidth) {
		super(LABEL_TEXT, bindWidth);
		initializeReorderLevelsButton();
	}
	
	private void initializeReorderLevelsButton() {
		ImageView myButtonImage = new ImageView(IMAGE_URL);
		myButtonImage.setFitHeight(BUTTON_IMAGE_HEIGHT);
		myButtonImage.setPreserveRatio(true);
		myReorderLevelsButton = new Button(BUTTON_TEXT, myButtonImage);
		myReorderLevelsButton.setOnAction(e -> notifyObservers(null));
		myReorderLevelsButton.prefHeightProperty().bind(getHBox().heightProperty().subtract(BUTTON_TOP_BOTTOM_PADDING));
		getHBox().getChildren().add(myReorderLevelsButton);
	}

}
