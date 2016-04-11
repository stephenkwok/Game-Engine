package authoringenvironment.view;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IEditableGameElement;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gui.view.IGUI;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GUILevelInspector implements IGUI {
	private static final String LEVEL_OPTIONS_RESOURCE = "levelEditorOptions";
	private static final String ACTORS = "Actors";
	private static final String LEVEL_ATTRIBUTES = "Level Attributes";
	private Pane myPane;
	private TabActors myActorsTab;
	private TabAttributes myAttributesTab;
	
	public GUILevelInspector(Controller controller, ResourceBundle myResources, List<Actor> availActors, IEditableGameElement level) {
		myPane = new StackPane();
		TabPane tabPane = new TabPane();
		myActorsTab = new TabActors(myResources, ACTORS, availActors);
		myAttributesTab = new TabAttributes(controller, myResources, LEVEL_ATTRIBUTES,LEVEL_OPTIONS_RESOURCE, level);
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
}
