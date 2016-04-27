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

import authoringenvironment.view.ActionFactory;
import authoringenvironment.view.KeyTriggerCreator;
import authoringenvironment.view.TickTriggerCreator;
import authoringenvironment.view.TriggerFactory;

/**
 * 
 * @author amyzhao
 *
 */
public class PopUpRuleAdder extends PopUpParent implements Observer {
	private static final String RESOURCE = "ruleAdder";
	private static final String CLASS = "Class";
	private static final String PARAMS = "Params";
	private static final String TRIGGERS = "Triggers";
	private static final String ACTIONS = "Actions";
	private static final String DELIMITER = ",";
	private static final String LABEL = "Label";
	private static final String PROMPT = "Prompt";
	private static final String TRIGGER_DIRECTORY = "gameengine.model.Triggerss";
	private static final String ACTION_DIRECTORY = "gameengine.model.Actions";
	private VBox myTriggerContainer;
	private ComboBoxLevelTriggerAndAction myTriggerComboBox;
	private ComboBoxLevelTriggerAndAction myActionComboBox;
	private VBox myActionContainer;
	private ResourceBundle myResources;
	private String myTriggerName;
	private String myActionName;
	private ActionFactory myActionFactory;
	private TriggerFactory myTriggerFactory;
	private List<Object> myActionParams;
	private List<Object> myTriggerParams;
	private List<Button> myActionParamButtons;
	private List<Button> myTriggerParamButtons;

	public PopUpRuleAdder(int popUpWidth, int popUpHeight) {
		super(popUpWidth, popUpHeight);
		myResources = ResourceBundle.getBundle(RESOURCE);
		myActionFactory = new ActionFactory();
		myTriggerFactory = new TriggerFactory();
		myActionParams = new ArrayList<>();
		myTriggerParams = new ArrayList<>();
		myActionParamButtons = new ArrayList<>();
		myTriggerParamButtons = new ArrayList<>();
		init();
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
		VBox toAdd = null;
		switch (myTriggerName) {
		case "Key":
			toAdd = new KeyTriggerCreator(myResources);
			break;
		case "Tick":
			toAdd = new TickTriggerCreator(myResources);
			break;
		case "Click":
			toAdd = new ClickTriggerCreator(myResources);
			break;
		}
		myTriggerContainer.getChildren().add(toAdd);
	}

	private void displayActionParameters() {
		switch (myActionName) {

		}
		//myActionContainer.getChildren().add(e);
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
