package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * 
 * This class creates a Label containing text (the name of an actor or level) and an image (a picture
 * of an actor or level). When clicked, the label sets the screen to either the Level Editing Environment,
 * if the Label's IEditableGameElement is a Level, or the Actor Editing Environment, if the Label's
 * IEditableGameElement is an Actor
 * 
 * @author Stephen
 *
 */

public class LabelClickable extends Label {

	private static final String IMAGE_TEXT_PADDING = "    ";
	private static final String DEFAULT_BORDER_COLOR_KEY = "defaultBorderColor";
	private static final String RESOURCE_BUNDLE_KEY = "mainScreenGUI";
	private static final Double FIT_SIZE = 75.0;
	private static final Double LABEL_PADDING = 10.0;
	private ResourceBundle myResources;
	private IEditableGameElement myEditable;
	private IEditingEnvironment myEnvironment;

	public LabelClickable(IEditableGameElement editable, IEditingEnvironment environment, Controller controller) {
		this.myEditable = editable;
		this.myEnvironment = environment;
		this.myResources = ResourceBundle.getBundle(RESOURCE_BUNDLE_KEY);
		this.setOnMouseClicked(e -> controller.goToEditingEnvironment(myEditable, myEnvironment));
		this.setStyle(myResources.getString(DEFAULT_BORDER_COLOR_KEY));
	}

	/**
	 * Updates the Label's text and image to account for any changes in the 
	 * Actor or Level's name and image
	 */
	public void update() {
		this.setText(IMAGE_TEXT_PADDING + myEditable.getMyName());
		ImageView imageView = new ImageView(myEditable.getImageView().getImage());
		imageView.setFitHeight(FIT_SIZE);
		imageView.setPreserveRatio(true);
		this.setPadding(new Insets(LABEL_PADDING));
		this.setGraphic(imageView);
	}

}
