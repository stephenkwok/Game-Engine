// This entire file is part of my masterpiece.
// Amy Zhao

// The purpose of this class within the context of our VOOGASalad project is to create the tab in both the
// Actor Editing Environment and the Level Editing Environment that contains all of the view elements (e.g.
// ComboBoxes, TextFields, etc.) that edit the actor or level's name, size, and other fields. It creates these
// elements, controls the updating of their values to reflect the current level or actor being updated, and also
// allows for an Editing Environment to attach itself as an observer that is notified whenever a field of an
// actor or level is updated.

// This class is well-designed because none of the elements it creates are hard-coded. All of the elements for the
// actor editing environment's TabField tab and all of the elements for the level editing environment's TabField tab
// are specified in properties files (myAttributesResources)--in theory, it is flexible enough that it could be used to construct any sort of
// attribute inspector for any game element. The only requirement being that all of the elements implement both 
// IGUIElement and IEditingElement so that, which allows for all of the elements to be created easily using the 
// GUIFactory using reflection, and for all of the elements' setEditable() methods to be called easily in this 
// class's own setEditable() method using a stream. Admittedly, casting is required to convert from the IGUIElement
// that is created by the GUIFactory to an IEditingElement that can be added into the myEditingElements list, but this
// is done through the addToEditingElementsList() method which will check that the element can be safely cast to an
// IEditingElement and display an error alert if not.

// Overall, this class demonstrates clean, concise code that takes advantage of interfaces (IEditingElement and IGUIElement),
// properties files, lambdas, and streams to provide improved readability, functionality, and flexibility to the programmer.

// TODO: add more about readings and stuff

package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingElement;
import authoringenvironment.model.IEditingEnvironment;
import gui.view.EditingElementParent;
import gui.view.GUIFactory;
import gui.view.IGUIElement;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

/**
 * Tab for setting attributes to be displayed in the Inspector Pane in both the Level and
 * Actor Editing Environments.
 * 
 * @author amyzhao
 *
 */
public class TabFields extends TabParent {
	private static final int PADDING = 10;
	private static final String EDITOR_ELEMENTS = "EditorElements";
	private static final String DELIMITER = ",";
	private static final String ERROR_MESSAGE = "ErrorMessage";
	private static final String SPACE = " ";
	private ResourceBundle myAttributesResources;
	private GUIFactory myFactory;
	private VBox myContent;
	private IEditableGameElement myEditableElement;
	private List<IEditingElement> myEditingElements;

	/**
	 * Constructor for an attributes tab.
	 * 
	 * @param controller:
	 *            controller for this authoring environment.
	 * @param myResources:
	 *            resource bundle for the authoring environment (used by super class).
	 * @param tabText:
	 *            name of this tab.
	 * @param optionsResource:
	 *            resource bundle containing info about the GUI elements for
	 *            this attributes tab.
	 * @param element:
	 *            element that is being edited by this attributes tab (i.e. a
	 *            level or an actor).
	 */
	public TabFields(ResourceBundle myResources, String tabText, String optionsResource, IEditableGameElement element) {
		super(myResources, tabText);
		this.myAttributesResources = ResourceBundle.getBundle(optionsResource);
		myFactory = new GUIFactory(myAttributesResources);
		myEditableElement = element;
		myEditingElements = new ArrayList<>();
		addElements();
	}

	/**
	 * Adds the elements specified by the resource file to the vbox for this
	 * tab.
	 */
	private void addElements() {
		VBox editingElementContainer = createEditingElementContainer();
		updateCurrentEditable(myEditableElement);
		myContent = editingElementContainer;
	}

	/**
	 * Creates and formats a VBox to hold all of the IEditingElements in this tab.
	 * @return VBox containing all of the desired IEditingElements.
	 */
	private VBox createEditingElementContainer() {
		VBox container = new VBox(PADDING);
		container.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
		container.getChildren().addAll(createElements(EDITOR_ELEMENTS));
		return container;
	}

	/**
	 * Creates the editing elements to be added to this tab.
	 * 
	 * @param key: key in resource file for the value that lists all the elements to create
	 * @return list of IGUIElements to be added to the tab.
	 */
	private List<Node> createElements(String key) {
		String[] elements = myAttributesResources.getString(key).split(DELIMITER);
		List<Node> createdElements = new ArrayList<>();
		for (int i = 0; i < elements.length; i++) {
			IGUIElement elementToCreate = myFactory.createNewGUIObject(elements[i]);
			createdElements.add(elementToCreate.createNode());
			addToEditingElementsList(elementToCreate);
		}
		return createdElements;
	}

	private void addToEditingElementsList(IGUIElement elementToCreate) {
		if (IEditingElement.class.isAssignableFrom(elementToCreate.getClass())) {
			myEditingElements.add((IEditingElement) elementToCreate);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(elementToCreate.getClass().getSimpleName() +
					SPACE + myAttributesResources.getString(ERROR_MESSAGE));
			Optional<ButtonType> result = alert.showAndWait();
		}
	}

	/**
	 * Add element to the container.
	 * @param element: element to add.
	 */
	public void addElement(IGUIElement element) {
		myContent.getChildren().add(element.createNode());
	}

	/**
	 * Updates the editable element that each attribute tab element is
	 * modifying.
	 * 
	 * @param element: the level or actor that you now want to modify.
	 */
	public void updateCurrentEditable(IEditableGameElement gameElement) {
		myEditableElement = gameElement;
		myEditingElements.stream().forEach(editingElement -> editingElement.setEditableElement(gameElement));
	}

	/**
	 * Sets the editing environment that's observing this tab.
	 * @param observer: editing environment.
	 */
	public void setObserver(IEditingEnvironment observer) {
		myEditingElements.stream().forEach(element -> ((EditingElementParent) element).addObserver(observer));
	}

	/**
	 * Returns the contents of this attributes tab to be added into panes.
	 */
	@Override
	Node getContent() {
		return myContent;
	}
}