package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingElement;
import authoringenvironment.model.IEditingEnvironment;
import gui.view.EditingElementParent;
import gui.view.GUIFactory;
import gui.view.IGUIElement;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * Tab for setting attributes to go in the Inspector Pane in either the Level or
 * Actor Editing Environment GUI.
 * 
 * @author amyzhao
 *
 */
public class TabFields extends TabParent {
	private static final int PADDING = 10;
	private static final String EDITOR_ELEMENTS = "EditorElements";
	private static final String DELIMITER = ",";
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
		updateEditable(myEditableElement);
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
	 * Creates the elements to be added to this tab.
	 * 
	 * @param key:
	 *            key in resource file for the value that lists all the elements
	 *            to create
	 * @return list of IGUIElements to be added to the tab.
	 */
	private List<Node> createElements(String key) {
		String[] elements = myAttributesResources.getString(key).split(DELIMITER);
		List<Node> createdElements = new ArrayList<>();
		for (int i = 0; i < elements.length; i++) {
			IGUIElement elementToCreate = myFactory.createNewGUIObject(elements[i]);
			createdElements.add(elementToCreate.createNode());
			myEditingElements.add((IEditingElement) elementToCreate);
		}
		return createdElements;
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
	 * @param element:
	 *            the level or actor that you now want to modify.
	 */
	public void updateEditable(IEditableGameElement gameElement) {
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
	 * Returns the content of this attributes tab.
	 */
	@Override
	Node getContent() {
		return myContent;
	}
}