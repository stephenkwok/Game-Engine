package gui.view;

import java.util.List;
import java.util.ResourceBundle;

public class ComboBoxOptions extends ComboBoxTextCell {
	private List<String> myOptions;
	
	public ComboBoxOptions(ResourceBundle myResources, String promptText, List<String> options) {
		super(myResources, promptText);
		myOptions = options;
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
