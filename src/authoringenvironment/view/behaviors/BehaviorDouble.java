package authoringenvironment.view.behaviors;

import java.util.ResourceBundle;

import gui.view.TextFieldWithButton;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class BehaviorDouble {
	private static final String LABEL = "Label";
	private static final String PROMPT = "Prompt";
	private static final String WIDTH = "Width";
	private String behaviorType;
	private HBox myHBox;
	private ResourceBundle myResources;
	
	public BehaviorDouble(String behaviorType, ResourceBundle myResources) {
		this.behaviorType = behaviorType;
		this.myResources = myResources; 
		initializeEnvironment();
	}
	
	private void initializeEnvironment(){
		TextFieldWithButton textField = new TextFieldWithButton(myResources.getString(behaviorType+LABEL), 
				myResources.getString(behaviorType+PROMPT), Double.parseDouble(myResources.getString(behaviorType+WIDTH)));
		myHBox = (HBox) textField.createNode();
	}
	
	public Node getNode(){
		return myHBox;
	}
	
	public String getBehvaiorType(){
		return behaviorType;
	}

}
