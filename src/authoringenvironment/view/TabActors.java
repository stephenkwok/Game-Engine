package authoringenvironment.view;

import java.util.List;

import java.util.ResourceBundle;

import gameengine.controller.Actor;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

/**
 * Actors tab to go in the Inspector Pane in the Level Editing Environment GUI.
 * @author amyzhao
 *
 */
public class TabActors extends TabParent {
	
	private static final int HGAP = 10;
	private static final int VGAP = 10;
	private static final int TILE_HEIGHT = 75;
	private static final int TILE_WIDTH = 75;
	private static final int NUM_COLS = 4;
	private static final int NUM_ROWS = 2;
	private List<Actor> availableActors;
	private TilePane myPane;
	
	public TabActors(ResourceBundle myResources, String tabText, List<Actor> availActors) {
		super(myResources, tabText);
		availableActors = availActors;
		
		Actor newActor1 = new Actor();
		newActor1.setImage(new Image("default_icon.png"));
		Actor newActor2 = new Actor();
		newActor2.setImage(new Image("default_icon.png"));
		Actor newActor3 = new Actor();
		newActor3.setImage(new Image("default_icon.png"));
		Actor newActor4 = new Actor();
		newActor4.setImage(new Image("default_icon.png"));
		availableActors.add(newActor1); // PLACEHOLDER RN, STEPHEN SHOULD'VE ADDED A DEFAULT ONE ALREADY
		availableActors.add(newActor2);
		availableActors.add(newActor3);
		availableActors.add(newActor4);
		
		//TODO: this isn't working...
		for (Actor actor: availableActors) {
			actor.setOnMouseDragged(new EventHandler<MouseEvent>() {
	            @Override public void handle(MouseEvent event) {
	                actor.setX(event.getSceneX());
	                actor.setY(event.getSceneY());
	                actor.setX(event.getSceneX());
	                actor.setY(event.getSceneY());
	                System.out.println("(" + event.getSceneX() + ", " + event.getSceneY() + ")");
	                event.consume();
	            }
	        }); 
		}
		
		myPane = new TilePane(HGAP, VGAP);
		myPane.setPrefTileHeight(TILE_HEIGHT);
		myPane.setPrefTileWidth(TILE_WIDTH);
		myPane.setPrefColumns(NUM_COLS);
		myPane.setPrefRows(NUM_ROWS);
		myPane.setOrientation(Orientation.HORIZONTAL);
		myPane.getChildren().addAll(availableActors);	
		
	}
	
	@Override
	Node getContent() {
		return myPane;
	}

}
