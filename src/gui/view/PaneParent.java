package gui.view;

import gui.controller.IScreenController;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class PaneParent implements IGUIElement{

	private Pane myPane;
	protected IScreenController myController;
	
	public PaneParent(IScreenController myController) {
		this.myController = myController;
	}

	@Override
	public Node createNode() {
		myPane = new Pane();
		return myPane;
	}

	@Override
	public void updateNode() {
		// TODO Auto-generated method stub
		
	}

}