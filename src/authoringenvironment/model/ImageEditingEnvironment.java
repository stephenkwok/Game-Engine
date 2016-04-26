package authoringenvironment.model;

import java.util.Observable;
import java.util.Observer;

import com.sun.media.jfxmediaimpl.platform.Platform;

import gui.view.PopUpParent;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ImageEditingEnvironment extends PopUpParent implements Observer {

	private static final String BORDER_COLOR = "-fx-border-color: black;";
	private static final int POPUP_WIDTH = 700;
	private static final int POPUP_HEIGHT = 800;
	private static final int DISPLAY_HEIGHT = 500;
	private final HBox myImageDisplay;
	private final HBox myToolbarContainer;
	private ImageView myImageView;
	private ImageEditingToolbar myToolbar;

	public ImageEditingEnvironment(ImageView imageView) {
		super(POPUP_WIDTH, POPUP_HEIGHT);
		myImageDisplay = new HBox();
		myToolbarContainer = new HBox();
		myImageView = imageView;
		myToolbar = new ImageEditingToolbar(myImageView);
		myToolbar.addObserver(this);
		initializeImageDisplay();
		initializeToolbarContainer();
		bindChildrenWidthsToContainerWidth();
	}

	private void initializeImageDisplay() {
		myImageDisplay.setPrefHeight(DISPLAY_HEIGHT);
		myImageDisplay.setAlignment(Pos.CENTER);
		myImageDisplay.setStyle(BORDER_COLOR);
		myImageDisplay.getChildren().add(myImageView);
		getContainer().getChildren().add(myImageDisplay);
	}

	private void initializeToolbarContainer() {
		myToolbarContainer.setPrefHeight(POPUP_HEIGHT - DISPLAY_HEIGHT);
		myToolbarContainer.setStyle(BORDER_COLOR);
		myToolbarContainer.getChildren().add(myToolbar.getToolbar());
		getContainer().getChildren().add(myToolbarContainer);
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers();	
		closePopUp();
	}


}
