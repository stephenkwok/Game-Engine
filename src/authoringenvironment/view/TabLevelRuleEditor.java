package authoringenvironment.view;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import gameengine.controller.Level;
import gameengine.model.Rule;
import gui.view.CheckBoxObject;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

/**
 * Tab to remove level rules.
 * @author amyzhao
 *
 */
public class TabLevelRuleEditor extends TabParent {
	private static final int CHECK_BOX_WIDTH = 450;
	private static final int PADDING = 10;
	private static final String DELETE = "Delete";
	private Level myLevel;
	private VBox myContainer;
	private List<Rule> currentRules;
	private VBox myCheckBoxes;
	private List<Rule> newRules;
	private Map<CheckBoxObject, Rule> myRuleMap;
	private Button myDeleteButton;
	
	/**
	 * Constructor for TabLevelRuleEditor
	 * @param myResources: resource bundle to use.
	 * @param tabText: title of tab.
	 * @param level: level to edit.
	 */
	public TabLevelRuleEditor(ResourceBundle myResources, String tabText, Level level) {
		super(myResources, tabText);
		myLevel = level;
		init();
	}
	
	/**
	 * Initialize the containers.
	 */
	private void init() {
		myContainer = new VBox(PADDING);
		myContainer.setAlignment(Pos.TOP_CENTER);
		myContainer.setPadding(new Insets(PADDING));
		myRuleMap = new HashMap<>();
		currentRules = new ArrayList<>();
		newRules = new ArrayList<>();
		myCheckBoxes = new VBox(PADDING);
		myDeleteButton = new Button(DELETE);
		myDeleteButton.setOnAction(e -> deleteSelectedRules());
		myContainer.getChildren().addAll(myCheckBoxes, myDeleteButton);
		updateRules();
	}
	
	/**
	 * Update the rules shown in the vbox via checkboxes.
	 */
	public void updateRules() {
		newRules.clear();
		for (String key: myLevel.getRules().keySet()) {
			for (int i = 0; i < myLevel.getRules().get(key).size(); i++) {
				if (!currentRules.contains(myLevel.getRules().get(key).get(i))) {
					newRules.add(myLevel.getRules().get(key).get(i));
				}
			}
		}
		currentRules.addAll(newRules);
		updateCheckBoxes();
	}
	
	/**
	 * Update the checkboxes shown in the vbox.
	 */
	public void updateCheckBoxes() {
		for (int i = 0; i < newRules.size(); i++) {
			CheckBoxObject checkBox = new CheckBoxObject(newRules.get(i).toString(), CHECK_BOX_WIDTH);
			myRuleMap.put(checkBox, newRules.get(i));
			myCheckBoxes.getChildren().add(checkBox.createNode());
		}
	}
	
	/**
	 * Delete the checked off rules.
	 */
	private void deleteSelectedRules() {
		List<CheckBoxObject> toRemove = new ArrayList<>();
		for (CheckBoxObject checkBox: myRuleMap.keySet()) {
			if (((CheckBox) checkBox.createNode()).isSelected()) {
				myLevel.removeRule(myRuleMap.get(checkBox));
				toRemove.add(checkBox);
				currentRules.remove(myRuleMap.get(checkBox));
			}
		}
		for (int i = 0; i < toRemove.size(); i++) {
			myRuleMap.remove(toRemove.get(i));
		}
		
		newRules = currentRules;
		myCheckBoxes.getChildren().clear();
		updateCheckBoxes();
	}
	
	/**
	 * Returns container.
	 */
	@Override
	Node getContent()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return myContainer;
	}

}