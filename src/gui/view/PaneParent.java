package gui.view;

import java.util.Observer;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class PaneParent implements IGUIElement{

	private Pane myPane;
	
	public PaneParent() {
		myPane = new Pane();
	}

	@Override
	public Node createNode() {
		return myPane;
	}

	@Override
	public void addNodeObserver(Observer observer) {
		// TODO Auto-generated method stub
		
	}

}
