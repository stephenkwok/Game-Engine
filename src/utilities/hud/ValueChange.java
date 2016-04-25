package Utilities.HUD;

public class ValueChange {
	private Object oldValue;
	private Object newValue;
	private Class<?> type;
	private String name;

	public ValueChange(Object oldValue, Object newValue, String name) {
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.type = newValue.getClass();
		this.name = name;
	}

	public ValueChange(Object newValue, String name) {
		this(null, newValue, name);
	}

	public Class<?> getType() {
		return type;
	}

	public Object getOldValue() {
		return oldValue;
	}

	public Object getNewValue() {
		return newValue;
	}

	public String getFieldName() {
		return name;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append("- Old: ");
		sb.append(oldValue);
		sb.append(", New: ");
		sb.append(newValue);
		return sb.toString();
	}

}
