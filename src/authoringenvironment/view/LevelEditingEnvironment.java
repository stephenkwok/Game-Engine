package authoringenvironment.view;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.controller.Level;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Level editing environment
 * 
 * @author amyzhao
 *
 */
public class LevelEditingEnvironment implements IEditingEnvironment, Observer {
	private static final String GUI_RESOURCE = "authoringGUI";
	private static final double SUBSCENE_WIDTH = 1000;
	private static final double SUBSCENE_HEIGHT = 575;
	private BorderPane myRoot;
	private GUILevelInspector myInspector;
	private ResourceBundle myResources;
	private VBox myLeftPane;
	private Level myLevel;
	private Map<IAuthoringActor, List<IAuthoringActor>> availableActors;
	private Pane myCenterPane;
	private Stage myStage;
	private Controller myController;
	private LevelPreview myLevelPreview;

	/**
	 * Constructor for a level editing environment.
	 * 
	 * @param controller:
	 *            authoring environment controller.
	 * @param actors:
	 *            list of currently available actors.
	 */
	public LevelEditingEnvironment(Map<IAuthoringActor, List<IAuthoringActor>> actors, Stage stage,
			Controller controller) {
		myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		availableActors = actors;
		myRoot = new BorderPane();
		myStage = stage;
		myController = controller;
		initializeEnvironment();
	}

	/**
	 * Initializes the level editing environment's center pane and left pane, as
	 * well as the ability to drag actors between the left and center pane.
	 */
	private void initializeEnvironment() {
		myRoot.setStyle("-fx-background-color: darkgray");
		initializeCenter();
		initializeLeftPane();
		updateDrag();
	}

	/**
	 * Initializes the left pane with a level inspector and an image/sound
	 * library.
	 */
	private void initializeLeftPane() {
		myLeftPane = new VBox();
		myLeftPane.prefHeightProperty().bind(myRoot.heightProperty());
		myRoot.setLeft(myLeftPane);
		myLeftPane.setMaxWidth(myStage.getWidth() - SUBSCENE_WIDTH);
		addChildrenToLeftPane();
	}

	private void addChildrenToLeftPane() {
		myInspector = new GUILevelInspector(myResources, availableActors.keySet(), this);
		myLeftPane.getChildren().add(myInspector.getPane());
		myInspector.getPane().prefHeightProperty().bind(myLeftPane.heightProperty());
	}

	/**
	 * Updates the drag behavior of the level editing environment to accommodate
	 * updates to currently available actors.
	 */
	private void updateDrag() {
		List<ImageviewActorIcon> icons = myInspector.getActorsTab().getIcons();
		setCenterPaneDragOver();
		setCenterPaneDragDropped();
		for (int i = 0; i < icons.size(); i++) {
			ImageviewActorIcon source = icons.get(i);
			setDragDetected(source);
			source.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
						if (mouseEvent.getClickCount() == 2) {
							System.out.println("Double clicked");
							myController.goToEditingEnvironment(source.getRefActor(),
									myController.getActorEditingEnvironment());
						}
					}
				}
			});
		}
	}

	/**
	 * Start drag and drop when drag is detected.
	 * 
	 * @param source:
	 *            node to be dragged.
	 */
	private void setDragDetected(ImageviewActorIcon source) {
		source.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Dragboard db = source.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString(Integer.toString(source.getID()));
				db.setContent(content);
				event.consume();
			}
		});
	}

	/**
	 * When dragged over the center pane, copy the gesture source.
	 */
	private void setCenterPaneDragOver() {
		myLevelPreview.getLevelPane().setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getGestureSource() != myLevelPreview.getLevelPane() && event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});
	}

	/**
	 * When an actor icon is dropped on the center pane, add its actor to the
	 * center pane and set the actor so that it moves in response to mouse
	 * drags.
	 */
	private void setCenterPaneDragDropped() {
		myLevelPreview.getLevelPane().setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
					ImageviewActorIcon icon = getIconById(Integer.parseInt(db.getString()));
					List<IAuthoringActor> val = availableActors.get(icon.getRefActor());
					IAuthoringActor actor = icon.getActor();
					val.add(actor);
					availableActors.put(icon.getRefActor(), val);
					myLevel.addActor(actor);
					//myLevel.addActor(icon.getRefActor());
					myLevelPreview.addActorToScene(actor);
					//myLevelPreview.addActorToScene(icon.getRefActor());
					success = true;
				}
				event.setDropCompleted(success);
				event.consume();
			}
		});
	}

	/**
	 * Gets an actor by its ID.
	 * 
	 * @param id:
	 *            ID of actor of interest.
	 * @return actor with given ID.
	 */
	private ImageviewActorIcon getIconById(int id) {
		List<ImageviewActorIcon> icons = myInspector.getActorsTab().getIcons();
		for (int i = 0; i < icons.size(); i++) {
			if (icons.get(i).getID() == id) {
				return icons.get(i);
			}
		}
		return null;
	}

	/**
	 * Initialize the center pane.
	 */
	private void initializeCenter() {
		myLevelPreview = new LevelPreview(this);
		myCenterPane = myLevelPreview.getPane();
		myRoot.setCenter(myCenterPane);
	}

	/**
	 * Get LevelEditingEnvironment's root pane.
	 */
	@Override
	public Pane getPane() {
		return myRoot;
	}

	/**
	 * Update the level that is being edited and update the level inspector to
	 * reflect the current level.
	 */
	@Override
	public void setEditableElement(IEditableGameElement editable) {
		myLevel = (Level) editable;
		myLevelPreview.updateLevelPreview(myLevel);
		updateActorsList();
		myInspector.getAttributesTab().updateEditable(myLevel);
	}

	public void changeBackgroundImage(Image image, File imageFile) {
		myLevelPreview.changeBackgroundImage(image, imageFile);
	}

	/**
	 * Update the list of available actors and update the level inspector to
	 * reflect the currently available actors.
	 * 
	 * @param updatedActorsList:
	 *            up-to-date list of available actors.
	 */
	public void updateActorsList() {
		myInspector.getActorsTab().setAvailableActors(availableActors.keySet());
		updateDrag();
	}

	public Level getLevel() {
		return myLevel;
	}

	@Override
	public Stage getStage() {
		return myStage;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == null) {
			myLevelPreview.resizeBackgroundBasedOnScrolling();
		} else {
			myController.updateRefActorSize((IAuthoringActor) arg);
			myLevelPreview.addLevelActorsToScene();
		}
	}

	public Controller getController() {
		return myController;
	}
}