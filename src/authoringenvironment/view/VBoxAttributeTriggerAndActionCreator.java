package authoringenvironment.view;

import java.util.*;

import authoringenvironment.controller.LevelEditingEnvironment;
import authoringenvironment.model.*;
import gameengine.model.AttributeType;
import gameengine.model.IGameElement;
import gameengine.model.Actions.Action;
import gameengine.model.Actions.ChangeAttribute;
import gameengine.model.Triggers.AttributeReached;
import gameengine.model.Triggers.Trigger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * VBox for displaying Attribute Triggers and Actions 
 * @author amyzhao
 *
 */
public class VBoxAttributeTriggerAndActionCreator extends VBox implements ILevelTriggerCreator, ILevelActionCreator {
	private static final String LEVEL = "Level";
	private static final String HEALTH = "Health";
	private static final String POINTS = "Points";
	private static final String TIME = "Time";
	private static final String DELIMITER = ",";
	private ComboBox myElementComboBox;
	private ComboBox myTypeComboBox;
	private TextField myValue;
	private IGameElement myElement;
	private ResourceBundle myResources;
	private IEditingEnvironment myEditingEnvironment;
	private Set<IAuthoringActor> myActors;
	
	/**
	 * Constructor for VBoxAttributeTriggerAndActionCreator
	 * @param resources: resource bundle to use.
	 * @param element: element to add trigger or action to.
	 * @param editor: editing environment currently in use.
	 * @param labelKey: key for label in resource bundle.
	 */
	public VBoxAttributeTriggerAndActionCreator(ResourceBundle resources, IGameElement element, IEditingEnvironment editor, String labelKey) {
		myElement = element;
		myResources = resources;
		myEditingEnvironment = editor;
		init(labelKey);
	}

	/**
	 * Initialize vbox with label.
	 * @param labelKey: key for label in resourcebundle.
	 */
	private void init(String labelKey) {
		String[] labelText = myResources.getString(labelKey).split(DELIMITER);
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
	
	/**
	 * Make the combobox for selecting the actor or level.
	 * @param label: label to add to combo box.
	 */
	private void makeElementComboBox(Label label) {
		HBox container = new HBox();
		myElementComboBox = new ComboBox<>(getElementNames());
		container.getChildren().addAll(label, myElementComboBox);
		this.getChildren().add(container);
	}
	
	/**
	 * Make the combobox for selecting an attribute type.
	 * @param label: label to add to combo box.
	 */
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
	
	/**
	 * Get the game element to add the action or trigger to.
	 * @return: game element to use.
	 */
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
	
	/**
	 * Make a textfield for a user to enter a trigger value into.
	 * @param label: label for text field.
	 */
	private void makeValueTextField(Label label) {
		HBox container = new HBox();
		myValue = new TextField();
		container.getChildren().addAll(label, myValue);
		this.getChildren().add(container);
	}
	
	/**
	 * Get the list of available game elements.
	 * @return: list of available game element names.
	 */
	private ObservableList<String> getElementNames() {
		List<String> names = new ArrayList<>();
		for (IAuthoringActor actor: myActors) {
			names.add(actor.getName());
		}
		names.add(myElement.getName());
		return FXCollections.observableArrayList(names);
	}
	
	/**
	 * Gets the attribute type to use.
	 * @return attribute type.
	 */
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
	
	/**
	 * Creates the trigger.
	 */
	@Override
	public Trigger createTrigger() {
		return new AttributeReached(getElementByName(), getAttributeType(), Integer.parseInt(myValue.getText()));
	}

	/**
	 * Creates the action.
	 */
	@Override
	public Action createAction() {
		return new ChangeAttribute(getElementByName(), getAttributeType(), Integer.parseInt(myValue.getText()));
	}

}
