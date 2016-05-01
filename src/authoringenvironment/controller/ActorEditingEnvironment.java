package authoringenvironment.controller;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import authoringenvironment.model.ActorRuleCreator;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import authoringenvironment.view.ActorImageViewer;
import authoringenvironment.view.GUILibrary;
import authoringenvironment.view.ImageviewActorIcon;
import authoringenvironment.view.TabFields;
import gameengine.model.Actor;
import gameengine.model.IRule;
import gui.view.CheckBoxApplyPhysics;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Returns BorderPane to represent Actor Editing Environment.
 * 
 * @author AnnieTang
 *
 */
public class ActorEditingEnvironment implements IEditingEnvironment, Observer {
	private static final Color DEFAULT_COLOR = Color.CORNFLOWERBLUE;
	private static final int ICON_WIDTH = 60;
	private static final String NEW_RULE_LABEL = "New Rule";
	private static final String ACTOR_CHARACTERISTICS_RESOURCE = "actorCharacteristicOptions";
	private static final String ACTOR_CHARACTERISTICS = "Actor Characteristics";
	private static final String ACTOR_ATTRIBUTES_RESOURCE = "actorAttributesOptions";
	private static final String ACTOR_ATTRIBUTES = "Actor Attributes";
	private static final int BUTTON_HEIGHT = 30;
	private static final int BUTTON_WIDTH = 100;
	private static final int LEFT_PANE_WIDTH = 350;
	private static final int FIELD_HEIGHT = 400;
	private static final String SET_RULE_LABEL = "Set Rules";
	private static final String APPLY_PHYSICS = "ApplyPhysics";
	private static final int APPLY_PHYSICS_WIDTH = 150;
	private static final Object TICK_KEY = "Tick";
	private BorderPane myRoot;
	private GUILibrary library;
	private TabFields characteristics;
	private TabFields attributes;
	private ResourceBundle myResources;

	private IAuthoringActor myActor;
	private ImageView myActorIV;

	private ActorImageViewer actorImageViewer;
	private ActorRuleCreator myActorRuleCreator;
	private GridPane myActorRuleCreatorPane;

	private Stage myStage;
	private Controller myController;
	
	private CheckBoxApplyPhysics checkPhysics;

	public ActorEditingEnvironment(ResourceBundle myResources, Stage stage, Controller myController) {
		this.myResources = myResources;
		this.myStage = stage;
		this.myController = myController;
		initializeEnvironment();
	}

	/**
	 * Return Pane representation of actor editing environment
	 */
	@Override
	public Pane getPane() {
		return myRoot;
	}

	/**
	 * Initialize resources and create actor editing environment by populating
	 * sections of the screen and setting default new Actor
	 */
	private void initializeEnvironment() {
		myRoot = new BorderPane();
		setDefaultActor();
		myActorRuleCreator = new ActorRuleCreator(this);
		setLeftPane();
		setCenterPane();
		setBottomPane();
	}

	/**
	 * Set Actor of actor editing environment to a default new Actor
	 */
	private void setDefaultActor() {
		IAuthoringActor defaultActor = (IAuthoringActor) new Actor();
		this.myActor = defaultActor;
		this.myActorIV = new ImageviewActorIcon(defaultActor, ICON_WIDTH);
	}

	/**
	 * Populate left section of the actor editing environment
	 */
	private void setLeftPane() {
		VBox vbox = new VBox();
		library = new GUILibrary(myActorRuleCreator);
		library.updateDragEvents();
		actorImageViewer = new ActorImageViewer(this, myActorIV);
		TabPane actorFields = new TabPane();
		actorFields.getTabs().addAll(actorCharacteristics(), actorAttributes());
		actorFields.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		actorFields.setPrefHeight(FIELD_HEIGHT);
		checkPhysics = new CheckBoxApplyPhysics(APPLY_PHYSICS, APPLY_PHYSICS_WIDTH, this);
		checkPhysics.addObserver(this);
		vbox.getChildren().addAll(actorImageViewer.getPane(), checkPhysics.createNode(), actorFields, library.getPane());
		vbox.setPrefWidth(LEFT_PANE_WIDTH);
		myRoot.setLeft(vbox);
	}
	
	private Tab actorCharacteristics(){
		characteristics = new TabFields(myResources, ACTOR_CHARACTERISTICS, ACTOR_CHARACTERISTICS_RESOURCE, myActor);
		characteristics.setObserver(this);
		characteristics.updateEditable(myActor);
		return characteristics.getTab();
	}
	
	private Tab actorAttributes(){
		attributes = new TabFields(myResources, ACTOR_ATTRIBUTES, ACTOR_ATTRIBUTES_RESOURCE, myActor);
		attributes.setObserver(this);
		attributes.updateEditable(myActor);
		return attributes.getTab();
	}

	/**
	 * Populate center section of the actor editing environment
	 */
	private void setCenterPane() {
		myActorRuleCreatorPane = myActorRuleCreator.getGridPane();
		ScrollPane myScrollPane = new ScrollPane();
		myScrollPane.setContent(myActorRuleCreatorPane);
		myRoot.setCenter(myScrollPane);
	}

	/**
	 * Populate bottom section of the actor editing environment
	 */
	private void setBottomPane() {
		HBox hbox = new HBox();
		hbox.setBackground(new Background(new BackgroundFill(DEFAULT_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
		hbox.getChildren().addAll(newRuleButton(), setRuleButton());
		hbox.setAlignment(Pos.CENTER_RIGHT);
		myRoot.setBottom(hbox);
	}

	/**
	 * Returns button to allow users to create a new rule
	 * 
	 * @return
	 */
	private Button newRuleButton() {
		Button toReturn = new Button(NEW_RULE_LABEL);
		toReturn.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		toReturn.setOnAction(event -> {
			myActorRuleCreator.addNewActorRule(); //
			setCenterPane();
			library.updateDragEvents();
		});
		return toReturn;
	}

	private Button setRuleButton() {
		Button toReturn = new Button(SET_RULE_LABEL);
		toReturn.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		toReturn.setOnAction(event -> {
			myActorRuleCreator.setRules();
			myController.updateActors((Actor) myActor);
		});
		return toReturn;
	}

	/**
	 * Set Actor for actor editing environment
	 */
	@Override
	public void setEditableElement(IEditableGameElement editable) {
		myActor = (IAuthoringActor) editable;
		myActorIV = new ImageviewActorIcon(myActor, ICON_WIDTH);
		setLeftPane();
		myActorRuleCreator.updateActorRules();
		library.updateDragEvents();
	}

	/**
	 * Return Actor currently in actor editing environment
	 * 
	 * @return
	 */
	public IEditableGameElement getEditable() {
		return myActor;
	}

	/**
	 * Set image used for Actor currently in actor editing environment
	 * 
	 * @param newImageView
	 */
	public void setActorImage(ImageView newImageView, String imageViewName) {
		myActor.setImageView(newImageView);
		myActor.setImageViewName(imageViewName);
		myActorIV = new ImageviewActorIcon(myActor, ICON_WIDTH);
		setLeftPane();
	}

	@Override
	public Stage getStage() {
		return myStage;
	}

	public Controller getController() {
		return this.myController;
	}

	@Override
	public void update(Observable o, Object arg) {
		myController.updateActors((Actor) arg);
		System.out.println("checkPHysics: ");
		System.out.println(checkPhysics.isSelected());
		if (checkPhysics.isSelected()) {
			myActorRuleCreator.applyPhysics();
		}
		//myActorRuleCreator.applyPhysics();
	}
	
	public boolean shouldApplyPhysics(){
		if(myActor.getRules().get(TICK_KEY)!=null){
			for(IRule rule: myActor.getRules().get(TICK_KEY)){
				if(rule.getMyAction().getClass().getSimpleName().equals(APPLY_PHYSICS)){
					return false;
				}
			}
		}
		return this.checkPhysics.isSelected();
	}
}