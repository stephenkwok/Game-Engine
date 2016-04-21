package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IAuthoringActor;
import gameengine.model.IRule;
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
	private GridPane myActorRuleCreatorPane;
	private List<ActorRule> myActorRules;
	private IAuthoringActor myActor;
	private double sceneWidth;
	private Controller myController;
	
	private List<IRule> myIRules;
	
	public ActorRuleCreator(IAuthoringActor myActor,double sceneWidth,Controller myController) {
		this.myActor = myActor;
		this.sceneWidth = sceneWidth;
		this.myController = myController;
		initializeEnvironment();
	}
	
	/**
	 * Initialize and create actor rule creator, the rightmost section of the actor editing environment
	 */
	private void initializeEnvironment(){
		rule_row = RULE_ROW_START;
		myActorRules = new ArrayList<>();
		myActorRuleCreatorPane = new GridPane();
		myActorRuleCreatorPane.setPrefWidth(sceneWidth*CONTAINERS_PERCENT_WIDTH);
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
	 * Create new rule for Actor currently in the actor editing environment and add to gridpane
	 */
	public void addNewRule() {
		ActorRule newRule = new ActorRule(this, myController);
		myActorRuleCreatorPane.add(newRule.getGridPane(), RULE_COL,rule_row);
		rule_row++;
		myActor.getActorRules().add(newRule);
	}
	/**
	 * Remove given rule from environment and from Actor currently in the environment 
	 * @param actorRule
	 */
	public void removeRule(ActorRule actorRule){
		myActorRules.remove(actorRule);
		myActorRuleCreatorPane.getChildren().remove(actorRule.getGridPane());
		myActor.getActorRules().remove(actorRule);
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
		for(ActorRule toRemove: myActorRules){
			myActorRuleCreatorPane.getChildren().remove(toRemove.getGridPane());
		}
		myActorRules = myActor.getActorRules();
		addUpdatedRules();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           ;
	}
	/**
	 * Populate actor editing environment with current Actor's rules 
	 */
	private void addUpdatedRules(){
		//recreate from IRules?
		rule_row = RULE_ROW_START;
		for(ActorRule toAdd: myActorRules){
			myActorRuleCreatorPane.add(toAdd.getGridPane(), RULE_COL, rule_row);
			rule_row++;
		}
	}
	/**
	 * Set the Actor that the ActorRules are being added to/removed from
	 * @param myActor
	 */
	public void setActor(IAuthoringActor myActor) {
		this.myActor = myActor;
	}
	/**
	 * Get the current IAuthoringActor
	 * @return IAuthoringActor
	 */
	public IAuthoringActor getActor(){
		return this.myActor;
	}
}
