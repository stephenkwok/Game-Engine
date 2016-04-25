package gameengine.model;

import gameengine.model.Triggers.AttributeReached;
import java.util.*;

/**
 * This class is purposed to store properties belonging to an actor that are
 * updated throughout the game that may signal some action to be performed once
 * they meet a specified criteria
 * 
 * @author colettetorres
 *
 */
public class Attribute extends Observable {

	private int myValue;
	private Set<Integer> myTriggerValues;
	private AttributeType myType;
	private IGameElement myOwner;

	public Attribute(AttributeType type, int initialValue, IGameElement owner) {
		myType = type;
		myValue = initialValue;
		myTriggerValues = new HashSet<Integer>();
		myOwner = owner;
	}

	/**
	 * Changes the Attribute's current value
	 *
	 * @param change
	 *            The amount to change the value by
	 */
	public void changeAttribute(int change) {
		myValue += change;
		// for bobby
		// setChanged();
		// notifyObservers();
		if (myTriggerValues.size() > 0 && myTriggerValues.contains(myValue)) {
			myOwner.handleReachedAttribute(new AttributeReached(myType, myOwner, myValue));
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
	 * @return The Attribute's current value
	 */
	public int getMyValue() {
		return myValue;
	}

	/**
	 * Provides the AttributeType for the Attribute
	 * 
	 * @return The Attribute's AttributeType
	 */
	public AttributeType getMyType() {
		return myType;
	}
}