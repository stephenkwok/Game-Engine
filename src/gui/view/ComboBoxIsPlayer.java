package gui.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ComboBoxIsPlayer extends ComboBoxTextCell {
	private static final String IS_PLAYER_OPTIONS = "IsPlayerOptions";
	private static final String DELIMITER = ",";
	private List<String> myOptions;
	
	public ComboBoxIsPlayer(ResourceBundle myResources, String promptText, String labelText) {
		super(myResources, promptText, labelText);
		myOptions = Arrays.asList(myResources.getString(IS_PLAYER_OPTIONS).split(DELIMITER));
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
