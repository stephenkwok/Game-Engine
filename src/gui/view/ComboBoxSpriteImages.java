package gui.view;

import authoringenvironment.view.ActorEditingEnvironment;
import authoringenvironment.model.IAuthoringActor;

public class ComboBoxSpriteImages extends ComboBoxActorImages {

	public ComboBoxSpriteImages(String promptText, String imageResource, ActorEditingEnvironment aEE) {
		super(promptText, imageResource, aEE);
	}
	
	@Override
	public void setButtonAction() {
		getComboButton().setOnAction(event -> {
			try {
				((IAuthoringActor) aEE.getEditable()).addSpriteImage(getComboBox().getValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}
