package authoringenvironment.view;

import java.util.Arrays;
import java.util.Observable;

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

public class LabelClickable extends Observable {

	private static final String IMAGE_TEXT_PADDING = "    ";
	private static final Double FIT_SIZE = 75.0;
	private static final Double LABEL_PADDING = 10.0;
	private Label myLabel;
	private IEditableGameElement myEditable;
	private IEditingEnvironment myEnvironment;

	public LabelClickable(IEditableGameElement editable, IEditingEnvironment environment) {
		this.myEditable = editable;
		this.myEnvironment = environment;
		this.myLabel = new Label();
		myLabel.setOnMouseClicked(e -> notifyController());
	}

	private void notifyController() {
		this.setChanged();
		this.notifyObservers(Arrays.asList(myEditable, myEnvironment));
	}
	
	/**
	 * Updates the Label's text and image to account for any changes in the 
	 * Actor or Level's name and image
	 */
	public void update() {
		myLabel.setText(IMAGE_TEXT_PADDING + myEditable.getMyName());
		ImageView imageView = new ImageView(myEditable.getMyImageView().getImage());
		imageView.setFitHeight(FIT_SIZE);
		imageView.setPreserveRatio(true);
		myLabel.setPadding(new Insets(LABEL_PADDING));
		myLabel.setGraphic(imageView);
	}
	
	/**
	 * 
	 * @return myLabel
	 */
	public Label getLabel() {
		return myLabel;
	}

}
