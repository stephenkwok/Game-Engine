package utilities.hud;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public class Property extends Observable {
	
	private Class<?> type;
	private Object value;
	private Set<Property> bindings;
	private String name;
	
	public Property(Property value) {
		bindings = new HashSet<>();
		this.type = ((Property) value).getType();
		bind((Property) value);
	}
	
	public Property(Object value, String name) {
		this.name = name;
		this.value = value;
		this.type = value.getClass();
		bindings = new HashSet<>();
	}
	
	public void setValue(Object newValue) throws IllegalArgumentException {
		if (newValue.getClass().equals(type)) {
			ValueChange change = new ValueChange(value, newValue, name);
			value = newValue;
			setChanged();
			notifyObservers(change);
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
			this.name = other.getFieldName();
			bindings.add(other);
			setValue(other.getValue());
			other.getBindings().add(this);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public void unbind(Property other) {
		bindings.remove(other);
		other.getBindings().remove(this);
	}
	
	public void updateBound() {
		for (Property p: bindings) {
			if (!p.getValue().equals(value)) {
				p.setValue(value);
			}
		}
	}
	
	public Set<Property> getBindings() {
		return bindings;
	}
	
	public String getFieldName() {
		return name;
	}
	
}
