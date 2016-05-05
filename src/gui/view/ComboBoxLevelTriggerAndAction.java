package gui.view;

import java.util.List;

/**
 * ComboBoxLevelTriggerOrAction
 * @author amyzhao
 *
 */
public class ComboBoxLevelTriggerAndAction extends ComboBoxTextCell {
	private List<String> myOptions;
	
	/**
	 * Constructor for ComboBoxLevelTriggerAndAction.
	 * @param promptText: initial prompt shown in combobox.
	 * @param labelText: label next to combobox.
	 * @param options: options to display in combobox.
	 */
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
		// do nothing
	}

}
