package authoringenvironment.view.behaviors;

import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.ActorRule;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import gameengine.model.IRule;
import gameengine.model.Actions.CreateActor;
import gui.view.CheckBoxObject;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CreateActorBehavior extends SelectActorBehavior {
	private HBox xCoordinate;
	private HBox yCoordinate;
	private static final String CREATE_ACTOR_BUNDLE = "createActorBehavior";
	private ResourceBundle myResources;
	private VBox coordinateOptions;
	private boolean xSpecific;
	private boolean ySpecific;
	private CheckBox xCheck;
	private CheckBox yCheck;

	public CreateActorBehavior(IRule myRule, ActorRule myActorRule, String behaviorType, ResourceBundle myResources,
			IAuthoringActor myActor, List<IAuthoringActor> myActors) {
		super(myRule, myActorRule, behaviorType, myResources, myActor, myActors);
		initializeEnvironment();
		addCoordinates();
	}
	
	private void initializeEnvironment(){
		this.myResources = ResourceBundle.getBundle(CREATE_ACTOR_BUNDLE);
		this.xSpecific = true;
		this.ySpecific = true;
		coordinateOptions = new VBox(Double.parseDouble(myResources.getString("Padding")));
		getHBox().getChildren().add(coordinateOptions);
		xCheck = getCoordinateCheckBox();
		xCheck.setOnAction(event -> updateXCoordinate(xCheck));
		yCheck = getCoordinateCheckBox();
		yCheck.setOnAction(event -> updateYCoordinate(yCheck));
	}
	
	private void addCoordinates(){
		setCoordinateTypes();
		coordinateOptions.getChildren().clear();
		coordinateOptions.getChildren().addAll(xCheck, xCoordinate, yCheck, yCoordinate);
	}
	
	private CheckBox getCoordinateCheckBox(){
		CheckBoxObject checkBoxObject = new CheckBoxObject(myResources.getString("CheckBoxPromptText"), 
				Integer.parseInt(myResources.getString("CheckBoxWidth")));
		CheckBox checkBox = (CheckBox) checkBoxObject.createNode();
		return checkBox;
	}
	
	private void updateYCoordinate(CheckBox checkBox){
		if(checkBox.isSelected()){
			this.ySpecific = false;
		}else{
			this.ySpecific = true;
		}
		addCoordinates();
	}
	
	private void updateXCoordinate(CheckBox checkBox){
		if(checkBox.isSelected()){
			this.xSpecific = false;
		}else{
			this.xSpecific = true;
		}
		addCoordinates();
	}
	
	private void setCoordinateTypes(){
		if(xSpecific){
			this.xCoordinate = getSpecificCoordinate(myResources.getString("X"));
		}
		else {
			this.xCoordinate = getRangeCoordinate(myResources.getString("X"));
		}
		if(ySpecific) {
			this.yCoordinate = getSpecificCoordinate(myResources.getString("Y"));
		}
		else {
			this.yCoordinate = getRangeCoordinate(myResources.getString("Y"));
		}
	}

	private HBox getSpecificCoordinate(String label){
		TextField coordinateField = new TextField();
		coordinateField.setPrefWidth(Integer.parseInt(myResources.getString("FieldWidth")));
		Label coordinateLabel = new Label(label);
		HBox coordinateContainer = new HBox(Integer.parseInt(myResources.getString("Padding")));
		coordinateContainer.getChildren().addAll(coordinateLabel, coordinateField);
		return coordinateContainer;
	}
	
	private HBox getRangeCoordinate(String label){
		TextField min = new TextField(myResources.getString("MIN"));
		min.setPrefWidth(Integer.parseInt(myResources.getString("FieldWidth")));
		TextField max = new TextField(myResources.getString("MAX"));
		max.setPrefWidth(Integer.parseInt(myResources.getString("FieldWidth")));
		Label coordinateLabel = new Label(label);
		HBox coordinateContainer = new HBox(Integer.parseInt(myResources.getString("Padding")));
		coordinateContainer.getChildren().addAll(coordinateLabel, min, max);
		return coordinateContainer;
	}
	
	@Override
	protected void createTriggerOrAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTriggerOrAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isTrigger() {
		return false;
	}

	@Override
	public void updateValueBasedOnEditable() {
		try{
			getComboBox().setValue((IEditableGameElement) (((CreateActor) getMyRule().getMyAction()).getMyActorToCopy()));
			//TODO
			
		}catch(Exception e){
		}
	}

}
