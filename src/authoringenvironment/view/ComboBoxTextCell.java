package authoringenvironment.view;

import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Label;
/**
 * ComboBox with text options. 
 * @author AnnieTang
 *
 */
abstract class ComboBoxTextCell extends ComboBoxParent {
	
	public ComboBoxTextCell(ResourceBundle myResources, String promptText) {
		super(promptText);
	}

	@Override
	abstract void setButtonAction();

	@Override
	protected Node getNodeForBox(String item) {
		return new Label(NO_NODE_FOR_BOX);
	}

	@Override
	abstract List<String> getOptionsList();

}
