package authoringenvironment.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 * Context menu for actor on level preview.
 * @author amyzhao
 *
 */
public class ContextMenuActorInLevel extends ContextMenu {
	private static final String DELETE = "Remove actor";
	private ImageviewActorIcon curIcon;
	private LevelPreviewEditing myLevelPreview;

	/**
	 * Constructor for a ContextMenuActorInLevel
	 * @param levelPreview: level preview to apply the context menu to.
	 */
	public ContextMenuActorInLevel(LevelPreviewEditing levelPreview) {
		myLevelPreview = levelPreview;
		curIcon = null;
		initMenuItems();
	}

	/**
	 * Initialize the menu items.
	 */
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

	/**
	 * Set icon for context menu.
	 * @param icon: icon to use.
	 */
	public void setIcon(ImageviewActorIcon icon) {
		curIcon = icon;
	}
}
