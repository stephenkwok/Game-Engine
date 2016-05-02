package authoringenvironment.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import authoringenvironment.controller.LevelEditingEnvironment;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.controller.Level;
import gameengine.model.IGameElement;
import gameengine.model.Rule;
import gameengine.model.Actions.Action;
import gameengine.model.Triggers.ITrigger;
import gui.view.ComboBoxLevelTriggerAndAction;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * Tab to add rules to a level.
 * @author amyzhao
 *
 */
public class TabRuleAdder extends TabParent implements Observer {
	private VBox myContainer;
	private static final String TRIGGERS = "Triggers";
	private static final String ACTIONS = "Actions";
	private static final String DELIMITER = ",";
	private static final String LABEL = "Label";
	private static final String PROMPT = "Prompt";
	private static final String CREATE_RULE = "Create rule";
	private static final int PADDING = 10;
	private static final String TRIGGER_CREATOR = "TriggerCreator";
	private static final String ACTION_CREATOR = "ActionCreator";
	private static final String LABEL_TEXT = "LabelText";
	private static final String DIRECTORY = "Directory";
	private static final String ATTRIBUTE_BEHAVIORS = "AttributeBehaviors";
	private static final Object CREATE_ACTOR = "CreateActor";
	private static final String NEEDS_RESOURCE = "NeedsResource";
	private static final String STANDARD_ACTION = "StandardAction";

	private VBox myTriggerContainer;
	private ComboBoxLevelTriggerAndAction myTriggerComboBox;
	private ComboBoxLevelTriggerAndAction myActionComboBox;
	private VBox myActionContainer;
	private String myTriggerName;
	private String myActionName;
	private VBox myTriggerCreator;
	private VBox myActionCreator;
	private Button myButton;
	private Level myLevel;
	private LevelEditingEnvironment myLevelEditor;
	private TabLevelRuleEditor myRuleEditor;
	private AlertGenerator myAlertGenerator;

	/**
	 * Constructor for TabRuleAdder.
	 * @param myResources: resource bundle to use.
	 * @param tabText: title of tab.
	 * @param environment: level editing environment.
	 * @param ruleEditor: rule editor that allows for deletion.
	 */
	public TabRuleAdder(ResourceBundle myResources, String tabText, LevelEditingEnvironment environment,
			TabLevelRuleEditor ruleEditor) {
		super(myResources, tabText);
		myContainer = new VBox(PADDING);
		myContainer.setPadding(new Insets(PADDING));
		myContainer.setAlignment(Pos.CENTER);
		myLevel = environment.getLevel();
		myLevelEditor = environment;
		myButton = new Button(CREATE_RULE);
		myButton.setOnAction(e -> createAndAddRule());
		init();
		myContainer.getChildren().add(myButton);
		myRuleEditor = ruleEditor;
		myAlertGenerator = new AlertGenerator();
	}

	/**
	 * Initialize containers.
	 */
	private void init() {
		initTriggerContainer();
		initActionContainer();
		myContainer.getChildren().addAll(myTriggerContainer, myActionContainer);
	}

	/**
	 * Initialize trigger containers.
	 */
	private void initTriggerContainer() {
		myTriggerContainer = new VBox(PADDING);
		myTriggerComboBox = new ComboBoxLevelTriggerAndAction(getResources().getString(TRIGGERS + LABEL),
				getResources().getString(TRIGGERS + PROMPT),
				Arrays.asList(getResources().getString(TRIGGERS).split(DELIMITER)));
		myTriggerComboBox.addObserver(this);
		myTriggerContainer.getChildren().add(myTriggerComboBox.createNode());
	}

	/**
	 * Initialize action containers.
	 */
	private void initActionContainer() {
		myActionContainer = new VBox(PADDING);
		myActionComboBox = new ComboBoxLevelTriggerAndAction(getResources().getString(ACTIONS + LABEL),
				getResources().getString(ACTIONS + PROMPT),
				Arrays.asList(getResources().getString(ACTIONS).split(DELIMITER)));
		myActionComboBox.addObserver(this);
		myActionContainer.getChildren().add(myActionComboBox.createNode());
	}

	/**
	 * Create and add rules that the user's selected.
	 */
	private void createAndAddRule() {
		myTriggerContainer.getChildren().remove(myTriggerCreator);
		myActionContainer.getChildren().remove(myActionCreator);
		ITrigger trigger = ((ILevelTriggerCreator) myTriggerCreator).createTrigger();
		Action action = ((ILevelActionCreator) myActionCreator).createAction();
		Rule rule = new Rule(trigger, action);
		myLevelEditor.addRuleToLevel(rule);
		myRuleEditor.updateRules();
	}

	/**
	 * Update parameter options based on selected trigger or action.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (Arrays.asList(getResources().getString(TRIGGERS).split(DELIMITER)).contains((String) arg)) {
			myTriggerName = (String) arg;
			displayTriggerParameters(myTriggerName);
		} else {
			myActionName = (String) arg;
			displayActionParameters(myActionName);
		}
	}

	/**
	 * Returns the container.
	 */
	@Override
	Node getContent()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return myContainer;
	}

	/**
	 * Display parameters based on trigger.
	 * @param name: trigger name.
	 */
	private void displayTriggerParameters(String name) {
		myTriggerCreator = displayParameters(name, name + TRIGGER_CREATOR);
		myTriggerContainer.getChildren().add(myTriggerCreator);
	}

	/**
	 * Display parameters based on action.
	 * @param name: action name.
	 */
	private void displayActionParameters(String name) {
		myActionCreator = displayParameters(name, name + ACTION_CREATOR);
		myActionContainer.getChildren().add(myActionCreator);
	}
	
	/**
	 * Display parameters for action or trigger.
	 * @param name: trigger or action name.
	 * @param className: class that corresponds to its creator.
	 * @return vbox displaying parameters.
	 */
	private VBox displayParameters(String name, String className) {
		Class<?> creator;
		try {
			creator = Class.forName(getResources().getString(DIRECTORY) + getResources().getString(className));
			Constructor<?> constructor;
			if (Arrays.asList(getResources().getString(ATTRIBUTE_BEHAVIORS).split(DELIMITER)).contains(name)) {
				constructor = creator.getConstructor(ResourceBundle.class, IGameElement.class,
						IEditingEnvironment.class, String.class);
				return (VBox) constructor.newInstance(getResources(), myLevel, myLevelEditor, name + LABEL_TEXT);
			} else if (name.equals(CREATE_ACTOR)) {
				constructor = creator.getConstructor(ResourceBundle.class, IGameElement.class,
						IEditingEnvironment.class);
				return (VBox) constructor.newInstance(getResources(), myLevel, myLevelEditor);
			} else if ((Arrays.asList(getResources().getString(NEEDS_RESOURCE).split(DELIMITER)).contains(name))) {
				constructor = creator.getConstructor(ResourceBundle.class, IGameElement.class);
				return (VBox) constructor.newInstance(getResources(), myLevel);
			} else if ((Arrays.asList(getResources().getString(STANDARD_ACTION).split(DELIMITER)).contains(name))) {
				constructor = creator.getConstructor(IGameElement.class);
				return (VBox) constructor.newInstance(myLevel);
			}
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			myAlertGenerator.generateAlert(e.getClass().toString());
		} 
		return null;
	}

}