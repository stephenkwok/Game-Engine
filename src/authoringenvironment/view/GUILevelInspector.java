package authoringenvironment.view;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.view.LevelEditingEnvironment;
import gui.view.ButtonFileChooserBackgroundImage;
import gui.view.IGUI;
import javafx.geometry.Pos;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUILevelInspector implements IGUI {
	private static final String BUTTON_LABEL = "Choose a new background image";
	private static final int BUTTON_HEIGHT = 30;
	private static final int BUTTON_WIDTH = 300;
	private static final String LEVEL_OPTIONS_RESOURCE = "levelEditorOptions";
	private static final String ACTORS = "Actors";
	private static final String LEVEL_ATTRIBUTES = "Level Attributes";
	private static final int SPACING = 5;
	private Pane myPane;
	private TabActors myActorsTab;
	private TabAttributes myAttributesTab;
	private VBox myContainer;
	private LevelEditingEnvironment myLevelEditor;
	private Stage myStage;
	
	/**
	 * Constructor for Level Inspector.
	 * @param controller: authoring environment controller.
	 * @param myResources: resource bundle for authoring environment.
	 * @param availActors: list of currently available actors.
	 * @param level: level that is being edited.
	 */
	public GUILevelInspector(ResourceBundle myResources, Set<IAuthoringActor> availActors, LevelEditingEnvironment editor, Stage stage) {
		myStage = stage;
		myLevelEditor = editor;
		init(myResources, availActors);
	}
	
	private void init(ResourceBundle myResources, Set<IAuthoringActor> availActors) {
		myPane = new StackPane();		
		myContainer = new VBox(SPACING);
		myContainer.setAlignment(Pos.CENTER);
		addChildrenToLevelInspector(myResources, availActors);
		myPane.getChildren().addAll(myContainer);
	}

	private void addChildrenToLevelInspector(ResourceBundle myResources, Set<IAuthoringActor> availActors) {
		myActorsTab = new TabActors(myResources, ACTORS, availActors);
		myAttributesTab = new TabAttributes(myResources, LEVEL_ATTRIBUTES,LEVEL_OPTIONS_RESOURCE, myLevelEditor.getLevel());
		// TODO: take out the null where the controller is later
		ButtonFileChooserBackgroundImage button = new ButtonFileChooserBackgroundImage(null, BUTTON_LABEL, null, myLevelEditor);
		addTabToContainer(myAttributesTab, false);
		myContainer.getChildren().add(button.createNode());
		addTabToContainer(myActorsTab, true);
	}
	/**
	 * Adds a tab to the Level Inspector's container.
	 * @param tab: tab to add.
	 * @param bindToContainer: true if you want tab to bind to its individual container's height; false o.w.
	 */
	private void addTabToContainer(TabParent tab, boolean bindToContainer) {
		VBox container = new VBox();
		TabPane tabPane = new TabPane();
		container.getChildren().add(tabPane);
		tabPane.getTabs().add(tab.getTab());
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		if (bindToContainer) {
			VBox.setVgrow(container, Priority.ALWAYS);
			tabPane.prefHeightProperty().bind(container.heightProperty());
		}
		
		myContainer.getChildren().add(container);
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
