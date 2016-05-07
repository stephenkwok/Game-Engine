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
	 * @param controller: controller for this authoring environment.
	 * @param myResources: resource bundle for the authoring environment (required by super class TabParent).
	 * @param tabText: name of this tab.
	 * @param optionsResource: resource bundle containing info about the GUI elements for this attributes tab.
	 * @param element: element that is being edited by this attributes tab (i.e. a level or an actor).
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
	 * Sets the editing environment that's observing this tab as an observer for each editing element.
	 * @param observer: editing environment.
	 */
	public void setObserver(IEditingEnvironment observer) {
		myEditingElements.stream().forEach(element -> ((EditingElementParent) element).addObserver(observer));
	}

	/**
	 * Adds the elements specified by the resource file to the vbox for this
	 * tab.
	 */
	private void addElements() {
		VBox editingElementsContainer = initElementsContainer();
		updateCurrentEditable(myEditableElement);
		myContent = editingElementsContainer;
	}

	private VBox initElementsContainer() {
		VBox container = new VBox(PADDING);
		container.setPadding(new Insets(PADDING));
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
			myEditingElements.add((IEditingElement) elementToCreate);
//			IEditingElement elementToCreate = (IEditingElement) myFactory.createNewGUIObject(elements[i]);
//			myEditingElements.add(elementToCreate);
//			createdElements.add(((IGUIElement) elementToCreate).createNode());
		}
		return createdElements;
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
	 * Add element to the container.
	 * @param element: element to add.
	 */
	public void addElement(IGUIElement element) {
		myContent.getChildren().add(element.createNode());
	}

	/**
	 * Returns the contents of this attributes tab to be added into panes.
	 */
	@Override
	Node getContent() {
		return myContent;
	}
}