package authoringenvironment.view;

import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
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

abstract class TabLibrary extends TabParent {
	protected ActorRuleCreator myActorRuleCreator;
	protected GridPane myTarget;
	
	public TabLibrary(ResourceBundle myResources, String tabText, ActorRuleCreator myRuleMaker) {
		super(myResources, tabText);
		this.myActorRuleCreator = myRuleMaker;
		if(myRuleMaker!=null) myTarget = this.myActorRuleCreator.getGridPane();
	}

	@Override
	abstract Node getContent();
	
	protected void setDragEvent(Label source) {
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
		           Label toAdd = getRuleContainer(event.getDragboard().getString());
		           setDragEvent(toAdd);
		           myTarget.add(toAdd, 0, 0);
		           success = true;
		        }
		        event.setDropCompleted(success);
		        event.consume();
		     }
		});
	}
	
	private Label getRuleContainer(String behaviorType){
		return new Label(behaviorType);
	}

}
