package gameengine.model;

import gameengine.model.Actions.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * This class is purposed to store properties belonging to an actor that are updated throughout the game
 * that may signal some action to be performed once they meet a specified criteria
 *
 * @author colettetorres
 */
public class Attribute extends Observable {

    private int myValue;
    private int myTriggerValue;
    private AttributeType myType;
    private List<Action> myActions;

    public Attribute(AttributeType type, int initialValue, int triggerValue, List<Action> actions) {
        myValue = initialValue;
        myTriggerValue = triggerValue;
        setMyType(type);
        setActions(actions);
    }

    public Attribute(AttributeType type, int initialValue) {
        myType = type;
        myValue = initialValue;
        myTriggerValue = Integer.MAX_VALUE;
        myActions = new ArrayList<>();
    }

    /**
     * Changes the Attribute's current value
     *
     * @param change The amount to change the value by
     */
    public void changeAttribute(int change) {
        myValue += change;
        System.out.println(myValue);
        if (myValue == myTriggerValue) {
            for (Action myAction : myActions) {
                myAction.perform();
            }
        }
    }


    /**
     * Sets the attribute's current value
     *
     * @param myValue
     */
    public void setMyValue(int myValue) {
        this.myValue = myValue;
    }

    /**
     * Determines the criteria the attribute needs to meet to signal some action
     *
     * @param myTriggerValue
     */
    public void setMyTriggerValue(int myTriggerValue) {
        this.myTriggerValue = myTriggerValue;
    }

    /**
     * Gets the action to be triggered once the attribute meets its specified criteria
     *
     * @return the action to be performed once the attribute reaches a certain value
     */
    public List<Action> getMyActions() {
        return myActions;
    }

    /**
     * Provides the Attribute's current value
     *
     * @return The Attribute's current value
     */
    public int getMyValue() {
        return myValue;
    }

    /**
     * Provides the Attribute's trigger value
     *
     * @return The Attribute's trigger value
     */
    public int getMyTriggerValue() {
        return myTriggerValue;
    }

    /**
     * Provides the AttributeType for the Attribute
     *
     * @return The Attribute's AttributeType
     */
    public AttributeType getMyType() {
        return myType;
    }

    /**
     * Sets the Attribute's AttributeTypes
     *
     * @param myType The Attribute's AttributeType
     */
    private void setMyType(AttributeType myType) {
        this.myType = myType;
    }

    /**
     * Sets the Attribute's Action
     *
     * @param myAction The desired Attribute Action
     */
    public void addAction(Action myAction) {
        this.myActions.add(myAction);
    }

    private void setActions(List<Action> actions) {
        myActions = actions;
    }
}

