package gui.view;

import java.util.Observable;
import java.util.Observer;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingElement;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;

/**
 * Checkbox object for option selection.
 * 
 * @author amyzhao
 *
 */
public class CheckBoxObject extends Observable implements IGUIElement, IEditingElement {
	private String myPromptText;
	private int myWidth;
	private IEditableGameElement myEditableElement;
	private CheckBox myCheckBox;

	/**
	 * Constructs a CheckBoxObject to edit a given element.
	 * 
	 * @param promptText:
	 *            option to check or un-check.
	 * @param width:
	 *            width of the checkbox.
	 */
	public CheckBoxObject(String promptText, int width) {
		myPromptText = promptText;
		myWidth = width;
		myEditableElement = null;
		myCheckBox = new CheckBox(myPromptText);
		myCheckBox.setPrefWidth(myWidth);
		myCheckBox.setAlignment(Pos.CENTER_LEFT);
		myCheckBox.setId(myPromptText);
	}

	/**
	 * Creates the checkbox.
	 */
	@Override
	public Node createNode() {
		
		return myCheckBox;
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
	 * 
	 * @return game/level/actor that this checkbox is editing.
	 */
	protected IEditableGameElement getEditableElement() {
		return myEditableElement;
	}

	@Override
	public void addNodeObserver(Observer observer) {
		this.addObserver(observer);
	}

}
