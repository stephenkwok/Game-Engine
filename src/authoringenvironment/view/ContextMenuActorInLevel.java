package authoringenvironment.view;

import java.util.List;

import authoringenvironment.model.IAuthoringActor;
import gameengine.controller.Level;
import gameengine.model.Actor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class ContextMenuActorInLevel extends ContextMenu {
	private static final String DELETE = "Remove actor";
	private static final String EDIT_SIZE = "Edit actor size";
	private static final String GROUP = "Group all actors of this type";
	private ImageviewActorIcon curIcon;
	private LevelPreview myLevelPreview;

	public ContextMenuActorInLevel(LevelPreview levelPreview) {
		myLevelPreview = levelPreview;
		curIcon = null;
		initMenuItems();
	}

	private void initMenuItems() {
		MenuItem delete = new MenuItem(DELETE);
		delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (curIcon != null) {
					myLevelPreview.removeActorFromLevel(curIcon);
				}
			}

		});
		
		MenuItem groupAll = new MenuItem(GROUP);
		groupAll.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myLevelPreview.groupActors(curIcon);
			}
		});
		getItems().addAll(delete, groupAll);
	}

	public void setIcon(ImageviewActorIcon icon) {
		curIcon = icon;
	}
}
