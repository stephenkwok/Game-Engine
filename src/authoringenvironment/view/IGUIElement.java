package authoringenvironment.view;

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
     */
    Node createNode();

    /**
     * This method updates the Node based on new information.
     */
    void updateNode();
}
