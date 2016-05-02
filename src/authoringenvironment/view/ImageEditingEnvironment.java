package authoringenvironment.view;

import java.util.Observable;
import java.util.Observer;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class generates a Pop Up that allows the author to edit an ImageView's 
 * size, rotation, opacity, ScaleX, and ScaleY.
 * 
 * @author Stephen
 *
 */

public abstract class ImageEditingEnvironment extends PopUpParent implements Observer {

	private static final String BORDER_COLOR = "-fx-border-color: black;";
	private static final int POPUP_WIDTH = 700;
	private static final int POPUP_HEIGHT = 800;
	private static final int DISPLAY_HEIGHT = 500;
	private final HBox myImageDisplay;
	private final HBox myToolbarContainer;
	private final ImageView myImageView;
	private final ImageEditingToolbar myToolbar;
	private final VBox myContainer;

	/**
	 * Creates a pop up that allows the author to easily edit an Image View's 
	 * size, rotation, scaleX, scaleY, and opacity
	 * @param imageView: the ImageView to edit
	 */
	public ImageEditingEnvironment(ImageView imageView) {
		super(POPUP_WIDTH, POPUP_HEIGHT);
		myContainer = new VBox();
		myImageDisplay = new HBox();
		myToolbarContainer = new HBox();
		myImageView = imageView;
		myToolbar = new ImageEditingToolbar(myImageView);
		myToolbar.addObserver(this);
		initializeImageDisplay();
		initializeToolbarContainer();
		addNodesToParentContainer();
		bindChildrenWidthsToContainerWidth();
	}

	/**
	 * Initializes the area that will display the ImageView being edited 
	 */
	private void initializeImageDisplay() {
		myImageDisplay.setPrefHeight(DISPLAY_HEIGHT);
		myImageDisplay.setAlignment(Pos.CENTER);
		myImageDisplay.setStyle(BORDER_COLOR);
		myImageDisplay.getChildren().add(myImageView);
		myContainer.getChildren().add(myImageDisplay);
	}

	/**
	 * Initializes the toolbar that will contain the sliders and buttons that adjust
	 * the ImageView's size, rotation, opacity, scaleX, and scaleY
	 */
	private void initializeToolbarContainer() {
		myToolbarContainer.setPrefHeight(POPUP_HEIGHT - DISPLAY_HEIGHT);
		myToolbarContainer.setStyle(BORDER_COLOR);
		myToolbar.getToolbar().prefWidthProperty().bind(myToolbarContainer.widthProperty());
		myToolbarContainer.getChildren().add(myToolbar.getToolbar());
		myContainer.getChildren().add(myToolbarContainer);
	}
	
	/**
	 * Adds this class' nodes to the parent container
	 */
	private void addNodesToParentContainer() {
		myContainer.prefHeightProperty().bind(getContainer().heightProperty());
		getContainer().getChildren().add(myContainer);
	}

	/**
	 * Reacts to the clicking of the toolbar's Finish button
	 */
	@Override
	public abstract void update(Observable o, Object arg);


}
