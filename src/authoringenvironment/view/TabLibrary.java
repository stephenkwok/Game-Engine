package authoringenvironment.view;

import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
/**
 * Abstract class for library tabs, so each Node in the tabs are draggable.
 * @author AnnieTang
 *
 */
abstract class TabLibrary extends TabParent {
	private static final int LABEL_IMAGE_HEIGHT = 50;
	protected ObservableList<Label> labels;
	protected ListView<Label> listView;
	protected ActorRuleCreator myActorRuleCreator;
	private List<ActorRule> myActorRules; //targets
	
	public TabLibrary(ResourceBundle myResources, String tabText, ActorRuleCreator myActorRuleCreator) {
		super(myResources, tabText);
		if(myActorRuleCreator!=null) myActorRules = myActorRuleCreator.getRules();
	}


	@Override
	abstract Node getContent();
	
	abstract void setContent();
	
	protected void setDragEvent(Label source) {
		for(ActorRule rule: myActorRules){
			setDragDetected(source, rule.getGridPane());
			setDragOver(source, rule.getGridPane());
			setDragEntered(source, rule.getGridPane());
			setDragExited(source, rule.getGridPane());
			setDragDropped(source, rule.getGridPane());
		}
	}

	private void setDragDetected(Label mySource, GridPane myTarget) {
		mySource.setOnDragDetected(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event){
				Dragboard db = mySource.startDragAndDrop(TransferMode.COPY);
				ClipboardContent content = new ClipboardContent();
				content.putString(mySource.getText());
				db.setContent(content);
				event.consume();
			}
		});
		
	}
	
	private void setDragOver(Label mySource, GridPane myTarget) {
		myTarget.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        if (event.getGestureSource() != myTarget &&
		                event.getDragboard().hasString()) {
		            event.acceptTransferModes(TransferMode.COPY);
		        }
		        event.consume();
		    }
		});
	}
	
	private void setDragEntered(Label mySource, GridPane myTarget) {
		myTarget.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		         if (event.getGestureSource() != myTarget &&
		                 event.getDragboard().hasString()) {
		             myTarget.setBackground(new Background(new BackgroundFill(Color.PALEGREEN,CornerRadii.EMPTY, Insets.EMPTY)));
		         }
		         event.consume();
		    }
		});
	}
	
	private void setDragExited(Label mySource, GridPane myTarget) {
		myTarget.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    	myTarget.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));
		        event.consume();
		    }
		});
	}

	private void setDragDropped(Label mySource, GridPane myTarget) {
		myTarget.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasString()) {
		        	addLabelToTarget(getLabelToAdd(event.getDragboard().getString()), myTarget);
//		        	setDragEvent(toAdd); //SET DRAG EVENT SO USER CAN REMOVE IT BY "PUTTING BACK IN LIBRARY"
		        	success = true;
		        }
		        event.setDropCompleted(success);
		        event.consume();
		     }
		});
	}
	
	private void addLabelToTarget(Label toAdd, GridPane myTarget){ //TODO
		myTarget.add(toAdd, 0, 0);
	}
	
	private Label getLabelToAdd(String libraryElement) {
		ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(libraryElement)));
		imageView.setFitHeight(LABEL_IMAGE_HEIGHT);
		imageView.setPreserveRatio(true);
		return new Label(libraryElement, imageView);
	}
	
	public void updateDragEvents(ActorRuleCreator myActorRuleCreator) {
		this.myActorRuleCreator = myActorRuleCreator;
		for(Label behaviorLabel: labels){
			if(myActorRuleCreator!=null){
				setDragEvent(behaviorLabel);
			}
		}
	}
}
