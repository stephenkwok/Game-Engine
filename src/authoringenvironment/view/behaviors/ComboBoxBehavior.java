package authoringenvironment.view.behaviors;

import java.util.List;
import java.util.ResourceBundle;

import gui.view.ComboBoxTextCell;
import javafx.scene.control.ComboBox;
/**
 * Parent abstract class for GUI representation of behaviors that take in a single parameter in ComboBox form
 * @author AnnieTang
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
	/**
	 * On click, set general field to ComboBox content
	 */
	@Override
	public void setButtonAction() {
		getComboButton().setOnAction(event->{
			this.value = (String) getComboBox().getValue();
		});
	}
	/**
	 * Return list of elements in ComboBox
	 */
	@Override
	abstract protected List<String> getOptionsList();
	
	/**
	 * Return general field String
	 * @return
	 */
	public String getValue(){
		return value;
	}
	
	/**
	 * Return String of behavior type
	 * @return
	 */
	public String getBehaviorType(){
		return behaviorType;
	}
	/**
	 * Return ComboBox
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ComboBox getComboBox(){
		return getComboBox();
	}

}
