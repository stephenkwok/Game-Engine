package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.controller.MainScreen;

/**
 * Instantiates IGUIElements based on a ResourceBundle String key passed into createNewGUIObject(String nodeTypeKey).
 *
 * @author AnnieTang
 */

public class GUIFactory {
	private ResourceBundle myResources;
	private MainScreen mainScreen;
	
	public GUIFactory(ResourceBundle myResources, MainScreen mainScreen){
		this.myResources = myResources;
		this.mainScreen = mainScreen;
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
