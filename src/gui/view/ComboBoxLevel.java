package gui.view;

import java.util.ResourceBundle;

import gameengine.controller.Level;

public abstract class ComboBoxLevel extends ComboBoxTextCell {
	private Level myLevel;
	
	public ComboBoxLevel(ResourceBundle myResources, String promptText, String labelText) {
		super(myResources, promptText, labelText);
		myLevel = null;
	}
	
	public void setLevel(Level level) {
		myLevel = level;
	}
	
	public Level getLevel() {
		return myLevel;
	}
}
