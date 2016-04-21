package authoringenvironment.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gameengine.model.IPlayActor;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Level editing environment
 * @author amyzhao
 *
 */
public class LevelEditingEnvironment implements IEditingEnvironment {
	private static final String GUI_RESOURCE = "authoringGUI";
	private static final String VERTICAL = "Vertically";
	private static final String HORIZONTAL = "Horizontally";
	private BorderPane myRoot;
	private GUILevelInspector myInspector;
	private ResourceBundle myResources;
	private VBox myLeftPane;
	private Canvas myCanvas;
	private Level myLevel;
	private Map<IAuthoringActor, List<IAuthoringActor>> availableActors;
	private Pane myLevelPane;
	private StackPane myStackPane;	// try setting stackpane to scrollpane's content, then adding imageview for background to stackpane and level on top
	private ScrollPane myCenterPane;
	private ImageView myLevelBackground;
	private List<ImageviewActorIcon> myActorPreviews;
	private Stage myStage;
	private static final double SUBSCENE_HEIGHT = 525; // 700 * 3/4
	private static final double SUBSCENE_WIDTH = 1000;
	private Rectangle myBoundary;

	/**
	 * Constructor for a level editing environment.
	 * @param controller: authoring environment controller.
	 * @param actors: list of currently available actors.
	 */
	public LevelEditingEnvironment(Map<IAuthoringActor, List<IAuthoringActor>> actors, Stage stage) {
		myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		availableActors = actors;
		myRoot = new BorderPane();
		myActorPreviews = new ArrayList<>();
		myCenterPane = new ScrollPane();
		myStage = stage;
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
		myRoot.setLeft(myLeftPane);
		addChildrenToLeftPane();
	}

	private void addChildrenToLeftPane() {
		myInspector = new GUILevelInspector(myResources, availableActors.keySet(), this, myStage);
		myLeftPane.getChildren().add(myInspector.getPane());
		myInspector.getPane().prefHeightProperty().bind(myLeftPane.heightProperty());
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
		myLevelPane.setOnDragOver(new EventHandler <DragEvent>() {
			public void handle(DragEvent event) {
			if (event.getGestureSource() != myLevelPane &&
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
		myLevelPane.setOnDragDropped(new EventHandler <DragEvent>() {
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
					addActorToScene(actor);
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
	private void moveActor(ImageviewActorIcon icon, MouseEvent event) {
		icon.updateIconActorPosition(event.getX(), event.getY());
		icon.setX(event.getX());
		icon.setY(event.getY());
	}
	
	/**
	 * Gets an actor by its ID.
	 * @param id: ID of actor of interest.
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
		myLevelPane = new Pane();
		myStackPane = new StackPane();
		myStackPane.setAlignment(Pos.CENTER);
		myBoundary = new Rectangle(SUBSCENE_WIDTH, SUBSCENE_HEIGHT);
		myBoundary.setFill(Color.TRANSPARENT);
		myBoundary.setStroke(Color.BLACK);
		myCenterPane.setContent(myStackPane);
		myStackPane.getChildren().addAll(myLevelPane, myBoundary);
		myCenterPane.setStyle("-fx-background-color: lightgray");
		myRoot.setCenter(myCenterPane);
		myCenterPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		myCenterPane.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		myCenterPane.setFitToHeight(true);
		myCenterPane.setFitToWidth(true);
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
	public void setEditableElement(IEditableGameElement editable) {
		myLevelPane.getChildren().clear();
		myStackPane.getChildren().clear();
		myLevel = (Level) editable;
		updateActorsList();
		updateLevel();
		myInspector.getAttributesTab().updateEditable(myLevel);
	}
	
	/**
	 * Updates the level that's being displayed.
	 * @param updatedLevel: new level.
	 */
	private void updateLevel() {
		myLevelPane.getChildren().removeAll(myActorPreviews);
		myLevelPane.getChildren().add(myBoundary);
		updateLevelBackground();
		addLevelActorsToScene();
	}
	
	/**
	 * Updates the preview to show the level's background.
	 */
	private void updateLevelBackground() {
		myLevelBackground = myLevel.getImageView();
		resizeBackgroundBasedOnScrolling();
		myStackPane.getChildren().addAll(myLevelBackground, myLevelPane);
	}
	
	public void changeBackgroundImage(Image image, File imageFile) {
		myStackPane.getChildren().clear();
		myLevel.setImageView(new ImageView(image));
		myLevel.setMyBackgroundImgName(imageFile.getPath());
		updateLevelBackground();
	}
	
	private void resizeBackgroundBasedOnScrolling() {
		if (myLevel.getMyScrollingDirection().equals(VERTICAL)) {
			myLevelBackground.setFitWidth(SUBSCENE_WIDTH);
		} else {
			myLevelBackground.setFitHeight(SUBSCENE_HEIGHT);
		}
		myLevelBackground.setPreserveRatio(true);
	}
	/**
	 * Add a level's actors to the preview in the center pane.
	 */
	private void addLevelActorsToScene() {
		myActorPreviews.clear();
		for (IPlayActor actor: myLevel.getActors()) {
			ImageviewActorIcon icon = addActorToScene((IAuthoringActor) actor);
			icon.setX(actor.getX());
			icon.setY(actor.getY());
		}
	}
	
	private ImageviewActorIcon addActorToScene(IAuthoringActor actor) {
		ImageviewActorIcon icon = new ImageviewActorIcon(actor, actor.getImageView().getFitHeight());
		setIconBehavior(icon);
		icon.setOnLevel(true);
		myActorPreviews.add(icon);
		myLevelPane.getChildren().add(icon);
		return icon;
	}
	
	private void setIconBehavior(ImageviewActorIcon icon) {
		icon.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				moveActor(icon, event);
				event.consume();
			}
		}); 
		
		ContextMenuActorInLevel contextMenu = new ContextMenuActorInLevel(this);
		icon.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
	        @Override
	        public void handle(ContextMenuEvent t) {
	        	contextMenu.setIcon(icon);
				contextMenu.show(icon, t.getSceneX(), t.getScreenY());
				System.out.println("pressed");
	        }
	    });
	}
	/**
	 * Update the list of available actors and update the level inspector to reflect the currently available actors.
	 * @param updatedActorsList: up-to-date list of available actors.
	 */
	public void updateActorsList() {
		myInspector.getActorsTab().setAvailableActors(availableActors.keySet());
		for (ImageviewActorIcon icon: myActorPreviews) {
			icon.updateImageView();
		}
		updateDrag();
	}

	public Level getLevel() {
		return myLevel;
	}
	
	@Override
	public Stage getStage() {
		return myStage;
	}
	
	public void removeActorFromLevel(ImageviewActorIcon icon) {
		myActorPreviews.remove(icon);
		myLevelPane.getChildren().remove(icon);
		myLevel.removeActor((Actor) icon.getRefActor()); 
	}
}