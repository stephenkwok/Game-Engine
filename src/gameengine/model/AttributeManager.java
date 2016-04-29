package gameengine.model;

import java.util.*;

public class AttributeManager {
	private Map<AttributeType, Attribute> attributeMap = new HashMap<AttributeType, Attribute>();

	public void addAttribute(Attribute attribute) {
		attributeMap.put(attribute.getMyType(), attribute);
	}

	public void removeAttribute(Attribute attribute) {
		attributeMap.remove(attribute.getMyType());
	}

	public void changeAttribute(AttributeType type, int change) {
		Attribute myAttribute = attributeMap.get(type);
		if (myAttribute != null) {
			myAttribute.changeAttribute(change);
		}
	}

	public Attribute getAttribute(AttributeType type) {
		return attributeMap.get(type);
	}
	
	public Map<AttributeType, Attribute> getAttributeMap() {
		return attributeMap;
	}
}
