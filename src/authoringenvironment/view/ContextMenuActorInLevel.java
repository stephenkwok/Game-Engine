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
	private LevelPreviewEditing myLevelPreview;

	public ContextMenuActorInLevel(LevelPreviewEditing levelPreview) {
		myLevelPreview = levelPreview;
		curIcon = null;
		initMenuItems();
	}

	private void initMenuItems() {
		MenuItem delete = new MenuItem(DELETE);
		delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (curIcon != null) {
					myLevelPreview.removeActorFromLevel(curIcon);
				}
			}

		});
		
		getItems().addAll(delete);
	}

	public void setIcon(ImageviewActorIcon icon) {
		curIcon = icon;
	}
}
