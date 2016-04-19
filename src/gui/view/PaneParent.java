package gui.view;

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

}
