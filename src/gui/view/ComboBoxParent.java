package gui.view;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Abstract class to implement different types of ComboBoxes.
 * 
 * @author AnnieTang
 */

public abstract class ComboBoxParent extends EditingElementParent {
	private static final int COMBOBOX_WIDTH = 150;
	private static final int VISIBLE_ROW_COUNT = 5;
	private static final int HBOX_SPACING = 5;
	private static final String GO = "Go";
	private static final int BUTTON_HEIGHT = 30;
	private static final int BUTTON_WIDTH = 40;
	private String promptText;
	private ObservableList<String> options;
	private ComboBox<String> comboBox;
	private Button comboButton;
	private String labelText;
	private HBox hbox;

	public ComboBoxParent(String promptText) {
		super(GO);
		this.promptText = promptText;
		this.labelText = null;
		this.comboButton = getButton();
		hbox = new HBox(HBOX_SPACING);
	}

	/**
	 * Creates ComboBox Node.
	 */
	@Override
	public Node createNode() {
		options = FXCollections.observableArrayList(getOptionsList());
		addLabel(hbox);
		comboBox = new ComboBox<>(options);
		comboBox.setVisibleRowCount(VISIBLE_ROW_COUNT);
		comboBox.setPrefWidth(COMBOBOX_WIDTH);
		comboBox.setPromptText(promptText);
		comboBox.setCellFactory(factory -> new MyCustomCell());
		comboButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		HBox.setHgrow(comboBox, Priority.ALWAYS);
		setButtonAction();
		hbox.getChildren().addAll(comboBox, comboButton);
		hbox.setAlignment(Pos.CENTER_LEFT);
		return hbox;
	}

	private void addLabel(HBox container) {
		if (labelText != null) {
			Label label = new Label(labelText);
			label.setWrapText(true);
			container.getChildren().add(label);
		}
	}

	/**
	 * Sets action when button is pressed.
	 */
	public abstract void setButtonAction();

	/**
	 * Creates custom cell factory for ComboBox
	 * 
	 * @author AnnieTang
	 */
	private class MyCustomCell extends ListCell<String> {
		@Override
		protected void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			if (item == null || empty) {
				setGraphic(null);
			} else {
				HBox hbox = new HBox();
				// Label lbl = new Label(item);
				hbox.getChildren().addAll(getNodeForBox(item));// ,lbl);
				setGraphic(hbox);
			}
		}
	}

	/**
	 * Sets icon for ComboBox
	 * 
	 * @param item
	 * @return
	 */
	protected abstract Node getNodeForBox(String item);

	/**
	 * Updates Node whenever new information or data is available.
	 */
	public void updateNode() {
		ObservableList<String> newOptions = FXCollections.observableArrayList(getOptionsList());
		comboBox.setItems(newOptions);
	}

	/**
	 * Returns list of items in the ComboBox.
	 * 
	 * @return
	 */
	protected abstract List<String> getOptionsList();

	/**
	 * Gets the label text for this combobox.
	 * 
	 * @return label text.
	 */
	protected String getLabelText() {
		return labelText;
	}

	/**
	 * Sets the label text for this combobox.
	 * 
	 * @param label:
	 *            label to use.
	 */
	protected void setLabelText(String label) {
		labelText = label;
	}

	/**
	 * Gets the GO button for this combobox.
	 * 
	 * @return GO button.
	 */
	protected Button getComboButton() {
		return comboButton;
	}

	/**
	 * Gets the combobox.
	 * 
	 * @return combobox.
	 */
	protected ComboBox<String> getComboBox() {
		return comboBox;
	}
	
	protected HBox getHBox(){
		return this.hbox;
	}
}
