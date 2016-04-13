package gui.view;

import authoringenvironment.model.IEditableGameElement;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Checkbox object for option selection.
 * @author amyzhao
 *
 */
public class CheckBoxObject implements IGUIElement, IGUIEditingElement {
	private String myPromptText;
	private int myWidth;
	private IEditableGameElement myEditableElement;
	
	/**
	 * Constructs a CheckBoxObject to edit a given element.
	 * @param promptText: option to check or un-check.
	 * @param width: width of the checkbox.
	 */
	public CheckBoxObject(String promptText, int width) {
		myPromptText = promptText;
		myWidth = width;
		myEditableElement = null;
	}
	
	/**
	 * Creates the checkbox.
	 */
	@Override
	public Node createNode() {
		CheckBox checkbox = new CheckBox(myPromptText);
		checkbox.setPrefWidth(myWidth);
		checkbox.setAlignment(Pos.CENTER_LEFT);
		checkbox.setId(myPromptText);
		return checkbox;
	}
	
	/**
	 * Sets the element that this checkbox is editing.
	 */
	@Override
	public void setEditableElement(IEditableGameElement element) {
		myEditableElement = element;
	}

	/**
	 * Gets the element that this checkbox is editing.
	 * @return game/level/actor that this checkbox is editing.
	 */
	protected IEditableGameElement getEditableElement() {
		return myEditableElement;
	}
	
}
