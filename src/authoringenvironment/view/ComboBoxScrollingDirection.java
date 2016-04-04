package authoringenvironment.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ComboBoxScrollingDirection extends ComboBoxParent {
	private static final String OPTIONS = "ScrollingDirectionOptions";
	private static final String LABEL = "ScrollingDirectionLabel";
	
	public ComboBoxScrollingDirection(ResourceBundle myResources, String promptText) {
		super(myResources, promptText);
	}

	@Override
	void setButtonAction() {
		// TODO Auto-generated method stub
	}

	@Override
	protected Node getNodeForBox(String item) {
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().add(new Label(myResources.getString(LABEL)));
		hbox.getChildren().add(createNode());
		return hbox;
	}

	@Override
	List<String> getOptionsList() {
		return Arrays.asList(myResources.getString(OPTIONS).split(","));
	}
}
