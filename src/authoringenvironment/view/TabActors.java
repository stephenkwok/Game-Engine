package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;

import gameengine.actors.Actor;
import gameengine.actors.PowerUpActor;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
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
	private static final int PADDING = 10;
	private List<ImageviewActorIcon> actorIcons;
	private TilePane myPane;
	
	public TabActors(ResourceBundle myResources, String tabText, List<Actor> availActors) {
		super(myResources, tabText);		
		Actor newActor1 = new PowerUpActor();
		newActor1.setID(1);
		Actor newActor2 = new PowerUpActor();
		newActor2.setID(2);;
		Actor newActor3 = new PowerUpActor();
		newActor3.setID(3);
		Actor newActor4 = new PowerUpActor();
		newActor4.setID(4);
		availActors.add(newActor1); // PLACEHOLDER RN, STEPHEN SHOULD'VE ADDED A DEFAULT ONE ALREADY
		availActors.add(newActor2);
		availActors.add(newActor3);
		availActors.add(newActor4);
		
		actorIcons = actorListToIconList(availActors);
		
		myPane = new TilePane(HGAP, VGAP);
		myPane.setPrefTileHeight(TILE_HEIGHT);
		myPane.setPrefTileWidth(TILE_WIDTH);
		myPane.setPrefColumns(NUM_COLS);
		myPane.setPrefRows(NUM_ROWS);
		myPane.setOrientation(Orientation.HORIZONTAL);
		myPane.setAlignment(Pos.TOP_CENTER);
		myPane.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));

		myPane.getChildren().addAll(actorIcons);	
	}
	
	private List<ImageviewActorIcon> actorListToIconList(List<Actor> actors) {
		List<ImageviewActorIcon> iconList = new ArrayList<>();
		for (int i = 0; i < actors.size(); i++) {
			iconList.add(new ImageviewActorIcon(actors.get(i)));
		}
		return iconList;
	}
	
	public List<Actor> getActors() {
		List<Actor> actorList = new ArrayList<>();
		for (int i = 0; i < actorIcons.size(); i++) {
			actorList.add(actorIcons.get(i).getActor());
		}
		return actorList;
	}
	
	public List<ImageviewActorIcon> getIcons() {
		return actorIcons;
	}
	
	@Override
	Node getContent() {
		return myPane;
	}

}
