package authoringenvironment.view;

import java.util.Arrays;

import authoringenvironment.model.*;
import gameengine.controller.IPlayLevel;
import gui.view.ObjectObservable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * 
 * This class creates a Label containing text (the name of an actor or level)
 * and an image (a picture of an actor or level). When clicked, the label sets
 * the screen to either the Level Editing Environment, if the Label's
 * IEditableGameElement is a Level, or the Actor Editing Environment, if the
 * Label's IEditableGameElement is an Actor
 * 
 * @author Stephen
 *
 */

public class PreviewUnitWithEditable extends ObjectObservable implements IEditingElement {

	private static final Double IMAGE_FIT_SIZE = 75.0;
	private static final Double LABEL_PADDING = 10.0;
	private static final Double HBOX_SPACING = 5.0;
	private HBox myHBox;
	private Label myLabel;
	private IEditableGameElement myEditable;
	private IEditingEnvironment myEnvironment;

	public PreviewUnitWithEditable(IEditableGameElement level, IEditingEnvironment environment) {
		myEditable = (IEditableGameElement) level;
		myEnvironment = environment;
		myLabel = new Label();
		myLabel.setPadding(new Insets(LABEL_PADDING));
		myLabel.setOnMouseClicked(e -> setButtonAction());
		myLabel.wrapTextProperty().setValue(true);
		myHBox = new HBox(myLabel);
		myHBox.setAlignment(Pos.CENTER_LEFT);
		myHBox.setSpacing(HBOX_SPACING);
	}

	// testing
	private void setButtonAction() {
		notifyObservers(Arrays.asList(myEditable, myEnvironment));
	}

	/**
	 * Updates the Label's text and image to account for any changes in the
	 * Actor or Level's name and image
	 */
	public void update() {
		myLabel.setText(myEditable.getName());
		ImageView imageView = new ImageView(myEditable.getImageView().getImage());
		imageView.setFitHeight(IMAGE_FIT_SIZE);
		imageView.setPreserveRatio(true);
		imageView.setRotate(myEditable.getImageView().getRotate());
		imageView.setOpacity(myEditable.getImageView().getOpacity());
		imageView.setScaleX(myEditable.getImageView().getScaleX());
		imageView.setScaleY(myEditable.getImageView().getScaleY());
		myLabel.setGraphic(imageView);
	}

	/**
	 * 
	 * @return the preview unit's instance of IEditableGameElement
	 */
	public IEditableGameElement getEditable() {
		return myEditable;
	}

	/**
	 * 
	 * @return the HBox containing all nodes in the preview unit
	 */
	public HBox getHBox() {
		return myHBox;
	}

	/**
	 * Sets the preview unit's IEditableGameElement to new IEditableGameElement
	 */
	@Override
	public void setEditableElement(IEditableGameElement editable) {
		myEditable = editable;
	}

}