package gameengine.model.Actions;

import gameengine.model.Actor;




/**
 * This action is an abstract class that contains logic and a uniform 
 * constructor for all gliding type actions. This action takes in a offset
 * value, which represents the number of pixels that the actor glides each
 * time the action is called. 
 * 
 * @author justinbergkamp
 *
 */
public abstract class GlidingAction extends ActorAction {

	Double glideOffset;
	
	public GlidingAction(Actor actor,Double offset) {
		super(actor);
		glideOffset = offset;
	}
	
	@Override
	public Object[] getParameters(){
		return new Object[]{getMyActor(),glideOffset};
	}

	public Double getGlideOffset() {
		return glideOffset;
	}
	
	@Override
	public abstract void perform();

}
