package gameengine.model.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import gameengine.model.Actor;
import gameengine.model.IPlayActor;

public class Spawn extends ActorAction{

	public Spawn(IPlayActor actor1) {
		super(actor1);
	}

	public void perform() {
		// spawnedActor.setHeading(getMyActor().getHeading());
		// spawnedActor.setX(getMyActor().getX());
		// spawnedActor.setY(getMyActor().getY());
		getMyActor().changed();
		List<Object> myList = new ArrayList<>();
		myList.add("addActor");
		// args.add(spawnedActor);
		System.out.println("In Spawn Action");
		((Observable) getMyActor()).notifyObservers(myList);
	}

}
