package authoringenvironment.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class ContextMenuActorInLevel extends ContextMenu {
	private static final String DELETE = "Remove actor";
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
