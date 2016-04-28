package gameengine.model.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import authoringenvironment.view.ActorCopier;
import gameengine.model.Actor;
import gameengine.model.IPlayActor;


public class Spawn extends ActorAction{

	IPlayActor mySpawnedActor;
	
	public Spawn(Actor actor1, Actor spawnedActor) {
		super(actor1);
		mySpawnedActor  = (IPlayActor) spawnedActor;
	}
	
	@Override
	public Object[] getParameters(){
		return new Object[]{getMyActor(),mySpawnedActor};
	}
	
	public void perform() {
		
		ActorCopier myActorCopier = new ActorCopier((Actor)mySpawnedActor);
		Actor clone = myActorCopier.makeCopy();
		
		System.out.println(mySpawnedActor.getName());
		clone.setHeading(getMyActor().getHeading());
		clone.setX(getMyActor().getBounds().getMaxX());
		clone.setY(getMyActor().getY());
		
		getMyActor().changed();
		List<Object> myList = new ArrayList<>();
		myList.add("addActor");
		myList.add(clone);

		System.out.println("In Spawn Action");
		((Observable) getMyActor()).notifyObservers(myList);
	}
}
