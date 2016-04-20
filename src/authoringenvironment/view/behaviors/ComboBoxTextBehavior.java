package authoringenvironment.view.behaviors;

import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.view.TriggerFactory;
import gui.view.ComboBoxTextCell;
import javafx.scene.control.ComboBox;
/**
 * Parent abstract class for GUI representation of behaviors that take in a single parameter in ComboBox form
 * @author AnnieTang
 */ 
public abstract class ComboBoxTextBehavior extends ComboBoxTextCell {
	private static final String LABEL = "Label";
	private static final String PROMPT = "Prompt";
	private String value;
	private String behaviorType;
	private TriggerFactory triggerFactory;
	private ResourceBundle myResources;
	
	public ComboBoxTextBehavior(String behaviorType, ResourceBundle myResources){
		super(myResources.getString(behaviorType+PROMPT), myResources.getString(behaviorType+LABEL));
		this.behaviorType = behaviorType;
		this.triggerFactory = new TriggerFactory();
		this.myResources = myResources;
	}
	/**
	 * On click, set general field to ComboBox content
	 */
	@Override
	public void setButtonAction() {
		getComboButton().setOnAction(event->{
			this.value = (String) getComboBox().getValue();
			createTriggerOrAction();
		});
	}
	/**
	 * Create ITrigger or IAction depending on type of behavior
	 */
	abstract void createTriggerOrAction();
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
		return this.value;
	}
	
	/**
	 * Return String of behavior type
	 * @return
	 */
	protected String getBehaviorType(){
		return this.behaviorType;
	}
	
	/**
	 * Gets the trigger factory. 
	 * @return factory
	 */
	protected TriggerFactory getTriggerFactory(){
		return this.triggerFactory;
	}
	/**
	 * Get Resource Bundle
	 * @return
	 */
	protected ResourceBundle getResources(){
		return this.myResources;
	}
}
