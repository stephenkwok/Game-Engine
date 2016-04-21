package authoringenvironment.view;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
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

/**
 * Rule container for an actor containing behavior, images, and/or sounds.
 * @author AnnieTang
 *
 */
public class ActorRule {
	private static final int PADDING = 20;
	private static final double RULE_HEIGHT = 250;
	private static final int CORNER_RADIUS = 20;
	private GridPane myRule;	
	private VBox triggers;
	private VBox actions;
	private ScrollPane trigScroll;
	private ScrollPane actScroll;
	private ActorRuleCreator myActorRuleCreator;
	private ResourceBundle myFactoryResources;
	private ResourceBundle myActorRuleResources;
	private static final String LIBRARY_BUNDLE = "library";
	private static final String ACTORRULE_BUNDLE = "actorrule";
	private String triggerBehaviors;
	private ActorRuleFactory actorRuleFactory;
	private Controller myController;
		
	public ActorRule(ActorRuleCreator myActorRuleCreator, Controller myController) {
		this.myActorRuleCreator = myActorRuleCreator;
		this.myController = myController;
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
		this.myFactoryResources = ResourceBundle.getBundle(LIBRARY_BUNDLE);
		this.myActorRuleResources = ResourceBundle.getBundle(ACTORRULE_BUNDLE);
		this.actorRuleFactory = new ActorRuleFactory(myFactoryResources, myActorRuleCreator.getActor(), myController.getActorMap(), myController.getLevels());
		this.triggerBehaviors = myFactoryResources.getString("TriggerBehaviors");
		myRule = new GridPane(); 
		myRule.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, new CornerRadii(CORNER_RADIUS), Insets.EMPTY)));
		myRule.setPadding(new Insets(PADDING,PADDING,PADDING,PADDING));
		myRule.setPrefSize(myActorRuleCreator.getGridPane().getWidth()*Double.parseDouble(myActorRuleResources.getString("RuleWidthPercent")), RULE_HEIGHT);
		addTriggerActionLabels();
		addTriggerActionContainers();
		addCloseButton();
	}
	
	/**
	 * Add labels for each section of rule container (trigger and action)
	 */
	private void addTriggerActionLabels(){
		Label triggerLabel = new Label(myActorRuleResources.getString("TriggerLabel"));
		Label actionLabel = new Label(myActorRuleResources.getString("ActionLabel"));
		myRule.add(triggerLabel, Integer.parseInt(myActorRuleResources.getString("DefaultCol")), Integer.parseInt(myActorRuleResources.getString("TriggerLabelRow")));
		myRule.add(actionLabel, Integer.parseInt(myActorRuleResources.getString("DefaultCol")), Integer.parseInt(myActorRuleResources.getString("ActionLabelRow")));
	}
	/**
	 * Add containers for triggers and actions where user can drop behaviors.
	 */
	private void addTriggerActionContainers(){
		triggers = new VBox(PADDING);
		triggers.setPrefSize(myRule.getPrefWidth()*Double.parseDouble(myActorRuleResources.getString("ContainerWidthPercent")), 
				myRule.getPrefHeight()*Double.parseDouble(myActorRuleResources.getString("ContainerHeightPercent")));
		actions = new VBox(PADDING);
		actions.setPrefSize(myRule.getPrefWidth()*Double.parseDouble(myActorRuleResources.getString("ContainerWidthPercent")), 
				myRule.getPrefHeight()*Double.parseDouble(myActorRuleResources.getString("ContainerHeightPercent")));
		trigScroll = new ScrollPane(triggers);
		actScroll = new ScrollPane(actions);
		myRule.add(trigScroll, Integer.parseInt(myActorRuleResources.getString("DefaultCol")), Integer.parseInt(myActorRuleResources.getString("TriggerRow")));
		myRule.add(actScroll, Integer.parseInt(myActorRuleResources.getString("DefaultCol")), Integer.parseInt(myActorRuleResources.getString("ActionRow")));
		
	}
	/**
	 * Close button to remove a rule
	 */
	private void addCloseButton(){
		Button close = new Button(myActorRuleResources.getString("Close"));
		close.setOnAction(event -> {
			myActorRuleCreator.removeRule(this);
		});
		myRule.add(close, Integer.parseInt(myActorRuleResources.getString("CloseCol")), Integer.parseInt(myActorRuleResources.getString("CloseRow")));
	}
	
	/**
	 * Add a behavior from library to rule.
	 * @param behavior
	 */
	public void addBehavior(Label behavior) {
		Node toAdd = actorRuleFactory.getAuthoringRule(behavior.getText(),null).createNode();
		toAdd.setOnMouseClicked(event -> {
			if(event.getClickCount()==2) remove(toAdd);
		});
		if(isTrigger(behavior.getText())) {
			triggers.getChildren().add(toAdd);
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
		if(isInPath(sound.getText(), myActorRuleResources.getString("Sounds"))){
			Node toAdd = actorRuleFactory.getAuthoringRule(myActorRuleResources.getString("PlaySoundBehavior"), sound.getText()).createNode();
			toAdd.setOnMouseClicked(event -> {
				if(event.getClickCount()==2) remove(toAdd);
			});
			actions.getChildren().add(toAdd);
		}
		else{
			Node toAdd = actorRuleFactory.getAuthoringRule(myActorRuleResources.getString("PlayMusicBehavior"), sound.getText()).createNode();
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
		Node toAdd = actorRuleFactory.getAuthoringRule(myActorRuleResources.getString("ChangeImageBehavior"),image.getText()).createNode();
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
