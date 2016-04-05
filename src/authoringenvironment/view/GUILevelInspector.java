package authoringenvironment.view;

import java.util.List;
import java.util.ResourceBundle;

import gameengine.actors.Actor;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GUILevelInspector implements IGUI {
	private static final String LEVEL_OPTIONS_RESOURCE = "levelEditorOptions";
	private ResourceBundle myResources;
	private static final String ACTORS = "Actors";
	private static final String LEVEL_ATTRIBUTES = "Level Attributes";
	private Pane myPane;
	private TabActors myActorsTab;
	private TabAttributes myAttributesTab;
	
	public GUILevelInspector(ResourceBundle myResources, List<Actor> availActors) {
		this.myResources = myResources;
		myPane = new StackPane();
		TabPane tabPane = new TabPane();
		myActorsTab = new TabActors(myResources, ACTORS, availActors);
		myAttributesTab = new TabAttributes(myResources, LEVEL_ATTRIBUTES,LEVEL_OPTIONS_RESOURCE);
		tabPane.getTabs().addAll(myActorsTab.getTab(), myAttributesTab.getTab());
		myPane.getChildren().add(tabPane);
	}

	@Override
	public Pane getPane() {
		return myPane;
	}
	
	public TabActors getActorsTab() {
		return myActorsTab;
	}
	
	public TabAttributes getAttributesTab() {
		return myAttributesTab;
	}

	@Override
	public void updateAllNodes() {
		// TODO Auto-generated method stub
		
	}

}
