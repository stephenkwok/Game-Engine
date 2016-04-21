package gameengine.model.Actions;

import java.util.ArrayList;
import java.util.List;

import gameengine.model.Actor;

public class Spawn extends Action{

	public Spawn(Actor assignedActor) {
		super(assignedActor);
	}

	public void perform(Actor spawnedActor) {
		spawnedActor.setHeading(getMyActor().getHeading());
		spawnedActor.setX(getMyActor().getX());
		spawnedActor.setY(getMyActor().getY());
		List<Object> args = new ArrayList<>();
		args.add("addActor");
		args.add(spawnedActor);
		//getMyActor().update(spawnedActor, args);
		
	}

	@Override
	public void perform() {}
	

}
