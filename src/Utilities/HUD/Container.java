package Utilities.HUD;

import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Container implements Observer{
	
	Property value1;
	Property value2;
	Property value3;
	
	public Container(Property v1, Property v2, Property v3) {
		value1 = v1;
		value2 = v2;
		value3 = v3;
		value1.addObserver(this);
		value2.addObserver(this);
		value3.addObserver(this);
	}
	

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(arg);
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
//		XMLCreator x = new XMLCreator();
//		try {
//			x.saveGame(c, new File("test.xml"));
//		} catch (Exception e) {
//			System.out.println("Failed1");
//		}
		XMLParser xp = new XMLParser();
		Container c2 = null;
		try {
			c2 = xp.extractGame(new File("test.xml"));
		} catch (Exception e) {
			System.out.println("Failed2");
		}
		System.out.println(c2);
	}

}
