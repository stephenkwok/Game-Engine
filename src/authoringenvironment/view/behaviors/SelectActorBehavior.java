package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IAuthoringBehavior;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.view.ActionFactory;
import authoringenvironment.view.ActorRule;
import authoringenvironment.view.TriggerFactory;
import gameengine.model.IAction;
import gameengine.model.IRule;
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

public abstract class SelectActorBehavior extends EditingElementParent implements IAuthoringBehavior {
	private static final double IMAGE_HEIGHT = 20;
	private static final String LABEL = "Label";
	private static final String PROMPT = "Prompt";
	private static final int COMBOBOX_WIDTH = 300;
	private static final int HBOX_SPACING = 5;
	private static final String GO = "Go";
	private static final int BUTTON_SIZE = 40;
	private String promptText;
	private ObservableList<IEditableGameElement> options;
	private ComboBox<IEditableGameElement> comboBox;
	private String labelText;
	private TriggerFactory triggerFactory;
	private ActionFactory actionFactory;
	private ActorRule myActorRule;
	private String behaviorType;
	private List<IAuthoringActor> myActors;
	private IAuthoringActor otherActor;
	private IAuthoringActor myActor;
	private IRule myRule;

	public SelectActorBehavior(IRule myRule, ActorRule myActorRule, String behaviorType, ResourceBundle myResources, 
			IAuthoringActor myActor, List<IAuthoringActor> myActors) {
		super(GO);
		this.myRule = myRule;
		this.behaviorType = behaviorType;
		this.promptText = myResources.getString(behaviorType + PROMPT);
		this.labelText = myResources.getString(behaviorType + LABEL);
		this.triggerFactory = new TriggerFactory();
		this.actionFactory = new ActionFactory();
		this.myActorRule = myActorRule;
		this.myActors = myActors;
		this.myActor = myActor;
	}

	/**
	 * Creates ComboBox Node.
	 */
	public Node createNode() {
		HBox hbox = new HBox(HBOX_SPACING);
		Label label = new Label(labelText);
		label.setWrapText(true);
		initComboBox();
		initButton();
		hbox.getChildren().addAll(label, comboBox, getButton());
		hbox.setAlignment(Pos.CENTER_LEFT);
		return hbox;
	}

	/**
	 * Initialize ComboBox with size, row count, text, cell factory
	 */
	private void initComboBox() {
		options = FXCollections.observableArrayList(getOptionsList());
		comboBox = new ComboBox<>(options);
		comboBox.setPrefWidth(COMBOBOX_WIDTH);
		comboBox.setPromptText(promptText);
		comboBox.setCellFactory(factory -> new MyCustomCell());
		HBox.setHgrow(comboBox, Priority.ALWAYS);
	}
	
	private void initButton(){
		getButton().setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
		setButtonAction(e -> {
			this.otherActor = (IAuthoringActor) comboBox.getValue();
			createTriggerOrAction();
			setTriggerOrAction();
		});
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
	 * Returns list of items (IAuthoringActors) in the ComboBox.
	 * 
	 * @return
	 */
	List<IEditableGameElement> getOptionsList() {
		List<IEditableGameElement> toReturn = new ArrayList<>();
		for (IAuthoringActor actor : myActors) {
			toReturn.add(actor);
		}
		return toReturn;
	}

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

	public abstract void updateValueBasedOnEditable();

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
	
	protected IAuthoringActor getMyActor(){
		return this.myActor;
	}
	
	protected IAuthoringActor getOtherActor(){
		return this.otherActor;
	}
	
	protected IRule getMyRule(){
		return this.myRule;
	}
	
	protected ComboBox<IEditableGameElement> getComboBox(){
		return this.comboBox;
	}
}