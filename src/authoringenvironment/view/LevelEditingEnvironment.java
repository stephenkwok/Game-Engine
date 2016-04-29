package authoringenvironment.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gamedata.controller.CreatorController;
import gamedata.controller.ParserController;
import gameengine.controller.Game;
import gameengine.controller.GameInfo;
import gameengine.controller.Level;
import gameengine.model.ActorState;
import gameengine.model.IPlayActor;
import gameplayer.controller.GameController;
import gameplayer.controller.PlayType;
import gameplayer.view.GameScreen;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
	private LevelInspector myInspector;
	private ResourceBundle myResources;
	private VBox myLeftPane;
	private Level myLevel;
	private Map<IAuthoringActor, List<IAuthoringActor>> availableActors;
	private Pane myCenterPane;
	private Stage myStage;
	private Controller myController;
	private LevelPreview myLevelPreview;
	private File myPreviewFile;
	
	//MICHAEL ADDED THESE BUT THEY PROB NEED TO BE CHANGED
	private Button myB;

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
	
	
	private void previewGame(){
		myPreviewFile = new File("preview.xml");
		Game model;
		GameController controller;
		GameScreen view;
        
		Group group = new Group();
        Scene scene = new Scene(group);

        model = new Game(new GameInfo(), myController.getLevels());
        //TODO this is duplicated from controller save game.... also no check for if actors is empty
        for(Level level: model.getLevels()) {
			for (IPlayActor actor: level.getActors()) {
				if (actor.checkState(ActorState.MAIN)) {
					level.getMainCharacters().add(actor);
				}
			}
			if (level.getMainCharacters().size() == 0) {
				level.getActors().get(0).addState(ActorState.MAIN);
			}
		}
        
        CreatorController creatorController = new CreatorController(model);
        try {
			creatorController.saveForPreviewing(myPreviewFile);
		} catch (SAXException | IOException | TransformerException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ParserController parserController = new ParserController();
        model = parserController.loadforPlaying(myPreviewFile);

        
        
        ParallelCamera camera = new ParallelCamera();
        view = new GameScreen(camera);

        controller = new GameController(model, PlayType.PREVIEW);
        controller.setGame(model);
        controller.setGameView(view);

        SubScene sub = view.getScene();
        sub.fillProperty().set(Color.BLUE);
        group.getChildren().add(sub);

        Stage stage = new Stage();
        stage.setWidth(800);
        stage.setHeight(600);

        sub.setCamera(camera);
        stage.setScene(scene);
        stage.show();
        controller.initialize(0);
        
        //controller.getView().clearGame();
        
        myPreviewFile.delete();
        
        
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
		myInspector = new LevelInspector(myResources, availableActors.keySet(), this);
		myLeftPane.getChildren().add(myInspector.getPane());
		//AND HERE
		myB = new Button("Preview");
		myB.setOnMouseClicked(e -> previewGame());
		myB.setLayoutX(100);
		myB.setLayoutY(300);
		//TODO I ADDED THIS LINE HERE
		myLeftPane.getChildren().add(myB);
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
					groupActors(actor);
					myInspector.getGarbageCollector().updateGarbageCollectingActors(myLevel.getActors());
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
		myInspector.getGarbageCollector().setEditableElement(myLevel);
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
			myController.updateRefActor((IAuthoringActor) arg);
			myLevelPreview.addLevelActorsToScene();
		}
	}

	public Controller getController() {
		return myController;
	}
	
	public Set<IAuthoringActor> getAvailableActors() {
		return availableActors.keySet();
	}
	
	public void groupActors(IAuthoringActor actor) {
		System.out.println("Grouping");
		ActorGroup actorList;
		if (myController.getActorGroups().containsKey(actor.getID())) {
			actorList = myController.getActorGroups().get(actor.getID());
		} else {
			actorList = new ActorGroup(actor.getName(), new ArrayList<>());
		}
		for (IAuthoringActor key: myController.getActorMap().keySet()) {
			List<IAuthoringActor> actorsForKey = myController.getActorMap().get(key);
			for (int i = 0; i < actorsForKey.size(); i++) {
				if (!actorList.getGroup().contains(actorsForKey.get(i))) {
					actorList.addActorToGroup(actorsForKey.get(i));
				}
			}
		}
		myController.getActorGroups().put(actor.getID(), actorList);
		System.out.println(myController.getActorGroups().get(actor.getID()).getGroup().size());
		//System.out.println(myController.getActorGroups().get(actor.getID()));
	}
}