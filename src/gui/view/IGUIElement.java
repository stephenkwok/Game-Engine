package gui.view;

import java.lang.reflect.InvocationTargetException;

import javafx.scene.Node;

/**
 * This interface is part of the internal API. To add new features, you
 * can create an instance of an Element class that implements this parent interface. 
 * @author AnnieTang
 */
public interface IGUIElement {
	/**
     * This method creates the GUI element and passes it to the GUI class as a Node
     * type.
     * @return 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
     */
    Node createNode() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
