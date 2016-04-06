package authoringenvironment.view;

import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import authoringenvironment.controller.Controller;
import gameengine.controller.Level;
import gameengine.model.Actor;
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
 * Front-end of the Level Editing Environment
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
	private List<Actor> availableActors;
	private Pane myCenterPane;
	private ImageView myLevelBackground;

	public GUILevelEditingEnvironment(Controller controller, List<Actor> actors) {
		myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		availableActors = actors;
		myRoot = new BorderPane();
		initializeEnvironment();
	}

	private void initializeEnvironment() {
		initializeCenter();
		initializeLeftPane();
		initializeDrag();
	}

	private void initializeLeftPane() {
		myLeftPane = new VBox();
		myLeftPane.prefHeightProperty().bind(myRoot.heightProperty());
		myInspector = new GUILevelInspector(myResources, availableActors);
		myLibrary = new GUILibrary();
		myLeftPane.getChildren().addAll(myInspector.getPane(), myLibrary.getPane());
		myRoot.setLeft(myLeftPane);
	}

	private void initializeDrag() {
		List<ImageviewActorIcon> icons = myInspector.getActorsTab().getIcons();

		setCenterPaneDragOver();
		setCenterPaneDragEntered();
		setCenterPaneDragExited();
		setCenterPaneDragDropped();
		
		for (int i = 0; i < icons.size(); i++) {
			ImageviewActorIcon source = icons.get(i);
			setDragDetected(source);
			setDragDone(source);
		}
	}

	private void setDragDetected(ImageviewActorIcon source) {
		source.setOnDragDetected(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("drag detected");
				Dragboard db = source.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString(Integer.toString(source.getID()));
				db.setContent(content);
				event.consume();
			}
		});
	}

	private void setDragDone(ImageviewActorIcon source) {
		source.setOnDragDone(new EventHandler <DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getTransferMode() == TransferMode.MOVE) {
					//                    source.setText("");
				}

				event.consume();
			}
		});
	}

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

	private void setCenterPaneDragEntered() {
		myCenterPane.setOnDragEntered(new EventHandler <DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getGestureSource() != myCenterPane &&
					event.getDragboard().hasString()) {
				}
				event.consume();
			}
		});
	}
	
	private void setCenterPaneDragExited() {
		myCenterPane.setOnDragExited(new EventHandler <DragEvent>() {
			public void handle(DragEvent event) {
				event.consume();
			}
		});
	}

	private void setCenterPaneDragDropped() {
		myCenterPane.setOnDragDropped(new EventHandler <DragEvent>() {
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
					Actor actor = getActorById(Integer.parseInt(db.getString()));
					actor.setOnDragDetected(null);
					actor.setOnDragDone(null);
					actor.setOnMouseDragged(new EventHandler<MouseEvent>() {
						@Override public void handle(MouseEvent event) {
							moveActor(actor, event);
							event.consume();
						}
					}); 
					myLevel.addActor(actor);
					myCenterPane.getChildren().add(actor);
					success = true;
				}
				event.setDropCompleted(success);
				event.consume();
			}
		});
	}
	
	private void moveActor(Actor actor, MouseEvent event) {
		actor.setX(event.getX());
		actor.setY(event.getY());
	}
	
	private Actor getActorById(int id) {
		for (int i = 0; i < availableActors.size(); i++) {
			if (availableActors.get(i).getID() == id) {
				return availableActors.get(i);
			}
		}
		return null;
	}

	//TODO: should initialize based on the level that's loaded on here
	private void initializeCenter() {
		myCenterPane = new Pane();
		myCanvas = new Canvas();
		myCenterPane.setStyle("-fx-background-color: white");
		myCenterPane.getChildren().add(myCanvas);
		myRoot.setCenter(myCenterPane);
	}

	@Override
	public Pane getPane() {
		return myRoot;
	}

	@Override
	public void updateAllNodes() {
		// TODO Auto-generated method stub

	}


	@Override
	public void setEditable(IEditableGameElement editable) {
		myCenterPane.getChildren().clear();
		myLevel = (Level) editable;
		myLevelBackground = new ImageView(myLevel.getImage());
		//TODO: SHOULD THIS FIT THE PANE OR PRESERVE RATIO?
		//myLevelBackground.setPreserveRatio(true);
		myLevelBackground.fitHeightProperty().bind(myCenterPane.heightProperty());
		myLevelBackground.fitWidthProperty().bind(myCenterPane.widthProperty());
		myCenterPane.getChildren().add(myLevelBackground);
		myCenterPane.getChildren().addAll(myLevel.getActors());
	}



}
