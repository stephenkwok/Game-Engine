package authoringenvironment.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import gameengine.controller.ILevel;
import gameengine.controller.Level;

/**
 * ComboBox that holds existing Levels and allows user to go to LevelEditingEnvironment to edit selected level
 * @author AnnieTang
 *
 */

public class ComboBoxLevel extends ComboBoxTextCell{
	private Map<String, ILevel> levelMap;
	private List<String> levelNames;
	List<Level> levels;
	private Controller myController;
	
	public ComboBoxLevel(ResourceBundle myResources, String promptText, Controller myController) {
		super(myResources,promptText);
		this.myController = myController;
		fillLevelNamesAndMap();
	}

	private void fillLevelNamesAndMap(){
		levels = myController.getLevels();
		levelMap = new HashMap<>();
		levelNames = new ArrayList<>();
		for(ILevel level: levels){
			levelNames.add(level.getName());
			levelMap.put(level.getName(), level);
		}
	}
	
	@Override
	void setButtonAction() {
		comboButton.setOnAction(event -> {
			String levelName = comboBox.getValue();
			ILevel level = levelMap.get(levelName);
			myController.goToLevelEditing(level);
		});
	}

	@Override
	List<String> getOptionsList() {
		levelNames.add("default");
		return levelNames;
	}
}
