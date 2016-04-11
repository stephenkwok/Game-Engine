package gui.view;

import authoringenvironment.model.IEditableGameElement;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Abstract class to implement different types of CheckBoxes;
 * @author amyzhao
 *
 */
public class CheckBoxObject implements IGUIElement, IGUIEditingElement {
	private String myPromptText;
	private int mySpacing;
	private int myWidth;
	private IEditableGameElement myEditableElement;
	
	public CheckBoxObject(String promptText, int spacing, int width) {
		myPromptText = promptText;
		myWidth = width;
		myEditableElement = null;
	}
	
	@Override
	public Node createNode() {
		CheckBox checkbox = new CheckBox(myPromptText);
		checkbox.setPrefWidth(myWidth);
		checkbox.setAlignment(Pos.CENTER_LEFT);
		checkbox.setId(myPromptText);
		return checkbox;
	}
	
	@Override
	public void setEditableElement(IEditableGameElement element) {
		myEditableElement = element;
	}

	protected IEditableGameElement getEditableElement() {
		return myEditableElement;
	}
	
}
