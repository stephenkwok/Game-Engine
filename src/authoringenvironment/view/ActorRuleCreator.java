package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IAuthoringActor;
import gameengine.model.Actor;
import gameengine.model.IRule;
//import gameengine.model.IRule;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
/**
 * Space in actor editing environment where rules are created.
 * @author AnnieTang
 *
 */
public class ActorRuleCreator {
	private static final int RULE_COL = 1;
	private static final int RULE_ROW_START = 1;
	private static final int VGAP = 10;
	private static final int HGAP = 10;
	private static final double CONTAINERS_PERCENT_WIDTH = 0.75;
	private int ruleRow;
	private GridPane myActorRuleCreatorPane;
	private List<ActorRule> myActorRules;
	private ActorEditingEnvironment aEE;
	
	public ActorRuleCreator(ActorEditingEnvironment aEE) {
		this.aEE = aEE;
		initializeEnvironment();
	}
	
	/**
	 * Initialize and create actor rule creator, the rightmost section of the actor editing environment
	 */
	private void initializeEnvironment(){
		ruleRow = RULE_ROW_START;
		myActorRules = new ArrayList<>();
		myActorRuleCreatorPane = new GridPane();
		myActorRuleCreatorPane.setPrefWidth(aEE.getStage().getWidth()*CONTAINERS_PERCENT_WIDTH);
		myActorRuleCreatorPane.setVgap(VGAP);
		myActorRuleCreatorPane.setHgap(HGAP);
	}
	

	/**
	 * Get GridPane representation of actor rule creator
	 * @return
	 */
	public GridPane getGridPane() {
		return myActorRuleCreatorPane;
	}

	/**
	 * Add given behavior to given ActorRule   
	 * @param rule
	 * @param behavior
	 */
	public void addBehavior(ActorRule rule, Label behavior){
		rule.addBehavior(behavior.getText());
	}
	/**
	 * Add given sound to given ActorRule
	 * @param rule
	 * @param sound
	 */
	public void addSound(ActorRule rule, Label sound){
		rule.addSound(sound.getText());
	}
	/**
	 * Add given image to given ActorRule
	 * @param rule
	 * @param image
	 */
	public void addImage(ActorRule rule, Label image){
		rule.addImage(image.getText());
	}
	
	/**
	 * Create new rule for Actor currently in the actor editing environment and add to gridpane
	 */
	public void addNewRule() {  
		ActorRule newRule = new ActorRule(this);
		myActorRuleCreatorPane.add(newRule.getGridPane(), RULE_COL,ruleRow);
		myActorRules.add(newRule);
		ruleRow++;
	}
	
	/**
	 * Remove given rule from environment and from Actor currently in the environment 
	 * @param actorRule
	 */
	public void removeRule(ActorRule actorRule){
		myActorRules.remove(actorRule);
		myActorRuleCreatorPane.getChildren().remove(actorRule.getGridPane());
		//remove IRule from Actor
	}
	
	
	/**
	 * Get ActorRules for Actor currently in the actor editing environment
	 * @return
	 */
	public List<ActorRule> getRules(){
		return myActorRules;
	}
	/**
	 * Each time actor editing environment is opened and set to a specific Actor, populates editing environment rules and
	 * fields based on the Actor 
	 */
	public void updateActorRules() {
		for(ActorRule toRemove: myActorRules) myActorRuleCreatorPane.getChildren().remove(toRemove.getGridPane());
		myActorRules.clear();
		ruleRow = RULE_ROW_START;
		System.out.println(((Actor) aEE.getEditable()).getRules());
		for(String behaviorType: ((Actor) aEE.getEditable()).getRules().keySet()){
			System.out.println("has IRules");
			ActorRule toAdd = new ActorRule(this);
			toAdd.addBehavior(behaviorType);
			for(IRule rule: ((Actor) aEE.getEditable()).getRules().get(behaviorType)){
//				System.out.println("Simple name: " + rule.getMyAction().getClass().getSimpleName());
				toAdd.addBehavior(rule.getMyAction().getClass().getSimpleName()); // name of Action
			}
			myActorRuleCreatorPane.add(toAdd.getGridPane(), RULE_COL, ruleRow);
			myActorRules.add(toAdd);
			ruleRow++;
		}
	}
	
	/**
	 * Get the current IAuthoringActor
	 * @return IAuthoringActor
	 */
	public IAuthoringActor getActor(){
		return (IAuthoringActor) aEE.getEditable();
	}

	public Controller getController() {
		return aEE.getController();
	}
}
