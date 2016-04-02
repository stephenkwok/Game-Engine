package authoringenvironment.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import authoringenvironment.controller.Controller;
import gameengine.controller.ILevel;

public class ComboBoxLevel extends ComboBoxTextCell{
	private Map<String, ILevel> levelMap;
	private List<String> levelNames;
	List<ILevel> levels;
	
	public ComboBoxLevel(ResourceBundle myResources, String promptText, Controller myController) {
		super(myResources,promptText, myController);
		levels = myController.getLevels();
		levelMap = new HashMap<>();
		levelNames = new ArrayList<>();
		fillLevelNamesAndMap();
	}

	private void fillLevelNamesAndMap(){
		for(ILevel level: levels){
//			levelNames.add(level.getLevelInfo().get("Name"));
//			levelMap.put(level.getLevelInfo().get("Name"), level);
		}
	}
	
	@Override
	void setButtonAction() {
		comboButton.setOnAction(event -> {
			String levelName = comboBox.getValue();
			ILevel level = levelMap.get(levelName);
//			mainScreen.goToLevelEditing(level, level.getLevelActors());
		});
	}

	@Override
	List<String> getOptionsList() {
		return levelNames;
	}
}
