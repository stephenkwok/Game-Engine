package gameengine.model.Actions;

import java.util.Arrays;
import java.util.Observable;

import authoringenvironment.view.ActorCopier;
import gameengine.model.Actor;
import gameengine.model.IPlayActor;

public class CreateActor extends ActorAction {
	Actor cloneActor;
	ActorCopier copier;
	double myX;
	double myY;
	private Actor myActorToCopy;
	
	public CreateActor(IPlayActor actor, Actor toCopy, double x, double y) {
		super(actor);
		copier = new ActorCopier(toCopy);
		myX = x;
		myY = y;
	}

	@Override
	public void perform() {
		cloneActor = copier.makeCopy();
		cloneActor.setX(myX);
		cloneActor.setY(myY);
		getMyActor().changed();
		((Observable) getMyActor()).notifyObservers(Arrays.asList(new Object[]{"addActor",cloneActor}));
	}

	public double getMyX() {
		return myX;
	}
	
	public double getMyY() {
		return myY;
	}
	
	public Actor getMyActorToCopy() {
		return myActorToCopy;
	}
}
