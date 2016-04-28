package gui.view;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import authoringenvironment.model.IActionCreator;
import authoringenvironment.view.AttributeTriggerAndActionCreator;
import authoringenvironment.view.CreateActorActionCreator;
import authoringenvironment.view.LoseGameActionCreator;
import authoringenvironment.view.NextLevelActionCreator;
import authoringenvironment.view.WinGameActionCreator;
import gameengine.controller.Level;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PopUpAddLevelTimer extends PopUpParent implements Observer {
	private static final String INITIAL = "initialTime";
	private static final String TRIGGER = "triggerTime";
	private static final String ACTION = "action";
	private static final String PROMPT = "Prompt";
	private static final String OPTIONS = "Options";
	private static final String RESOURCES = "levelTimer";
	private static final String MINUTES = "Minutes:";
	private static final String SECONDS = "Seconds:";
	private static final String ACTION_PROMPT = "Select";
	private static final String DELIMITER = ",";
	private static final String DONE = "Done";
	private ResourceBundle myResources;
	private TextField myInitialMinutes;
	private TextField myInitialSeconds;
	private TextField myTriggerMinutes;
	private TextField myTriggerSeconds;
	private ComboBoxLevelTriggerAndAction myAction;
	private VBox myActionCreator;
	private Level myLevel;
	private Button myButton;
	
	public PopUpAddLevelTimer(int popUpWidth, int popUpHeight, Level level) {
		super(popUpWidth, popUpHeight);
		myResources = ResourceBundle.getBundle(RESOURCES);
		myLevel = level;
		init();
	}
	
	private void init() {
		addInitialTimeBox();
		addTriggerTimeBox();
		addActionBox();
	}
	
	private void addInitialTimeBox() {
		addTimeBox(INITIAL + PROMPT, myInitialMinutes, myInitialSeconds);
	}
	
	private void addTriggerTimeBox() {
		addTimeBox(TRIGGER + PROMPT, myTriggerMinutes, myTriggerSeconds);
	}
	
	private void addTimeBox(String labelKey, TextField minutesTextField, TextField secondsTextField) {
		VBox container = new VBox();
		Label label = new Label(myResources.getString(labelKey));
		
		HBox minutesContainer = new HBox();
		Label minutesLabel = new Label(MINUTES);
		minutesTextField = new TextField();
		minutesContainer.getChildren().addAll(minutesLabel, minutesTextField);
		
		HBox secondsContainer = new HBox();
		Label secondsLabel = new Label(SECONDS);
		secondsTextField = new TextField();
		secondsContainer.getChildren().addAll(secondsLabel, secondsTextField);
		
		container.getChildren().addAll(label, minutesContainer, secondsContainer);
		getContainer().getChildren().add(container);
	}
	
	private void addActionBox() {
		myAction = new ComboBoxLevelTriggerAndAction(myResources.getString(ACTION + PROMPT), ACTION_PROMPT, Arrays.asList(myResources.getString(ACTION + OPTIONS).split(DELIMITER)));
		myAction.addObserver(this);
		getContainer().getChildren().add(myAction.createNode());
	}

	@Override
	public void update(Observable o, Object arg) {
		displayActionParameters((String) arg);
		createLevelTimer();
	}

	private void displayActionParameters(String name) {
		myActionCreator = null;
		switch (name) {
		case "WinGame":
			myActionCreator = new WinGameActionCreator(myLevel);
			break;
		case "LoseGame":
			myActionCreator = new LoseGameActionCreator(myLevel);
			break;
		case "NextLevel":
			myActionCreator = new NextLevelActionCreator(myLevel);
			break;
		}
	}
	
	private void createLevelTimer() {
		
	}
}
