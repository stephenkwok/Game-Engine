package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IAuthoringActor;
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
	private int rule_row;
	private GridPane myRuleCreator;
	private List<ActorRule> myRules;
	private ActorEditingEnvironment aEE; 
	private Controller myController;
	
	public ActorRuleCreator(ActorEditingEnvironment aEE, Controller myController) {
		this.aEE = aEE;
		this.myController = myController;
		initializeEnvironment();
	}
	
	/**
	 * Initialize and create actor rule creator, the rightmost section of the actor editing environment
	 */
	private void initializeEnvironment(){
		rule_row = RULE_ROW_START;
		myRules = new ArrayList<>();
		myRuleCreator = new GridPane();
		myRuleCreator.setPrefWidth(myController.getSceneWidth()*CONTAINERS_PERCENT_WIDTH);
		myRuleCreator.setVgap(VGAP);
		myRuleCreator.setHgap(HGAP);
	}

	/**
	 * Get GridPane representation of actor rule creator
	 * @return
	 */
	public GridPane getGridPane() {
		return myRuleCreator;
	}

	/**
	 * Add given behavior to given ActorRule   
	 * @param rule
	 * @param behavior
	 */
	public void addBehavior(ActorRule rule, Label behavior){
		rule.addBehavior(behavior);
	}
	/**
	 * Add given sound to given ActorRule
	 * @param rule
	 * @param sound
	 */
	public void addSound(ActorRule rule, Label sound){
		rule.addSound(sound);
	}
	/**
	 * Add given image to given ActorRule
	 * @param rule
	 * @param image
	 */
	public void addImage(ActorRule rule, Label image){
		rule.addImage(image);
	}
	/**
	 * Create new rule for Actor currently in the actor editing environment
	 */
	public void addNewRule() {
		ActorRule newRule = new ActorRule(this);
		myRuleCreator.add(newRule.getGridPane(), RULE_COL,rule_row);
		rule_row++;
		((IAuthoringActor) aEE.getEditable()).addActorRule(newRule);
		aEE.updateDragEventsForLibrary();
	}
	/**
	 * Remove given rule from Actor currently in the actor editing environment 
	 * @param actorRule
	 */
	public void removeRule(ActorRule actorRule){
		myRules.remove(actorRule);
		myRuleCreator.getChildren().remove(actorRule.getGridPane());
		((IAuthoringActor) aEE.getEditable()).removeActorRule(actorRule);
	}
	/**
	 * Get ActorRules for Actor currently in the actor editing environment
	 * @return
	 */
	public List<ActorRule> getRules(){
		return myRules;
	}
	/**
	 * Return reference to Controller
	 * @return
	 */
	public Controller getController(){
		return myController;
	}
	/**
	 * Each time actor editing environment is opened and set to a specific Actor, populates editing environment rules and
	 * fields based on the Actor 
	 */
	public void updateRules() {
		for(ActorRule toRemove: myRules){
			myRuleCreator.getChildren().remove(toRemove.getGridPane());
		}
		myRules = ((IAuthoringActor) aEE.getEditable()).getActorRules();
		addUpdatedRules()                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    ;
	}
	/**
	 * Populate actor editing environment with current Actor's rules 
	 */
	private void addUpdatedRules(){
		rule_row = RULE_ROW_START;
		for(ActorRule toAdd: myRules){
			myRuleCreator.add(toAdd.getGridPane(), RULE_COL, rule_row);
			rule_row++;
		}
		aEE.updateDragEventsForLibrary();
	}

}
