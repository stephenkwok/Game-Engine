package authoringenvironment.view;

import java.util.List;
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
import javafx.scene.paint.Color;
/**
 * Abstract class for library tabs, so each Node in the tabs are draggable.
 * @author AnnieTang
 *
 */
abstract class TabLibrary extends TabParent {
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
		System.out.println(myActorRules.size());
		for(ActorRule rule: myActorRules){
			System.out.println(source.getText() + " " + rule);
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
