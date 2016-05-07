package gameengine.model;

import java.util.Set;

import voogasalad.util.hud.source.Property;

public interface IAttribute {
    /**
     * Provides the AttributeType for the Attribute
     * @return  The Attribute's AttributeType
     */
    public AttributeType getMyType();
    
    /**
     * Changes the Attribute's current value
     *
     * @param change The amount to change the value by
     */
    public void changeAttribute(int change);

    /**
     * Provides the Attribute's current value
     *
     * @return  The Attribute's current value
     */
	public int getMyValue();
	
    /**
     * Provides the Atrribute's Trigger values
     * 
     * @return	The Attribute's Trigger values
     */
	public Set<Integer> getTriggerValues();
	
	/**
	 * Determines the criteria the attribute needs to meet to signal some action
	 * 
	 * @param myTriggerValue
	 */
	public void addTriggerValue(int triggerValue);

	public Property<?> getProperty();
}
