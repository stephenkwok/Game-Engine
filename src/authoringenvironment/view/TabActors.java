package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;

import gameengine.model.IAuthoringActor;
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
	
	/**
	 * Constructs a tab to display icons of currently available actors.
	 * @param myResources: authoring environment resource.
	 * @param tabText: name of this tab.
	 * @param availActors: list of currently available actors.
	 */
	public TabActors(ResourceBundle myResources, String tabText, List<IAuthoringActor> availActors) {
		super(myResources, tabText);
		actorIcons = new ArrayList<ImageviewActorIcon>();	
		myPane = new TilePane(HGAP, VGAP);
		myPane.setPrefTileHeight(TILE_HEIGHT);
		myPane.setPrefTileWidth(TILE_WIDTH);
		myPane.setPrefColumns(NUM_COLS);
		myPane.setPrefRows(NUM_ROWS);
		myPane.setOrientation(Orientation.HORIZONTAL);
		myPane.setAlignment(Pos.TOP_LEFT);
		myPane.setPadding(new Insets(PADDING));
		setAvailableActors(availActors);
	}
	
	/**
	 * Converts a list of actors to a list of icons for the actors.
	 * @param actors
	 * @return
	 */
	private List<ImageviewActorIcon> actorListToIconList(List<IAuthoringActor> actors) {
		List<ImageviewActorIcon> iconList = new ArrayList<>();
		for (int i = 0; i < actors.size(); i++) {
			iconList.add(new ImageviewActorIcon(actors.get(i)));
		}
		return iconList;
	}
	
	/**
	 * Converts the current list of icons to a list of actors.
	 * @return list of currently available actors
	 */
	public List<IAuthoringActor> getActors() {
		List<IAuthoringActor> actorList = new ArrayList<>();
		for (int i = 0; i < actorIcons.size(); i++) {
			actorList.add(actorIcons.get(i).getActor());
		}
		return actorList;
	}
	
	/**
	 * Get the current list of icons.
	 * @return list of icons of available actors.
	 */
	public List<ImageviewActorIcon> getIcons() {
		return actorIcons;
	}
	
	/**
	 * Set the list of available actor icons based on an updated list of actors.
	 * @param updatedActors: updated list of actors.
	 */
	public void setAvailableActors(List<IAuthoringActor> updatedActors) {
		myPane.getChildren().removeAll(actorIcons);
		actorIcons.clear();
		actorIcons = actorListToIconList(updatedActors);
		myPane.getChildren().addAll(actorIcons);	
	}
	
	/**
	 * Return the contents of this tab.
	 */
	@Override
	Node getContent() {
		return myPane;
	}

}
