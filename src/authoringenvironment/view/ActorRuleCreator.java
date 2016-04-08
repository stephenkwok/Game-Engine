package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;

import authoringenvironment.controller.Controller;
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
		aEE.updateDragEventsForLibrary();
	}
	
	public List<ActorRule> getRules(){
		return myRules;
	}

}
