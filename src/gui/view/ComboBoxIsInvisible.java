package gui.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import gameengine.model.Actor;
import gameengine.model.ActorState;

/**
 * 
 * This class generates a ComboBox allowing the author to set the visibility of an Actor
 * 
 * @author Stephen
 *
 */

public class ComboBoxIsInvisible extends ComboBoxTextCell {
	private static final String IS_INVISIBLE_OPTIONS = "IsInvisibleOptions";
	private static final String DELIMITER = ",";
	private final ResourceBundle myResources;

	public ComboBoxIsInvisible(ResourceBundle myResources, String promptText, String labelText) {
		super(promptText, labelText);
		this.myResources = myResources;
	}

	/**
	 * Sets an Actor to be invisible if the author selects true; sets the 
	 * actor to be visible if the author selects false;
	 */
	@Override
	public void setButtonAction() {
		getComboButton().setOnAction(event -> {
			if (Boolean.parseBoolean(getComboBox().getValue())) {
				((Actor) getEditableElement()).addState(ActorState.INVISIBLE);
			}
			else {
				((Actor) getEditableElement()).removeState(ActorState.INVISIBLE);
			}
			this.notifyObservers((IAuthoringActor) this.getEditableElement());
		});
	}

	/**
	 * Returns a List of the options for the ComboBox
	 */
	@Override
	public List<String> getOptionsList() {
		return Arrays.asList(myResources.getString(IS_INVISIBLE_OPTIONS).split(DELIMITER));
	}

	/**
	 * Sets ComboBox's value to true if the Actor is invisible; false otherwise
	 */
	@Override
	protected void updateValueBasedOnEditable() {
		getComboBox().setValue(Boolean.toString(((IAuthoringActor) getEditableElement()).checkState(ActorState.INVISIBLE)));
	}

}
