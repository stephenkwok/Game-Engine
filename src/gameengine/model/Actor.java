package gameengine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import authoringenvironment.model.IEditableGameElement;
import gameengine.controller.Action;
import gameengine.model.Triggers.ClickTrigger;
import gameengine.model.Triggers.CollisionTrigger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class defines the logic for an Actor object.
 * Each interactive element in the Game is an Actor. Actors contains a set of rules that are contained
 * within the myRules map. When provided with a Trigger object, all actions associated with a particular Trigger
 * are executed. The Actor also extends the ImageView class so they will also be visual elements.
 *
 * @author blakekaplan
 */

public class Actor extends ImageView implements IActor, IEditableGameElement {

    private static final double DEGREES_TO_RADIANS = Math.PI / 180;
    private static final String DEFAULT_NAME = "Default Name";
    private static final String DEFAULT_IMAGE_NAME = "default_actor.jpg";
    private int health;
    private int points;
    private int myID;
    private String myName;
    private String myActorType;
    private int myStrength;

    private Map<String, List<Action>> myRules;

    /**
     * Converts a list of Rules to a map of trigger to list of Actions
     *
     */
    public Actor() {
        myRules = new HashMap<>();
        myName = DEFAULT_NAME;
        setImage(new Image(getClass().getClassLoader().getResourceAsStream(DEFAULT_IMAGE_NAME)));
    }

    /**
     * Provides the Actor's health
     *
     * @return The Actor's health
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Updates the Actor's health
     *
     */
    public void setHealth(int newHealth){
    	health = newHealth;
    }
    

    /**
     * Provides the Actor's number of points
     *
     * @return The Actor's number of points
     */
    @Override
    public int getPoints() {
        return points;
    }
    
	/**
	 * Updates the Actor's number of points
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	

    /**
     * Moves the Actor based on a provided distance and direction
     *
     * @param distance  The distance to move the Actor
     * @param direction The direction that the Actor should move in
     */
    @Override
    public void move(double distance, double direction) {
        setX(distance * Math.cos(direction * DEGREES_TO_RADIANS));
        setY(distance * Math.sin(direction * DEGREES_TO_RADIANS));
    }

    /**
     * Changes the Actor's number of points
     *
     * @param change The desired change in the Actor's number of points
     */
    @Override
    public void changePoints(int change) {
        points += change;
    }

    /**
     * Changes the Actor's health
     *
     * @param change The desired change in the Actor's amount of health
     */
    @Override
    public void changeHealth(int change) {
        health += change;
    }

    /**
     * Calls the appropriate sequence of Actions based on a provided Trigger
     * @param myPhysicsEngine 
     */
    @Override
    public void performActionsFor(PhysicsEngine myPhysicsEngine, String triggerString) {
        List<Action> myActions = myRules.get(triggerString);
        for (Action myAction : myActions) {
            myAction.perform(myPhysicsEngine);
        }
    }

    /**
     * Adds a new Rule to the Actor
     *
     * @param newRule The Rule to be added to the Actor
     */
    @Override
    public void addRule(IRule newRule) {
        if (myRules.containsKey(newRule.getTrigger().getTriggerName())) {
            List<Action> myActions = myRules.get(newRule.getTrigger().getTriggerName());
            myActions.add(newRule.getAction());
            myRules.put(newRule.getTrigger().getTriggerName(), myActions);
        } else {
            List<Action> myActions = new ArrayList<>();
            myActions.add(newRule.getAction());
            myRules.put(newRule.getTrigger().getTriggerName(), myActions);
        }
    }

    /**
     * Provides a list of Triggers that the Actor responds to
     *
     * @return The list of Triggers that the Actor responds to
     */
    public Set<String> getTriggers() {
        return myRules.keySet();
    }


    /**
     * Sets the Actor type to distinguish between enemy/neutral etc
     * @param newActorType
     */
	public void setActorType(String newActorType){
		myActorType = newActorType;
	}

    /**
     * @return The Actor's type
     */
	public String getActorType() {
		return myActorType;
	}
	
    /**
     * Provides the Actor's ID number
     *
     * @return The Actor's ID number
     */
    @Override
    public int getID() {
        return myID;
    }

	@Override
	public double getXVelo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getYVelo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setXPos(double updateXPosition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setYPos(double updateYPosition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setXVelo(double updateXVelo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setYVelo(double updateYVelo) {
		// TODO Auto-generated method stub
	}
	
	public void setID(int ID) {
		myID = ID;
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	public void setName(String name) {
		myName = name;
	}

	public int getStrength() {
		return myStrength;
	}
	//TODO JUSTIN ::::::::)
	//public void typeOfCollision;
	
	public void collidesWith(Actor a) {
//		ClickTrigger collision = typeOfCollision(this, a);
//		Action action = myRules.get(collision.getTriggerName()).get(0);
//		action.performOn(a);
	}

}
