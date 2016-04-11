package gameplayer.view;

import java.util.Properties;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;

public abstract class Window {
	
	private String TITLE;
	private Group root;
	
	private double WINDOW_WIDTH;
	private double WINDOW_HEIGHT;
	Properties UILabels;
	
	public Window(double width, double height) {
		root = new Group();
		WINDOW_WIDTH = width;
		WINDOW_HEIGHT = height;
		TITLE = null;
	}
	
	public String getTitle () {
     return TITLE;
 }
	
	public void setTitle(String s) {
		TITLE = s;
	}
	
	public Group getRoot() {
		return root;
	}
	
	public double getWidth() {
		return WINDOW_WIDTH;
	}
	
	
	public double getHeight() {
		return WINDOW_HEIGHT;
	}
	
	public abstract void init();
	

}