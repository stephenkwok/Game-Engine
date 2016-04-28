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
		clone.setHeading(getMyActor().getHeading());
		
		System.out.println(clone.getHeading());
		
		if(clone.getHeading() == 0){
			clone.setX(getMyActor().getBounds().getMaxX());
			clone.setY(getMyActor().getBounds().getMinY() + getMyActor().getBounds().getHeight()/2 - clone.getBounds().getHeight()/2);
		}else if(clone.getHeading() == 180){
			clone.setX(getMyActor().getBounds().getMinX()-clone.getBounds().getWidth());
			clone.setY(getMyActor().getBounds().getMinY() + getMyActor().getBounds().getHeight()/2 - clone.getBounds().getHeight()/2);
		}else{
			clone.setX(getMyActor().getBounds().getMinX() +getMyActor().getBounds().getWidth()/2 - clone.getBounds().getWidth()/2);
			clone.setY(getMyActor().getY()-clone.getBounds().getHeight());
		}
		getMyActor().changed();
		List<Object> myList = new ArrayList<>();
		myList.add("addActor");
		myList.add(clone);

		((Observable) getMyActor()).notifyObservers(myList);
	}
}
