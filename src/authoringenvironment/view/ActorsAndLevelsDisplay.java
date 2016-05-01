package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import authoringenvironment.model.IEditableGameElement;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * 
 * This class displays all the created Actors and Levels
 * 
 * @author Stephen
 *
 */

public class ActorsAndLevelsDisplay extends Observable implements Observer {

	private static final int NUM_SCROLLPANES = 2;
	private static final int SCROLLPANE_BAR_WIDTH = 30;
	private final DoubleExpression myWidth;
	private final DoubleExpression myHeight;
	private HBox myContainer;
	private VBox myActorsDisplay, myLevelsDisplay, myActorPreviewUnitsContainer, myLevelPreviewUnitsContainer;
	private ScrollPane myActorScrollPane, myLevelScrollPane;
	private HBoxDisplayHeader actorsDisplayHeader, levelsDisplayHeader;
	private List<PreviewUnitWithEditable> myPreviewUnits;
	private List<PreviewUnitWithLevel> myLevelPreviewUnits;

	public ActorsAndLevelsDisplay(DoubleExpression width, DoubleExpression height) {
		myWidth = width;
		myHeight = height;
		myContainer = new HBox();
		myActorsDisplay = new VBox();
		myLevelsDisplay = new VBox();
		myLevelPreviewUnitsContainer = new VBox();
		myActorPreviewUnitsContainer = new VBox();
		myActorScrollPane = new ScrollPane();
		myLevelScrollPane = new ScrollPane();
		actorsDisplayHeader = new HBoxDisplayHeaderActor(myActorsDisplay.widthProperty());
		levelsDisplayHeader = new HBoxDisplayHeaderLevel(myLevelsDisplay.widthProperty());
		levelsDisplayHeader.addObserver(this);																		
		myPreviewUnits = new ArrayList<>();
		myLevelPreviewUnits = new ArrayList<>();
		setScrollPanesContent();
		bindNodeSizes();
		addNodesToDisplay();
	}

	/**
	 * Sets the content of the Actor and Level ScrollPanes to a container holding
	 * all Actor and Level preview units
	 */
	private void setScrollPanesContent() {
		myActorScrollPane.setContent(myActorPreviewUnitsContainer);
		myLevelScrollPane.setContent(myLevelPreviewUnitsContainer);
	}
	
	/**
	 * Binds each node in the display to the widths and heights of their respective parent containers
	 */
	private void bindNodeSizes() {
		bindNodeSizeToGivenSize(myContainer, myWidth, myHeight);
		bindNodeSizeToGivenSize(myActorsDisplay, myContainer.widthProperty().subtract(NUM_SCROLLPANES), null);
		bindNodeSizeToGivenSize(myLevelsDisplay, myContainer.widthProperty().subtract(NUM_SCROLLPANES), null);
		bindNodeSizeToGivenSize(myActorPreviewUnitsContainer,
				myActorScrollPane.widthProperty().subtract(SCROLLPANE_BAR_WIDTH), myActorScrollPane.heightProperty());
		bindNodeSizeToGivenSize(myLevelPreviewUnitsContainer,
				myLevelScrollPane.widthProperty().subtract(SCROLLPANE_BAR_WIDTH), myLevelScrollPane.heightProperty());
		bindNodeSizeToGivenSize(myActorScrollPane, myActorsDisplay.widthProperty(),
				myActorsDisplay.heightProperty().subtract(actorsDisplayHeader.getHBox().heightProperty()));
		bindNodeSizeToGivenSize(myLevelScrollPane, myLevelsDisplay.widthProperty(),
				myLevelsDisplay.heightProperty().subtract(levelsDisplayHeader.getHBox().heightProperty()));
	}
	
	/**
	 * Adds all created nodes to the parent container to be displayed
	 */
	private void addNodesToDisplay() {
		myActorsDisplay.getChildren().addAll(actorsDisplayHeader.getHBox(), myActorScrollPane);
		myLevelsDisplay.getChildren().addAll(levelsDisplayHeader.getHBox(), myLevelScrollPane);
		myContainer.getChildren().addAll(myActorsDisplay, myLevelsDisplay);
	}

	/**
	 * Binds a region to a given width and height
	 * 
	 * @param child: region whose width and height are to be bound
	 * @param width: the width the child's width will be bound to
	 * @param height: the height the child's height will be bound to
	 */
	private void bindNodeSizeToGivenSize(Region child, DoubleExpression width, DoubleExpression height) {
		if (width != null)
			child.prefWidthProperty().bind(width);
		if (height != null)
			child.prefHeightProperty().bind(height);
	}

	/**
	 * Creates a PreviewUnitWithEditable associated with an Actor
	 * 
	 * @param actor: actor to be linked to LabelClickable being created
	 * @param actorEditor: IEditingEnvironment in which Actor is to be edited
	 * @return a PreviewUnitWithEditable associated with an Actor
	 */
	public PreviewUnitWithEditable createActorPreviewUnit(IEditableGameElement actor) {
		PreviewUnitWithEditable actorPreviewUnit = new PreviewUnitWithEditable(actor);
		initializePreviewUnit(actorPreviewUnit, myActorPreviewUnitsContainer);
		return actorPreviewUnit;
	}

	/**
	 * Creates a PreviewUnitWithEditable associated with a Level
	 * 
	 * @param level: level to be linked to preview unit being created
	 * @param levelEditor: IEditingEnvironment in which Level is to be edited
	 * @return a preview unit associated with a Level
	 */
	public PreviewUnitWithEditable createLevelPreviewUnit(IEditableGameElement level) {
		PreviewUnitWithLevel levelPreviewUnit = new PreviewUnitWithLevel(level);
		initializePreviewUnit(levelPreviewUnit, myLevelPreviewUnitsContainer);
		myLevelPreviewUnits.add(levelPreviewUnit);
		return levelPreviewUnit;
	}

	/**
	 * Initializes a preview unit 
	 * @param previewUnit: preview unit to be initialized
	 * @param parent: container to hold the preview unit
	 */
	private void initializePreviewUnit(PreviewUnitWithEditable previewUnit, VBox parent) {
		HBox previewUnitHBox = previewUnit.getHBox();
		bindNodeSizeToGivenSize(previewUnitHBox, parent.widthProperty(), null);
		parent.getChildren().add(previewUnitHBox);
		myPreviewUnits.add(previewUnit);
		updatePreviewUnits();
	}
	
	/**
	 * Updates all preview units to account for any changes in the name or
	 * image of Actors and Levels
	 */
	public void updatePreviewUnits() {
		myPreviewUnits.stream().forEach(unit -> unit.update());
	}
	
	/**
	 * 
	 * @return the container holding all GUI elements in the ActorsAndLevelsDisplay
	 */
	public HBox getPane() {
		return myContainer;
	}
	
	/**
	 * Notifies all observers
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		setChanged();
		notifyObservers(arg1);
	}
	
	/**
	 * 
	 * @return: a List of all of the display's preview units
	 */
	public List<PreviewUnitWithEditable> getPreviewUnits() {
		return myPreviewUnits;
	}
	
	/**
	 * 
	 * @return a List of all of the display's preview units linked to a Level
	 */
	public List<PreviewUnitWithLevel> getLevelPreviewUnits() {
		return myLevelPreviewUnits;
	}

	/**
	 * 
	 * @return the VBox holding all Level preview units
	 */
	public VBox getLevelPreviewUnitsContainer() {
		return myLevelPreviewUnitsContainer;
	}
}
