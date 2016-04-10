package gui.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import gameengine.controller.Level;
import gui.controller.IScreenController;

/**
 * ComboBox that holds existing Levels and allows user to go to LevelEditingEnvironment to edit selected level
 * @author AnnieTang
 *
 */

public class ComboBoxLevel extends ComboBoxTextCell{
	private Map<String, Level> levelMap;
	private List<String> levelNames;
	List<Level> levels;
	private Controller myController;
	
	public ComboBoxLevel(ResourceBundle myResources, String promptText, Controller myController) {
		super(myResources,promptText);
		this.myController = (Controller) myController;
		fillLevelNamesAndMap();
	}

	private void fillLevelNamesAndMap(){
		levels = myController.getLevels();
		levelMap = new HashMap<>();
		levelNames = new ArrayList<>();
		for(Level level: levels){
			levelNames.add(level.getName());
			levelMap.put(level.getName(), level);
		}
	}
	
	@Override
	public void setButtonAction() {
		comboButton.setOnAction(event -> {
			String levelName = comboBox.getValue();
			Level level = levelMap.get(levelName);
			myController.goToLevelEditing(level);
		});
	}

	@Override
	public List<String> getOptionsList() {
		levelNames.add("default");
		return levelNames;
	}
}
