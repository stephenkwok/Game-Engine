package authoringenvironment.view;

import java.util.ResourceBundle;
import java.util.Set;

import authoringenvironment.controller.LevelEditingEnvironment;
import authoringenvironment.model.IAuthoringActor;
import gui.view.ButtonFileChooserBackgroundImage;
import gui.view.CheckBoxesGarbageCollection;
import gui.view.IGUI;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LevelInspector implements IGUI {
	private static final String BUTTON_LABEL = "Choose a new background image";
	private static final int RULE_ADDER_HEIGHT = 300;
	private static final int RULE_ADDER_WIDTH = 400;
	private static final int TIMER_ADDER_HEIGHT = 300;
	private static final int TIMER_ADDER_WIDTH = 300;
	private static final int PADDING = 10;
	private static final String LEVEL_OPTIONS_RESOURCE = "levelEditorOptions";
	private static final String ACTORS = "Actors";
	private static final String LEVEL_ATTRIBUTES = "Level Attributes";
	private static final int SPACING = 5;
	private static final String EDIT_RULES = "Edit level rules";
	private static final String ADD_TIMER = "Add level timer";
	private static final String PREVIEW = "Preview";
	private Pane myPane;
	private TabActors myActorsTab;
	private TabFields myAttributesTab;
	private VBox myContainer;
	private LevelEditingEnvironment myLevelEditor;
	private CheckBoxesGarbageCollection myGarbageCollector;

	/**
	 * Constructor for Level Inspector.
	 * 
	 * @param controller:
	 *            authoring environment controller.
	 * @param myResources:
	 *            resource bundle for authoring environment.
	 * @param availActors:
	 *            list of currently available actors.
	 * @param level:
	 *            level that is being edited.
	 */
	public LevelInspector(ResourceBundle myResources, Set<IAuthoringActor> availActors,
			LevelEditingEnvironment editor) {
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
		myAttributesTab = new TabFields(myResources, LEVEL_ATTRIBUTES,LEVEL_OPTIONS_RESOURCE, myLevelEditor.getLevel());
		myAttributesTab.setObserver(myLevelEditor);
		HBox buttonBox = addEditingButtons();
		ButtonFileChooserBackgroundImage button = new ButtonFileChooserBackgroundImage(BUTTON_LABEL, null, myLevelEditor, myLevelEditor.getStage());
		button.addObserver(myLevelEditor);
		myAttributesTab.addElement(button);
		myGarbageCollector = new CheckBoxesGarbageCollection();
		myAttributesTab.addElement(myGarbageCollector);
		addTabToContainer(myAttributesTab, false);
		myContainer.getChildren().add(buttonBox);
		addTabToContainer(myActorsTab, true);
		addPreviewButton();
	}
	
	public CheckBoxesGarbageCollection getGarbageCollector() {
		return myGarbageCollector;
	}
	
	private HBox addEditingButtons() {
		Button addRuleButton = new Button(EDIT_RULES);
		addRuleButton.setOnAction(e -> new PopUpRuleAdder(RULE_ADDER_WIDTH, RULE_ADDER_HEIGHT, myLevelEditor));
		Button addTimerButton = new Button(ADD_TIMER);
		addTimerButton.setOnAction(e -> new PopUpAddLevelTimer(TIMER_ADDER_WIDTH, TIMER_ADDER_HEIGHT, myLevelEditor.getLevel()));
		HBox box = new HBox(PADDING);
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(addRuleButton, addTimerButton);
		return box;
	}
	
	private void addPreviewButton() {
		Button myB = new Button(PREVIEW);
		myB.setOnMouseClicked(e -> myLevelEditor.previewGame());
		myB.setLayoutX(100);
		myB.setLayoutY(300);
		myContainer.getChildren().add(myB);
	}

	/**
	 * Adds a tab to the Level Inspector's container.
	 * 
	 * @param tab:
	 *            tab to add.
	 * @param bindToContainer:
	 *            true if you want tab to bind to its individual container's
	 *            height; false o.w.
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
	 * 
	 * @return tab of available actors.
	 */
	public TabActors getActorsTab() {
		return myActorsTab;
	}

	/**
	 * Returns the tab of level attributes.
	 * 
	 * @return tab of level attributes.
	 */
	public TabFields getAttributesTab() {
		return myAttributesTab;
	}
}
