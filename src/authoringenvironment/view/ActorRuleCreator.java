package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;

import authoringenvironment.controller.Controller;
import gameengine.model.Actor;
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
	private GUIActorEditingEnvironment aEE; 
	private Controller myController;
	
	public ActorRuleCreator(GUIActorEditingEnvironment aEE, Controller myController) {
		this.aEE = aEE;
		this.myController = myController;
		initializeEnvironment();
	}
	
	private void initializeEnvironment(){
		rule_row = RULE_ROW_START;
		myRules = new ArrayList<>();
		myRuleCreator = new GridPane();
		myRuleCreator.setPrefWidth(myController.getSceneWidth()*CONTAINERS_PERCENT_WIDTH);
		myRuleCreator.setVgap(VGAP);
		myRuleCreator.setHgap(HGAP);
	}

	public GridPane getGridPane() {
		return myRuleCreator;
	}

	public void addBehavior(ActorRule rule, Label behavior){
		rule.addBehavior(behavior);
	}
	
	public void addSound(ActorRule rule, Label sound){
		rule.addSound(sound);
	}
	
	public void addImage(ActorRule rule, Label image){
		rule.addImage(image);
	}

	public void addNewRule() {
		ActorRule newRule = new ActorRule(this);
		myRuleCreator.add(newRule.getGridPane(), RULE_COL,rule_row);
		rule_row++;
		myRules.add(newRule);
		((Actor) aEE.getEditable()).addActorRule(newRule);
		aEE.updateDragEventsForLibrary();
	}
	
	public void removeRule(ActorRule actorRule){
		myRules.remove(actorRule);
		myRuleCreator.getChildren().remove(actorRule.getGridPane());
		((Actor) aEE.getEditable()).removeActorRule(actorRule);
	}
	
	public List<ActorRule> getRules(){
		return myRules;
	}
	
	public Controller getController(){
		return myController;
	}

	public void updateRules() {
		for(ActorRule toRemove: myRules){
			myRuleCreator.getChildren().remove(toRemove.getGridPane());
			((Actor) aEE.getEditable()).removeActorRule(toRemove);
		}
		myRules = ((Actor) aEE.getEditable()).getActorRules();
		addUpdatedRules();
	}
	private void addUpdatedRules(){
		rule_row = RULE_ROW_START;
		for(ActorRule toAdd: myRules){
			myRuleCreator.add(toAdd.getGridPane(), RULE_COL, rule_row);
		}
		aEE.updateDragEventsForLibrary();
	}

}
