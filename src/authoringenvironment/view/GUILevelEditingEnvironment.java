package authoringenvironment.view;

import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import authoringenvironment.controller.Controller;
import gameengine.actors.Actor;
import gameengine.controller.ILevel;
import gameengine.model.IActor;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
	private ILevel myLevel;
	private List<Actor> availableActors;
	private Pane myCenterPane;

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
		List<Actor> actors = myInspector.getActorsTab().getActors();

		for (int i = 0; i < actors.size(); i++) {
			Actor source = actors.get(i);
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

			myCenterPane.setOnDragOver(new EventHandler <DragEvent>() {
				public void handle(DragEvent event) {
					System.out.println("onDragOver");

					/* accept it only if it is  not dragged from the same node 
					 * and if it has a string data */
					if (event.getGestureSource() != myCenterPane &&
							event.getDragboard().hasString()) {
						/* allow for both copying and moving, whatever user chooses */
						event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
					}

					event.consume();
				}
			});
			
			myCenterPane.setOnDragEntered(new EventHandler <DragEvent>() {
	            public void handle(DragEvent event) {
	                /* the drag-and-drop gesture entered the target */
	                System.out.println("onDragEntered");
	                /* show to the user that it is an actual gesture target */
	                if (event.getGestureSource() != myCenterPane &&
	                        event.getDragboard().hasString()) {
	                   // myCanvas.setFill(Color.GREEN);
	                }
	                
	                event.consume();
	            }
	        });

			myCenterPane.setOnDragExited(new EventHandler <DragEvent>() {
	            public void handle(DragEvent event) {
	            	System.out.println("dragExited");
	                /* mouse moved away, remove the graphical cues */
	                //myCanvas.setFill(Color.BLACK);
	                
	                event.consume();
	            }
	        });
	        
	        
	        myCenterPane.setOnDragDropped(new EventHandler <DragEvent>() {
	            public void handle(DragEvent event) {
	                System.out.println("onDragDropped");
	                Dragboard db = event.getDragboard();
	                boolean success = false;
	                if (db.hasString()) {
	                	Actor actor = getActorById(Integer.parseInt(db.getString()));
	                	actor.setOnDragDetected(null);
	                	actor.setOnDragDone(null);
	                	actor.setOnMouseDragged(new EventHandler<MouseEvent>() {
	                        @Override public void handle(MouseEvent event) {
	                            actor.setX(event.getX());
	                            actor.setY(event.getY());
	                            System.out.println("(" + actor.getX() + ", " + actor.getY() + ")");
	                            event.consume();
	                        }
	                    }); 
	                    myCenterPane.getChildren().add(actor);
	                    success = true;
	                }
	                /* let the source know whether the string was successfully 
	                 * transferred and used */
	                event.setDropCompleted(success);
	                
	                event.consume();
	            }
	        });

	        source.setOnDragDone(new EventHandler <DragEvent>() {
	            public void handle(DragEvent event) {
	                /* the drag-and-drop gesture ended */
	                System.out.println("onDragDone");
	                /* if the data was successfully moved, clear it */
	                if (event.getTransferMode() == TransferMode.MOVE) {
//	                    source.setText("");
	                }
	                
	                event.consume();
	            }
	        });

		}
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

	public void setLevel(ILevel level){
		myLevel = level;
	}

	@Override
	public void setEditable(IEditableGameElement editable) {
		myLevel = (ILevel) editable;
	}



}
