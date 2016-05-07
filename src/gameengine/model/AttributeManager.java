// This entire file is part of my masterpiece.
// Colette Torres 
/**
 * This code is purposed to handle a game element's maintenance of attributes, such as by allowing an attribute to be added to or removed from
 * a game element, or by accessing a game element's attributes and modifying them.
 * 
 * As with the RuleManager, I think this class shows good design because it enhances our program's adherence to the Single Responsibility
 * Principle, reducing coupling and and maintaining a cleaner focus for game element classes.  It is also a very concise class with small,
 * specific methods with no code smells.  
 * For more details, see the comment under RuleManager.
 */
package gameengine.model;

import java.util.HashMap;
import java.util.Map;

public class AttributeManager {
	private Map<AttributeType, IAttribute> attributeMap = new HashMap<AttributeType, IAttribute>();

	/**
	 * Adds an Attribute to the AttributeManager
	 * 
	 * @param attribute	The Attribute to be added
	 */
	public void addAttribute(IAttribute attribute) {
		attributeMap.put(attribute.getMyType(), attribute);
	}

	/**
	 * Removes an Attribute from the AttributeManager
	 * 
	 * @param attribute	The Attribute to be removed
	 */
	public void removeAttribute(IAttribute attribute) {
		attributeMap.remove(attribute.getMyType());
	}

	/**
	 * Changes the value of an Attribute in the AttributeManager
	 * 
	 * @param type	The AttributeType of the Attribute being changed
	 * @param change	The value to change the Attribute by
	 */
	public void changeAttribute(AttributeType type, int change) {
		IAttribute myAttribute = attributeMap.get(type);
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
	public IAttribute getAttribute(AttributeType type) {
		return attributeMap.get(type);
	}
	
	/**
	 * Provides the AttributeManager's Attribute Map
	 * 
	 * @return	The AttributeManager's Attribute Map
	 */
	public Map<AttributeType, IAttribute> getAttributeMap() {
		return attributeMap;
	}
}
