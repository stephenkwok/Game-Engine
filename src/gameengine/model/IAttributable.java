package gameengine.model;

import gameengine.model.Triggers.AttributeReached;

public interface IAttributable {
	public void handleReachedAttribute(AttributeReached trigger);
    public Attribute getAttribute(AttributeType type);
    public String getName();
    public void changeAttribute(AttributeType type, int change);
}
