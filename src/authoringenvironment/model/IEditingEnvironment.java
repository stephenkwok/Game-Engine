package authoringenvironment.model;

import javafx.scene.layout.Pane;

/**
 * This class serves as the interface for editing environments that have an IEditableGameElement
 * that can be set, and a Pane that can be returned 
 * 
 * @author Stephen
 *
 */

public interface IEditingEnvironment {	
	
	/**
	 * Sets the editing environment's IEditableGameElement
	 * @param editable that the editing environment's IEditableGameElement will be set to
	 */
	public void setEditable(IEditableGameElement editable);
	
	/**
	 * 
	 * @return the Pane containing the editing environment's GUI Elements
	 */
	public Pane getPane();

}
