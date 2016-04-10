package gui.view;

import gui.controller.IScreenController;
import javafx.scene.Node;
import javafx.scene.control.Menu;

public abstract class MenuParent implements IGUIElement{

	protected IScreenController myController;
	private String menuText;
	protected Menu myItem;
	
	public MenuParent(IScreenController myController, String menuText) {
		this.myController = myController;
		this.menuText = menuText;
	}

	@Override
	public Node createNode() {
		myItem = new Menu();
		myItem.setText(menuText);
		setOnButtonAction();
		//THIS RETURNS NULL BECAUSE A MENU IS NOT A NODE
		return null;
	}
	
	public abstract void setOnButtonAction();
}