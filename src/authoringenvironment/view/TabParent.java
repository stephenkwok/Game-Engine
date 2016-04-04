package authoringenvironment.view;
import java.io.File;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
/**
 * Parent tab class that holds variable content.
 * @author AnnieTang
 *
 */
import javafx.scene.control.Tab;
abstract class TabParent{
	protected Tab tab;
	protected String tabText;
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
		File directory = new File(myResources.getString(tabText));
		for(File file: directory.listFiles()){
			fileNames.add(file.getName());
		}
	}
	
	public Tab createTab() {
		tab = new Tab(tabText);
		try {
			setContent();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tab;
	}
	
	public void updateNode(){
		try {
			setContent();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	abstract void setContent() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

}
