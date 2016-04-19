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
	private IAuthoringActor curActor;
	private LevelEditingEnvironment myLevelEditingEnvironment;
	
	public ContextMenuActorInLevel(LevelEditingEnvironment levelEditor) {
		myLevelEditingEnvironment = levelEditor;
		curActor = null;
		initMenuItems();
	}
	
	private void initMenuItems() {
		MenuItem delete = new MenuItem(DELETE);
		delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (curActor != null) {
					myLevelEditingEnvironment.removeActorFromLevel(curActor);
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
	
	public void setActor(IAuthoringActor actor) {
		curActor = actor;
	}
}
