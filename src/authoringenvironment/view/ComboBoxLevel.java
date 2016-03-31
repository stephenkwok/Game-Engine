package authoringenvironment.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import authoringenvironment.controller.MainScreen;
import authoringenvironment.model.ICreatedLevel;

public class ComboBoxLevel extends ComboBoxTextCell{
	private Map<String, ICreatedLevel> levelMap;
	private List<String> levelNames;
	List<ICreatedLevel> levels;
	
	public ComboBoxLevel(ResourceBundle myResources, String promptText, MainScreen mainScreen) {
		super(myResources,promptText, mainScreen);
		levels = mainScreen.getLevels();
		levelMap = new HashMap<>();
		levelNames = new ArrayList<>();
		fillLevelNamesAndMap();
	}

	private void fillLevelNamesAndMap(){
		for(ICreatedLevel level: levels){
			levelNames.add(level.getLevelInfo().get("Name"));
			levelMap.put(level.getLevelInfo().get("Name"), level);
		}
	}
	
	@Override
	void setButtonAction() {
		comboButton.setOnAction(event -> {
			String levelName = comboBox.getValue();
			ICreatedLevel level = levelMap.get(levelName);
			mainScreen.goToLevelEditing(level, level.getLevelActors());
		});
	}

	@Override
	List<String> getOptionsList() {
		return levelNames;
	}
}
