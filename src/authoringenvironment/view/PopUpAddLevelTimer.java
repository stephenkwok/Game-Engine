package authoringenvironment.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import authoringenvironment.model.IActionCreator;
import gameengine.controller.Level;
import gameengine.model.Attribute;
import gameengine.model.AttributeType;
import gameengine.model.IGameElement;
import gameengine.model.Rule;
import gameengine.model.Actions.Action;
import gameengine.model.Actions.ChangeAttribute;
import gameengine.model.Triggers.AttributeReached;
import gameengine.model.Triggers.ITrigger;
import gameengine.model.Triggers.TickTrigger;
import gui.view.ComboBoxLevelTriggerAndAction;
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
	private static final String DIRECTORY = "Directory";
	private static final int ONE = 1;
	private static final int TICKS_PER_SECOND = 50;
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
		myInitialMinutes = new TextField();
		myInitialSeconds = new TextField();
		addTimeBox(INITIAL + PROMPT, myInitialMinutes, myInitialSeconds);
	}
	
	private void addTriggerTimeBox() {
		myTriggerMinutes = new TextField();
		myTriggerSeconds = new TextField();
		addTimeBox(TRIGGER + PROMPT, myTriggerMinutes, myTriggerSeconds);
	}
	
	private void addTimeBox(String labelKey, TextField minutesTextField, TextField secondsTextField) {
		VBox container = new VBox();
		Label label = new Label(myResources.getString(labelKey));
		
		HBox minutesContainer = new HBox();
		Label minutesLabel = new Label(MINUTES);
		minutesContainer.getChildren().addAll(minutesLabel, minutesTextField);
		
		HBox secondsContainer = new HBox();
		Label secondsLabel = new Label(SECONDS);
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
	
	private int convertToTicks(TextField minutesBox, TextField secondsBox) {
		Integer minutes = Integer.parseInt(minutesBox.getText());
		Integer seconds = Integer.parseInt(secondsBox.getText());
		return minutes * 60 + seconds;
	}
	
	private void initializeAttribute(int initialValue) {
		Attribute attribute = new Attribute(AttributeType.TIME, initialValue, myLevel);
		myLevel.addAttribute(attribute);
	}
	
	private void createAttributeReachedRule(int triggerValue) {
		ITrigger trigger = new AttributeReached(myLevel, AttributeType.TIME, triggerValue);
		Action action = ((IActionCreator) myActionCreator).createAction();
		myLevel.addRule(new Rule(trigger, action));
		this.closePopUp();
	}
	
	private void createChangeAttributeRule(int initialValue, int triggerValue) {
		int change = determineChange(initialValue, triggerValue);
		ITrigger trigger = new TickTrigger(ONE);
		Action action = new ChangeAttribute(myLevel, AttributeType.TIME, change);
		myLevel.addRule(new Rule(trigger, action));
	}
	
	private void createLevelTimer() {
		int initialTicks = convertToTicks(myInitialMinutes, myInitialSeconds);
		int triggerTicks = convertToTicks(myTriggerMinutes, myTriggerSeconds);
		initializeAttribute(initialTicks);
		createAttributeReachedRule(triggerTicks);
		createChangeAttributeRule(initialTicks, triggerTicks);
	}
	
	private int determineChange(int initialValue, int triggerValue) {
		if (initialValue > triggerValue) {
			return -TICKS_PER_SECOND;
		} else {
			return TICKS_PER_SECOND;
		}
	}
	
	private void displayActionParameters(String name) {
		myActionCreator = null;
		Class<?> creator;
		try {
			creator = Class.forName(myResources.getString(DIRECTORY) + myResources.getString(name));
			Constructor<?> constructor = creator.getConstructor(IGameElement.class);
			myActionCreator = (VBox) constructor.newInstance(myLevel);
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
	}
	
}
