package authoringenvironment.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.Label;
/**
 * ComboBox with text options. 
 * @author AnnieTang
 *
 */
abstract class ComboBoxText extends GUIComboBox {
	List<String> optionsList;
	public ComboBoxText(ResourceBundle myResources, String promptText, String optionsKey) {
		super(myResources,promptText);
		optionsList = new ArrayList<>(Arrays.asList(myResources.getString(optionsKey).split(",")));
	}

	@Override
	abstract void setButtonAction();

	@Override
	protected Node getNodeForBox(String item) {
		return new Label(NO_NODE_FOR_BOX);
	}

	@Override
	protected List<String> optionsList() {
		return optionsList;
	}

}
