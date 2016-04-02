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
	private Controller mainScreen;
	
	public GUIFactory(ResourceBundle myResources, Controller myController){
		this.myResources = myResources;
		this.mainScreen = myController;
	}
	
	/**
	 * Creates new IGUIElement based on nodeTypeKey passed in. 
	 * @param nodeTypeKey
	 * @return IGUIElement
	 */
	public IGUIElement createNewGUIObject(String nodeTypeKey){
		String nodeType = myResources.getString(nodeTypeKey);
		switch(nodeType){
		case("SaveLoadButtons"): return new SaveLoad(myResources, mainScreen);
		}
		return null;
	}
}
