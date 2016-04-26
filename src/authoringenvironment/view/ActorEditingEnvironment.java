package authoringenvironment.view;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.model.Actor;
import gui.view.GUILibrary;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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
 * @author AnnieTang
 *
 */
public class ActorEditingEnvironment implements IEditingEnvironment, Observer {
	private static final Color DEFAULT_COLOR = Color.CORNFLOWERBLUE;
	private static final int ICON_HEIGHT = 75;
	private static final String NEW_RULE_LABEL = "New Rule";
	private static final String ACTOR_OPTIONS_RESOURCE = "actorEditorOptions";
	private static final String ACTOR_ATTRIBUTES = "Actor Attributes";
	private static final int BUTTON_HEIGHT = 30;
	private static final int BUTTON_WIDTH = 100;
	private static final int LEFT_PANE_WIDTH = 350;
	private static final String SET_RULE_LABEL = "Set Rules";
	private BorderPane myRoot;
	private GUILibrary library;
	private TabFields fields;
	private ResourceBundle myResources;
	
	private IAuthoringActor myActor;
	private ImageView myActorIV;
	
	private GUIActorImageViewer actorImageViewer;
	private ActorRuleCreator myActorRuleCreator;
	private GridPane myActorRuleCreatorPane;
	
	private Stage myStage;
	private Controller myController;

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
	 * Initialize resources and create actor editing environment by populating sections of the screen and setting default new Actor
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
		this.myActorIV = new ImageviewActorIcon(defaultActor, ICON_HEIGHT);
	}
	/**
	 * Populate left section of the actor editing environment
	 */
	private void setLeftPane() {
		VBox vbox = new VBox();
		fields = new TabFields(myResources, ACTOR_ATTRIBUTES, ACTOR_OPTIONS_RESOURCE, myActor);
		fields.setObserver(this);
		fields.updateEditable(myActor);
		TabPane attributeTP = new TabPane();
		attributeTP.getTabs().add(fields.getTab());
		attributeTP.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		library = new GUILibrary(myActorRuleCreator);
		actorImageViewer = new GUIActorImageViewer(this, myActorIV);
		vbox.getChildren().addAll(actorImageViewer.getPane(), attributeTP, library.getPane());
		vbox.setPrefWidth(LEFT_PANE_WIDTH);
		myRoot.setLeft(vbox);
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
	
	private Button setRuleButton(){
		Button toReturn = new Button(SET_RULE_LABEL);
		toReturn.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		toReturn.setOnAction(event -> {
			myActorRuleCreator.setRules();
		});
		return toReturn;
	}

	/**
	 * Set Actor for actor editing environment
	 */
	@Override
	public void setEditableElement(IEditableGameElement editable) {
		myActor = (IAuthoringActor) editable;
		myActorIV = new ImageviewActorIcon(myActor,ICON_HEIGHT);
		setLeftPane();
		myActorRuleCreator.updateActorRules();
		library.updateDragEvents();
	}
	/**
	 * Return Actor currently in actor editing environment
	 * @return
	 */
	public IEditableGameElement getEditable(){
		return myActor;
	}
	
	/**
	 * Set image used for Actor currently in actor editing environment 
	 * @param newImageView
	 */
	public void setActorImage(ImageView newImageView, String imageViewName) {
		myActor.setImageView(newImageView);
		myActor.setImageViewName(imageViewName);
		myActorIV = new ImageviewActorIcon(myActor, ICON_HEIGHT);
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
		myController.updateActors((IAuthoringActor) arg);
	}
}
