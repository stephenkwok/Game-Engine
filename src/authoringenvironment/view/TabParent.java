package authoringenvironment.view;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.Tab;
abstract class TabParent{
	protected Tab tab;
	private String tabText;
	protected ResourceBundle myResources;
	protected List<String> fileNames;
	
	public TabParent(ResourceBundle myResources, String tabText) {
		this.tabText = tabText;
		this.myResources = myResources;
	}
	
	/**
	 * Fills list of Strings based on which images/sounds are in the given file directory.
	 */
	protected void fillFileNames(){
		fileNames = new ArrayList<>();
		File imageDir = new File(myResources.getString(tabText));
		for(File imageFile: imageDir.listFiles()){
			fileNames.add(imageFile.getName());
		}
	}
	
	public Tab createTab() {
		tab = new Tab(tabText);
		setContent();
		return tab;
	}
	
	public void updateNode(){
		setContent();
	}
	
	abstract void setContent();

}
