package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import authoringenvironment.controller.LevelEditingEnvironment;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.model.Actor;
import gameengine.model.AttributeType;
import gameengine.model.IGameElement;
import gameengine.model.Actions.Action;
import gameengine.model.Actions.ChangeAttribute;
import gameengine.model.Triggers.AttributeReached;
import gameengine.model.Triggers.ITrigger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AttributeTriggerAndActionCreator extends VBox implements ILevelTriggerCreator, ILevelActionCreator {
	private static final String LEVEL = "Level";
	private static final String HEALTH = "Health";
	private static final String POINTS = "Points";
	private static final String TIME = "Time";
	private ComboBox myElementComboBox;
	private ComboBox myTypeComboBox;
	private TextField myValue;
	private IGameElement myElement;
	private ResourceBundle myResources;
	private IEditingEnvironment myEditingEnvironment;
	private Set<IAuthoringActor> myActors;
	
	
	public AttributeTriggerAndActionCreator(ResourceBundle resources, IGameElement element, IEditingEnvironment editor, String labelKey) {
		myElement = element;
		myResources = resources;
		myEditingEnvironment = editor;
		init(labelKey);
	}

	private void init(String labelKey) {
		String[] labelText = myResources.getString(labelKey).split(",");
		Label[] labels = new Label[labelText.length];
		for (int i = 0; i < labelText.length; i++) {
			labels[i] = new Label(labelText[i]);
		}
		if (myElement.getClass().getSimpleName().equals(LEVEL)) {
			myActors = ((LevelEditingEnvironment) myEditingEnvironment).getAvailableActors();
		}
		makeElementComboBox(labels[0]);
		makeTypeComboBox(labels[1]);
		makeValueTextField(labels[2]);
	}
	
	private void makeElementComboBox(Label label) {
		HBox container = new HBox();
		myElementComboBox = new ComboBox<>(getElementNames());
		container.getChildren().addAll(label, myElementComboBox);
		this.getChildren().add(container);
	}
	
	private void makeTypeComboBox(Label label) {
		HBox container = new HBox();
		List<String> options = new ArrayList<>();
		options.add(HEALTH);
		options.add(POINTS);
		options.add(TIME);
		myTypeComboBox = new ComboBox<>(FXCollections.observableArrayList(options));
		container.getChildren().addAll(label, myTypeComboBox);
		this.getChildren().add(container);
	}
	
	private IGameElement getElementByName() {
		if (myElement.getName().equals(myElementComboBox.getValue())) {
			return myElement;
		}
		for (IAuthoringActor actor: myActors) {
			if (actor.getName().equals(myElementComboBox.getValue())) {
				return (IGameElement) actor;
			}
		}
		return null;
	}
	private void makeValueTextField(Label label) {
		HBox container = new HBox();
		myValue = new TextField();
		container.getChildren().addAll(label, myValue);
		this.getChildren().add(container);
	}
	
	private ObservableList<String> getElementNames() {
		List<String> names = new ArrayList<>();
		for (IAuthoringActor actor: myActors) {
			names.add(actor.getName());
		}
		names.add(myElement.getName());
		return FXCollections.observableArrayList(names);
	}
	
	private AttributeType getAttributeType() {
		AttributeType type = null;
		switch ((String) myTypeComboBox.getValue()) {
		case HEALTH:
			type = AttributeType.HEALTH;
			break;
		case POINTS:
			type = AttributeType.POINTS;
			break;
		case TIME:
			type = AttributeType.TIME;
			break;
		}
		return type;
	}
	
	@Override
	public ITrigger createTrigger() {
		return new AttributeReached(getElementByName(), getAttributeType(), Integer.parseInt(myValue.getText()));
	}

	@Override
	public Action createAction() {
		return new ChangeAttribute(getElementByName(), getAttributeType(), Integer.parseInt(myValue.getText()));
	}

}
