package authoringenvironment.view;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import gameengine.controller.Level;
import gameengine.model.Rule;
import gui.view.CheckBoxObject;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class TabLevelRuleEditor extends TabParent {
	private static final int CHECK_BOX_WIDTH = 250;
	private static final String DELETE = "Delete";
	private Level myLevel;
	private VBox myContainer;
	private List<Rule> currentRules;
	private VBox myCheckBoxes;
	private List<Rule> newRules;
	private Map<CheckBoxObject, Rule> myRuleMap;
	private Button myDeleteButton;
	
	public TabLevelRuleEditor(ResourceBundle myResources, String tabText, Level level) {
		super(myResources, tabText);
		myLevel = level;
		init();
	}
	
	private void init() {
		myContainer = new VBox();
		myRuleMap = new HashMap<>();
		currentRules = new ArrayList<>();
		newRules = new ArrayList<>();
		myCheckBoxes = new VBox();
		myDeleteButton = new Button(DELETE);
		myDeleteButton.setOnAction(e -> deleteSelectedRules());
		myContainer.getChildren().addAll(myCheckBoxes, myDeleteButton);
		updateRules();
	}
	
	public void updateRules() {
		for (String key: myLevel.getRules().keySet()) {
			for (int i = 0; i < myLevel.getRules().get(key).size(); i++) {
				newRules.add(myLevel.getRules().get(key).get(i));
			}
			currentRules.addAll(newRules);
		}
		updateCheckBoxes();
	}
	
	public void updateCheckBoxes() {
		for (int i = 0; i < newRules.size(); i++) {
			CheckBoxObject checkBox = new CheckBoxObject(newRules.get(i).toString(), CHECK_BOX_WIDTH);
			myRuleMap.put(checkBox, newRules.get(i));
			myCheckBoxes.getChildren().add(checkBox.createNode());
		}
	}
	
	private void deleteSelectedRules() {
		List<CheckBoxObject> toRemove = new ArrayList<>();
		for (CheckBoxObject checkBox: myRuleMap.keySet()) {
			if (((CheckBox) checkBox.createNode()).isSelected()) {
				myLevel.removeRule(myRuleMap.get(checkBox));
				toRemove.add(checkBox);
				myCheckBoxes.getChildren().remove(checkBox);
			}
		}
		for (int i = 0; i < toRemove.size(); i++) {
			myRuleMap.remove(toRemove.get(i));
		}
	}
	
	@Override
	Node getContent()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return myContainer;
	}

}