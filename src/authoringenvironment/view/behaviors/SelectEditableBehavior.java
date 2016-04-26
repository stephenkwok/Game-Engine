package authoringenvironment.view.behaviors;

import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.view.ActionFactory;
import authoringenvironment.view.ActorRule;
import authoringenvironment.view.TriggerFactory;
import gameengine.model.IAction;
import gameengine.model.Triggers.ITrigger;
import gui.view.EditingElementParent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Abstract class to implement ComboBox<IAuthoringActor> or ComboBox<Level>.
 * 
 * @author AnnieTang
 */

public abstract class SelectEditableBehavior extends EditingElementParent implements IAuthoringBehavior {
	private static final double IMAGE_HEIGHT = 20;
	private static final String LABEL = "Label";
	private static final String PROMPT = "Prompt";
	private static final int COMBOBOX_WIDTH = 150;
	private static final int VISIBLE_ROW_COUNT = 5;
	private static final int HBOX_SPACING = 5;
	private static final String GO = "Go";
	private static final int BUTTON_HEIGHT = 30;
	private static final int BUTTON_WIDTH = 40;
	private String promptText;
	private ObservableList<IEditableGameElement> options;
	private ComboBox<IEditableGameElement> comboBox;
	private String labelText;
	private TriggerFactory triggerFactory;
	private ActionFactory actionFactory;
	private ActorRule myActorRule;
	private String behaviorType;

	public SelectEditableBehavior(ActorRule myActorRule, String behaviorType, ResourceBundle myResources) {
		super(GO);
		this.behaviorType = behaviorType;
		this.promptText = myResources.getString(behaviorType + PROMPT);
		this.labelText = myResources.getString(behaviorType + LABEL);
		this.triggerFactory = new TriggerFactory();
		this.actionFactory = new ActionFactory();
		this.myActorRule = myActorRule;
	}

	/**
	 * Creates ComboBox Node.
	 */
	public Node createNode() {
		HBox hbox = new HBox(HBOX_SPACING);
		options = FXCollections.observableArrayList(getOptionsList());
		Label label = new Label(labelText);
		label.setWrapText(true);
		initComboBox();
		getButton().setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		HBox.setHgrow(comboBox, Priority.ALWAYS);
		hbox.getChildren().addAll(label, comboBox, getButton());
		hbox.setAlignment(Pos.CENTER_LEFT);
		return hbox;
	}

	/**
	 * Initialize ComboBox with size, row count, text, cell factory
	 */
	private void initComboBox() {
		comboBox = new ComboBox<>(options);
		comboBox.setVisibleRowCount(VISIBLE_ROW_COUNT);
		comboBox.setPrefWidth(COMBOBOX_WIDTH);
		comboBox.setPromptText(promptText);
		comboBox.setCellFactory(factory -> new MyCustomCell());
	}

	/**
	 * Creates custom cell factory for ComboBox
	 * 
	 * @author AnnieTang
	 */
	private class MyCustomCell extends ListCell<IEditableGameElement> {
		@Override
		protected void updateItem(IEditableGameElement item, boolean empty) {
			super.updateItem(item, empty);
			if (item == null || empty) {
				setGraphic(null);
			} else {
				HBox graphic = new HBox();
				ImageView imageView = item.getImageView();
				imageView.setFitHeight(IMAGE_HEIGHT);
				imageView.setPreserveRatio(true);
				graphic.getChildren().addAll(imageView, new Label(item.getName()));
				setGraphic(graphic);
			}
		}
	}

	/**
	 * Updates Node whenever new information or data is available.
	 */
	public void updateNode() {
		options = FXCollections.observableArrayList(getOptionsList());
		comboBox.setItems(options);
	}

	/**
	 * Returns list of items in the ComboBox.
	 * 
	 * @return
	 */
	abstract List<IEditableGameElement> getOptionsList();

	/**
	 * Create ITrigger or IAction depending on type of behavior
	 */
	protected abstract void createTriggerOrAction();

	/**
	 * Add ITrigger or IAction to actor IRule
	 */
	public abstract void setTriggerOrAction();

	/**
	 * Return if this behavior is a trigger
	 */
	public abstract boolean isTrigger();

	protected abstract void updateValueBasedOnEditable();

	/**
	 * Gets the combobox.
	 * 
	 * @return combobox.
	 */
	protected ComboBox<IEditableGameElement> getComboBox() {
		return comboBox;
	}

	/**
	 * Gets the trigger factory.
	 * 
	 * @return factory
	 */
	protected TriggerFactory getTriggerFactory() {
		return this.triggerFactory;
	}

	/**
	 * Gets the action factory.
	 * 
	 * @return factory
	 */
	protected ActionFactory getActionFactory() {
		return this.actionFactory;
	}

	public void setTrigger(IAuthoringBehavior key, ITrigger value) {
		myActorRule.setTrigger(key, value);
	}

	public void setAction(IAuthoringBehavior key, IAction value) {
		myActorRule.setAction(key, value);
	}

	protected String getBehaviorType() {
		return this.behaviorType;
	}
}
