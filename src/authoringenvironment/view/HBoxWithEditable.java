package authoringenvironment.view;

import java.util.Arrays;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingElement;
import authoringenvironment.model.IEditingEnvironment;
import gui.view.ObjectObservable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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

public class HBoxWithEditable extends ObjectObservable implements IEditingElement {

	private static final String IMAGE_TEXT_PADDING = "    ";
	private static final Double IMAGE_FIT_SIZE = 75.0;
	private static final Double LABEL_PADDING = 10.0;
	private static final Double HBOX_SPACING = 10.0;
	private HBox myHBox;
	private Label myLabel;
	private IEditableGameElement myEditable;
	private IEditingEnvironment myEnvironment;

	public HBoxWithEditable(IEditableGameElement editable, IEditingEnvironment environment) {
		myEditable = editable;
		myEnvironment = environment;
		myLabel = new Label();
		myHBox = new HBox(myLabel);
		myHBox.setAlignment(Pos.CENTER_LEFT);
		myHBox.setSpacing(HBOX_SPACING);
		myLabel.setOnMouseClicked(e -> setButtonAction());
	}
	
	//testing
	private void setButtonAction() {
		notifyObservers(Arrays.asList(myEditable, myEnvironment));
	}
	
	/**
	 * Updates the Label's text and image to account for any changes in the 
	 * Actor or Level's name and image
	 */
	public void update() {
		myLabel.setText(IMAGE_TEXT_PADDING + myEditable.getMyName());
		ImageView imageView = new ImageView(myEditable.getMyImageView().getImage());
		imageView.setFitHeight(IMAGE_FIT_SIZE);
		imageView.setPreserveRatio(true);
		myLabel.setPadding(new Insets(LABEL_PADDING));
		myLabel.setGraphic(imageView);
	}
	
	public IEditableGameElement getEditable() {
		return myEditable;
	}
	
	public HBox getHBox() {
		return myHBox;
	}

	@Override
	public void setEditableElement(IEditableGameElement editable) {
		myEditable = editable;
	}

}