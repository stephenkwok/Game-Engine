package gui.view;

import java.util.ResourceBundle;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PopUpAddLevelTimer extends PopUpParent {
	private static final String INITIAL = "initialTime";
	private static final String TRIGGER = "triggerTime";
	private static final String ACTION = "action";
	private static final String PROMPT = "Prompt";
	private static final String OPTIONS = "Options";
	private static final String RESOURCES = "levelTimer";
	private static final String MINUTES = "Minutes:";
	private static final String SECONDS = "Seconds:";
	private ResourceBundle myResources;
	private TextField myInitialMinutes;
	private TextField myInitialSeconds;
	private TextField myTriggerMinutes;
	private TextField myTriggerSeconds;
	private ComboBox myAction;
	
	public PopUpAddLevelTimer(int popUpWidth, int popUpHeight) {
		super(popUpWidth, popUpHeight);
		myResources = ResourceBundle.getBundle(RESOURCES);
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
		
	}

}
