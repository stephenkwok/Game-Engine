package gui.view;

import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IEditableGameElement;
import javafx.scene.Node;
import javafx.scene.control.Label;
/**
 * ComboBox with only text representation of options. 
 * @author AnnieTang
 *
 */
public abstract class ComboBoxTextCell extends ComboBoxParent {
	
	public ComboBoxTextCell(ResourceBundle myResources, String promptText, String labelText) {
		super(promptText);
		this.labelText = labelText;
	}

	@Override
	public abstract void setButtonAction();

	@Override
	protected Node getNodeForBox(String item) {
		return new Label(NO_NODE_FOR_BOX);
	}
	
	@Override
	public abstract List<String> getOptionsList();

}
