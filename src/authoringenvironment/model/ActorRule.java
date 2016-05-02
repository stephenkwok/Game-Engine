package authoringenvironment.model;

import java.util.*;

import authoringenvironment.controller.Controller;
import gameengine.model.*;
import gameengine.model.Actions.Action;
import gameengine.model.Triggers.ITrigger;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Rule container for an actor containing behavior, images, and/or sounds.
 * @author AnnieTang
 */
public class ActorRule {
	private GridPane myRule;
	private VBox myTriggerNodes;
	private VBox myActionNodes;
	private ActorRuleCreator myActorRuleCreator;
	private ResourceBundle myFactoryResources;
	private static final String LIBRARY_BUNDLE = "library";
	private ResourceBundle myActorRuleResources;
	private static final String ACTORRULE_BUNDLE = "actorrule";
	private static final int FIRST_INDEX = 0;
	private ActorRuleFactory actorRuleFactory;
	private Controller myController;
	private Map<IAuthoringBehavior, List<Object>> authoringBehaviorMap;
	private ITrigger myTrigger;
	private List<IAction> myActions;
	private IAuthoringActor myActor;

	public ActorRule(ActorRuleCreator myActorRuleCreator) {
		this.myActorRuleCreator = myActorRuleCreator;
		this.myController = myActorRuleCreator.getController();
		this.myActor = myActorRuleCreator.getActor();
		initializeEnvironment();
	}

	/**
	 * Returns new rule container
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
		this.actorRuleFactory = new ActorRuleFactory(myFactoryResources, myActor, myController, this);
		this.authoringBehaviorMap = new HashMap<>();
		this.myActions = new ArrayList<>();
		myRule = new GridPane();
		myRule.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE,
				new CornerRadii(Integer.parseInt(myActorRuleResources.getString("CornerRadius"))), Insets.EMPTY)));
		myRule.setPadding(new Insets(Integer.parseInt(myActorRuleResources.getString("Padding")),
				Integer.parseInt(myActorRuleResources.getString("Padding")),
				Integer.parseInt(myActorRuleResources.getString("Padding")),
				Integer.parseInt(myActorRuleResources.getString("Padding"))));
		myRule.setPrefSize(
				myActorRuleCreator.getGridPane().getPrefWidth()
						* Double.parseDouble(myActorRuleResources.getString("RuleWidthPercent")),
				Integer.parseInt(myActorRuleResources.getString("RuleHeight")));
		addTriggerActionLabels();
		addTriggerActionContainers();
		addCloseButton();
	}

	/**
	 * Add labels for each section of rule container (trigger and action)
	 */
	private void addTriggerActionLabels() {
		Label triggerLabel = new Label(myActorRuleResources.getString("TriggerLabel"));
		Label actionLabel = new Label(myActorRuleResources.getString("ActionLabel"));
		myRule.add(triggerLabel, Integer.parseInt(myActorRuleResources.getString("DefaultCol")),
				Integer.parseInt(myActorRuleResources.getString("TriggerLabelRow")));
		myRule.add(actionLabel, Integer.parseInt(myActorRuleResources.getString("DefaultCol")),
				Integer.parseInt(myActorRuleResources.getString("ActionLabelRow")));
	}

	/**
	 * Add containers for triggers and actions where user can drop behaviors.
	 */
	private void addTriggerActionContainers() {
		myTriggerNodes = new VBox(Integer.parseInt(myActorRuleResources.getString("Padding")));
		myTriggerNodes.setPrefSize(
				myRule.getPrefWidth() * Double.parseDouble(myActorRuleResources.getString("ContainerWidthPercent")),
				myRule.getPrefHeight() * Double.parseDouble(myActorRuleResources.getString("ContainerHeightPercent")));
		myActionNodes = new VBox(Integer.parseInt(myActorRuleResources.getString("Padding")));
		myActionNodes.setPrefSize(
				myRule.getPrefWidth() * Double.parseDouble(myActorRuleResources.getString("ContainerWidthPercent")),
				myRule.getPrefHeight() * Double.parseDouble(myActorRuleResources.getString("ContainerHeightPercent")));
		ScrollPane trigNodesScroll = new ScrollPane(myTriggerNodes);
		ScrollPane actNodesScroll = new ScrollPane(myActionNodes);
		myRule.add(trigNodesScroll, Integer.parseInt(myActorRuleResources.getString("DefaultCol")),
				Integer.parseInt(myActorRuleResources.getString("TriggerRow")));
		myRule.add(actNodesScroll, Integer.parseInt(myActorRuleResources.getString("DefaultCol")),
				Integer.parseInt(myActorRuleResources.getString("ActionRow")));
	}

	/**
	 * Close button to remove a rule
	 */
	private void addCloseButton() {
		Button close = new Button(myActorRuleResources.getString("Close"));
		close.setOnAction(event -> {
			int index = 0;
			while (authoringBehaviorMap.size() > 1) { // remove actions first, then trigger. no loop to avoid concurrent modification
				if (!isITrigger(new ArrayList<IAuthoringBehavior>(authoringBehaviorMap.keySet()).get(index))) {
					remove(new ArrayList<IAuthoringBehavior>(authoringBehaviorMap.keySet()).get(index));
				} else
					index++;
			}
			if (authoringBehaviorMap.size() != 0)
				remove(new ArrayList<IAuthoringBehavior>(authoringBehaviorMap.keySet()).get(FIRST_INDEX));
			myActorRuleCreator.removeActorRule(this);
		});
		myRule.add(close, Integer.parseInt(myActorRuleResources.getString("CloseCol")),
				Integer.parseInt(myActorRuleResources.getString("CloseRow")));
	}

	/**
	 * Add a behavior from library to rule.
	 */
	public void addBehavior(String behaviorType, IRule rule) {
		if (!(isTriggerType(behaviorType) && myTriggerNodes.getChildren().size() != 0)) {
			IAuthoringBehavior element = actorRuleFactory.getAuthoringRule(behaviorType, rule);
			authoringBehaviorMap.put(element, new ArrayList<>());
			Node node = (element).createNode();
			element.updateValueBasedOnEditable();
			setRemoveEvent(node, element);
			if (isTriggerType(behaviorType)) {
				myTriggerNodes.getChildren().add(node);
			} else {
				myActionNodes.getChildren().add(node);
			}
			authoringBehaviorMap.get(element).add(Integer.parseInt(myActorRuleResources.getString("NodeIndex")), node);
		}
	}
	
	/**
	 * Add a sound from the sound library to rule.
	 */
	public void addSound(String behaviorType, String soundName){
		IAuthoringBehavior element = actorRuleFactory.getSoundRule(behaviorType, soundName);
		authoringBehaviorMap.put(element, new ArrayList<>());
		Node node = (element).createNode();
		element.updateValueBasedOnEditable();
		setRemoveEvent(node, element);
		myActionNodes.getChildren().add(node);
		authoringBehaviorMap.get(element).add(Integer.parseInt(myActorRuleResources.getString("NodeIndex")), node);
	}

	/**
	 * Remove trigger or action from rule
	 */
	public void remove(IAuthoringBehavior toRemove) {
		myTriggerNodes.getChildren().remove( authoringBehaviorMap.get(toRemove).get(Integer.parseInt(myActorRuleResources.getString("NodeIndex"))));
		myActionNodes.getChildren().remove( authoringBehaviorMap.get(toRemove).get(Integer.parseInt(myActorRuleResources.getString("NodeIndex"))));
		if (isITrigger(toRemove)) {
			try {
				removeTrigger(toRemove);
			} catch (Exception e) {
				myTrigger = null;
			}
		} else {
			try {
				removeAction(toRemove);
			} catch (Exception e) {
				authoringBehaviorMap.remove(toRemove);
			}
		}
	}
	
	/**
	 * Removes rule's trigger value 
	 */
	private void removeTrigger(IAuthoringBehavior toRemove) {
		authoringBehaviorMap.remove(toRemove); // remove trigger from authoring behavior
		myActor.getRules().remove(myTrigger.getMyKey()); // remove rules corresponding to this trigger key from actor
		// remove rules from authoring behaviors in actorRuleMap that have the current trigger as their trigger
		for (IAuthoringBehavior authoringBehavior : authoringBehaviorMap.keySet()) {
			String otherTriggerKey = ((Rule) authoringBehaviorMap.get(authoringBehavior)
					.get(Integer.parseInt(myActorRuleResources.getString("IRuleIndex")))).getMyTrigger().getMyKey();
			if (otherTriggerKey.equals(myTrigger.getMyKey())) {
				authoringBehaviorMap.get(authoringBehavior)
						.remove(Integer.parseInt(myActorRuleResources.getString("IRuleIndex")));
			}
		}
		myTrigger = null;
	}
	/**
	 * Remove Action corresponding to given IAuthoringBehavior
	 */
	private void removeAction(IAuthoringBehavior toRemove) {
		myActions.remove(authoringBehaviorMap.get(toRemove)
				.get(Integer.parseInt(myActorRuleResources.getString("TriggerActionIndex"))));
		Rule ruleToRemove = (Rule) authoringBehaviorMap.get(toRemove)
				.get(Integer.parseInt(myActorRuleResources.getString("IRuleIndex")));
		removeIRuleFromActor(ruleToRemove);
		authoringBehaviorMap.remove(toRemove);
	}
	/**
	 * Remove given IRule from the Actor currently being edited
	 */
	private void removeIRuleFromActor(IRule toRemove) {
		List<Rule> rulesForCurrentTrigger = myActor.getRules().get(myTrigger.getMyKey());
		rulesForCurrentTrigger.remove(toRemove);
	}
	/**
	 * Set Trigger value
	 */
	public void setTrigger(IAuthoringBehavior key, ITrigger value) {
		myTrigger = value;
		authoringBehaviorMap.get(key).add(value);
	}
	/**
	 * Set Action value(s)
	 */
	public void setAction(IAuthoringBehavior key, IAction value) {
		myActions.add(value);
		authoringBehaviorMap.get(key).add(value);
	}
	/**
	 * Show alert 
	 */
	private void showAlert(String alertHeader, String alertContent) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(alertHeader);
		alert.setContentText(alertContent);
		alert.showAndWait();
	}
	/**
	 * Set rules in the Actor Editing Environment to the Actor
	 */
	public void setRules() {
		if (myActorRuleCreator.isNewlyReturned()) {
			System.out.println("here");
			myActorRuleCreator.setNewlyReturned(false);
			myActor.getRules().clear();
		}
		if (myTrigger == null) {
			for (IAuthoringBehavior authoringBehavior : authoringBehaviorMap.keySet()) {
				if (isITrigger(authoringBehavior)) addRuleFromBehavior(authoringBehavior);
			}
		}
		try {
			for (IAuthoringBehavior authoringBehavior : authoringBehaviorMap.keySet()) {
				addRuleFromBehavior(authoringBehavior);
			}
		} catch (Exception e) {
			showAlert(myActorRuleResources.getString("SomethingNotSet"), myActorRuleResources.getString("SetBoth"));
		}
	}
	/**
	 * Add Rule based on given IAuthoringBehavior
	 */
	private void addRuleFromBehavior(IAuthoringBehavior authoringBehavior) {
		authoringBehavior.setValue();
		if (!isITrigger(authoringBehavior)) {
			Action myAction = (Action) authoringBehaviorMap.get(authoringBehavior)
					.get(Integer.parseInt(myActorRuleResources.getString("TriggerActionIndex")));
			Rule newRule = new Rule(myTrigger, myAction);
			Map<String, List<Rule>> ruleMap = myActor.getRules();
			if (!(ruleMap.containsKey(myTrigger.getMyKey()))) {
				myActor.addRule(newRule);
				authoringBehaviorMap.get(authoringBehavior).add(newRule);
			} else if (actionNotYetAdded(ruleMap, myAction)) {
				myActor.addRule(newRule);
				authoringBehaviorMap.get(authoringBehavior).add(newRule);
			}
		}
	}
	/**
	 * Checks if given Action has been added to the Actor for this Trigger yet
	 */
	private boolean actionNotYetAdded(Map<String, List<Rule>> ruleMap, IAction value) {
		for (Rule rule : ruleMap.get(myTrigger.getMyKey())) {
			if (rule.getMyAction() == value)
				return false;
		}
		return true;
	}

	/**
	 * Return if given behavior is a trigger type behavior
	 */
	private boolean isTriggerType(String behavior) {
		List<String> triggers = Arrays.asList(myFactoryResources.getString("TriggerBehaviors").split(" "));
		return triggers.contains(behavior);
	}
	/**
	 * Returns if given IAuthoringBehavior outputs a Trigger
	 */
	private boolean isITrigger(IAuthoringBehavior authoringBehavior) {
		return authoringBehavior.isTrigger();
	}
	/**
	 * Sets remove event so double clicking on a Trigger or Action will delete it 
	 */
	private void setRemoveEvent(Node node, IAuthoringBehavior element) {
		node.setOnMouseClicked(event -> { if (event.getClickCount() == 2) remove(element); });
	}
}