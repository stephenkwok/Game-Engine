package authoringenvironment.view;

import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IEditableGameElement;
import gameengine.model.Actor;
import gui.view.IGUI;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GUILevelInspector implements IGUI {
	private static final String LEVEL_OPTIONS_RESOURCE = "levelEditorOptions";
	private static final String ACTORS = "Actors";
	private static final String LEVEL_ATTRIBUTES = "Level Attributes";
	private Pane myPane;
	private TabActors myActorsTab;
	private TabAttributes myAttributesTab;
	
	/**
	 * Constructor for Level Inspector.
	 * @param controller: authoring environment controller.
	 * @param myResources: resource bundle for authoring environment.
	 * @param availActors: list of currently available actors.
	 * @param level: level that is being edited.
	 */
	public GUILevelInspector(Controller controller, ResourceBundle myResources, List<Actor> availActors, IEditableGameElement level) {
		myPane = new StackPane();
		TabPane tabPane = new TabPane();
		myActorsTab = new TabActors(myResources, ACTORS, availActors);
		myAttributesTab = new TabAttributes(controller, myResources, LEVEL_ATTRIBUTES,LEVEL_OPTIONS_RESOURCE, level);
		tabPane.getTabs().addAll(myActorsTab.getTab(), myAttributesTab.getTab());
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		myPane.getChildren().add(tabPane);
	}

	/**
	 * Returns the pane that the level inspector is built on.
	 */
	@Override
	public Pane getPane() {
		return myPane;
	}
	
	/**
	 * Returns the tab of available actors.
	 * @return tab of available actors.
	 */
	public TabActors getActorsTab() {
		return myActorsTab;
	}
	
	/**
	 * Returns the tab of level attributes.
	 * @return tab of level attributes.
	 */
	public TabAttributes getAttributesTab() {
		return myAttributesTab;
	}
}
