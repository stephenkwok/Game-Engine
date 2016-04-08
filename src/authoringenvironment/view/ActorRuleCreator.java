package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
/**
 * Space in actor editing environment where rules are created.
 * @author AnnieTang
 *
 */
public class ActorRuleCreator {
	private static final int RULE_COL = 0;
	private static final int RULE_ROW_START = 0;
	private static final int VGAP = 10;
	private int rule_row;
	private GridPane myRuleCreator;
	private List<ActorRule> myRules;
	private GUIActorEditingEnvironment aEE; 
	
	public ActorRuleCreator(GUIActorEditingEnvironment aEE) {
		this.aEE = aEE;
		initializeEnvironment();
	}
	
	private void initializeEnvironment(){
		rule_row = RULE_ROW_START;
		myRules = new ArrayList<>();
		myRuleCreator = new GridPane();
		myRuleCreator.setVgap(VGAP);
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
		ActorRule newRule = new ActorRule();
		myRuleCreator.add(newRule.getGridPane(), RULE_COL,rule_row);
		rule_row++;
		myRules.add(newRule);
		aEE.updateDragEventsForLibrary();
	}
	
	public List<ActorRule> getRules(){
		return myRules;
	}

}
