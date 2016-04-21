package authoringenvironment.view.behaviors;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.view.ActorRule;
import gameengine.model.IAction;
import gameengine.model.ITrigger;
/**
 * GUI representation of behaviors: ChangeMusic, PlaySound, PlayMusic
 * @author AnnieTang
 */
public class NOTEUSEDResourceOptionsBehavior extends ComboBoxBehavior {
	private static final String PATHNAME = "Pathname";
	private List<String> fileNames;
	private String pathname;
	private IAction myAction;
	
	public NOTEUSEDResourceOptionsBehavior(ActorRule myActorRule, String behaviorType, ResourceBundle myResources){
		super(myActorRule, behaviorType, myResources);
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
		File fileDir = new File(pathname);
		for(File file: fileDir.listFiles()){
			fileNames.add(file.getName());
		}
	}

	@Override
	protected void updateValueBasedOnEditable() {
		getComboBox().setValue(getValue());
	}
	
	@Override
	void createTriggerOrAction() {
		// TODO Auto-generated method stub
		//myAction = 
		
	}
	@Override
	public IAction getAction() {
		return this.myAction;
	}
	@Override
	public ITrigger getTrigger() {
		return null;
	}
}
