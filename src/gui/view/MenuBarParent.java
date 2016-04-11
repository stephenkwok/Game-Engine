package gui.view;

import gui.controller.IScreenController;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;

public abstract class MenuBarParent implements IGUIElement {

	protected IScreenController myController;
	protected MenuBar myMenu;

	public MenuBarParent(IScreenController myController) {
		this.myController = myController;
	}

	/**
	 * Creates and returns button
	 */
	@Override
	public Node createNode() {
		myMenu = new MenuBar();
		return myMenu;
	}
	
	

	
	public void updateNode(){
		
	}

}