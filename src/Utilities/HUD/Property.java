package Utilities.HUD;

import java.util.Observable;

public class Property extends Observable{
	Class<?> type;
	Object value;
	
	public Property(Object value) {
		this.value = value;
		this.type = value.getClass();
	}
	
	public void setValue(Object newValue) throws IllegalArgumentException {
		
		if (newValue.getClass().equals(type)) {
			value = newValue;
			setChanged();
			notifyObservers();
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public Object getValue() {
		return value;
	}
	
	
	public Class<?> getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
	
}
