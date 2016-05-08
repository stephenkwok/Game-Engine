// This entire file is part of my masterpiece.
// Michelle Chen

/**
 * Much of my masterpiece focuses on the backend design of rules, the basic building blocks for "things happening" in our games. 
 * Thus, I begin in the rule class.
 * 
 * Actor behavior is defined by "rules" that represent a one-to-one mapping of triggers (causes) to actions (effects). One of our
 * primary design goals as a team was to be as flexible as possible in letting users determine said rules for their game,
 * and this component of my masterpiece in particular showcases the linking of a trigger and associated action to be performed when the 
 * trigger is signaled. It strives to keep triggers and actions very modular and in doing so, enables our engine to support 
 * many different types of game genres. Through utilization of the rule class, the actor is thus only defined by its provided 
 * behaviors, which makes for very easy utilization and extensibility (which I will speak to more in the action classes) and also allows
 * us to create any type of actor that does anything within the context of our game engine. Rules build off of the flexibility of the 
 * actor and implement a hierarchy that hypothetically lets us extend our engine to beyond even the realm of just games.
 * 
 * Usage of rules within our program not only makes for a more modular workflow, but also reveals flexibility in our overall 
 * design. The flow between triggers and actions is a direct effect of our overall engine hierarchy: we placed a very heavy 
 * focus on creating a hierarchy for this architecture where the most fundamental components of the game are closed so as to 
 * very clearly define the role, a trigger plays in the flow of the game while allowing for their extension to support different 
 * types of triggers as games are designed to be more and more complex. By means of some sort of trigger signal (e.g. a mouse click, 
 * a key press, a collision) and resulting executable action (e.g. move a character right, fire a missile from the character, 
 * etc.), we are able to support many different types of game genres. In order to make an actor do more, the user need only to
 * create another rule linking a trigger to an action, clear evidence of the overall extensibility of this component.
 * 
 * I note here that though I did not author this class, I (and the other members of the engine team) had several extensive conversations 
 * about the overall hierarchy and structure of our engine set up. I include this file in my masterpiece because I believe that it plays 
 * a central role in the overall design that I am highlighting.
 */


package gameengine.model;

import gameengine.model.Actions.Action;
import gameengine.model.Triggers.ITrigger;

/**
 * Contains a trigger and linked action to be performed when the trigger is
 * signaled
 * 
 * @author blakekaplan
 *
 */

public class Rule implements IRule{

    private ITrigger myTrigger;
    private Action myAction;
    
    /**
     * Rules represent the cause and effect carried out by a one to one mapping of a trigger to an action
     * @param trigger
     * @param action
     */
    public Rule(ITrigger trigger, Action action){
        setMyTrigger(trigger);
        setMyAction(action);
    }
    
    /**
     * Returns the trigger of the rule 
     */
    @Override
    public ITrigger getMyTrigger() {
        return myTrigger;
    }
    
    /**
     * Returns the action of the rule 
     */
    @Override
    public Action getMyAction() {
        return myAction;
    }
    
    /**
     * Sets the trigger for the rule 
     * @param myTrigger
     */
	public void setMyTrigger(ITrigger myTrigger) {
		this.myTrigger = myTrigger;
	}

	/**
	 * Sets the action for the rule
	 * 
	 * @param myAction
	 */
	public void setMyAction(Action myAction) {
		this.myAction = myAction;
	}
	
	/**
	 * Returns the trigger to action relation as a string
	 */
	@Override
	public String toString() {
		return "Trigger: " + myTrigger.getClass().getSimpleName() + "\t causes \t Action:" + myAction.getClass().getSimpleName();
	}
	
	/**
	 * Checks for equality between rules
	 */
	@Override
	public boolean equals(Object other){
		return ((Rule)other).getMyAction().equals(getMyAction()) && myTrigger.equals(((Rule)other).getMyTrigger());
	}
}
