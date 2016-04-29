package authoringenvironment.view;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import authoringenvironment.model.IActionCreator;
import authoringenvironment.model.ITriggerCreator;
import gameengine.controller.Level;
import gameengine.model.Rule;
import gameengine.model.Actions.Action;
import gameengine.model.Triggers.ITrigger;
import gui.view.ClickTriggerCreator;
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

	private void displayTriggerParameters() {
		myTriggerCreator = null;
		switch (myTriggerName) {
		case "Key":
			myTriggerCreator = new KeyTriggerCreator(getResources(), myLevel);
			break;
		case "Tick":
			myTriggerCreator = new TickTriggerCreator(getResources(), myLevel);
			break;
		case "Click":
			myTriggerCreator = new ClickTriggerCreator(getResources(), myLevel);
			break;
		case "AttributeReached": 
			myTriggerCreator = new AttributeTriggerAndActionCreator(getResources(), myLevel, myLevelEditor, "AttributeReachedLabelText");
			break;
		}
		myTriggerContainer.getChildren().add(myTriggerCreator);
	}

	private void displayActionParameters() {
		myActionCreator = null;
		switch (myActionName) {
		case "WinGame":
			myActionCreator = new WinGameActionCreator(myLevel);
			break;
		case "LoseGame":
			myActionCreator = new LoseGameActionCreator(myLevel);
			break;
		case "CreateActor":
			myActionCreator = new CreateActorActionCreator(getResources(), myLevel, myLevelEditor);
			break;
		case "ChangeAttribute":
			myActionCreator = new AttributeTriggerAndActionCreator(getResources(), myLevel, myLevelEditor, "ChangeAttributeLabelText");
			break;
		case "NextLevel":
			myActionCreator = new NextLevelActionCreator(myLevel);
			break;
		}
		myActionContainer.getChildren().add(myActionCreator);
	}

	private void createAndAddRule() {
		ITrigger trigger = ((ITriggerCreator) myTriggerCreator).createTrigger();
		Action action = ((IActionCreator) myActionCreator).createAction();
		myLevel.addRule(new Rule(trigger, action));
		myRuleEditor.updateRules();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (Arrays.asList(getResources().getString(TRIGGERS).split(DELIMITER)).contains((String) arg)) {
			myTriggerName = (String) arg;
			displayTriggerParameters();
		} else {
			myActionName = (String) arg;
			displayActionParameters();
		}
	}
	
	@Override
	Node getContent()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		return myContainer;
	}

}