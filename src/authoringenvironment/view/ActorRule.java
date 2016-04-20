package authoringenvironment.view;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import gameengine.controller.Level;

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
	private static final String ACTIONS = "Actions:";
	private static final int DEFAULT_COL = 0;
	private static final int TRIGGERS_LABEL_ROW = 0;
	private static final int TRIGGERS_ROW = 1;
	private static final int ACTIONS_LABEL_ROW = 2;
	private static final int ACTIONS_ROW = 3;
	private static final double RULE_HEIGHT = 250;
	private static final int CORNER_RADIUS = 20;
	private static final double STACKPANE_PERCENT_WIDTH = 0.90;
	private static final double STACKPANE_PERCENT_HEIGHT = 0.3;
	private static final String CHANGE_IMAGE = "ChangeImage";
	private static final String PLAY_SOUND = "PlaySound";
	private static final String PLAY_MUSIC = "PlayMusic";
	private GridPane myRule;	
	private VBox triggers;
	private VBox actions;
	private ScrollPane trigScroll;
	private ScrollPane actScroll;
	private ActorRuleCreator myActorRuleCreator;
	private ResourceBundle myLibraryResources;
	private static final String LIBRARY_BUNDLE = "library";
	private String triggerBehaviors;
	private ActorRuleFactory actorRuleFactory;
	
	public ActorRule(ActorRuleCreator myActorRuleCreator, List<IAuthoringActor> myActors, List<Level> myLevels) {
		this.myActorRuleCreator = myActorRuleCreator;
		this.myLibraryResources = ResourceBundle.getBundle(LIBRARY_BUNDLE);
		this.actorRuleFactory = new ActorRuleFactory(myLibraryResources, myActorRuleCreator.getActor(), myActors, myLevels);
		this.triggerBehaviors = myLibraryResources.getString("TriggerBehaviors");
		initializeEnvironment();
	}
	
	/**
	 * Returns new rule container
	 * @return
	 */
	public GridPane getGridPane() {
		return myRule;
	}
	
	/**
	 * Create rule container and set preferences 
	 */
	private void initializeEnvironment() {
		myRule = new GridPane(); 
		myRule.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, new CornerRadii(CORNER_RADIUS), Insets.EMPTY)));
		myRule.setPadding(new Insets(PADDING,PADDING,PADDING,PADDING));
		myRule.setPrefSize(myActorRuleCreator.getGridPane().getWidth(), RULE_HEIGHT);
		addTriggerActionLabels();
		addTriggerActionContainers();
		addCloseButton();
	}
	
	/**
	 * Add labels for each section of rule container (trigger and action)
	 */
	private void addTriggerActionLabels(){
		Label triggerLabel = new Label(TRIGGERS);
		Label actionLabel = new Label(ACTIONS);
		myRule.add(triggerLabel, DEFAULT_COL, TRIGGERS_LABEL_ROW);
		myRule.add(actionLabel, DEFAULT_COL, ACTIONS_LABEL_ROW);
	}
	/**
	 * Add containers for triggers and actions where user can drop behaviors.
	 */
	private void addTriggerActionContainers(){
		triggers = new VBox(PADDING);
		triggers.setPrefSize(myRule.getPrefWidth()*STACKPANE_PERCENT_WIDTH, myRule.getPrefHeight()*STACKPANE_PERCENT_HEIGHT);
		actions = new VBox(PADDING);
		actions.setPrefSize(myRule.getPrefWidth()*STACKPANE_PERCENT_WIDTH, myRule.getPrefHeight()*STACKPANE_PERCENT_HEIGHT);
		trigScroll = new ScrollPane(triggers);
		actScroll = new ScrollPane(actions);
		myRule.add(trigScroll, DEFAULT_COL, TRIGGERS_ROW);
		myRule.add(actScroll, DEFAULT_COL, ACTIONS_ROW);
		
	}
	/**
	 * Close button to remove a rule
	 */
	private void addCloseButton(){
		Button close = new Button(CLOSE);
		close.setOnAction(event -> {
			myActorRuleCreator.removeRule(this);
		});
		myRule.add(close, CLOSE_COL, CLOSE_ROW);
	}
	
	/**
	 * Add a behavior from library to rule.
	 * @param behavior
	 */
	public void addBehavior(Label behavior) {
		Node toAdd = actorRuleFactory.getBehaviorHBox(behavior.getText(),null);
		toAdd.setOnMouseClicked(event -> {
			if(event.getClickCount()==2) remove(toAdd);
		});
		if(isTrigger(behavior.getText())) {
			triggers.getChildren().add(toAdd);
			//TODO: Create ITrigger and add to list of ITriggers
		}
		else actions.getChildren().add(toAdd);
	}
	/**
	 * Return if given behavior is a trigger type behavior
	 * @param behavior
	 * @return
	 */
	private boolean isTrigger(String behavior){
		List<String> triggers = Arrays.asList(triggerBehaviors.split(" "));
		return triggers.contains(behavior);
	}
	/**
	 * Add sound from library to rule
	 * @param sound
	 */
	public void addSound(Label sound) {
		if(isInPath(sound.getText(), myLibraryResources.getString("Sounds"))){
			Node toAdd = actorRuleFactory.getBehaviorHBox(PLAY_SOUND, sound.getText());
			toAdd.setOnMouseClicked(event -> {
				if(event.getClickCount()==2) remove(toAdd);
			});
			actions.getChildren().add(toAdd);
		}
		else{
			Node toAdd = actorRuleFactory.getBehaviorHBox(PLAY_MUSIC, sound.getText());
			toAdd.setOnMouseClicked(event -> {
				if(event.getClickCount()==2) remove(toAdd);
			});
			actions.getChildren().add(toAdd);
		}
	}
	/**
	 * return if given filename is in directory of given pathname
	 * @param fileName
	 * @param pathname
	 * @return
	 */
	private boolean isInPath(String fileName, String pathname){
		File dir = new File(pathname);
		List<String> fileNames = new ArrayList<>();
		for(File file:dir.listFiles()){
			fileNames.add(file.getName());
		}
		return fileNames.contains(fileName);
	}
	/**
	 * Add image from library to rule
	 * @param image
	 */
	public void addImage(Label image) {
		Node toAdd = actorRuleFactory.getBehaviorHBox(CHANGE_IMAGE,image.getText());
		toAdd.setOnMouseClicked(event -> {
			if(event.getClickCount()==2) remove(toAdd);
		});
		actions.getChildren().add(toAdd);
	}
	
	/**
	 * Remove trigger or action from rule 
	 * @param toRemove
	 */
	public void remove(Node toRemove){
		triggers.getChildren().remove(toRemove);
		actions.getChildren().remove(toRemove);
	}
}
