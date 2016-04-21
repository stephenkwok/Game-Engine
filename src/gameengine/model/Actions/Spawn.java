package gameengine.model.Actions;

import gameengine.model.Actor;

public class Spawn extends Action{

	public Spawn(Actor assignedActor) {
		super(assignedActor);
	}

	public void perform(Actor spawnedActor) {
		spawnedActor.setHeading(getMyActor().getHeading());
		spawnedActor.setX(getMyActor().getX());
		spawnedActor.setY(getMyActor().getY());
		
		//getMyActor().update(spawnedActor, spawnedActor);
		
//		Now have to add spawnedActor to the list of Actors
//		Could it be created with isDead set to true 
//		and here we set it false?
		
	}

	@Override
	public void perform() {}
	

}
