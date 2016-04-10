package gui.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ComboBoxScrollingDirection extends ComboBoxTextCell {
	private static final String SCROLLING_DIRECTION_OPTIONS = "ScrollingDirectionOptions";
	private static final String DELIMITER = ",";
	private List<String> myOptions;
	
	public ComboBoxScrollingDirection(ResourceBundle myResources, String promptText) {
		super(myResources, promptText);
		myOptions = Arrays.asList(myResources.getString(SCROLLING_DIRECTION_OPTIONS).split(DELIMITER));
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
