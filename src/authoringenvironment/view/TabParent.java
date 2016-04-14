package authoringenvironment.view;
import java.io.File;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;


/**
 * Parent tab class that holds variable content.
 * @author AnnieTang
 *
 */
import javafx.scene.control.Tab;
abstract class TabParent{
	private Tab tab;
	private String tabText;
	private ResourceBundle myResources;
	private List<String> fileNames;
	private ScrollPane sp;
	
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
	/**
	 * Return scrollable tab
	 * @return
	 */
	public Tab getTab() {
		tab = new Tab(tabText);
		try {
			sp = new ScrollPane();
			sp.setFitToHeight(true);
			sp.setFitToWidth(true);
			sp.setContent(getContent());
			tab.setContent(sp);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tab;
	}
	/**
	 * Reset content of tab 
	 */
	public void updateNode(){
		try {
			sp.setContent(getContent());
			tab.setContent(sp);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Get content of tab
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	abstract Node getContent() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

	protected List<String> getFileNames() {
		return fileNames;
	}
	
	protected ResourceBundle getResources() {
		return myResources;
	}
	
	protected String getTabText() {
		return tabText;
	}
}
