package gui.view;

import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.Label;
/**
 * ComboBox with only text representation of options. 
 * @author AnnieTang
 *
 */
public abstract class ComboBoxTextCell extends ComboBoxParent {
	
	public ComboBoxTextCell(String promptText, String labelText) {
		super(promptText);
		setLabelText(labelText);
	}

	@Override
	public abstract void setButtonAction();

	@Override
	protected Node getNodeForBox(String item) {
		return new Label(item);
	}
	
	@Override
	protected abstract List<String> getOptionsList();

}
