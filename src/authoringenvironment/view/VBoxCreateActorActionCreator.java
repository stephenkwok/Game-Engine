package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import authoringenvironment.controller.LevelEditingEnvironment;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.model.Actor;
import gameengine.model.IGameElement;
import gameengine.model.Actions.Action;
import gameengine.model.Actions.CreateActor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * VBoxCreateActorActionCreator
 * @author amyzhao
 *
 */
public class VBoxCreateActorActionCreator extends VBox implements ILevelActionCreator {
	private static final String RANDOM = "Randomly spawn within a range";
	private static final String FIXED = "Fixed spawn location";
	private static final String DELIMITER = ",";
	private static final String LEVEL = "Level";
	private static final String LABEL_TEXT = "CreateActorLabelText";
	private static final String INITIAL_POSITION = "Enter initial position of created actor";
	private static final String CHOOSE = "Choose";
	private static final String RANDOM_TEXT_FIELD = "RandomIntervalTextFields";
	private static final String FIXED_TEXT_FIELD = "FixedTextFields";
	private String randomOrFixed;
	private IGameElement myElement;
	private ResourceBundle myResources;
	private Set<IAuthoringActor> myActors;
	private IEditingEnvironment myEditingEnvironment;
	private ComboBox myActorComboBox;
	private ComboBox myRandomOrFixedComboBox;
	private TextField myMaxX;
	private TextField myMaxY;
	private TextField myMinX;
	private TextField myMinY;
	private TextField myX;
	private TextField myY;
	
	/**
	 * Constructor for VBoxCreateActorActionCreator
	 * @param resource: resource bundle to use.
	 * @param element: game element to add action to.
	 * @param environment: editing environment.
	 */
	public VBoxCreateActorActionCreator(ResourceBundle resource, IGameElement element, IEditingEnvironment environment) {
		myElement = element;
		myEditingEnvironment = environment;
		myResources = resource;
		init();
	}
	
	/**
	 * Initialize the vbox.
	 */
	private void init() {
		String[] labelText = myResources.getString(LABEL_TEXT).split(DELIMITER);
		Label[] labels = new Label[labelText.length];
		for (int i = 0; i < labelText.length; i++) {
			labels[i] = new Label(labelText[i]);
		}
		this.getChildren().addAll(makeActorComboBox(labels[0]), makeRandomOrFixedComboBox(labels[1]));
	}
	
	/**
	 * Make combobox based on selection of random or fixed.
	 * @param label: label for combobox.
	 * @return combobox.
	 */
	private HBox makeRandomOrFixedComboBox(Label label) {
		HBox comboContainer = new HBox();
		List<String> options = new ArrayList<>();
		options.add(RANDOM);
		options.add(FIXED);
		ObservableList<String> comboOptions = FXCollections.observableArrayList(options);
		myRandomOrFixedComboBox = new ComboBox<>(comboOptions);
		Button button = new Button(CHOOSE);
		button.setPrefWidth(100);
		button.setOnAction(e -> {
			randomOrFixed = (String) myRandomOrFixedComboBox.getValue();
			displayXYOptions();
		});
		comboContainer.getChildren().addAll(label, myRandomOrFixedComboBox, button);
		return comboContainer;		
	}
	
	/**
	 * Display options for xy positions.
	 */
	private void displayXYOptions() {
		this.getChildren().add(new Label(INITIAL_POSITION));
		switch (randomOrFixed) {
		case RANDOM:
			makeRandomIntervalTextFields();
			break;
		case FIXED:
			makeFixedTextFields();
			break;
		}
	}
	
	/**
	 * Make textfields for a random interval.
	 */
	private void makeRandomIntervalTextFields() {
		String[] labelText = myResources.getString(RANDOM_TEXT_FIELD).split(DELIMITER);
		Label[] labels = new Label[labelText.length];
		for (int i = 0; i < labelText.length; i++) {
			labels[i] = new Label(labelText[i]);
		}
		HBox minXContainer = new HBox();
		myMinX = new TextField();
		minXContainer.getChildren().addAll(labels[0], myMinX);
		
		HBox minYContainer = new HBox();
		myMinY = new TextField();
		minYContainer.getChildren().addAll(labels[1], myMinY);

		HBox maxXContainer = new HBox();
		myMaxX = new TextField();
		maxXContainer.getChildren().addAll(labels[2], myMaxX);

		HBox maxYContainer = new HBox();
		myMaxY = new TextField();
		maxYContainer.getChildren().addAll(labels[3], myMaxY);
		this.getChildren().addAll(minXContainer, minYContainer, maxXContainer, maxYContainer);
	}
	
	/**
	 * Make textfields for fixed position.
	 */
	private void makeFixedTextFields() {
		String[] labelText = myResources.getString(FIXED_TEXT_FIELD).split(DELIMITER);
		Label[] labels = new Label[labelText.length];
		for (int i = 0; i < labelText.length; i++) {
			labels[i] = new Label(labelText[i]);
		}
		HBox myXContainer = new HBox();
		myX = new TextField();
		myXContainer.getChildren().addAll(labels[0], myX);
		
		HBox myYContainer = new HBox();
		myY = new TextField();
		myYContainer.getChildren().addAll(labels[1], myY);
		this.getChildren().addAll(myXContainer, myYContainer);
	}
	
	/**
	 * Make actor selecting combobox.
	 * @param label
	 * @return
	 */
	private HBox makeActorComboBox(Label label) {
		if (myElement.getClass().getSimpleName().equals(LEVEL)) {
			myActors = ((LevelEditingEnvironment) myEditingEnvironment).getAvailableActors();
		}
		HBox comboContainer = new HBox();
		ObservableList<String> options = getActorNames();
		myActorComboBox = new ComboBox<>(options);
		comboContainer.getChildren().addAll(label, myActorComboBox);
		return comboContainer;
	}
	
	/**
	 * Get actor by name.
	 * @return actor.
	 */
	private Actor getActorByName() {
		for (IAuthoringActor actor: myActors) {
			if (actor.getName().equals(myActorComboBox.getValue())) {
				return (Actor) actor;
			}
		}
		return null;
	}
	
	/**
	 * Get available actors names.
	 * @return list of available actors' names.
	 */
	private ObservableList<String> getActorNames() {
		List<String> names = new ArrayList<>();
		for (IAuthoringActor actor: myActors) {
			names.add(actor.getName());
		}
		return FXCollections.observableArrayList(names);
	}
	
	@Override
	public Action createAction() {
		switch (randomOrFixed) {
		case RANDOM:
			return new CreateActor(myElement, getActorByName(),
					Double.parseDouble(myMinX.getText()), Double.parseDouble(myMaxX.getText()),
					Double.parseDouble(myMinY.getText()), Double.parseDouble(myMaxY.getText()));
		case FIXED:
			return new CreateActor(myElement, getActorByName(),
					Double.parseDouble(myX.getText()), Double.parseDouble(myX.getText()));
		}
		return null;
	}

}
