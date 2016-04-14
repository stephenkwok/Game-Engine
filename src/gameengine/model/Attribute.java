package gameengine.model;

import gameengine.model.Actions.Action;

import java.util.Observable;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import gameengine.model.Actions.Action;

public class Attribute extends Observable {

    private int myValue;
    private int myTriggerValue;
    private AttributeType myType;
    private Action myAction;

    public Attribute(AttributeType type, int initialValue, int triggerValue, Action action) {
        myValue = initialValue;
        myTriggerValue = triggerValue;
        setMyType(type);
        setMyAction(action);
    }

    public Attribute(AttributeType type, int initialValue) {
        myType = type;
        myValue = initialValue;
        myTriggerValue = Integer.MAX_VALUE;
        myAction = null;
    }

    /**
     * Changes the Attribute's current value
     *
     * @param change The amount to change the value by
     */
    public void changeAttribute(int change) {
        myValue += change;
        setChanged();
        notifyObservers();
        if (myValue == myTriggerValue) {
            myAction.perform();
        }
    }

    /**
     * Provides the Attribute's current value
     *
     * @return  The Attribute's current value
     */
    public int getMyValue() {
        return myValue;
    }

    /**
     * Provides the Attribute's trigger value
     * @return  The Attribute's trigger value
     */
    public int getMyTriggerValue() {
        return myTriggerValue;
    }

    /**
     * Provides the AttributeType for the Attribute
     * @return  The Attribute's AttributeType
     */
    public AttributeType getMyType() {
        return myType;
    }

    /**
     * Sets the Attribute's AttributeTypes
     * @param myType    The Attribute's AttributeType
     */
    private void setMyType(AttributeType myType) {
        this.myType = myType;
    }

    /**
     * Sets the Attribute's Action
     * @param myAction  The desired Attribute Action
     */
    private void setMyAction(Action myAction) {
        this.myAction = myAction;
    }
}

