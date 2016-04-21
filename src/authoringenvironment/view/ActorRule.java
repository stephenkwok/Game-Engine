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
	private GridPane myRule;	
	private VBox triggerNodes;
	private VBox actionNodes;
	private ScrollPane trigNodesScroll;
	private ScrollPane actNodesScroll;
	private ActorRuleCreator myActorRuleCreator;
	private ResourceBundle myFactoryResources;
	private static final String LIBRARY_BUNDLE = "library";
	private ResourceBundle myActorRuleResources;
	private static final String ACTORRULE_BUNDLE = "actorrule";
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
		this.actorRuleFactory = new ActorRuleFactory(myFactoryResources, myActorRuleCreator.getActor(), myController);
		myRule = new GridPane(); 
		myRule.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, new CornerRadii(Integer.parseInt(myActorRuleResources.getString("CornerRadius"))), Insets.EMPTY)));
		myRule.setPadding(new Insets(Integer.parseInt(myActorRuleResources.getString("Padding")),Integer.parseInt(myActorRuleResources.getString("Padding")),Integer.parseInt(myActorRuleResources.getString("Padding")),Integer.parseInt(myActorRuleResources.getString("Padding"))));
		myRule.setPrefSize(myActorRuleCreator.getGridPane().getWidth()*Double.parseDouble(myActorRuleResources.getString("RuleWidthPercent")), Integer.parseInt(myActorRuleResources.getString("RuleHeight")));
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
		triggerNodes = new VBox(Integer.parseInt(myActorRuleResources.getString("Padding")));
		triggerNodes.setPrefSize(myRule.getPrefWidth()*Double.parseDouble(myActorRuleResources.getString("ContainerWidthPercent")), 
				myRule.getPrefHeight()*Double.parseDouble(myActorRuleResources.getString("ContainerHeightPercent")));
		actionNodes = new VBox(Integer.parseInt(myActorRuleResources.getString("Padding")));
		actionNodes.setPrefSize(myRule.getPrefWidth()*Double.parseDouble(myActorRuleResources.getString("ContainerWidthPercent")), 
				myRule.getPrefHeight()*Double.parseDouble(myActorRuleResources.getString("ContainerHeightPercent")));
		trigNodesScroll = new ScrollPane(triggerNodes);
		actNodesScroll = new ScrollPane(actionNodes);
		myRule.add(trigNodesScroll, Integer.parseInt(myActorRuleResources.getString("DefaultCol")), Integer.parseInt(myActorRuleResources.getString("TriggerRow")));
		myRule.add(actNodesScroll, Integer.parseInt(myActorRuleResources.getString("DefaultCol")), Integer.parseInt(myActorRuleResources.getString("ActionRow")));
		
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
			triggerNodes.getChildren().add(toAdd);
		}
		else actionNodes.getChildren().add(toAdd);
	}
	/**
	 * Return if given behavior is a trigger type behavior
	 * @param behavior
	 * @return
	 */
	private boolean isTrigger(String behavior){
		List<String> triggers = Arrays.asList(myFactoryResources.getString("TriggerBehaviors").split(" "));
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
			actionNodes.getChildren().add(toAdd);
		}
		else{
			Node toAdd = actorRuleFactory.getAuthoringRule(myActorRuleResources.getString("PlayMusicBehavior"), sound.getText()).createNode();
			toAdd.setOnMouseClicked(event -> {
				if(event.getClickCount()==2) remove(toAdd);
			});
			actionNodes.getChildren().add(toAdd);
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
		actionNodes.getChildren().add(toAdd);
	}
	
	/**
	 * Remove trigger or action from rule 
	 * @param toRemove
	 */
	public void remove(Node toRemove){
		triggerNodes.getChildren().remove(toRemove);
		actionNodes.getChildren().remove(toRemove);
	}
}
