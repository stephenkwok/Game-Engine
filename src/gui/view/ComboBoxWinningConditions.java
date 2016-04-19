package gui.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import gameengine.controller.Level;

/**
 * ComboBox for level's winning conditions.
 * @author amyzhao
 *
 */

public class ComboBoxWinningConditions extends ComboBoxTextCell {
	private static final String WINNING_CONDITIONS_OPTIONS = "WinningConditionsOptions";
	private static final String DELIMITER = ",";
	private List<String> myOptions;

	/**
	 * Constructs a combobox using the resource file's available choices for winning conditions with a label and a "GO" button.
	 * @param myResources: resource bundle containing possible winning conditions.
	 * @param promptText: text for selection prompt.
	 * @param labelText: text for label.
	 */
	public ComboBoxWinningConditions(ResourceBundle myResources, String promptText, String labelText) {
		super(myResources, promptText, labelText);
		myOptions = Arrays.asList(myResources.getString(WINNING_CONDITIONS_OPTIONS).split(DELIMITER));
	}

	/**
	 * Sets the action of the "GO" button to update the level's winning condition.
	 */
	@Override
	public void setButtonAction() {
		getComboButton().setOnAction(event->{
			((Level) getEditableElement()).setMyWinningCondition(getComboBox().getValue());
		});
	}

	/**
	 * Returns the combobox's list of options.
	 */
	@Override
	public List<String> getOptionsList() {
		return myOptions;
	}

	/**
	 * Sets the value of the combobox to match that of the level.
	 */
	@Override
	protected void updateValueBasedOnEditable() {
		if (((Level) getEditableElement()).getMyWinningCondition() != null) {
			getComboBox().setValue(((Level) getEditableElement()).getMyWinningCondition());
		}
	}

}
