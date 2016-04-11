package gui.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import gameengine.model.Actor;

public class ComboBoxIsPlayer extends ComboBoxTextCell {
	private static final String IS_PLAYER_OPTIONS = "IsPlayerOptions";
	private static final String DELIMITER = ",";
	private ResourceBundle myResources;
	
	public ComboBoxIsPlayer(ResourceBundle myResources, String promptText, String labelText) {
		super(myResources, promptText, labelText);
		this.myResources = myResources;
	}

	@Override
	public void setButtonAction() {
		comboButton.setOnAction(event->{
			((Actor) getEditableElement()).setMain(Boolean.parseBoolean(comboBox.getValue()));
		});
	}

	@Override
	public List<String> getOptionsList() {
		return Arrays.asList(myResources.getString(IS_PLAYER_OPTIONS).split(DELIMITER));
	}

}
