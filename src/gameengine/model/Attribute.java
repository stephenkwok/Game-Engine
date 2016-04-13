package gameengine.model;

import gameengine.model.Actions.Action;

import java.util.Observable;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import gameengine.model.Actions.Action;

/**
 * This class is purposed to store properties belonging to an actor that are updated throughout the game 
 * that may signal some action to be performed once they meet a specified criteria
 * @author colettetorres
 *
 */
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

    public Attribute(AttributeType type, int initialValue){
        myType = type;
        myValue = initialValue;
        myTriggerValue = Integer.MAX_VALUE;
        myAction = null;
    }
    
    /**
     * Updates the attribute's current value by the specified amount of change
     * @param change
     */
    public void changeAttribute(int change) {
    	myValue += change;
    	setChanged();
    	notifyObservers();
        if(myValue == myTriggerValue){
        	myAction.perform();
        }
    }
    
    /**
     * Gets the attribute's current value 
     * @return the current value of the attribute
     */
	public int getMyValue() {
		return myValue;
	}
	
	/**
	 * Sets the attribute's current value 
	 * @param myValue
	 */
	public void setMyValue(int myValue) {
		this.myValue = myValue;
	}
	
	/**
	 * Gets the specified value that the attribute needs to meet to signal some action
	 * @return a particular value needed to be reached by the attribute 
	 */
	public int getMyTriggerValue() {
		return myTriggerValue;
	}
	
	/**
	 * Determines the criteria the attribute needs to meet to signal some action
	 * @param myTriggerValue
	 */
	public void setMyTriggerValue(int myTriggerValue) {
		this.myTriggerValue = myTriggerValue;
	}
	
	/**
	 * Gets the type of attribute
	 * @return the type of attribute
	 */
	public AttributeType getMyType() {
		return myType;
	}
	
	/**
	 * Sets the type of the attribute 
	 * @param myType
	 */
	public void setMyType(AttributeType myType) {
		this.myType = myType;
	}
	
	/**
	 * Gets the action to be triggered once the attribute meets its specified criteria
	 * @return the action to be performed once the attribute reaches a certain value 
	 */
	public Action getMyAction() {
		return myAction;
	}
	
	/**
	 * Sets the action to be triggered once the attribute meets its specified criteria
	 * @param myAction
	 */
	public void setMyAction(Action myAction) {
		this.myAction = myAction;
	}
}

