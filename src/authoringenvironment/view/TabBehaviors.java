package authoringenvironment.view;

import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
/**
 * Tab contains ListView of behaviors and drag/drop behavior for behaviors.
 * Drag/Drop event handling code mostly acquired from Oracle. 
 * @author AnnieTang
 *
 */
public class TabBehaviors extends TabParent {
	private ObservableList<Label> behaviorLabels;
	private GUIActorRuleMaker myRuleMaker;
	private Pane myTarget;
	
	public TabBehaviors(ResourceBundle myResources, String tabText, GUIActorRuleMaker myRuleMaker) {
		super(myResources, tabText);
		this.myRuleMaker = myRuleMaker;
		if(myRuleMaker!=null) myTarget = this.myRuleMaker.getPane();
	}

	@Override
	Node getContent() {
		behaviorLabels = FXCollections.observableArrayList();
		for(String behavior: myResources.getString(tabText).split(" ")){
			Label mySource = new Label(behavior);
			if(myRuleMaker!=null){
				setDragEvent(mySource);
			}
			behaviorLabels.add(mySource);
		}
		ListView<Label> listView = new ListView<>(behaviorLabels);
		return listView;
	}

	private void setDragEvent(Label source) {
		setDragDetected(source);
		setDragOver(source);
		setDragEntered(source);
		setDragExited(source);
		setDragDropped(source);
	}

	private void setDragDetected(Label source) {
		source.setOnDragDetected(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event){
				Dragboard db = source.startDragAndDrop(TransferMode.COPY);
				ClipboardContent content = new ClipboardContent();
				content.putString(source.getText());
				db.setContent(content);
				event.consume();
			}
		});
		
	}
	
	private void setDragOver(Label source) {
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
	
	private void setDragEntered(Label source) {
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
	
	private void setDragExited(Label source) {
		myTarget.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    	myTarget.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));
		        event.consume();
		    }
		});
	}

	private void setDragDropped(Label source) {
		myTarget.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasString()) {
		           GridPane gp = (GridPane) myTarget;
		           Node toAdd = getRuleContainer(event.getDragboard().getString());
		           gp.add(toAdd, 0, 0);
		           success = true;
		        }
		        event.setDropCompleted(success);
		        event.consume();
		     }
		});
	}
	
	private Node getRuleContainer(String behaviorType){
		return new Label(behaviorType);
	}
}
