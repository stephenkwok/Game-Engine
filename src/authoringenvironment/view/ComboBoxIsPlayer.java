package authoringenvironment.view;

import java.util.List;
import java.util.ResourceBundle;

public class ComboBoxIsPlayer extends ComboBoxTextCell {
	private List<String> myOptions;
	
	public ComboBoxIsPlayer(ResourceBundle myResources, String promptText, List<String> options) {
		super(myResources, promptText);
		this.myOptions = options;
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
