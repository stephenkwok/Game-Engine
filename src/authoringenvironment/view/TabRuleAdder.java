package authoringenvironment.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import authoringenvironment.controller.LevelEditingEnvironment;
import authoringenvironment.model.IActionCreator;
import authoringenvironment.model.IEditingEnvironment;
import authoringenvironment.model.ITriggerCreator;
import gameengine.controller.Level;
import gameengine.model.IGameElement;
import gameengine.model.Rule;
import gameengine.model.Actions.Action;
import gameengine.model.Triggers.ITrigger;
import gui.view.ComboBoxLevelTriggerAndAction;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TabRuleAdder extends TabParent implements Observer{
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

	public TabRuleAdder(ResourceBundle myResources, String tabText, LevelEditingEnvironment environment, TabLevelRuleEditor ruleEditor) {
		super(myResources, tabText);
		myContainer = new VBox();
		myContainer.setPadding(new Insets(PADDING));
		myLevel = environment.getLevel();
		myLevelEditor = environment;
		myButton = new Button(CREATE_RULE);
		myButton.setOnAction(e -> createAndAddRule());
		init();
		myContainer.getChildren().add(myButton);	
		myRuleEditor = ruleEditor;
	}

	private void init() {
		initTriggerContainer();
		initActionContainer();
		myContainer.getChildren().addAll(myTriggerContainer, myActionContainer);
	} 

	private void initTriggerContainer() {
		myTriggerContainer = new VBox();
		myTriggerComboBox = new ComboBoxLevelTriggerAndAction(getResources().getString(TRIGGERS + LABEL), getResources().getString(TRIGGERS + PROMPT), Arrays.asList(getResources().getString(TRIGGERS).split(DELIMITER)));
		myTriggerComboBox.addObserver(this);
		myTriggerContainer.getChildren().add(myTriggerComboBox.createNode());
	}

	private void initActionContainer() {
		myActionContainer = new VBox();
		myActionComboBox = new ComboBoxLevelTriggerAndAction(getResources().getString(ACTIONS + LABEL), getResources().getString(ACTIONS + PROMPT), Arrays.asList(getResources().getString(ACTIONS).split(DELIMITER)));
		myActionComboBox.addObserver(this);
		myActionContainer.getChildren().add(myActionComboBox.createNode());
	}

	private void createAndAddRule() {
		myTriggerContainer.getChildren().remove(myTriggerCreator);
		myActionContainer.getChildren().remove(myActionCreator);
		ITrigger trigger = ((ITriggerCreator) myTriggerCreator).createTrigger();
		Action action = ((IActionCreator) myActionCreator).createAction();
		myLevel.addRule(new Rule(trigger, action));
		myRuleEditor.updateRules();
	}

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

	@Override
	Node getContent()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return myContainer;
	}
	
	private void displayTriggerParameters(String name) {
		myTriggerCreator = null;
		Class<?> creator;
		try {
			creator = Class.forName(getResources().getString(DIRECTORY) + getResources().getString(name + TRIGGER_CREATOR));
			Constructor<?> constructor;
			if (Arrays.asList(getResources().getString(ATTRIBUTE_BEHAVIORS).split(DELIMITER)).contains(name)) {
				constructor = creator.getConstructor(ResourceBundle.class, IGameElement.class, IEditingEnvironment.class, String.class);
				myTriggerCreator = (VBox) constructor.newInstance(getResources(), myLevel, myLevelEditor, name + LABEL_TEXT);
			} else {
				constructor = creator.getConstructor(ResourceBundle.class, IGameElement.class);
				myTriggerCreator = (VBox) constructor.newInstance(getResources(), myLevel);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myTriggerContainer.getChildren().add(myTriggerCreator);
	}


	private void displayActionParameters(String name) {
		myActionCreator = null;
		Class<?> creator;
		try {
			creator = Class.forName(getResources().getString(DIRECTORY) + getResources().getString(name + ACTION_CREATOR));
			Constructor<?> constructor;
			if (Arrays.asList(getResources().getString(ATTRIBUTE_BEHAVIORS).split(DELIMITER)).contains(name)) {
				constructor = creator.getConstructor(ResourceBundle.class, IGameElement.class, IEditingEnvironment.class, String.class);
				myActionCreator = (VBox) constructor.newInstance(getResources(), myLevel, myLevelEditor, name + LABEL_TEXT);
			} else if (name.equals(CREATE_ACTOR)) {
				constructor = creator.getConstructor(ResourceBundle.class, IGameElement.class, IEditingEnvironment.class);
				myActionCreator = (VBox) constructor.newInstance(getResources(), myLevel, myLevelEditor);
			} else {
				constructor = creator.getConstructor(IGameElement.class);
				myActionCreator = (VBox) constructor.newInstance(myLevel);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myActionContainer.getChildren().add(myActionCreator);
	}


}