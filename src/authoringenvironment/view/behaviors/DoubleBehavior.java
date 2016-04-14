package authoringenvironment.view.behaviors;

import java.util.ResourceBundle;

import gui.view.TextFieldWithButton;
import javafx.scene.Node;
/**
 * GUI representation of behaviors that take in a single Double as a parameter
 * @author AnnieTang
 */
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
	public double getQuantity(){
		return quantity;
	}

	@Override
	protected void updateValueBasedOnEditable() {
	}
	@Override
	public Node createNode() {
		// TODO Auto-generated method stub
		return null;
	}
}
