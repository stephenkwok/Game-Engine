package authoringenvironment.view;

import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class ComboBoxWinningConditions extends ComboBoxParent {
	private List<String> myOptions;
	
	public ComboBoxWinningConditions(ResourceBundle myResources, String promptText, List<String> options) {
		super(myResources, promptText);
		myOptions = options;
	}

	@Override
	void setButtonAction() {
		// TODO Auto-generated method stub
	}

	@Override
	protected Node getNodeForBox(String item) {
		return new Label(NO_NODE_FOR_BOX);
	}

	@Override
	List<String> getOptionsList() {
		return myOptions;
	}

}
