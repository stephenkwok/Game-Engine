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
		getMyActor().update(spawnedActor, args);
		
//		Now have to add spawnedActor to the list of Actors
//		Could it be created with isDead set to true 
//		and here we set it false?
		
	}

	@Override
	public void perform() {}
	

}