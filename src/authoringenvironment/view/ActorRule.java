package authoringenvironment.view;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import authoringenvironment.view.behaviors.IAuthoringRule;
import gameengine.model.Actor;
import gameengine.model.IAction;
import gameengine.model.IRule;
import gameengine.model.ITrigger;
import gameengine.model.Rule;
import gameengine.model.Actions.Action;
import gui.view.IGUIElement;
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
//make the IRule and add to Actor
//Then when re-populating based on IRule, lists should update
/**
 * Rule container for an actor containing behavior, images, and/or sounds.
 * @author AnnieTang
 *
 */
public class ActorRule {
	private GridPane myRule;	
	private VBox myTriggerNodes;
	private VBox myActionNodes;
	private ScrollPane trigNodesScroll;
	private ScrollPane actNodesScroll;
	private ActorRuleCreator myActorRuleCreator;
	private ResourceBundle myFactoryResources;
	private static final String LIBRARY_BUNDLE = "library";
	private ResourceBundle myActorRuleResources;
	private static final String ACTORRULE_BUNDLE = "actorrule";
	private static final int NODE_INDEX = 0;
	private static final int TRIGGERACTION_INDEX = 1;
	private ActorRuleFactory actorRuleFactory;
	private Controller myController;
	private Map<IAuthoringRule, List<Object>> actorRuleMap;
	private ITrigger myTrigger;
	private List<IAction> myActions;
	
	public ActorRule(ActorRuleCreator myActorRuleCreator) {
		this.myActorRuleCreator = myActorRuleCreator;
		this.myController = myActorRuleCreator.getController();
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
		this.actorRuleFactory = new ActorRuleFactory(myFactoryResources, myActorRuleCreator.getActor(), myController, this);
		this.actorRuleMap = new HashMap<>();
		this.myActions = new ArrayList<>();
		myRule = new GridPane(); 
		myRule.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, new CornerRadii(Integer.parseInt(myActorRuleResources.getString("CornerRadius"))), Insets.EMPTY)));
		myRule.setPadding(new Insets(Integer.parseInt(myActorRuleResources.getString("Padding")),Integer.parseInt(myActorRuleResources.getString("Padding")),Integer.parseInt(myActorRuleResources.getString("Padding")),Integer.parseInt(myActorRuleResources.getString("Padding"))));
		myRule.setPrefSize(myActorRuleCreator.getGridPane().getPrefWidth()*Double.parseDouble(myActorRuleResources.getString("RuleWidthPercent")), Integer.parseInt(myActorRuleResources.getString("RuleHeight")));
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
		myTriggerNodes = new VBox(Integer.parseInt(myActorRuleResources.getString("Padding")));
		myTriggerNodes.setPrefSize(myRule.getPrefWidth()*Double.parseDouble(myActorRuleResources.getString("ContainerWidthPercent")), myRule.getPrefHeight()*Double.parseDouble(myActorRuleResources.getString("ContainerHeightPercent")));
		myActionNodes = new VBox(Integer.parseInt(myActorRuleResources.getString("Padding")));
		myActionNodes.setPrefSize(myRule.getPrefWidth()*Double.parseDouble(myActorRuleResources.getString("ContainerWidthPercent")), myRule.getPrefHeight()*Double.parseDouble(myActorRuleResources.getString("ContainerHeightPercent")));
		trigNodesScroll = new ScrollPane(myTriggerNodes);
		actNodesScroll = new ScrollPane(myActionNodes);
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
	public void addBehavior(String behaviorType) {
		IAuthoringRule element = actorRuleFactory.getAuthoringRule(behaviorType,null);
		List<Object> value = new ArrayList<>();
		actorRuleMap.put(element, value);
		Node node = ((IGUIElement) element).createNode();
		node.setOnMouseClicked(event -> {
			if(event.getClickCount()==2) remove(element);
		});
		if(isTrigger(behaviorType)) myTriggerNodes.getChildren().add(node);
		else myActionNodes.getChildren().add(node);
		actorRuleMap.get(element).add(NODE_INDEX, node);
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
	 * @param soundName
	 */
	public void addSound(String soundName) {
		IAuthoringRule element; 
		Node node;
		if(isInPath(soundName, myActorRuleResources.getString("Sounds"))){
			element = actorRuleFactory.getAuthoringRule(myActorRuleResources.getString("PlaySoundBehavior"), soundName);
			node = element.createNode();
		}
		else{
			element = actorRuleFactory.getAuthoringRule(myActorRuleResources.getString("PlayMusicBehavior"), soundName);
			node = element.createNode();
		}
		node.setOnMouseClicked(event -> {
			if(event.getClickCount()==2) remove(element);
		});
		myActionNodes.getChildren().add(node);
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
	 * @param imageName
	 */
	public void addImage(String imageName) {
		IAuthoringRule element = actorRuleFactory.getAuthoringRule(myActorRuleResources.getString("ChangeImageBehavior"),imageName); 
		Node node = element.createNode();
		node.setOnMouseClicked(event -> {
			if(event.getClickCount()==2) remove(element);
		});
		myActionNodes.getChildren().add(node);
	}
	
	/**
	 * Remove trigger or action from rule 
	 * @param toRemove
	 */
	public void remove(IAuthoringRule toRemove){
		myTriggerNodes.getChildren().remove(actorRuleMap.get(toRemove).get(NODE_INDEX));
		myActionNodes.getChildren().remove(actorRuleMap.get(toRemove).get(NODE_INDEX));
		myTrigger = null;
		myActions.remove(actorRuleMap.get(toRemove).get(TRIGGERACTION_INDEX));
		actorRuleMap.remove(toRemove);
		
	}
	
	public void addTrigger(IAuthoringRule key, ITrigger value){
		actorRuleMap.get(key).add(value);
		myTrigger = value;
		System.out.println(myTrigger);
		addIRulesForTrigger();
		//add IRule to map
	}
	
	public void addAction(IAuthoringRule key, IAction value){
		actorRuleMap.get(key).add(value);
		System.out.println(actorRuleMap);
		myActions.add(value);
		addIRulesForTrigger();
		//add IRule to map
	}
	
	private void addIRulesForTrigger(){
		List<Rule> value = new ArrayList<>();
		for(IAction action: myActions){
			value.add(new Rule(myTrigger, (Action) action));
		}
		try{
			((Actor) myActorRuleCreator.getActor()).getRules().put(myTrigger.getMyKey(),value);
		}catch(Exception e){
			System.out.println("Trigger not yet set for IRule");
		}
	}
	
}
