package authoringenvironment.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IAuthoringActor;
import gameengine.model.Actor;
import gameengine.model.IRule;
import gameengine.model.Rule;
import gameengine.model.Actions.ApplyPhysics;
import gameengine.model.Actions.ChangeAttribute;
import gameengine.model.Triggers.TickTrigger;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Space in actor editing environment where rules are created.
 * 
 * @author AnnieTang
 *
 */
public class ActorRuleCreator {
	private static final int RULE_COL = 1;
	private static final int RULE_ROW_START = 1;
	private static final String RESOURCE_BASE = "actionfactory";
	private static final String KEY = "Key";
	private static final int VGAP = 10;
	private static final int HGAP = 10;
	private static final double CONTAINERS_PERCENT_WIDTH = 0.75;
	private static final Object CHANGE_ATTRIBUTE = "ChangeAttribute";
	private static final int ZERO = 0;
	private static final String POINTS = "POINTS";
	private static final String HEALTH = "HEALTH";
	private int ruleRow;
	private GridPane myActorRuleCreatorPane;
	private List<ActorRule> myActorRules;
	private ActorEditingEnvironment aEE;
	private ResourceBundle myActionResources;
	private boolean newlyReturned;

	public ActorRuleCreator(ActorEditingEnvironment aEE) {
		this.aEE = aEE;
		initializeEnvironment();
	}

	/**
	 * Initialize and create actor rule creator, the rightmost section of the
	 * actor editing environment
	 */
	private void initializeEnvironment() {
		this.ruleRow = RULE_ROW_START;
		this.myActorRules = new ArrayList<>();
		this.myActionResources = ResourceBundle.getBundle(RESOURCE_BASE);
		this.newlyReturned = true;
		myActorRuleCreatorPane = new GridPane();
		myActorRuleCreatorPane.setPrefWidth(aEE.getStage().getWidth() * CONTAINERS_PERCENT_WIDTH);
		myActorRuleCreatorPane.setVgap(VGAP);
		myActorRuleCreatorPane.setHgap(HGAP);
	}

	/**
	 * Get GridPane representation of actor rule creator
	 * 
	 * @return
	 */
	public GridPane getGridPane() {
		return myActorRuleCreatorPane;
	}

	/**
	 * Add given behavior to given ActorRule
	 * 
	 * @param rule
	 * @param behavior
	 */
	public void addBehavior(ActorRule rule, String behavior) {
		rule.addBehavior(behavior, null);
	}

	/**
	 * Add given sound to given ActorRule
	 * 
	 * @param rule
	 * @param sound
	 */
	public void addSound(ActorRule rule, Label sound) {
		// rule.addSound(sound.getText());
	}

	/**
	 * Create new rule for Actor currently in the actor editing environment and
	 * add to gridpane
	 */
	public void addNewActorRule() {
		ActorRule newRule = new ActorRule(this);
		myActorRuleCreatorPane.add(newRule.getGridPane(), RULE_COL, ruleRow);
		myActorRules.add(newRule);
		ruleRow++;
	}

	/**
	 * Remove given rule from environment and from Actor currently in the
	 * environment
	 * 
	 * @param actorRule
	 */
	public void removeActorRule(ActorRule actorRule) {
		myActorRules.remove(actorRule);
		myActorRuleCreatorPane.getChildren().remove(actorRule.getGridPane());
	}

	/**
	 * Get ActorRules for Actor currently in the actor editing environment
	 * 
	 * @return
	 */
	public List<ActorRule> getActorRules() {
		return myActorRules;
	}

	/**
	 * Each time actor editing environment is opened and set to a specific
	 * Actor, populates editing environment rules and fields based on the Actor
	 */
	public void updateActorRules() {
		resetEnvironment();
		for (String triggerType : ((IAuthoringActor) aEE.getEditable()).getRules().keySet()) {
			ActorRule toAdd = new ActorRule(this);
			toAdd.addBehavior(checkForKeyOrAttributeTrigger(triggerType), 
					((IAuthoringActor) aEE.getEditable()).getRules().get(triggerType).get(ZERO));
			for (IRule rule : ((IAuthoringActor) aEE.getEditable()).getRules().get(triggerType)) {
				String simpleName = rule.getMyAction().getClass().getSimpleName();
				if (simpleName.equals(CHANGE_ATTRIBUTE)) {
					String attributeType = ((ChangeAttribute) rule.getMyAction()).getMyAttributeType();
					toAdd.addBehavior(myActionResources.getString(attributeType), rule);
				} else
					toAdd.addBehavior(simpleName, rule); 
			}
			myActorRuleCreatorPane.add(toAdd.getGridPane(), RULE_COL, ruleRow);
			myActorRules.add(toAdd);
			ruleRow++;
		}
	}
	
	protected void applyPhysics(){
		if(aEE.shouldApplyPhysics()){
			Rule toAdd = new Rule(new TickTrigger(), new ApplyPhysics((Actor) aEE.getEditable()));
			((IAuthoringActor) aEE.getEditable()).addRule(toAdd);
		}
	}
	
	private void resetEnvironment(){
		this.newlyReturned = true;
		for(ActorRule toRemove: myActorRules) myActorRuleCreatorPane.getChildren().remove(toRemove.getGridPane());
		myActorRules.clear();
		ruleRow = RULE_ROW_START;
	}

	/**
	 * Get the current IAuthoringActor
	 * 
	 * @return IAuthoringActor
	 */
	public IAuthoringActor getActor() {
		return (IAuthoringActor) aEE.getEditable();
	}

	public Controller getController() {
		return aEE.getController();
	}

	public void setRules() {
		for (ActorRule actorRule : myActorRules) {
			actorRule.setRules();
		}
	}

	private String checkForKeyOrAttributeTrigger(String triggerType) {
		if (Arrays.asList(myActionResources.getString("KeyInputs").split(" ")).contains(triggerType)) {
			return KEY;
		}else if(triggerType.contains(POINTS)){
			return myActionResources.getString(POINTS);
		}else if(triggerType.contains(HEALTH)){
			return myActionResources.getString(HEALTH);
		}
		return triggerType;
	}

	public boolean isNewlyReturned() {
		return this.newlyReturned;
	}

	public void setNewlyReturned(boolean bool) {
		this.newlyReturned = bool;
	}
}