package authoringenvironment.view.behaviors;

import java.util.ResourceBundle;

import gui.view.TextFieldWithButton;
/**
 * GUI representation of behaviors that take in a single Double as a parameter
 * @author AnnieTang
 */
public abstract class DoubleBehavior extends TextFieldWithButton{
	private static final String LABEL = "Label";
	private static final String PROMPT = "Prompt";
	private static final String WIDTH = "Width";
	private String behaviorType;
	private double value;
	
	public DoubleBehavior(String behaviorType, ResourceBundle myResources) {
		super(myResources.getString(behaviorType+LABEL), 
				myResources.getString(behaviorType+PROMPT), Double.parseDouble(myResources.getString(behaviorType+WIDTH)));
		this.behaviorType = behaviorType;
		setButtonAction(event -> {
			this.value = Double.parseDouble(getTextFieldInput());
			createRuleTriggerOrAction();
		});
	}
	
	abstract void createRuleTriggerOrAction();
	
	/**
	 * Return String of behavior type
	 * @return
	 */
	public String getBehaviorType(){
		return behaviorType;
	}
	/**
	 * Return Double quantity value
	 * @return
	 */
	public double getValue(){
		return value;
	}

	@Override
	protected void updateValueBasedOnEditable(){	
		setTextFieldValue(String.valueOf(value));
	}
}
