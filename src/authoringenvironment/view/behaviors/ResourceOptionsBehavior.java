package authoringenvironment.view.behaviors;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
/**
 * GUI representation of behavior that requires single input in ComboBox form, where ComboBox is populated by values from a ResourceBundle
 * @author AnnieTang
 */
public class ResourceOptionsBehavior extends ComboBoxBehavior {
	private static final String PATHNAME = "Pathname";
	private List<String> fileNames;
	private String pathname;
	
	public ResourceOptionsBehavior(String behaviorType, ResourceBundle myResources){
		super(behaviorType, myResources);
		this.pathname = myResources.getString(behaviorType+PATHNAME);
		fileNames = new ArrayList<>();
		fillFileNames();
	}
	/**
	 * Return list of file names to be set as combobox options
	 */
	@Override
	protected List<String> getOptionsList(){
		return fileNames;
	}
	/**
	 * Fills list of file names to be set as combobox options
	 */
	private void fillFileNames(){
		File imageDir = new File(pathname);
		for(File imageFile: imageDir.listFiles()){
			fileNames.add(imageFile.getName());
		}
	}

	@Override
	protected void updateValueBasedOnEditable() {
	}
}
