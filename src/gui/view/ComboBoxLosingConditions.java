package gui.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import gameengine.controller.Level;

/**
 * ComboBox for level's losing conditions.
 * @author amyzhao
 *
 */
public class ComboBoxLosingConditions extends ComboBoxTextCell {
	private static final String LOSING_CONDITIONS_OPTIONS = "LosingConditionsOptions";
	private static final String DELIMITER = ",";
	private List<String> myOptions;
	
	/**
	 * Constructs a combobox using the resource file's available choices for losing conditions with a label and a "GO" button.
	 * @param myResources: resource bundle containing possible losing conditions.
	 * @param promptText: text for selection prompt.
	 * @param labelText: text for label.
	 */
	public ComboBoxLosingConditions(ResourceBundle myResources, String promptText, String labelText) {
		super(myResources, promptText, labelText);
		myOptions = Arrays.asList(myResources.getString(LOSING_CONDITIONS_OPTIONS).split(DELIMITER));
	}

	/**
	 * Sets the action of the "GO" button to update the level's losing condition.
	 */
	@Override
	public void setButtonAction() {
		getComboButton().setOnAction(event->{
			((Level) getEditableElement()).setMyLosingCondition(getComboBox().getValue());
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
		if (((Level) getEditableElement()).getMyLosingCondition() != null) {
			getComboBox().setValue(((Level) getEditableElement()).getMyLosingCondition());
		}
	}

}
