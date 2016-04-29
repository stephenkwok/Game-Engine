package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;
import java.util.Set;

import authoringenvironment.model.IAuthoringActor;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * Actors tab to go in the Inspector Pane in the Level Editing Environment GUI.
 * 
 * @author amyzhao
 *
 */
public class TabActors extends TabParent {
	private static final int ICON_WIDTH = 60;
	private static final int HGAP = 15;
	private static final int VGAP = 15;
	private static final int TILE_HEIGHT = 125;
	private static final int TILE_WIDTH = 75;
	private static final int NUM_COLS = 4;
	private static final int NUM_ROWS = 2;
	private static final int PADDING = 10;
	private List<ImageviewActorIcon> actorIcons;
	private List<IAuthoringActor> curActors;
	private List<VBox> icons;
	private TilePane myPane;

	/**
	 * Constructs a tab to display icons of currently available actors.
	 * 
	 * @param myResources:
	 *            authoring environment resource.
	 * @param tabText:
	 *            name of this tab.
	 * @param availActors:
	 *            list of currently available actors.
	 */
	public TabActors(ResourceBundle myResources, String tabText, Set<IAuthoringActor> availActors) {
		super(myResources, tabText);
		actorIcons = new ArrayList<>();
		curActors = new ArrayList<>();
		myPane = new TilePane(HGAP, VGAP);
		icons = new ArrayList<>();
		formatTab();
		setAvailableActors(availActors);
	}

	private void formatTab() {
		myPane.setPrefTileHeight(TILE_HEIGHT);
		myPane.setPrefTileWidth(TILE_WIDTH);
		myPane.setPrefColumns(NUM_COLS);
		myPane.setPrefRows(NUM_ROWS);
		myPane.setOrientation(Orientation.HORIZONTAL);
		myPane.setAlignment(Pos.TOP_LEFT);
		myPane.setPadding(new Insets(PADDING));
		myPane.setPrefHeight(200);
	}

	/**
	 * Converts a list of actors to a list of icons for the actors.
	 * 
	 * @param actors
	 * @return
	 */
	private List<ImageviewActorIcon> actorListToIconList(List<IAuthoringActor> actors) {
		List<ImageviewActorIcon> iconList = new ArrayList<>();
		for (int i = 0; i < actors.size(); i++) {
			iconList.add(new ImageviewActorIcon(actors.get(i), ICON_WIDTH));
		}
		return iconList;
	}
	
	private void updateActorList(Set<IAuthoringActor> actors) {
		for (IAuthoringActor actor : actors) {
			if (!curActors.contains(actor)) {
				curActors.add(actor);
			}			
		}
	}

	/**
	 * Converts the current list of icons to a list of actors.
	 * 
	 * @return list of currently available actors
	 */
	public List<IAuthoringActor> getActors() {
		List<IAuthoringActor> actorList = new ArrayList<>();
		for (int i = 0; i < actorIcons.size(); i++) {
			actorList.add(actorIcons.get(i).getRefActor());
		}
		return actorList;
	}

	/**
	 * Get the current list of icons.
	 * 
	 * @return list of icons of available actors.
	 */
	public List<ImageviewActorIcon> getIcons() {
		return actorIcons;
	}

	/**
	 * Set the list of available actor icons based on an updated list of actors.
	 * 
	 * @param updatedActors:
	 *            updated list of actors.
	 */
	public void setAvailableActors(Set<IAuthoringActor> updatedActors) {
//		actorIcons.clear();
		updateActorList(updatedActors);
		actorIcons = actorListToIconList(curActors);
		addIconsToPane();
		
	}

	private void addIconsToPane() {
		myPane.getChildren().removeAll(icons);
		icons.clear();
		for (int i = 0; i < actorIcons.size(); i++) {
			VBox box = new VBox();
			Label name = new Label(actorIcons.get(i).getRefActor().getName());
			name.setWrapText(true);
			box.setAlignment(Pos.CENTER);
			box.getChildren().addAll(actorIcons.get(i), name);
			icons.add(box);
		}
		myPane.getChildren().addAll(icons);
	}
	/**
	 * Return the contents of this tab.
	 */
	@Override
	Node getContent() {
		return myPane;
	}

}