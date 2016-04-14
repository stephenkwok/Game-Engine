package gui.view;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IGUIEditingElement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * This class serves as the abstract class for all classes that have an instance of
 * IEditableGameElement and consists of a set of GUI Elements (one of which must be 
 * a button) that enable the game author to set certain attributes for that 
 * IEditableGameElement
 * 
 * @author Stephen
 *
 */
public abstract class EditingElementParent implements IGUIElement, IGUIEditingElement {
	
	private IEditableGameElement myEditable;
	private Button myButton;
	
	public EditingElementParent(String buttonText) {
		myButton = new Button(buttonText);
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
		myEditable = element;		
	}
	
	/**
	 * 
	 * @return the EditingElement's IEditableGameElement
	 */
	public IEditableGameElement getEditableElement() {
		return myEditable; 
	}
	
	/**
	 * Sets the EditingElement's button's action on click
	 * 
	 * @param buttonAction - method to call when button clicked 
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
