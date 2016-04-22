package utilities.hud;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Property extends Observable{
	
	private Class<?> type;
	private Object value;
	private List<Property> bindings;
	
	public Property(Object value) {
		bindings = new ArrayList<>();
		if (value instanceof Property) {
			this.type = ((Property) value).getType();
			this.value = ((Property) value).getValue();
			bindings.add((Property) value);
			((Property) value).getBindings().add(this);
		} else {
			this.value = value;
			this.type = value.getClass();
		}
	}
	
	public void setValue(Object newValue) throws IllegalArgumentException {
		if (newValue.getClass().equals(type)) {
			value = newValue;
			setChanged();
			notifyObservers(newValue);
			updateBound();
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
	
	//will set the value of this property to the one it's bound to
	//is a bidirectional binding
	public void bind(Property other) throws IllegalArgumentException {
		if (other.getType().equals(type)) {
			bindings.add(other);
			setValue(other.getValue());
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public void unbind(Property other) {
		bindings.remove(other);
	}
	
	public void updateBound() {
		for (Property p: bindings) {
			if (!p.getValue().equals(value)) {
				p.setValue(value);
			}
		}
	}
	
	public List<Property> getBindings() {
		return bindings;
	}
	
	
}
