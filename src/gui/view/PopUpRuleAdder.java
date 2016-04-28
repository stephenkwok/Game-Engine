package gui.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoringenvironment.model.IActionCreator;
import authoringenvironment.model.ITriggerCreator;
import authoringenvironment.view.ActionFactory;
import authoringenvironment.view.AttributeTriggerAndActionCreator;
import authoringenvironment.view.CreateActorActionCreator;
import authoringenvironment.view.KeyTriggerCreator;
import authoringenvironment.view.LevelEditingEnvironment;
import authoringenvironment.view.LoseGameActionCreator;
import authoringenvironment.view.NextLevelActionCreator;
import authoringenvironment.view.TickTriggerCreator;
import authoringenvironment.view.TriggerFactory;
import authoringenvironment.view.WinGameActionCreator;
import gameengine.controller.Level;
import gameengine.model.IAction;
import gameengine.model.IGameElement;
import gameengine.model.Rule;
import gameengine.model.Actions.Action;
import gameengine.model.Triggers.ITrigger;

/**
 * 
 * @author amyzhao
 *
 */
public class PopUpRuleAdder extends PopUpParent implements Observer {
	private static final String RESOURCE = "ruleAdder";
	private static final String TRIGGERS = "Triggers";
	private static final String ACTIONS = "Actions";
	private static final String DELIMITER = ",";
	private static final String LABEL = "Label";
	private static final String PROMPT = "Prompt";
	private static final String CREATE_RULE = "Create rule";
	private VBox myTriggerContainer;
	private ComboBoxLevelTriggerAndAction myTriggerComboBox;
	private ComboBoxLevelTriggerAndAction myActionComboBox;
	private VBox myActionContainer;
	private ResourceBundle myResources;
	private String myTriggerName;
	private String myActionName;
	private VBox myTriggerCreator;
	private VBox myActionCreator;
	private Button myButton;
	private Level myLevel;
	private LevelEditingEnvironment myLevelEditor;

	public PopUpRuleAdder(int popUpWidth, int popUpHeight, LevelEditingEnvironment environment) {
		super(popUpWidth, popUpHeight);
		myResources = ResourceBundle.getBundle(RESOURCE);
		myLevel = environment.getLevel();
		myLevelEditor = environment;
		myButton = new Button(CREATE_RULE);
		myButton.setOnAction(e -> createAndAddRule());
		init();
		getContainer().getChildren().add(myButton);
	}

	private void init() {
		initTriggerContainer();
		initActionContainer();
		getContainer().getChildren().addAll(myTriggerContainer, myActionContainer);
	} 

	private void initTriggerContainer() {
		myTriggerContainer = new VBox();
		myTriggerComboBox = new ComboBoxLevelTriggerAndAction(myResources.getString(TRIGGERS + LABEL), myResources.getString(TRIGGERS + PROMPT), Arrays.asList(myResources.getString(TRIGGERS).split(DELIMITER)));
		myTriggerComboBox.addObserver(this);
		myTriggerContainer.getChildren().add(myTriggerComboBox.createNode());
	}

	private void initActionContainer() {
		myActionContainer = new VBox();
		myActionComboBox = new ComboBoxLevelTriggerAndAction(myResources.getString(ACTIONS + LABEL), myResources.getString(ACTIONS + PROMPT), Arrays.asList(myResources.getString(ACTIONS).split(DELIMITER)));
		myActionComboBox.addObserver(this);
		myActionContainer.getChildren().add(myActionComboBox.createNode());
	}

	private void displayTriggerParameters() {
		myTriggerCreator = null;
		switch (myTriggerName) {
		case "Key":
			myTriggerCreator = new KeyTriggerCreator(myResources, myLevel);
			break;
		case "Tick":
			myTriggerCreator = new TickTriggerCreator(myResources, myLevel);
			break;
		case "Click":
			myTriggerCreator = new ClickTriggerCreator(myResources, myLevel);
			break;
		case "AttributeReached": 
			myTriggerCreator = new AttributeTriggerAndActionCreator(myResources, myLevel, myLevelEditor, "AttributeReachedLabelText");
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
			myActionCreator = new CreateActorActionCreator(myResources, myLevel, myLevelEditor);
			break;
		case "ChangeAttribute":
			myActionCreator = new AttributeTriggerAndActionCreator(myResources, myLevel, myLevelEditor, "ChangeAttributeLabelText");
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
		this.closePopUp();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (Arrays.asList(myResources.getString(TRIGGERS).split(DELIMITER)).contains((String) arg)) {
			myTriggerName = (String) arg;
			displayTriggerParameters();
		} else {
			myActionName = (String) arg;
			displayActionParameters();
		}
	}
}
