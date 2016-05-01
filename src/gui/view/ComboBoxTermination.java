package gui.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * ComboBox for level's termination conditions.
 * 
 * @author amyzhao
 *
 */

public class ComboBoxTermination extends ComboBoxTextCell {
	private static final String TERMINATION_OPTIONS = "TerminationOptions";
	private static final String DELIMITER = ",";
	private List<String> myOptions;

	/**
	 * Constructs a combobox using the resource file's available choices for
	 * termination conditions with a label and a "GO" button.
	 * 
	 * @param myResources:
	 *            resource bundle containing possible winning conditions.
	 * @param promptText:
	 *            text for selection prompt.
	 * @param labelText:
	 *            text for label.
	 */
	public ComboBoxTermination(ResourceBundle myResources, String promptText, String labelText) {
		super(promptText, labelText);
		myOptions = Arrays.asList(myResources.getString(TERMINATION_OPTIONS).split(DELIMITER));
	}

	/**
	 * Sets the action of the "GO" button to update the level's termination
	 * condition.
	 */
	@Override
	public void setButtonAction() {
		/*
		 * getComboButton().setOnAction(event->{ ((Level)
		 * getEditableElement()).setMyTermination(getComboBox().getValue()); });
		 */
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
		/*
		 * if (((Level) getEditableElement()).getMyLosingCondition() != null) {
		 * getComboBox().setValue(((Level)
		 * getEditableElement()).getMyLosingCondition()); }
		 */
	}

}
