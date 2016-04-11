package gui.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import gameengine.controller.Level;

public class ComboBoxScrollingDirection extends ComboBoxTextCell {
	private static final String SCROLLING_DIRECTION_OPTIONS = "ScrollingDirectionOptions";
	private static final String DELIMITER = ",";
	private List<String> myOptions;
	
	public ComboBoxScrollingDirection(ResourceBundle myResources, String promptText, String labelText) {
		super(myResources, promptText, labelText);
		myOptions = Arrays.asList(myResources.getString(SCROLLING_DIRECTION_OPTIONS).split(DELIMITER));
	}

	@Override
	public void setButtonAction() {
		comboButton.setOnAction(event->{
			((Level) getEditableElement()).setScrollingDirection(comboBox.getValue());
		});
	}

	@Override
	public List<String> getOptionsList() {
		return myOptions;
	}

}
