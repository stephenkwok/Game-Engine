package gui.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import gameengine.controller.Level;

public class ComboBoxTermination extends ComboBoxTextCell {
	private static final String TERMINATION_OPTIONS = "TerminationOptions";
	private static final String DELIMITER = ",";
	private List<String> myOptions;
	
	public ComboBoxTermination(ResourceBundle myResources, String promptText, String labelText) {
		super(myResources, promptText, labelText);
		myOptions = Arrays.asList(myResources.getString(TERMINATION_OPTIONS).split(DELIMITER));
	}

	@Override
	public void setButtonAction() {
		comboButton.setOnAction(event->{
			((Level) getEditableElement()).setMyTermination(comboBox.getValue());
		});
	}

	@Override
	public List<String> getOptionsList() {
		return myOptions;
	}

}
