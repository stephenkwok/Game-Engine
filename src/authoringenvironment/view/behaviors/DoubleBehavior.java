package authoringenvironment.view.behaviors;

import java.util.ResourceBundle;

import gui.view.TextFieldWithButton;

public class DoubleBehavior extends TextFieldWithButton{
	private static final String LABEL = "Label";
	private static final String PROMPT = "Prompt";
	private static final String WIDTH = "Width";
	private String behaviorType;
	private double quantity;
	
	public DoubleBehavior(String behaviorType, ResourceBundle myResources) {
		super(myResources.getString(behaviorType+LABEL), 
				myResources.getString(behaviorType+PROMPT), Double.parseDouble(myResources.getString(behaviorType+WIDTH)));
		this.behaviorType = behaviorType;
		setButtonAction(event -> {
			this.quantity = Double.parseDouble(getTextFieldInput());
		});
	}
	
	public String getBehaviorType(){
		return behaviorType;
	}
	
	public double getQuantity(){
		return quantity;
	}
}
