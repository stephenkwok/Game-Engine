package gui.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ComboBoxLosingConditions extends ComboBoxTextCell {
	private static final String LOSING_CONDITIONS_OPTIONS = "LosingConditionsOptions";
	private static final String DELIMITER = ",";
	private List<String> myOptions;
	
	public ComboBoxLosingConditions(ResourceBundle myResources, String promptText) {
		super(myResources, promptText);
		myOptions = Arrays.asList(myResources.getString(LOSING_CONDITIONS_OPTIONS).split(DELIMITER));
	}

	@Override
	public void setButtonAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getOptionsList() {
		return myOptions;
	}

}
