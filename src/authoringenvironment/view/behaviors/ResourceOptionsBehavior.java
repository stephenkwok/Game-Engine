package authoringenvironment.view.behaviors;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
/**
 * 
 * @author AnnieTang
 *
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

	@Override
	protected List<String> getOptionsList(){
		return fileNames;
	}
	
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
