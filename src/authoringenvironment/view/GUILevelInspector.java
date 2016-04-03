package authoringenvironment.view;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GUILevelInspector implements IGUI {
	private ResourceBundle myResources;
	private static final String TAB_NAME = "Inspector";
	private static final String ACTORS = "Actors";
	private static final String LEVEL_ATTRIBUTES = "Level Attributes";
	
	public GUILevelInspector(ResourceBundle myResources) {
		this.myResources = myResources;
	}
	@Override
	public Scene getScene()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pane getPane() {
		StackPane wrapper = new StackPane();
		TabPane tabPane = new TabPane();
		TabActors actors = new TabActors(myResources, ACTORS);
		TabLevelAttributes attr = new TabLevelAttributes(myResources, LEVEL_ATTRIBUTES);
		tabPane.getTabs().addAll(actors.createTab(), attr.createTab());
		wrapper.getChildren().add(tabPane);
		return wrapper;
	}

	@Override
	public void updateAllNodes() {
		// TODO Auto-generated method stub
		
	}

}
