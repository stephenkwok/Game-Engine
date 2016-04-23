package authoringenvironment.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import authoringenvironment.view.behaviors.IAuthoringBehavior;
import gameengine.model.Actor;
import gameengine.model.IAction;
import gameengine.model.IRule;
import gameengine.model.ITrigger;
import gameengine.model.Rule;
import gameengine.model.Actions.Action;
import gui.view.IGUIElement;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	private VBox myTriggerNodes;
	private VBox myActionNodes;
	private ScrollPane trigNodesScroll;
	private ScrollPane actNodesScroll;
	private ActorRuleCreator myActorRuleCreator;
	private ResourceBundle myFactoryResources;
	private static final String LIBRARY_BUNDLE = "library";
	private ResourceBundle myActorRuleResources;
	private static final String ACTORRULE_BUNDLE = "actorrule";
	private ActorRuleFactory actorRuleFactory;
	private Controller myController;
	private Map<IAuthoringBehavior, List<Object>> authoringBehaviorMap;
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
		this.authoringBehaviorMap = new HashMap<>();
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
			//TODO:
		});
		myRule.add(close, Integer.parseInt(myActorRuleResources.getString("CloseCol")), Integer.parseInt(myActorRuleResources.getString("CloseRow")));
	}
	
	/**
	 * Add a behavior from library to rule.
	 * @param behavior
	 */
	public void addBehavior(String behaviorType) {
		if(!(isTrigger(behaviorType) && myTriggerNodes.getChildren().size()!=0)){
			IAuthoringBehavior element = actorRuleFactory.getAuthoringRule(behaviorType,null);
			authoringBehaviorMap.put(element, new ArrayList<>());
			Node node = ((IGUIElement) element).createNode();
			setRemoveEvent(node, element);			
			if(isTrigger(behaviorType)){
				myTriggerNodes.getChildren().add(node);
			}
			else{
				myActionNodes.getChildren().add(node);
			}
			authoringBehaviorMap.get(element).add(Integer.parseInt(myActorRuleResources.getString("NodeIndex")), node);
		}
		System.out.println("AFTER ADDING BEHAVIOR");
		System.out.println("Authoring Behavior Map: " + authoringBehaviorMap.size() + authoringBehaviorMap);
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
	 * Remove trigger or action from rule 
	 * @param toRemove
	 */
	public void remove(IAuthoringBehavior toRemove){
		myTriggerNodes.getChildren().remove(authoringBehaviorMap.get(toRemove).get(Integer.parseInt(myActorRuleResources.getString("NodeIndex"))));
		myActionNodes.getChildren().remove(authoringBehaviorMap.get(toRemove).get(Integer.parseInt(myActorRuleResources.getString("NodeIndex"))));
		if(isMyTrigger(toRemove)){
			try{ removeTrigger(toRemove);}
			catch(Exception e){ myTrigger = null;}
		}else{
			try{ removeAction(toRemove);}
			catch(Exception e){authoringBehaviorMap.remove(toRemove);}
		}
	}
	
	private void removeTrigger(IAuthoringBehavior toRemove){
		//remove rules corresponding to this trigger key from actor
		((Actor) myActorRuleCreator.getActor()).getRules().remove(myTrigger.getMyKey());
		//remove trigger from authoring behavior
		authoringBehaviorMap.remove(toRemove);
		//remove rules from authoring behaviors in actorRuleMap that have the current trigger as their trigger
		for(IAuthoringBehavior authoringBehavior: authoringBehaviorMap.keySet()){
			String otherTriggerKey = ((Rule) authoringBehaviorMap.get(authoringBehavior).get(Integer.parseInt(myActorRuleResources.getString("IRuleIndex")))).getMyTrigger().getMyKey();
			if(otherTriggerKey.equals(myTrigger.getMyKey())){
				authoringBehaviorMap.get(authoringBehavior).remove(Integer.parseInt(myActorRuleResources.getString("IRuleIndex")));
			}
		}
		myTrigger = null;
		System.out.println("AFTER REMOVING TRIGGER");
		System.out.println("Rule Map: " + ((Actor) myActorRuleCreator.getActor()).getRules().size() + ((Actor) myActorRuleCreator.getActor()).getRules());
		System.out.println("Authoring Behavior Map: " + authoringBehaviorMap.size() + authoringBehaviorMap);
	}
	
	private void removeAction(IAuthoringBehavior toRemove){
		myActions.remove(authoringBehaviorMap.get(toRemove).get(Integer.parseInt(myActorRuleResources.getString("TriggerActionIndex"))));
		Rule ruleToRemove = (Rule) authoringBehaviorMap.get(toRemove).get(Integer.parseInt(myActorRuleResources.getString("IRuleIndex")));
		removeIRuleFromActor(ruleToRemove);
		authoringBehaviorMap.remove(toRemove);
	}
	
	private void removeIRuleFromActor(IRule toRemove){
		List<Rule> rulesForCurrentTrigger = ((Actor) myActorRuleCreator.getActor()).getRules().get(myTrigger.getMyKey());
		rulesForCurrentTrigger.remove(toRemove);
	}
	
	public void setTrigger(IAuthoringBehavior key, ITrigger value){
		myTrigger = value;
		authoringBehaviorMap.get(key).add(value);
	}
	
	public void setAction(IAuthoringBehavior key, IAction value){
		myActions.add(value);
		authoringBehaviorMap.get(key).add(value);
	}
	
	private void showAlert(String alertHeader, String alertContent){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(alertHeader);
		alert.setContentText(alertContent);
		alert.showAndWait();
	}
	
	public void setRules(){
		if(myTrigger==null || myActions.size()==0){
			showAlert(myActorRuleResources.getString("SomethingNotSet"),myActorRuleResources.getString("SetBoth"));
		}
		else{
			for(IAuthoringBehavior authoringBehavior: authoringBehaviorMap.keySet()){
				//for each authoringbehavior that is an action, create a new rule
				if(!isMyTrigger(authoringBehavior)){
					Action myAction = (Action) authoringBehaviorMap.get(authoringBehavior).get(Integer.parseInt(myActorRuleResources.getString("TriggerActionIndex")));
					Rule newRule = new Rule(myTrigger, myAction);
					//add this rule to the actor for the current trigger value
					Map<String, List<Rule>> ruleMap = ((Actor) myActorRuleCreator.getActor()).getRules(); 
					if(!(ruleMap.containsKey(myTrigger.getMyKey()))){
						List<Rule> newList = new ArrayList<>();
						newList.add(newRule);
						ruleMap.put(myTrigger.getMyKey(), newList);
						((Actor) myActorRuleCreator.getActor()).setMyRules(ruleMap);
						authoringBehaviorMap.get(authoringBehavior).add(newRule);
					} else if(actionNotYetAdded(ruleMap, myAction)){
						ruleMap.get(myTrigger.getMyKey()).add(newRule);
						((Actor) myActorRuleCreator.getActor()).setMyRules(ruleMap);
						//add this new rule to map as third thing for this authoring behavior
						authoringBehaviorMap.get(authoringBehavior).add(newRule);
					}
				}
			}
		}
		System.out.println("AFTER SETTING RULES");
		System.out.println("Authoring Behavior Map: " + authoringBehaviorMap.size() + authoringBehaviorMap);
		System.out.println("Rule Map: " + ((Actor) myActorRuleCreator.getActor()).getRules().size() + ((Actor) myActorRuleCreator.getActor()).getRules());
	}
	
	private boolean actionNotYetAdded(Map<String, List<Rule>> ruleMap, IAction value){
		for(Rule rule: ruleMap.get(myTrigger.getMyKey())){
			if(rule.getMyAction()==value) return false;
		}
		return true;
	}
	
	private boolean isMyTrigger(IAuthoringBehavior authoringBehavior){
		return myTrigger == authoringBehaviorMap.get(authoringBehavior).get(Integer.parseInt(myActorRuleResources.getString("TriggerActionIndex")));
	}

	private void setRemoveEvent(Node node, IAuthoringBehavior element){
		node.setOnMouseClicked(event -> {
			if(event.getClickCount()==2){
				remove(element);
			}
		});
	}
}
