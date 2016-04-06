package authoringenvironment.view;

import java.util.List;
import java.util.ResourceBundle;

public class ComboBoxTermination extends ComboBoxTextCell {
	private List<String> myOptions;
	
	public ComboBoxTermination(ResourceBundle myResources, String promptText, List<String> options) {
		super(myResources, promptText);
		myOptions = options;
	}

	@Override
	void setButtonAction() {
		// TODO Auto-generated method stub
	}

	@Override
	List<String> getOptionsList() {
		return myOptions;
	}

}
