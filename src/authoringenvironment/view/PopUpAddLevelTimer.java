package authoringenvironment.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
	private static final String EMPTY = "";
	private static final int TICKS_PER_SECOND = 50;
	private static final String BACKGROUND_COLOR = "-fx-background-color: lightgray";
	private static final int PADDING = 10;
	private ResourceBundle myResources;
	private TextField myInitialMinutes;
	private TextField myInitialSeconds;
	private TextField myTriggerMinutes;
	private TextField myTriggerSeconds;
	private ComboBoxLevelTriggerAndAction myAction;
	private VBox myActionCreator;
	private Level myLevel;
	
	/**
	 * Constructor for a PopUpAddLevelTimer
	 * @param popUpWidth: width of popup.
	 * @param popUpHeight: height of popup.
	 * @param level: level to add timers to.
	 */
	public PopUpAddLevelTimer(int popUpWidth, int popUpHeight, Level level) {
		super(popUpWidth, popUpHeight);
		myResources = ResourceBundle.getBundle(RESOURCES);
		myLevel = level;
		init();
	}
	
	/**
	 * Initialize pop up.
	 */
	private void init() {
		formatContainer();
		addInitialTimeBox();
		addTriggerTimeBox();
		addActionBox();
	}
	
	/**
	 * Format the pop up container.
	 */
	private void formatContainer() {
		getContainer().setAlignment(Pos.CENTER);
		getContainer().setStyle(BACKGROUND_COLOR);
		getContainer().setPadding(new Insets(PADDING));
		getContainer().setSpacing(PADDING);
	}
	
	/**
	 * Add textfields for initial time.
	 */
	private void addInitialTimeBox() {
		myInitialMinutes = new TextField();
		myInitialSeconds = new TextField();
		addTimeBox(INITIAL + PROMPT, myInitialMinutes, myInitialSeconds);
	}
	
	/**
	 * Add textfields for trigger time.
	 */
	private void addTriggerTimeBox() {
		myTriggerMinutes = new TextField();
		myTriggerSeconds = new TextField();
		addTimeBox(TRIGGER + PROMPT, myTriggerMinutes, myTriggerSeconds);
	}
	
	/**
	 * Add vbox for time textfields.
	 * @param labelKey: key for label.
	 * @param minutesTextField: textfield for minutes.
	 * @param secondsTextField: textfield for seconds.
	 */
	private void addTimeBox(String labelKey, TextField minutesTextField, TextField secondsTextField) {
		VBox container = new VBox(PADDING);
		Label label = new Label(myResources.getString(labelKey));
		
		HBox minutesContainer = new HBox(PADDING);
		Label minutesLabel = new Label(MINUTES);
		minutesContainer.getChildren().addAll(minutesLabel, minutesTextField);
		
		HBox secondsContainer = new HBox(PADDING);
		Label secondsLabel = new Label(SECONDS);
		secondsContainer.getChildren().addAll(secondsLabel, secondsTextField);
		
		container.getChildren().addAll(label, minutesContainer, secondsContainer);
		getContainer().getChildren().add(container);
	}
	
	/**
	 * Add a combobox to select action.
	 */
	private void addActionBox() {
		myAction = new ComboBoxLevelTriggerAndAction(myResources.getString(ACTION + PROMPT), ACTION_PROMPT, Arrays.asList(myResources.getString(ACTION + OPTIONS).split(DELIMITER)));
		myAction.addObserver(this);
		getContainer().getChildren().add(myAction.createNode());
	}

	/**
	 * Update to display parameter options and create the level timer.
	 */
	@Override
	public void update(Observable o, Object arg) {
		displayActionParameters((String) arg);
		createLevelTimer();
	}
	
	/**
	 * Convert textfield entered minutes and seconds to ticks.
	 * @param minutesBox: textfield to enter minutes into.
	 * @param secondsBox: textfield to enter seconds into.
	 * @return # of ticks.
	 */
	private int convertToTicks(TextField minutesBox, TextField secondsBox) {
		Integer minutes = getValueFromTextField(minutesBox);
		Integer seconds = getValueFromTextField(secondsBox);
		return (minutes * 60 + seconds) * TICKS_PER_SECOND;
	}
	
	/**
	 * Get a value from a textfield.
	 * @param text: text to interpret.
	 * @return value or 0 if empty.
	 */
	private int getValueFromTextField(TextField text) {
		if (text.getText().equals(EMPTY)) {
			return 0;
		} else {
			return Integer.parseInt(text.getText());
		}
	}
	
	/**
	 * Initialize the time attribute for the level.
	 * @param initialValue: initial time value.
	 */
	private void initializeAttribute(int initialValue) {
		Attribute attribute = new Attribute(AttributeType.TIME, initialValue, myLevel);
		myLevel.addAttribute(attribute);
	}
	
	/**
	 * Create the attribute reached rule for the level.
	 * @param triggerValue: time at which to trigger the rule.
	 */
	private void createAttributeReachedRule(int triggerValue) {
		ITrigger trigger = new AttributeReached(myLevel, AttributeType.TIME, triggerValue);
		Action action = ((ILevelActionCreator) myActionCreator).createAction();
		myLevel.addRule(new Rule(trigger, action));
		this.closePopUp();
	}
	
	/**
	 * Create change attribute rule for the level's time attribute.
	 * @param initialValue: initial time value.
	 * @param triggerValue: time at which to trigger rule.
	 */
	private void createChangeAttributeRule(int initialValue, int triggerValue) {
		int change = determineChange(initialValue, triggerValue);
		ITrigger trigger = new TickTrigger(ONE);
		Action action = new ChangeAttribute(myLevel, AttributeType.TIME, change);
		myLevel.addRule(new Rule(trigger, action));
	}
	
	/**
	 * Create the level timer.
	 */
	private void createLevelTimer() {
		int initialTicks = convertToTicks(myInitialMinutes, myInitialSeconds);
		int triggerTicks = convertToTicks(myTriggerMinutes, myTriggerSeconds);
		initializeAttribute(initialTicks);
		createAttributeReachedRule(triggerTicks);
		createChangeAttributeRule(initialTicks, triggerTicks);
	}
	
	/**
	 * Determine whether to increase or decrease time.
	 * @param initialValue: starting time.
	 * @param triggerValue: trigger time.
	 * @return -1 if initial value is less than trigger; +1 if initial value is more than trigger.
	 */
	private int determineChange(int initialValue, int triggerValue) {
		if (initialValue > triggerValue) {
			return -1;
		} else {
			return 1;
		}
	}
	
	/**
	 * Display the action parameters.
	 * @param name: name of action.
	 */
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
