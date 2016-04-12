package authoringenvironment.view.behaviors;

import java.util.List;
import java.util.ResourceBundle;

import gui.view.ComboBoxTextCell;

public class ComboBoxBehavior extends ComboBoxTextCell {
	private static final String LABEL = "Label";
	private static final String PROMPT = "Prompt";
	private static final String WIDTH = "Width";
	
	public ComboBoxBehavior(String behaviorType, ResourceBundle myResources){
		super(myResources, myResources.getString(behaviorType+PROMPT), myResources.getString(behaviorType+LABEL));
		comboBox.setPrefWidth(Double.parseDouble(myResources.getString(behaviorType+WIDTH)));
	}

	@Override
	public void setButtonAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getOptionsList() {
		// TODO Auto-generated method stub
		return null;
	}

}
