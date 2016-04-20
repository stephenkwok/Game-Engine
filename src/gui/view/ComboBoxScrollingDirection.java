package gui.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import gameengine.controller.Level;

/**
 * ComboBox for level's scrolling direction.
 * @author amyzhao
 *
 */
public class ComboBoxScrollingDirection extends ComboBoxTextCell {
	private static final String SCROLLING_DIRECTION_OPTIONS = "ScrollingDirectionOptions";
	private static final String DELIMITER = ",";
	private List<String> myOptions;

	/**
	 * Constructs a combobox using the resource file's available choices for scrolling directions with a label and a "GO" button.
	 * @param myResources: resource bundle containing possible scrolling directions.
	 * @param promptText: text for selection prompt.
	 * @param labelText: text for label.
	 */
	public ComboBoxScrollingDirection(ResourceBundle myResources, String promptText, String labelText) {
		super(promptText, labelText);
		myOptions = Arrays.asList(myResources.getString(SCROLLING_DIRECTION_OPTIONS).split(DELIMITER));
	}

	/**
	 * Sets the action of the "GO" button to update the level's scrolling direction.
	 */
	@Override
	public void setButtonAction() {
		getComboButton().setOnAction(event->{
			((Level) getEditableElement()).setMyScrollingDirection(getComboBox().getValue());
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
		if (((Level) getEditableElement()).getMyScrollingDirection() != null) {
			getComboBox().setValue(((Level) getEditableElement()).getMyScrollingDirection());
		}
	}

}
