package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.model.Actor;
import gameengine.model.IAuthoringActor;
import gui.view.GUILibrary;
import gui.view.IGUI;
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

/**
 * Returns BorderPane to represent Actor Editing Environment.
 * 
 * @author AnnieTang
 *
 */
public class GUIActorEditingEnvironment implements IGUI, IEditingEnvironment {
	private static final String NEW_RULE_LABEL = "New Rule";
	private static final String ACTOR_OPTIONS_RESOURCE = "actorEditorOptions";
	private static final String ACTOR_ATTRIBUTES = "Actor Attributes";
	private static final int BUTTON_HEIGHT = 30;
	private static final int BUTTON_WIDTH = 100;
	private static final int LEFT_PANE_WIDTH = 350;
	private BorderPane myRoot;
	private GUILibrary library;
	private TabAttributes attributes;
	private Controller myController;
	private ResourceBundle myResources;
	private IAuthoringActor myActor;
	private ImageView myActorIV;
	private ActorRuleCreator myActorRuleCreator;
	private GridPane myRuleCreator;
	private GUIActorImageViewer actorImageViewer;

	public GUIActorEditingEnvironment(Controller myController, ResourceBundle myResources) {
		this.myController = myController;
		this.myResources = myResources;
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
	 * Each time actor editing environment is opened and set to a specific Actor, populates editing environment rules and
	 * fields based on the Actor 
	 */
	public void updateRules(){
		myActorRuleCreator.updateRules();
	}
	/**
	 * Initialize resources and create actor editing environment by populating sections of the screen and setting default new Actor
	 */
	private void initializeEnvironment() {
		myRoot = new BorderPane();
		myActorRuleCreator = new ActorRuleCreator(this, myController);
		setDefaultActor();
		setLeftPane();
		setCenterPane();
		setBottomPane();
	}
	/**
	 * Set Actor of actor editing environment to a default new Actor
	 */
	private void setDefaultActor() {
		IAuthoringActor defaultActor = new Actor();
		this.myActor = defaultActor;
		this.myActorIV = new ImageviewActorIcon(defaultActor);
	}
	/**
	 * Populate left section of the actor editing environment
	 */
	private void setLeftPane() {
		attributes = new TabAttributes(myController, myResources, ACTOR_ATTRIBUTES, ACTOR_OPTIONS_RESOURCE, myActor);
		VBox vbox = new VBox();
		TabPane attributeTP = new TabPane();
		attributeTP.getTabs().add(attributes.getTab());
		attributeTP.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		library = new GUILibrary(myActorRuleCreator);
		vbox.getChildren().addAll(getActorImageViewer(), attributeTP, library.getPane());
		vbox.setPrefWidth(LEFT_PANE_WIDTH);
		myRoot.setLeft(vbox);
	}
	/**
	 * Returns visual of the current Actor's image as well as options to set the image.
	 * @return
	 */
	private Pane getActorImageViewer() {
		actorImageViewer = new GUIActorImageViewer(this, myController, myActorIV);
		return actorImageViewer.getPane();
	}
	/**
	 * Populate center section of the actor editing environment
	 */
	private void setCenterPane() {
		myRuleCreator = myActorRuleCreator.getGridPane();
		ScrollPane myScrollPane = new ScrollPane();
		myScrollPane.setContent(myRuleCreator);
		myScrollPane.setBackground(
				new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		myRoot.setCenter(myScrollPane);
	}
	/**
	 * Populate bottom section of the actor editing environment
	 */
	private void setBottomPane() {
		HBox hbox = new HBox();
		hbox.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		hbox.getChildren().add(getNewRuleButton());
		hbox.setAlignment(Pos.CENTER_RIGHT);
		myRoot.setBottom(hbox);
	}
	/**
	 * Returns button to allow users to create a new rule
	 * @return
	 */
	private Button getNewRuleButton() {
		Button toReturn = new Button(NEW_RULE_LABEL);
		toReturn.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		toReturn.setOnAction(event -> {
			myActorRuleCreator.addNewRule();
		});
		return toReturn;
	}

	/**
	 * Set Actor for actor editing environment
	 */
	@Override
	public void setEditable(IEditableGameElement editable) {
		myActor = (IAuthoringActor) editable;
		myActorIV = new ImageviewActorIcon(myActor);
		setLeftPane();
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
		myActor.setMyImageViewName(imageViewName);
		myActorIV = new ImageviewActorIcon(myActor);
		setLeftPane();
	}
	/**
	 * Each time a new rule is created, updates drag events for library elements to set new rule as a target 
	 */
	public void updateDragEventsForLibrary() {
		library.updateDragEvents();
	}

}
