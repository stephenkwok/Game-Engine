package gameengine.model;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import gameengine.model.Triggers.AttributeReached;
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


    private Property<Integer> myValue;
    private Set<Integer> myTriggerValues;
    private AttributeType myType;
    private IGameElement myOwner;

    public Attribute(AttributeType type, int initialValue, IGameElement owner) {
        myType = type;
        myValue = new Property<Integer>(initialValue,owner.getName()+ " " + formatAttributeName(type.toString()));
        myTriggerValues = new HashSet<Integer>();
        myOwner = owner;
    }

    /**
     * Changes the Attribute's current value
     *
     * @param change The amount to change the value by
     */
    public void changeAttribute(int change) {
        myValue.setValue(myValue.getValue()+change);
        if(myTriggerValues.size()>0 && myTriggerValues.contains(myValue.getValue())){
        	myOwner.handleReachedAttribute(new AttributeReached(myOwner, myType, myValue.getValue()));
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
        return myValue.getValue();
    }

    /**
     * Provides the AttributeType for the Attribute
     * @return  The Attribute's AttributeType
     */
    public AttributeType getMyType() {
        return myType;
    }
    
    public Property<Integer> getProperty() {
    	return myValue;
    }
    
    public Set<Integer> getTriggerValues(){
    	return myTriggerValues;
    }
    
    public void setTriggerValues(Set<Integer> triggers){
    	myTriggerValues = triggers;
    }
    
    private String formatAttributeName(String rawName) {
    	return Character.toUpperCase(rawName.charAt(0)) + rawName.substring(1).toLowerCase();
    }
        
}