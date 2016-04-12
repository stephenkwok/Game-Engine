package authoringenvironment.view.behaviors;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.view.ComboBoxTextCell;

public class ComboBoxBehavior extends ComboBoxTextCell {
	private static final String LABEL = "Label";
	private static final String PROMPT = "Prompt";
	private static final String PATHNAME = "Pathname";
	private List<String> fileNames;
	private String pathname;
	private String value;
	private String behaviorType;
	
	public ComboBoxBehavior(String behaviorType, ResourceBundle myResources){
		super(myResources, myResources.getString(behaviorType+PROMPT), myResources.getString(behaviorType+LABEL));
		this.behaviorType = behaviorType;
		this.pathname = myResources.getString(behaviorType+PATHNAME);
		fileNames = new ArrayList<>();
		fillFileNames();
	}

	@Override
	public void setButtonAction() {
		comboButton.setOnAction(event->{
			this.value = comboBox.getValue();
		});
	}

	@Override
	protected List<String> getOptionsList(){
		return fileNames;
	}
	
	public void fillFileNames(){
		File imageDir = new File(pathname);
		for(File imageFile: imageDir.listFiles()){
			fileNames.add(imageFile.getName());
		}
	}
	
	public String getValue(){
		return value;
	}
	
	public String getBehaviorType(){
		return behaviorType;
	}
}
