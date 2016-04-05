package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.model.IActor;
import javafx.geometry.Insets;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
		setRightPane();
		setLeftPane();
	}
	
	private void setLeftPane(){
		VBox vbox = new VBox();
		attributes = new TabAttributes(myResources,ACTOR_ATTRIBUTES,ACTOR_OPTIONS_RESOURCE);
		TabPane attributeTP = new TabPane();
		attributeTP.getTabs().add(attributes.getTab());
		library = new GUILibrary();
		vbox.getChildren().addAll(getActorImageViewer(), attributeTP, library.getPane());
		myRoot.setLeft(vbox);
	}
	
	private StackPane getActorImageViewer(){
		ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("marshmallow.png")));
		imageView.setFitHeight(ACTOR_IMAGE_HEIGHT);
		imageView.setPreserveRatio(true);
		
		StackPane sp = new StackPane();
		sp.getChildren().add(imageView);
		sp.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		return sp;
	}
	
	private void setRightPane(){
		//right will be draggable area thingiemabob
	}
	
	@Override
	public void setEditable(IEditableGameElement editable) {
		myActor = (IActor) editable;
	}
	
	public void setActor(IActor actor){
		myActor = actor;
	}
}
