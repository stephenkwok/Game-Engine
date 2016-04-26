package gameengine.model;

import gameengine.model.Triggers.AttributeReached;
import java.util.*;

import voogasalad.util.hud.source.Property;


/**
 * This class is purposed to store properties belonging to an actor that are
 * updated throughout the game that may signal some action to be performed once
 * they meet a specified criteria
 * 
 * @author colettetorres
 *
 */
public class Attribute extends Observable {


    private Property myValue;
    private Set<Integer> myTriggerValues;
    private AttributeType myType;
    private IGameElement myOwner;

    public Attribute(AttributeType type, int initialValue, IGameElement owner) {
        myType = type;
        myValue = new Property(initialValue,owner.getName()+type.toString());
        myTriggerValues = new HashSet<Integer>();
        myOwner = owner;
    }

    /**
     * Changes the Attribute's current value
     *
     * @param change The amount to change the value by
     */
    public void changeAttribute(int change) {
        myValue.setValue((int)myValue.getValue()+change);
        if(myTriggerValues.size()>0 && myTriggerValues.contains((int)myValue.getValue())){
        	myOwner.handleReachedAttribute(new AttributeReached(myType,myOwner,(int)myValue.getValue()));
        }
    }
	
	/**
	 * Determines the criteria the attribute needs to meet to signal some action
	 * 
	 * @param myTriggerValue
	 */
	public void addTriggerValue(int myTriggerValue) {
		myTriggerValues.add(myTriggerValue);
	}

    /**
     * Provides the Attribute's current value
     *
     * @return  The Attribute's current value
     */
    public int getMyValue() {
        return (int)myValue.getValue();
    }

    /**
     * Provides the AttributeType for the Attribute
     * @return  The Attribute's AttributeType
     */
    public AttributeType getMyType() {
        return myType;
    }
    
    
    public Property getProperty() {
    	return myValue;
    }
        
}