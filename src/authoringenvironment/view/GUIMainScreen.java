package authoringenvironment.view;

import java.util.List;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GUIMainScreen implements IGUI {
	
	private Controller controller;
	private GUIMain guiMain;
	private List<IEditableGameElement> createdActors;
	private List<IEditableGameElement> createdLevels;
	private VBox actorLabelContainer;
	private VBox levelLabelContainer;
	private ScrollPane actorScrollPane;
	private ScrollPane levelScrollPane;
	private HBox scrollPaneContainer;
	private BorderPane borderPane;
	private IEditingEnvironment levelEditor;
	private IEditingEnvironment actorEditor;
	private List<ClickableLabel> levelLabels;
	private List<ClickableLabel> actorLabels;

	public GUIMainScreen(List<IEditableGameElement> actors, List<IEditableGameElement> levels,
			IEditingEnvironment levelEditor, IEditingEnvironment actorEditor, Controller controller, GUIMain guiMain) {
		createdActors = actors;
		createdLevels = levels;
		this.levelEditor = levelEditor;
		this.actorEditor = actorEditor;
		this.controller = controller;
		this.guiMain = guiMain;
	}

	public void initializeEnvironment() {
		borderPane = new BorderPane();
		scrollPaneContainer = new HBox();
		scrollPaneContainer.prefHeightProperty().bind(borderPane.heightProperty());
		scrollPaneContainer.prefWidthProperty().bind(borderPane.widthProperty());
		actorLabelContainer = new VBox();
		levelLabelContainer = new VBox();
		actorScrollPane = initScrollPane(actorLabelContainer.widthProperty(), actorLabelContainer);
		levelScrollPane = initScrollPane(levelLabelContainer.widthProperty(), levelLabelContainer);
		scrollPaneContainer.getChildren().addAll(levelScrollPane, actorScrollPane);
		borderPane.setCenter(scrollPaneContainer);
	}
	
	private ScrollPane initScrollPane(ReadOnlyDoubleProperty bindingProperty, VBox container) {
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.prefWidthProperty().bind(bindingProperty);
		scrollPane.setContent(container);
		return scrollPane;
	}

	public Pane getPane() {
		return borderPane;
	}

	private void updateLabels(List<ClickableLabel> labels) {
		for (ClickableLabel label : labels) {
			label.update();
		}
	}
	
	@Override
	public void updateAllNodes() {
		updateLabels(levelLabels);
		updateLabels(actorLabels);
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return null;
	}
}
