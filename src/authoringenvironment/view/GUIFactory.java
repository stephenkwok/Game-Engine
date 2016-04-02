package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;

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
		case("HomeButton"): return new ButtonHome(myController, myResources.getString("HomeButtonText"), 
				myResources.getString("HomeButtonIcon"));
		case("SaveButton"): return new ButtonSave(myController, myResources.getString("SaveButtonText"),
				myResources.getString("SaveButtonIcon"));
		case("LoadButton"): return new ButtonLoad(myController, myResources.getString("LoadButtonText"),
				myResources.getString("LoadButtonIcon"));
		case("LevelComboBox"): return new ComboBoxLevel(myResources, myResources.getString("LevelComboBoxPrompt"), myController);
		case("NewLevelButton"): return new ButtonNewLevel(myController, myResources.getString("NewLevelButtonText"),
				myResources.getString("NewLevelButtonIcon"));
		case("NewActorButton"): return new ButtonNewActor(myController, myResources.getString("NewActorButtonText"),
				myResources.getString("NewActorButtonIcon"));
		}
		return null;
	}
}
