package authoringenvironment.view;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.model.Actor;
import gui.view.GUILibrary;
import gui.view.IGUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
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
	private static final double ACTOR_PERCENT_HEIGHT = 0.1;
	private double actorImageHeight;
	private BorderPane myRoot;
	private GUILibrary library;
	private TabAttributes attributes;
	private Controller myController;
	private ResourceBundle myResources;
	private Actor myActor;
	private ImageView myActorIV;
	private ActorRuleCreator myActorRuleCreator;
	private GridPane myRuleCreator;
	private GUIActorImageViewer actorImageViewer;

	public GUIActorEditingEnvironment(Controller myController, ResourceBundle myResources) {
		this.myController = myController;
		this.myResources = myResources;
		this.actorImageHeight = myController.getSceneHeight()*ACTOR_PERCENT_HEIGHT;
		initializeEnvironment();
	}

	@Override
	public Pane getPane() {
		return myRoot;
	}

	private void initializeEnvironment() {
		myRoot = new BorderPane();
		myActorRuleCreator = new ActorRuleCreator(this, myController);
		setDefaultActor();
		setLeftPane();
		setCenterPane();
		setBottomPane();
	}

	private void setDefaultActor() {
		Actor defaultActor = new Actor();
		ImageView defaultIV = defaultActor.getImageView();
		defaultIV.setFitHeight(actorImageHeight);
		defaultIV.setPreserveRatio(true);
		this.myActor = defaultActor;
		this.myActorIV = defaultIV;
	}

	private void setLeftPane() {
		attributes = new TabAttributes(myController, myResources, ACTOR_ATTRIBUTES, ACTOR_OPTIONS_RESOURCE);
		VBox vbox = new VBox();
		TabPane attributeTP = new TabPane();
		attributeTP.getTabs().add(attributes.getTab());
		library = new GUILibrary(myActorRuleCreator);
		vbox.getChildren().addAll(getActorImageViewer(), attributeTP, library.getPane());
		myRoot.setLeft(vbox);
	}

	private Pane getActorImageViewer() {
		actorImageViewer = new GUIActorImageViewer(this, myController, myActorIV);
		return actorImageViewer.getPane();
	}

	private void setCenterPane() {
		myRuleCreator = myActorRuleCreator.getGridPane();
		ScrollPane myScrollPane = new ScrollPane();
		myScrollPane.setContent(myRuleCreator);
		myScrollPane.setBackground(
				new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		myRoot.setCenter(myScrollPane);
	}

	private void setBottomPane() {
		HBox hbox = new HBox();
		hbox.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		hbox.getChildren().add(getNewRuleButton());
		hbox.setAlignment(Pos.CENTER_RIGHT);
		myRoot.setBottom(hbox);
	}

	private Button getNewRuleButton() {
		Button toReturn = new Button(NEW_RULE_LABEL);
		toReturn.setOnAction(event -> {
			myActorRuleCreator.addNewRule();
		});
		return toReturn;
	}

	@Override
	public void setEditable(IEditableGameElement editable) {
		myActor = (Actor) editable;
		myActorIV = myActor.getImageView();
	}

	public void setActorImage(ImageView newImageView) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		myActorIV = newImageView;
		myActorIV.setFitHeight(actorImageHeight);
		myActorIV.setPreserveRatio(true);
		myActor.setImageView(myActorIV);
		setLeftPane();
	}

	public void updateDragEventsForLibrary() {
		library.updateDragEvents();
	}
}
