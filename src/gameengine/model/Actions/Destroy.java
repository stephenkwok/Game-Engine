package gameengine.model.Actions;

import gameengine.controller.Level;
import gameengine.model.Actor;

public class Destroy extends Action{
	
	Level level = new Level();
	public Destroy(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		level.removeActor(getActor());
	}

}
