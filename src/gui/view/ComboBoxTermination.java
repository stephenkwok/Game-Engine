package gui.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ComboBoxTermination extends ComboBoxTextCell {
	private static final String TERMINATION_OPTIONS = "TerminationOptions";
	private static final String DELIMITER = ",";
	private List<String> myOptions;
	
	public ComboBoxTermination(ResourceBundle myResources, String promptText) {
		super(myResources, promptText);
		myOptions = Arrays.asList(myResources.getString(TERMINATION_OPTIONS).split(DELIMITER));
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
