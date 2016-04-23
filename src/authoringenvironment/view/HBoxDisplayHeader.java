package authoringenvironment.view;


import gui.view.ObjectObservable;
import javafx.beans.binding.DoubleExpression;
import javafx.geometry.Pos;
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
	
	private static final String HBOX_BORDER_COLOR = "-fx-border-color: gray;";
	private static final String LABEL_FONT = "Calibri";
	private static final double HBOX_HEIGHT = 80.0;
	private static final int LABEL_FONT_SIZE = 30;
	private static final int HBOX_SPACING = 30;
	private final HBox myHBox;
	private final Label myLabel;
	
	public HBoxDisplayHeader(String labelText, DoubleExpression bindWidth) {
		myHBox = new HBox(HBOX_SPACING);
		myHBox.setMinHeight(HBOX_HEIGHT);
		myHBox.setMaxHeight(HBOX_HEIGHT);
		myHBox.prefWidthProperty().bind(bindWidth);
		myHBox.setAlignment(Pos.CENTER);
		myHBox.setStyle(HBOX_BORDER_COLOR);
		myLabel = new Label(labelText);
		myLabel.prefHeightProperty().bind(myHBox.heightProperty());
		myLabel.setFont(new Font(LABEL_FONT, LABEL_FONT_SIZE));
		myLabel.setAlignment(Pos.CENTER);
		myHBox.getChildren().add(myLabel);
	}
	
	/**
	 * 
	 * @return myHBox
	 */
	public HBox getHBox() {
		return myHBox;
	}
}
