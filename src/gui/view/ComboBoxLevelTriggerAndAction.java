package gui.view;

import java.util.List;

public class ComboBoxLevelTriggerAndAction extends ComboBoxTextCell {
	private List<String> myOptions;
	
	public ComboBoxLevelTriggerAndAction(String promptText, String labelText, List<String> options) {
		super(promptText, labelText);
		myOptions = options;
	}

	@Override
	public void setButtonAction() {
		getButton().setOnAction(e -> notifyObservers(getComboBox().getValue()));
	}

	@Override
	protected List<String> getOptionsList() {
		return myOptions;
	}

	@Override
	protected void updateValueBasedOnEditable() {
		// TODO Auto-generated method stub
		
	}

}
