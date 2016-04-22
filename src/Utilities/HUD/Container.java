package Utilities.HUD;

public class Container {
	
	Property value1;
	Property value2;
	Property value3;
	
	public Container(Property v1, Property v2, Property v3) {
		value1 = v1;
		value2 = v2;
		value3 = v3;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(value1);
		sb.append(", ");
		sb.append(value2);
		sb.append(", ");
		sb.append(value3);
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Container c = new Container(new Property(1), new Property("hello"), new Property("aylmao"));
		System.out.println(c.toString());
		c.value1.setValue(5);
		System.out.println(c.toString());
	}

}
