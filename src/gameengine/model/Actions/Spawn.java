package gameengine.model.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import authoringenvironment.model.IAuthoringActor;
import gameengine.model.Actor;
import gameengine.model.IPlayActor;
import javafx.scene.image.ImageView;

public class Spawn extends Action{
	
	IPlayActor mySpawnedActor ;
	Integer cloneNum = 900;
	
	public Spawn(IPlayActor actor1, IPlayActor spawnedActor) {
		super(actor1);
		mySpawnedActor  = spawnedActor;
	}

	public void perform() {
		System.out.println(mySpawnedActor.getName());
		mySpawnedActor.setHeading(getMyActor().getHeading());
		mySpawnedActor.setX(getMyActor().getBounds().getMaxX());
		mySpawnedActor.setY(getMyActor().getY());
		getMyActor().changed();
		List<Object> myList = new ArrayList<>();
		myList.add("addActor");
		myList.add(mySpawnedActor);
		System.out.println("In Spawn Action");
        ((Observable) getMyActor()).notifyObservers(myList);				
	}
	
	private IAuthoringActor cloneActor(IAuthoringActor spawnedActor){
		 IAuthoringActor clone = (IAuthoringActor) new Actor();	
		 ImageView imageView = spawnedActor.getImageView();
		 clone.setImageView(imageView);
		 clone.setName("bullet"+cloneNum);
	     clone.setID(cloneNum);
	     cloneNum = cloneNum+1;
		return clone;	
	}

}
