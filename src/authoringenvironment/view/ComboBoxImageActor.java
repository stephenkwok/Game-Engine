package authoringenvironment.view;

import java.util.Map;
import java.util.ResourceBundle;

import authoringenvironment.model.CreatedActor;

public class ComboBoxImageActor extends ComboBoxImage {
	CreatedActor actor;
	
	public ComboBoxImageActor(ResourceBundle myResources, String promptText, CreatedActor toModify) {
		super(myResources, promptText);
		this.actor = toModify;
	}

	@Override
	void setButtonAction() {
		comboButton.setOnAction(event -> {
			Map<String,String> infoMap = actor.getInfo();
			infoMap.put("Image",comboBox.getValue());
		});
	}

}
