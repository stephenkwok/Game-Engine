package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import authoringenvironment.controller.Controller;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gameengine.model.IAuthoringActor;
import gui.view.GUILibrary;
import gui.view.IGUI;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Level editing environment
 * @author amyzhao
 *
 */
public class GUILevelEditingEnvironment implements IGUI, IEditingEnvironment {
	private static final String GUI_RESOURCE = "authoringGUI";
	private BorderPane myRoot;
	private GUILibrary myLibrary;
	private GUILevelInspector myInspector;
	private ResourceBundle myResources;
	private VBox myLeftPane;
	private Canvas myCanvas;
	private Level myLevel;
	private List<IAuthoringActor> availableActors;
	private Pane myCenterPane;
	private ImageView myLevelBackground;
	private Controller myController;

	/**
	 * Constructor for a level editing environment.
	 * @param controller: authoring environment controller.
	 * @param actors: list of currently available actors.
	 */
	public GUILevelEditingEnvironment(Controller controller, List<IAuthoringActor> actors) {
		myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		availableActors = actors;
		myRoot = new BorderPane();
		myController = controller;
		initializeEnvironment();
	}

	/**
	 * Initializes the level editing environment's center pane and left pane, as well as the ability to drag actors between
	 * the left and center pane.
	 */
	private void initializeEnvironment() {
		initializeCenter();
		initializeLeftPane();
		updateDrag();
	}

	/**
	 * Initializes the left pane with a level inspector and an image/sound library.
	 */
	private void initializeLeftPane() {
		myLeftPane = new VBox();
		myLeftPane.prefHeightProperty().bind(myRoot.heightProperty());
		myInspector = new GUILevelInspector(myController, myResources, availableActors, myLevel);
		myLibrary = new GUILibrary();
		myLeftPane.getChildren().addAll(myInspector.getPane(), myLibrary.getPane());
		myRoot.setLeft(myLeftPane);
	}

	/**
	 * Updates the drag behavior of the level editing environment to accommodate updates to currently available actors.
	 */
	private void updateDrag() {
		List<ImageviewActorIcon> icons = myInspector.getActorsTab().getIcons();
		setCenterPaneDragOver();
		setCenterPaneDragDropped();
		for (int i = 0; i < icons.size(); i++) {
			ImageviewActorIcon source = icons.get(i);
			setDragDetected(source);
		}
	}

	/**
	 * Start drag and drop when drag is detected.
	 * @param source: node to be dragged.
	 */
	private void setDragDetected(ImageviewActorIcon source) {
		source.setOnDragDetected(new EventHandler <MouseEvent>() {
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
		myCenterPane.setOnDragOver(new EventHandler <DragEvent>() {
			public void handle(DragEvent event) {
			if (event.getGestureSource() != myCenterPane &&
						event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});
	}

	/**
	 * When an actor icon is dropped on the center pane, add its actor to the center pane and set the actor so that it moves
	 * in response to mouse drags.
	 */
	private void setCenterPaneDragDropped() {
		myCenterPane.setOnDragDropped(new EventHandler <DragEvent>() {
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
					IAuthoringActor actor = getActorById(Integer.parseInt(db.getString()));
					ImageView actorIV = actor.getImageView();
					actorIV.setOnDragDetected(null);
					actorIV.setOnMouseDragged(new EventHandler<MouseEvent>() {
						@Override public void handle(MouseEvent event) {
							moveActor(actor, actorIV, event);
							event.consume();
						}
					}); 
					myLevel.addActor((Actor) actor);
					myCenterPane.getChildren().add(actorIV);
					success = true;
				}
				event.setDropCompleted(success);
				event.consume();
			}
		});
	}
	
	/**
	 * Move an actor by changing its (x, y)-coordinates and visualize by moving its imageview.
	 * @param actor: actor to move.
	 * @param actorIV: Imageview of actor to move.
	 * @param event: drag.
	 */
	private void moveActor(IAuthoringActor actor, ImageView actorIV, MouseEvent event) {
		actor.setX(event.getX());
		actor.setY(event.getY());
		actorIV.setX(event.getX());
		actorIV.setY(event.getY());
	}
	
	/**
	 * Gets an actor by its ID.
	 * @param id: ID of actor of interest.
	 * @return actor with given ID.
	 */
	private IAuthoringActor getActorById(int id) {
		for (int i = 0; i < availableActors.size(); i++) {
			if (availableActors.get(i).getMyID() == id) {
				return availableActors.get(i);
			}
		}
		return null;
	}

	/**
	 * Initialize the center pane.
	 */
	private void initializeCenter() {
		myCenterPane = new Pane();
		myCanvas = new Canvas();
		myCenterPane.setStyle("-fx-background-color: white");
		myCenterPane.getChildren().add(myCanvas);
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
	 * Update the level that is being edited and update the level inspector to reflect the current level.
	 */
	@Override
	public void setEditable(IEditableGameElement editable) {
		myCenterPane.getChildren().clear();
		myLevel = (Level) editable;
		myLevelBackground = myLevel.getImageView();
		myLevelBackground.setPreserveRatio(true);
		myLevelBackground.fitWidthProperty().bind(myCenterPane.widthProperty());
		myCenterPane.getChildren().add(myLevelBackground);
		List<ImageView> actorIVs = new ArrayList<>();
		for(IAuthoringActor actor: myLevel.getActors()) actorIVs.add(actor.getImageView());
		myCenterPane.getChildren().addAll(actorIVs);
		myInspector.getAttributesTab().updateEditable(myLevel);
	}
	
	/**
	 * Update the list of available actors and update the level inspector to reflect the currently available actors.
	 * @param updatedActorsList: up-to-date list of available actors.
	 */
	public void updateActorsList(List<IAuthoringActor> updatedActorsList) {
		availableActors = updatedActorsList;
		myInspector.getActorsTab().setAvailableActors(availableActors);
		updateDrag();
	}

}