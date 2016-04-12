package gui.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import gameengine.controller.Level;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ComboBoxLosingConditions extends ComboBoxTextCell {
	private static final String LOSING_CONDITIONS_OPTIONS = "LosingConditionsOptions";
	private static final String DELIMITER = ",";
	private List<String> myOptions;
	
	public ComboBoxLosingConditions(ResourceBundle myResources, String promptText, String labelText) {
		super(myResources, promptText, labelText);
		myOptions = Arrays.asList(myResources.getString(LOSING_CONDITIONS_OPTIONS).split(DELIMITER));
	}

	@Override
	public void setButtonAction() {
		comboButton.setOnAction(event->{
			((Level) getEditableElement()).setLosingCondition(comboBox.getValue());
		});
	}

	@Override
	public List<String> getOptionsList() {
		return myOptions;
	}

	@Override
	protected void updateValueBasedOnEditable() {
		if (((Level) getEditableElement()).getLosingCondition() != null) {
			comboBox.setValue(((Level) getEditableElement()).getLosingCondition());
		}
	}

}
