/**
 * This entire file is part of my masterpiece.
 * Annie Tang
 * 
 *   The purpose of this code is to allow the user to create tabs with draggable content that they can
 *   place in authoring environment's library, or elsewhere. It contains all the logic for setting drag events.
 *   The things that are abstracted are what the content of each tab is. 
 *   
 *   This is good code because it does not have any code smells (see GUILibrary no code smells). It also utilizes
 *   lambda expressions, which is something we learned in class this semester. This class also sets up a hierarchy 
 *   for library tabs that is easily extensible (see TabLibraryBehaviors, TabLibrarySounds). Also, instead of using 
 *   protected variables, I have getters and setters for the variables that will be needed by subclasses.  
 */
package authoringenvironment.view;

import java.util.*;
import authoringenvironment.model.*;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Abstract class for library tabs, so each Node in the tabs are draggable.
 * Drag/Drop event handling code mostly acquired from Oracle.
 * @author AnnieTang
 *
 */
abstract class TabLibrary extends TabParent {
	private static final int FILE_EXT_LENGTH = 4;
	private static final String SOUND_FILE_EXTS = ".mp3";
	private static final double CORNER_RADIUS = 20;
	private ObservableList<Label> labels;
	private ListView<Label> listView;
	private ActorRuleCreator myActorRuleCreator;
	private List<ActorRule> myActorRules; // targets

	public TabLibrary(ResourceBundle myResources, String tabText, ActorRuleCreator myActorRuleCreator) {
		super(myResources, tabText);
		if (myActorRuleCreator != null)
			myActorRules = myActorRuleCreator.getActorRules();
	}

	/**
	 * Get content of current Tab
	 */
	@Override
	abstract Node getContent();

	/**
	 * Set content of current Tab
	 */
	abstract void setContent();

	/**
	 * Set drag event for given source and given TransferMode
	 * 
	 * @param source
	 * @param transferMode
	 */
	protected void setDragEvent(Label source, TransferMode transferMode) {
		/*refactored this for masterpiece, realized I did not need all three arguments (source, transferMode, 
		 * rule.getGridPane()) for all drag methods.
		 */
		for (ActorRule rule : myActorRules) {
			setDragDetected(source, transferMode);
			setDragOver(rule.getGridPane(), transferMode);
			setDragEntered(rule.getGridPane());
			setDragExited(rule.getGridPane());
			setDragDropped(rule.getGridPane(), rule);
		}
	}

	/**
	 * Sets behavior to act on drag
	 * 
	 * @param mySource
	 * @param myTarget
	 * @param transferMode
	 */
	private void setDragDetected(Label mySource, TransferMode transferMode) {
		/*
		 * Refactored to use lambda expressions
		 */
		mySource.setOnDragDetected(event -> {
			Dragboard db = mySource.startDragAndDrop(transferMode);
			ClipboardContent content = new ClipboardContent();
			content.putString(mySource.getText());
			db.setContent(content);
			event.consume();
		});

	}

	/**
	 * Sets behavior to act on drag over
	 * 
	 * @param mySource
	 * @param myTarget
	 * @param transferMode
	 */
	private void setDragOver(GridPane myTarget, TransferMode transferMode) {
		/*
		 * Refactored to use lambda expressions
		 */
		myTarget.setOnDragOver(event -> {
			if (event.getGestureSource() != myTarget && event.getDragboard().hasString()) {
				event.acceptTransferModes(transferMode);
			}
			event.consume();
		});
	}

	/**
	 * Sets behavior to act on drag entered
	 * 
	 * @param mySource
	 * @param myTarget
	 */
	private void setDragEntered(GridPane myTarget) {
		/*
		 * Refactored to use lambda expressions
		 */
		myTarget.setOnDragEntered(event -> {
			if (event.getGestureSource() != myTarget && event.getDragboard().hasString()) {
				myTarget.setBackground(new Background(
						new BackgroundFill(Color.PALEGREEN, new CornerRadii(CORNER_RADIUS), Insets.EMPTY)));
			}
			event.consume();
		});
	}

	/**
	 * Sets behavior to act on drag exited
	 * 
	 * @param mySource
	 * @param myTarget
	 */
	private void setDragExited(GridPane myTarget) {
		/*
		 * Refactored to use lambda expressions
		 */
		myTarget.setOnDragExited(event -> {
			myTarget.setBackground(new Background(
					new BackgroundFill(Color.LIGHTSKYBLUE, new CornerRadii(CORNER_RADIUS), Insets.EMPTY)));
			event.consume();
		});
	}

	/**
	 * Sets behavior to act on drag dropped
	 * 
	 * @param mySource
	 * @param myTarget
	 * @param myActorRule
	 */
	private void setDragDropped(GridPane myTarget, ActorRule myActorRule) {
		/*
		 * Refactored to use lambda expressions
		 */
		myTarget.setOnDragDropped(event -> {
			Dragboard db = event.getDragboard();
			boolean success = false;
			if (db.hasString()) {
				addNodeToTarget(new Label(event.getDragboard().getString()), myActorRule);
				success = true;
			}
			event.setDropCompleted(success);
			event.consume();
		});
	}

	/**
	 * Adds expanded Node with parameters for given library element to given
	 * ActorRule object
	 * 
	 * @param toAdd
	 * @param myActorRule
	 */
	private void addNodeToTarget(Label toAdd, ActorRule myActorRule) {
		if (matchesExtensions(toAdd.getText(), SOUND_FILE_EXTS))
			myActorRuleCreator.addSound(myActorRule, "SoundAction", toAdd.getText());
		else
			myActorRuleCreator.addBehavior(myActorRule, toAdd.getText());
	}

	/**
	 * Returns whether given name of library element matches at least one of
	 * given extension(s)
	 * 
	 * @param libraryElement
	 * @param extensions
	 * @return
	 */
	private boolean matchesExtensions(String libraryElement, String extensions) {
		List<String> fileExts = Arrays.asList(extensions.split(" "));
		if (libraryElement.length() > 4)
			return fileExts.contains(
					libraryElement.substring(libraryElement.length() - FILE_EXT_LENGTH, libraryElement.length()));
		return false;
	}

	/**
	 * 
	 * @param myActorRuleCreator
	 */
	public void updateDragEvents(ActorRuleCreator myActorRuleCreator) {
		this.myActorRuleCreator = myActorRuleCreator;
		this.myActorRules = myActorRuleCreator.getActorRules();
		for (Label behaviorLabel : labels) {
			if (myActorRuleCreator != null) {
				setDragEvent(behaviorLabel, TransferMode.COPY);
			}
		}
	}

	/**
	 * Gets labels for the elements in this library.
	 * 
	 * @return list of labels.
	 */
	protected ObservableList<Label> getLabels() {
		return labels;
	}

	/**
	 * Sets the labels for elements in this library.
	 * 
	 * @param labels:
	 *            observable list of labels to use.
	 */
	protected void setLabels(ObservableList<Label> labels) {
		this.labels = labels;
	}

	/**
	 * Gets the current list of labels.
	 * 
	 * @return listview of labels.
	 */
	protected ListView<Label> getListView() {
		return listView;
	}

	/**
	 * Sets the listview of labels.
	 * 
	 * @param listview:
	 *            listview to set labels to.
	 */
	protected void setListView(ListView<Label> listview) {
		listView = listview;
	}

	/**
	 * Gets the actor rule creator used by this environment.
	 * 
	 * @return actor rule creator.
	 */
	protected ActorRuleCreator getActorRuleCreator() {
		return myActorRuleCreator;
	}
}
