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
	private ImageviewActorIcon curIcon;
	private LevelEditingEnvironment myLevelEditingEnvironment;
	
	public ContextMenuActorInLevel(LevelEditingEnvironment levelEditor) {
		myLevelEditingEnvironment = levelEditor;
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
					System.out.println("blurp");
					myLevelEditingEnvironment.removeActorFromLevel(curIcon);
				}
			}
			
		});
		
		MenuItem editSize = new MenuItem(EDIT_SIZE);
		editSize.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//TODO: FILL THIS IN - pop up thing?
			}
			
		});
		
		getItems().addAll(delete, editSize);
	}
	
	public void setIcon(ImageviewActorIcon icon) {
		curIcon = icon;
	}
}
