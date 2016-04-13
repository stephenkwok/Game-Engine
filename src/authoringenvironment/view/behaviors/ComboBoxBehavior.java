package authoringenvironment.view.behaviors;

import java.util.List;
import java.util.ResourceBundle;

import gui.view.ComboBoxTextCell;
import javafx.scene.control.ComboBox;
/**
 * 
 * @author AnnieTang
 *
 */
public abstract class ComboBoxBehavior extends ComboBoxTextCell {
	private static final String LABEL = "Label";
	private static final String PROMPT = "Prompt";
	private String value;
	private String behaviorType;
	
	public ComboBoxBehavior(String behaviorType, ResourceBundle myResources){
		super(myResources, myResources.getString(behaviorType+PROMPT), myResources.getString(behaviorType+LABEL));
		this.behaviorType = behaviorType;
	}

	@Override
	public void setButtonAction() {
		comboButton.setOnAction(event->{
			this.value = comboBox.getValue();
		});
	}

	@Override
	abstract protected List<String> getOptionsList();
	
	
	public String getValue(){
		return value;
	}
	
	public String getBehaviorType(){
		return behaviorType;
	}
	
	@SuppressWarnings("rawtypes")
	public ComboBox getComboBox(){
		return comboBox;
	}

}
