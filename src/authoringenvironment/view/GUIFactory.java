package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Instantiates IGUIElements based on a ResourceBundle String key passed into createNewGUIObject(String nodeTypeKey).
 *
 * @author AnnieTang
 */

public class GUIFactory {
	private ResourceBundle myResources;
	private Controller myController;
	
	public GUIFactory(ResourceBundle myResources, Controller myController){
		this.myResources = myResources;
		this.myController = myController;
	}
	
	/**
	 * Creates new IGUIElement based on nodeTypeKey passed in. 
	 * @param nodeTypeKey
	 * @return IGUIElement
	 */
	public IGUIElement createNewGUIObject(String nodeTypeKey){
		String nodeType = myResources.getString(nodeTypeKey);
		switch(nodeType){
		case("HomeButton"): return new ButtonParent(myController, myResources.getString("HomeButtonText"), 
				myResources.getString("HomeButtonIcon"), 
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						myController.goToMainScreen();
					};
				});
		case("SaveButton"): return new ButtonSave(myController, myResources.getString("SaveButtonText"),
				myResources.getString("SaveButtonIcon"), null);
		case("LoadButton"): return new ButtonLoad(myController, myResources.getString("LoadButtonText"),
				myResources.getString("LoadButtonIcon"), null);
		case("LevelComboBox"): return new ComboBoxLevel(myResources, myResources.getString("LevelComboBoxPrompt"), myController);
		}
		return null;
	}
}

/**
new EventHandler<ActionEvent>() {
public void handle(ActionEvent event) {
	
};
});
**/