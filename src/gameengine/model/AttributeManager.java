package gameengine.model;

import java.util.HashMap;
import java.util.Map;

public class AttributeManager {
	private Map<AttributeType, Attribute> attributeMap = new HashMap<AttributeType, Attribute>();

	/**
	 * Adds an Attribute to the AttributeManager
	 * 
	 * @param attribute	The Attribute to be added
	 */
	public void addAttribute(Attribute attribute) {
		attributeMap.put(attribute.getMyType(), attribute);
	}

	/**
	 * Removes an Attribute from the AttributeManager
	 * 
	 * @param attribute	The Attribute to be removed
	 */
	public void removeAttribute(Attribute attribute) {
		attributeMap.remove(attribute.getMyType());
	}

	/**
	 * Changes the value of an Attribute in the AttributeManager
	 * 
	 * @param type	The AttributeType of the Attribute being changed
	 * @param change	The value to change the Attribute by
	 */
	public void changeAttribute(AttributeType type, int change) {
		Attribute myAttribute = attributeMap.get(type);
		if (myAttribute != null) {
			myAttribute.changeAttribute(change);
		}
	}

	/**
	 * Gets the AttributeManager's Attribute of a particular AttributeType
	 * 
	 * @param type	The AttributeType of the Attribute to get
	 * @return	The Attribute for the desired AttributeType
	 */
	public Attribute getAttribute(AttributeType type) {
		return attributeMap.get(type);
	}
	
	/**
	 * Provides the AttributeManager's Attribute Map
	 * 
	 * @return	The AttributeManager's Attribute Map
	 */
	public Map<AttributeType, Attribute> getAttributeMap() {
		return attributeMap;
	}
}
