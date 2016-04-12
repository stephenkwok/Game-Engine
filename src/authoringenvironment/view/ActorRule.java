package authoringenvironment.view;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import gui.view.IGUIElement;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Rule container for an actor containing behavior, images, and/or sounds.
 * @author AnnieTang
 *
 */
public class ActorRule {
	private static final String CLOSE = "x";
	private static final int CLOSE_ROW = 0;
	private static final int CLOSE_COL = 2;
	private static final int PADDING = 20;
	private static final String TRIGGERS = "Triggers:";
	private static final String RESULTS = "Results:";
	private static final int DEFAULT_COL = 0;
	private static final int TRIGGERS_LABEL_ROW = 0;
	private static final int TRIGGERS_ROW = 1;
	private static final int RESULTS_LABEL_ROW = 2;
	private static final int RESULTS_ROW = 3;
	private double ruleWidth; 
	private static final double RULE_HEIGHT = 250;
	private static final int CORNER_RADIUS = 20;
	private static final double RULE_PERCENT_WIDTH = 0.90;
	private static final double STACKPANE_PERCENT_WIDTH = 0.92;
	private static final double STACKPANE_PERCENT_HEIGHT = 0.3;
	private GridPane myRule;	
	private VBox triggers;
	private VBox results;
	private ScrollPane trigScroll;
	private ScrollPane resScroll;
	private ActorRuleCreator myActorRuleCreator;
	private ResourceBundle myLibraryResources;
	private static final String LIBRARY_BUNDLE = "library";
	private String triggerBehaviors;
	private static final String PACKAGE = "authoringenvironment.view.behaviors.";
	private static final String CLASS = "Class";
	
	public ActorRule(ActorRuleCreator myActorRuleCreator) {
		this.myActorRuleCreator = myActorRuleCreator;
		this.ruleWidth = myActorRuleCreator.getGridPane().getPrefWidth()*RULE_PERCENT_WIDTH;
		initializeEnvironment();
	}
	
	public GridPane getGridPane() {
		return myRule;
	}
	
	private void initializeEnvironment() {
		this.myLibraryResources = ResourceBundle.getBundle(LIBRARY_BUNDLE);
		this.triggerBehaviors = myLibraryResources.getString("TriggerBehaviors");
		myRule = new GridPane(); 
		myRule.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, new CornerRadii(CORNER_RADIUS), Insets.EMPTY)));
		myRule.setPadding(new Insets(PADDING,PADDING,PADDING,PADDING));
		myRule.setPrefSize(ruleWidth, RULE_HEIGHT);
		addTriggerResultLabels();
		addTriggerResultContainers();
		addCloseButton();
	}
	
	private void addTriggerResultLabels(){
		Label triggerLabel = new Label(TRIGGERS);
		Label resultLabel = new Label(RESULTS);
		myRule.add(triggerLabel, DEFAULT_COL, TRIGGERS_LABEL_ROW);
		myRule.add(resultLabel, DEFAULT_COL, RESULTS_LABEL_ROW);
	}
	
	private void addTriggerResultContainers(){
		triggers = new VBox(PADDING);
		triggers.setPrefSize(myRule.getPrefWidth()*STACKPANE_PERCENT_WIDTH, myRule.getPrefHeight()*STACKPANE_PERCENT_HEIGHT);
		results = new VBox(PADDING);
		results.setPrefSize(myRule.getPrefWidth()*STACKPANE_PERCENT_WIDTH, myRule.getPrefHeight()*STACKPANE_PERCENT_HEIGHT);
		trigScroll = new ScrollPane(triggers);
		resScroll = new ScrollPane(results);
		myRule.add(trigScroll, DEFAULT_COL, TRIGGERS_ROW);
		myRule.add(resScroll, DEFAULT_COL, RESULTS_ROW);
		
	}

	private void addCloseButton(){
		Button close = new Button(CLOSE);
		close.setOnAction(event -> {
			myActorRuleCreator.getRules().remove(myRule);
			myActorRuleCreator.getGridPane().getChildren().remove(myRule);
		});
		myRule.add(close, CLOSE_COL, CLOSE_ROW);
	}
	
	public void addBehavior(Label behavior) {
		Node toAdd = getBehaviorHBox(behavior);
		toAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
    	    @Override
    	    public void handle(MouseEvent click) {
    	        if (click.getClickCount() == 2) {
    	           remove(toAdd);
    	        }
    	    }
    	});
		if(isTrigger(behavior.getText())) triggers.getChildren().add(toAdd);
		else results.getChildren().add(toAdd);
	}

	private boolean isTrigger(String behavior){
		List<String> triggers = Arrays.asList(triggerBehaviors.split(" "));
		return triggers.contains(behavior);
	}
	
	public void addSound(Label sound) {
		//PlaySound or PlayMusic
		results.getChildren().add(sound);
	}

	public void addImage(Label image) {
		//ChangeImage
		results.getChildren().add(image);
	}
	
	public void remove(Node toRemove){
		triggers.getChildren().remove(toRemove);
		results.getChildren().remove(toRemove);
	}
	
	private Node getBehaviorHBox(Label behavior){
		String behaviorType = behavior.getText();
		try{
			String className = PACKAGE + myLibraryResources.getString(behaviorType+CLASS);
			Class<?> clazz = Class.forName(className);
			Constructor<?> constructor = clazz.getConstructor(String.class, ResourceBundle.class);
			return ((IGUIElement) constructor.newInstance(behaviorType,myLibraryResources)).createNode();
		}catch(Exception e1){
			try{
				String className = PACKAGE + myLibraryResources.getString(behaviorType+CLASS);
				Class<?> clazz = Class.forName(className);
				Constructor<?> constructor = clazz.getConstructor(String.class, ResourceBundle.class, Controller.class);
				return ((IGUIElement) constructor.newInstance(behaviorType,myLibraryResources,myActorRuleCreator.getController())).createNode();
			}catch(Exception e2){
				return behavior;
			}
		}
	}
}
