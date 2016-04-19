package authoringenvironment.view;


import gui.view.ObjectObservable;
import javafx.beans.binding.DoubleExpression;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * This class acts as the base class for all display headers made up of 
 * Nodes within an HBox
 * 
 * @author Stephen
 *
 */

public abstract class HBoxDisplayHeader extends ObjectObservable {
	
	private static final double HBOX_HEIGHT = 80.0;
	private static final String LABEL_FONT = "Calibri";
	private static final int LABEL_FONT_SIZE = 30;
	private static final int HBOX_SPACING = 30;
	private final HBox myHBox;
	private final Label myLabel;
	
	public HBoxDisplayHeader(String labelText, DoubleExpression bindWidth) {
		myHBox = new HBox(HBOX_SPACING);
		myHBox.setMinHeight(HBOX_HEIGHT);
		myHBox.prefWidthProperty().bind(bindWidth);
		myHBox.setAlignment(Pos.CENTER);
		myLabel = new Label(labelText);
		myLabel.setPrefHeight(HBOX_HEIGHT);
		myLabel.setFont(new Font(LABEL_FONT, LABEL_FONT_SIZE));
		myLabel.setAlignment(Pos.CENTER);
		addToHBox(myLabel);
	}

	/**
	 * Adds node to myHBox
	 * 
	 * @param node: node to add to the HBox
	 */
	protected void addToHBox(Node node) {
		myHBox.getChildren().add(node);
	}
	
	/**
	 * 
	 * @return myHBox
	 */
	public HBox getHBox() {
		return myHBox;
	}
}
