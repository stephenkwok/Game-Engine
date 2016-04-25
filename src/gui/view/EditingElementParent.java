package gui.view;

import java.util.Observer;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingElement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * This class serves as the abstract class for all classes that have an instance
 * of IEditableGameElement and consists of a set of GUI Elements (one of which
 * must be a button) that enable the game author to set certain attributes for
 * that IEditableGameElement
 * 
 * @author Stephen
 *
 */
public abstract class EditingElementParent extends ObjectObservable implements IGUIElement, IEditingElement {

	private IEditableGameElement myEditableElement;
	private Button myButton;

	public EditingElementParent(String buttonText) {
		myButton = new Button(buttonText);
	}

	@Override
	public void addNodeObserver(Observer observer) {
		this.addObserver(observer);
	}

	/**
	 * Updates the class' GUI Elements to display the IEditableGameElement's
	 * current value for the attribute the class is editing
	 */
	protected abstract void updateValueBasedOnEditable();

	/**
	 * Sets the EditingElement's instance of IEditableGameElement
	 */
	@Override
	public void setEditableElement(IEditableGameElement element) {
		myEditableElement = element;
		if (myEditableElement != null) {
			updateValueBasedOnEditable();
		}
	}

	/**
	 * 
	 * @return the EditingElement's IEditableGameElement
	 */
	public IEditableGameElement getEditableElement() {
		return myEditableElement;
	}

	/**
	 * Sets the EditingElement's button's action on click
	 * 
	 * @param buttonAction
	 *            - method to call when button clicked
	 */
	protected void setButtonAction(EventHandler<ActionEvent> buttonAction) {
		myButton.setOnAction(buttonAction);
	}

	/**
	 * 
	 * @return the EditingElement's button
	 */
	protected Button getButton() {
		return myButton;
	}

}
