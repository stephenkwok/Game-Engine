package gui.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


import authoringenvironment.model.IAuthoringActor;
import gameengine.model.ActorState;


public class ComboBoxIsPlayer extends ComboBoxTextCell {
	private static final String IS_PLAYER_OPTIONS = "IsPlayerOptions";
	private static final String DELIMITER = ",";
	private ResourceBundle myResources;

	public ComboBoxIsPlayer(ResourceBundle myResources, String promptText, String labelText) {
		super(promptText, labelText);
		this.myResources = myResources;
	}

	@Override
	public void setButtonAction() {
		getComboButton().setOnAction(event -> {
			if(Boolean.parseBoolean(getComboBox().getValue())){
				((IAuthoringActor) getEditableElement()).addState(ActorState.MAIN);
			}
		});
	}

	@Override
	public List<String> getOptionsList() {
		return Arrays.asList(myResources.getString(IS_PLAYER_OPTIONS).split(DELIMITER));
	}

	@Override
	protected void updateValueBasedOnEditable() {
		getComboBox().setValue(Boolean.toString(((IAuthoringActor) getEditableElement()).checkState(ActorState.MAIN)));
	}

}
