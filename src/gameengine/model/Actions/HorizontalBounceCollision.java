// This entire file is part of my masterpiece.
// Michelle Chen

/**
 * This class is an example of an action, the component of a rule that represents the "effect" from a triggering "cause". HorizontalBounceCollision works in part 
 * through utilization of the physics engine--here, when an actor collides with an obstacle, it uses the force of gravity and a "bounce" factor to elastically push 
 * the actor back as to not move through the object it collides with. It is different from HorizontalStaticCollision: in a bounce collision, the actor physically 
 * "bounces" back, rather than just hitting an obstacle and stopping (which is what the static collision does). HorizontalBounceCollision is furthermore an event triggered action 
 * that acts exclusively on Actors that contain it. It is one of many movement based actions we implement; I include it in my masterpiece to speak more to the flow
 * of our design while giving a concrete example of the rule component in action.
 * 
 * An overarching theme of our action classes are that they are short and readable--class names make clear the functionality of the classes. Use of descriptive 
 * naming conventions for methods and variables allows for more readability and an easier understanding of our design for future extensions. To that end, I believe
 * the implemented features in the actions package are probably some of the easiest to extend: by following the open-closed principle, implementing any new action would 
 * only require extending the relevant class and implementing the methods (as well as creating a corresponding method inside of the physics engine if the action reqires
 * forces and values contained in the physics engine). Actions are also incredibly flexible--actors within our game engine can carry out any user defined action. By 
 * extending the abstract class ActorAction (that extends the abstract class Action which implements the IAction interface), we can more effectively share code among closely 
 * related classes and utilize common methods to modify the state of the object (the actor) in our methods. Of course, in implementing actions, we strove to carry out principles
 * of object oriented programming. More specifically, the inheritance hierarchy touched on above reduces duplicated code and organizes our code to increase functionality. We
 * are also careful to utilize encapsulation in order to hide unnecessary details in our classes and provide a clear interface for working with them.
 */

package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * This Action represents an elastic sideways collision between Actors
 * 
 * @author justinbergkamp, michellechen
 *
 */

public class HorizontalBounceCollision extends ActorAction{

	public HorizontalBounceCollision(Actor assignedActor) {
		super(assignedActor);
	}

	/**
	 * Collides actor elastically against object
	 */
	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().horizontalBounceCollision(getMyActor());
		
	}

}
