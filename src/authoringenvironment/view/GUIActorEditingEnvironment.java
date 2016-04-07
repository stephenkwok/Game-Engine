package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.model.Actor;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
	private Controller myController;
	private ResourceBundle myResources;
	private Actor myActor;
	private ImageView myActorIV;
	private GUIActorRuleMaker ruleMaker;
	private GUIActorImageViewer actorImageViewer;
	
	public GUIActorEditingEnvironment(Controller myController, ResourceBundle myResources) {
		this.myController = myController;
		this.myResources = myResources;
		initializeEnvironment();
	}

	@Override
	public Pane getPane() {
		return myRoot;
	}
	
	private void initializeEnvironment() {
		myRoot = new BorderPane();
		setDefaultActor();	
		setCenterPane(); 
		setLeftPane();
	}
	
	private void setDefaultActor(){
		Actor defaultActor = new Actor();
		ImageView defaultIV = defaultActor.getImageView();
		defaultIV.setFitHeight(ACTOR_IMAGE_HEIGHT);
		defaultIV.setPreserveRatio(true);
		this.myActor = defaultActor;
		this.myActorIV = defaultIV;
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
	
	private Pane getActorImageViewer(){
		actorImageViewer= new GUIActorImageViewer(this, myController, myActorIV);
		return actorImageViewer.getPane();
	}
	
	private void setCenterPane(){
		ruleMaker = new GUIActorRuleMaker();
		myRoot.setCenter(ruleMaker.getPane());
	}
	
	@Override
	public void setEditable(IEditableGameElement editable) {
		myActor = (Actor) editable;
		myActorIV = myActor.getImageView();
	}
	
	public void setActorImage(ImageView newImageView){
		myActorIV = newImageView;
		myActor.setImageView(newImageView);
		setLeftPane();
		
	}
}
