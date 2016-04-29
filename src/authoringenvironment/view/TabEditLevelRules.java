package authoringenvironment.view;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import gameengine.controller.Level;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class TabEditLevelRules extends TabParent {
	private Level myLevel;
	private VBox myContainer;
	
	public TabEditLevelRules(ResourceBundle myResources, String tabText, Level level) {
		super(myResources, tabText);
		myLevel = level;
	}

	@Override
	Node getContent()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

}