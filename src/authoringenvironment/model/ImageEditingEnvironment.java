package authoringenvironment.model;

import gui.view.PopUpParent;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class ImageEditingEnvironment extends PopUpParent {

	private static final int POPUP_WIDTH = 700;
	private static final int POPUP_HEIGHT = 800;
	private final HBox imageDisplay;
	
	
	public ImageEditingEnvironment() {
		super(POPUP_WIDTH, POPUP_HEIGHT);
		imageDisplay = new HBox();
		initializeImageDisplay();
		bindChildrenWidthsToContainerWidth();
	}
	
	private void initializeImageDisplay() {
		imageDisplay.setAlignment(Pos.CENTER);
	}
	
	

}
