package gui.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ComboBoxWinningConditions extends ComboBoxTextCell {
	private static final String WINNING_CONDITIONS_OPTIONS = "WinningConditionsOptions";
	private static final String DELIMITER = ",";
	private List<String> myOptions;
	
	public ComboBoxWinningConditions(ResourceBundle myResources, String promptText) {
		super(myResources, promptText);
		myOptions = Arrays.asList(myResources.getString(WINNING_CONDITIONS_OPTIONS).split(DELIMITER));
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
