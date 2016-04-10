package authoringenvironment.view;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import gui.view.ComboBoxOptions;
import gui.view.GUIFactory;
import gui.view.IGUIElement;
import gui.view.TextFieldWithButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Abstract Tab for setting attributes to go in the Inspector Pane in either the Level or Actor Editing Environment GUI.
 * @author amyzhao, AnnieTang
 *
 */
public class TabAttributes extends TabParent {
	private static final int PADDING = 10;
	private static final String DELIMITER = ",";
	private static final String EDITOR_ELEMENTS = "EditorElements";
	private ResourceBundle myAttributesResources;
	private GUIFactory myFactory;
	private Controller myController;
	private VBox myContent;
	
	public TabAttributes(Controller controller, ResourceBundle myResources, String tabText, String levelOptionsResource) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		super(myResources, tabText);
		this.myAttributesResources = ResourceBundle.getBundle(levelOptionsResource);
		myFactory = new GUIFactory(myAttributesResources, myController);
		createElements();
	}

	private void createElements() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		VBox vbox = new VBox(PADDING);
		vbox.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
		String[] elements = myAttributesResources.getString(EDITOR_ELEMENTS).split(DELIMITER);
		
		for (int i = 0; i < elements.length; i++) {
			IGUIElement elementToCreate = myFactory.createNewGUIObject(elements[i]);
			vbox.getChildren().add(elementToCreate.createNode());
		}
		
		myContent = vbox;
	}
	@Override
	Node getContent() {
		return myContent;
	}
}