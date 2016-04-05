package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.actors.Actor;
import gameengine.actors.PowerUpActor;
import gameengine.model.IActor;
import javafx.geometry.Insets;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
/**
 * Returns BorderPane to represent Actor Editing Environment. 
 * @author AnnieTang
 *
 */
public class GUIActorEditingEnvironment implements IGUI, IEditingEnvironment {
	private static final int ACTOR_IMAGE_HEIGHT = 100; 
	private static final String ACTOR_OPTIONS_RESOURCE = "actorEditorOptions";
	private static final String ACTOR_ATTRIBUTES = "Actor Attributes";
	private BorderPane myRoot;
	private GUILibrary library;
	private TabAttributes attributes;
	private ResourceBundle myResources;
	private IActor myActor;
	private GUIActorRuleMaker ruleMaker;
	
	public GUIActorEditingEnvironment(ResourceBundle myResources) {
		this.myResources = myResources;
		initializeEnvironment();
	}

	@Override
	public void updateAllNodes() { 
	}

	@Override
	public Pane getPane() {
		return myRoot;
	}
	
	private void initializeEnvironment() {
		myRoot = new BorderPane();
		//Test Actor ImageView usage
		Actor testActor = new PowerUpActor();
		testActor.setFitHeight(ACTOR_IMAGE_HEIGHT);
		testActor.setPreserveRatio(true);
		this.myActor = testActor;
	
		setCenterPane(); // will fill up right side 
		setLeftPane();
	}
	
	private void setLeftPane(){
		VBox vbox = new VBox();
		attributes = new TabAttributes(myResources,ACTOR_ATTRIBUTES,ACTOR_OPTIONS_RESOURCE);
		TabPane attributeTP = new TabPane();
		attributeTP.getTabs().add(attributes.getTab());
		library = new GUILibrary(ruleMaker);
		vbox.getChildren().addAll(getActorImageViewer(), attributeTP, library.getPane());
		myRoot.setLeft(vbox);
	}
	
	private StackPane getActorImageViewer(){
		Actor myCurrentActor = (Actor) myActor;
		StackPane sp = new StackPane();
		sp.getChildren().add(myCurrentActor);
		sp.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		return sp;
	}
	
	private void setCenterPane(){
		ruleMaker = new GUIActorRuleMaker();
		myRoot.setCenter(ruleMaker.getPane());
	}
	
	@Override
	public void setEditable(IEditableGameElement editable) {
		myActor = (IActor) editable;
	}
}
