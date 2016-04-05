package authoringenvironment.model;

import javafx.scene.layout.Pane;

public interface IEditingEnvironment {	
	public void setEditable(IEditableGameElement editable);
	
	public Pane getPane();
}
