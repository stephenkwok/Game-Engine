package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.sun.xml.internal.bind.v2.runtime.output.NamespaceContextImpl.Element;

import authoringenvironment.model.IActionCreator;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gameengine.model.IAction;
import gameengine.model.IGameElement;
import gameengine.model.Actions.CreateActor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class CreateActorActionCreator extends VBox implements IActionCreator {
	private static final String RANDOM = "Randomly spawn within a range";
	private static final String FIXED = "Fixed spawn location";
	private static final String DELIMITER = ",";
	private static final String LEVEL = "Level";
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
	
	public CreateActorActionCreator(ResourceBundle resource, IGameElement element, IEditingEnvironment environment) {
		myElement = element;
		myEditingEnvironment = environment;
		myResources = resource;
		init();
	}
	
	private void init() {
		String[] labelText = myResources.getString("CreateActorLabelText").split(DELIMITER);
		Label[] labels = new Label[labelText.length];
		for (int i = 0; i < labelText.length; i++) {
			labels[i] = new Label(labelText[i]);
		}
		this.getChildren().addAll(makeActorComboBox(labels[0]), makeRandomOrFixedComboBox(labels[1]));
	}
	
	private HBox makeRandomOrFixedComboBox(Label label) {
		HBox comboContainer = new HBox();
		List<String> options = new ArrayList<>();
		options.add(RANDOM);
		options.add(FIXED);
		ObservableList<String> comboOptions = FXCollections.observableArrayList(options);
		myRandomOrFixedComboBox = new ComboBox<>(comboOptions);
		Button button = new Button("Choose");
		button.setOnAction(e -> {
			randomOrFixed = (String) myRandomOrFixedComboBox.getValue();
			displayXYOptions();
		});
		comboContainer.getChildren().addAll(label, myRandomOrFixedComboBox, button);
		return comboContainer;		
	}
	
	private void displayXYOptions() {
		this.getChildren().add(new Label("Enter initial position of created actor"));
		switch (randomOrFixed) {
		case RANDOM:
			makeRandomIntervalTextFields();
			break;
		case FIXED:
			makeFixedTextFields();
			break;
		}
	}
	
	private void makeRandomIntervalTextFields() {
		String[] labelText = myResources.getString("RandomIntervalTextFields").split(DELIMITER);
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
	
	private void makeFixedTextFields() {
		String[] labelText = myResources.getString("FixedTextFields").split(DELIMITER);
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
	
	private Actor getActorByName() {
		for (IAuthoringActor actor: myActors) {
			if (actor.getName().equals(myActorComboBox.getValue())) {
				return (Actor) actor;
			}
		}
		return null;
	}
	
	private ObservableList<String> getActorNames() {
		List<String> names = new ArrayList<>();
		for (IAuthoringActor actor: myActors) {
			names.add(actor.getName());
		}
		return FXCollections.observableArrayList(names);
	}
	@Override
	public IAction createAction() {
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
