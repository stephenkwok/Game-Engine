package gui.view;

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
public class CheckBoxObject implements IGUIElement {
	private String myPromptText;
	private int mySpacing;
	private int myWidth;
	
	public CheckBoxObject(String promptText, int spacing, int width) {
		myPromptText = promptText;
		myWidth = width;
	}
	
	@Override
	public Node createNode() {
		CheckBox checkbox = new CheckBox(myPromptText);
		checkbox.setPrefWidth(myWidth);
		checkbox.setAlignment(Pos.CENTER_LEFT);
		checkbox.setId(myPromptText);
		return checkbox;
	}
}
