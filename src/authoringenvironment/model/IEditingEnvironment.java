package authoringenvironment.model;

import javafx.scene.Scene;

public interface IEditingEnvironment {

	public void initializeEnvironment();
	
	public void setEditable(IEditableGameElement editable);
	
	public Scene getScene();
}
