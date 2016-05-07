// This entire file is part of my masterpiece
// Stephen Kwok

// I believe this class is well-designed for several reasons:
// First of all, this class has been designed to be as flexible and extensible as possible (As proof, this class is extended 
// by the PreviewUnitWithLevel class (also a part of the masterpiece) and is even completely reused in my Slogo extension).
// To keep this class extensible, I maintain an HBox called myHBox that contains all GUI Elements that the preview unit may
// need to contain. This design was chosen over my original design of having this class extend Label, since only a limited
// number of things can be added to a Label whereas an infinite number of nodes (space permitting) can be added to an HBox.
// As a result, even though the basic functionality that you would expect from a preview unit is there in that you can view
// some editable's name and image are there, programmers are also given the flexibility of incorporating additional features
// (as will be demonstrated by the PreviewUnitWithLevel) class. Another design decision I made to keep this class as flexible
// and extensible as possible is giving each preview unit a reference to an IEditableGameElememt. This interface promises 
// that all classes that extend the interface have getters and setters for the editable's name and image, which is important 
// because those are the two core things that the preview unit needs to display. However, by having all objects that might be
// displayed by a preview unit implement the IEditableGameElement interface, I ensure that you don't need separate preview unit
// classes for each object to be displayed: this class can support any object as long as it implements the IEditableGameElement,
// which I prove by using this very same class to display all the created turtles and their images for my Slogo front-end extension.
// No modifications at all needed to be made to this class (other than changing the name of the IEditableGameELement interface
// to better fit the purposes of Slogo) to support the display of Turtles, and the only changes made to the Turtle class were added 
// (not changed) code to make the Turtle class implement IEditableGameElement. Lastly, the Observer/Observable design pattern is utilized
// so that other classes can make changes in response to changes in the preview unit. The use of Observer/Observables helps keep 
// this class extensible since no specific class needs to be passed into the preview unit and then have its methods called from 
// within the preview unit when changes should be made. Rather, ANY class can observe this class and make adjustments accordingly 
// from outside the class, meaning it does not matter that our program that has a Controller class that does things in response to 
// the preview unit. Since the Controller is not passed to the preview unit, other programmers do not need our same Controller class
// in order to reuse this class.

// Another reason I believe this code is well-designed is because the way the preview unit is updated to reflect changes 
// in the editable's name or image is completely encapsulated within this class. Other classes need not know anything about this
// class other than the fact that calling this class' update() method will make the desired changes to the preview unit.

// Last but not least, this class is well-written in the sense that it has descriptive class, variable and method names that make 
// clear exactly what each thing is responsible for. In addition, the methods have been designed to be short in length and modular 
// so as to make debugging and understanding my code simpler for those unfamiliar with the class. Last but not least, the class has
// been designed to follow the Single Responsibility Principle. Its sole purpose is to provide a preview of some object, and you will not
// find any logic within this class that does not belong here. In doing so, I ensure that the class does exactly what you would expect given
// the class name, and that those looking through the code do not struggle with understanding what this class is for and why it performs
// actions it should not be responsible for. 

package authoringenvironment.view;

import authoringenvironment.model.*;
import gui.view.ObjectObservable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * 
 * This class creates a preview unit containing text (the name of an actor or level)
 * and an image (a picture of an actor or level). When clicked, the preview unit sets
 * the screen to either the Level Editing Environment, if the preview unit's
 * IEditableGameElement is a Level, or the Actor Editing Environment, if the
 * preview unit's IEditableGameElement is an Actor
 * 
 * @author Stephen
 *
 */

public class PreviewUnitWithEditable extends ObjectObservable implements IEditingElement {

	private static final Double IMAGE_FIT_SIZE = 75.0;
	private static final Double LABEL_PADDING = 10.0;
	private static final Double HBOX_SPACING = 5.0;
	private final HBox myHBox;
	private final Label myLabel;
	private IEditableGameElement myEditable;

	/**
	 * Creates a preview unit for an IEditableGameElement
	 * @param editable: the IEditableGameElement the preview unit will be displaying
	 */
	public PreviewUnitWithEditable(IEditableGameElement editable) {
		myEditable = editable;
		myLabel = new Label();
		myLabel.setPadding(new Insets(LABEL_PADDING));
		myLabel.setOnMouseClicked(e -> notifyObservers(editable));
		myLabel.wrapTextProperty().setValue(true);
		myHBox = new HBox(myLabel);
		myHBox.setAlignment(Pos.CENTER_LEFT);
		myHBox.setSpacing(HBOX_SPACING);
	}
	
	/**
	 * Updates the preview unit's text and image to account for any changes in the
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