package gameengine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import authoringenvironment.model.IEditableGameElement;
import gameengine.model.IActor;
import gameengine.model.IRule;
import gameengine.model.Actions.Action;
import gameengine.model.Triggers.AttributeType;
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

public class Actor extends Observable implements IActor, IEditableGameElement {

    private static final double DEGREES_TO_RADIANS = Math.PI / 180;
    private static final String DEFAULT_NAME = "Default Name";
    private static final String DEFAULT_IMAGE_NAME = "default_actor.jpg";
    private double x;
    private double y;
    private int myID;
    private String myName;
    private ImageView myImageView;
    private Map<String, List<Action>> myRules;
    private Map<AttributeType, Attribute> attributeMap;
    private PhysicsEngine myPhysicsEngine;

    /**
     * Converts a list of Rules to a map of trigger to list of Actions
     */
    public Actor() {
        myRules = new HashMap<>();
        attributeMap = new HashMap<>();
        myName = DEFAULT_NAME;
        setImageView(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(DEFAULT_IMAGE_NAME))));
    }

    /**
     * Moves the Actor based on a provided distance and direction
     *
     * @param distance  The distance to move the Actor
     * @param direction The direction that the Actor should move in
     */
    @Override
    public void move(double distance, double direction) {
        x = distance * Math.cos(direction * DEGREES_TO_RADIANS);
        y = distance * Math.sin(direction * DEGREES_TO_RADIANS);
        System.out.println(x);//
    }

    /**
     * Calls the appropriate sequence of Actions based on a provided Trigger
     *
     * @param myPhysicsEngine
     */
    @Override
    public void performActionsFor(PhysicsEngine myPhysicsEngine, String triggerString) {
        List<Action> myActions = myRules.get(triggerString);
        for (Action myAction : myActions) {
            myAction.perform();
        }
    }

    /**
     * Adds a new Attribute to an Actors
     *
     * @param newAttribute The new Actor Attribute
     */
    public void addAttribute(Attribute newAttribute) {
        attributeMap.put(newAttribute.getType(), newAttribute);
    }

    /**
     * Modifies the current value of an Attribute
     *
     * @param type   The type of the Attribute to be changed
     * @param change The amount to change the Attribute by
     */
    public void changeAttribute(AttributeType type, int change) {

        Attribute myAttribute = attributeMap.get(type);
        myAttribute.changeAttribute(change);
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

    @Override

    public ImageView getImageView() {
        return myImageView;
    }

    @Override
    public void setImageView(ImageView imageView) {
        myImageView = imageView;
    }


    //TODO JUSTIN ::::::::)
    //public void typeOfCollision;

    public void collidesWith(Actor a) {
//		ClickTrigger collision = typeOfCollision(this, a);
//		Action action = myRules.get(collision.getTriggerName()).get(0);
//		action.performOn(a);
    }

    @Override
    public double getX() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getY() {
        // TODO Auto-generated method stub
        return 0;
    }

	public void setEngine(PhysicsEngine physicsEngine) {
		myPhysicsEngine = physicsEngine;
	}
	
	public PhysicsEngine getPhysicsEngine(){
		return myPhysicsEngine;
	}

}
