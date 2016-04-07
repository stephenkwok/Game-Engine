package authoringenvironment.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ComboBoxActorImages extends ComboBoxImageCell{
	GUIActorEditingEnvironment aEE;
	public ComboBoxActorImages(String promptText, String imageResource, GUIActorEditingEnvironment aEE) {
		super(promptText,imageResource);
		this.aEE = aEE;
		
	}

	@Override
	void setButtonAction() {
		comboButton.setOnAction(event->{
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(comboBox.getValue()));
			aEE.setActorImage(new ImageView(image));
		});
	}

}
