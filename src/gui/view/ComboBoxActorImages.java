package gui.view;

import authoringenvironment.view.GUIActorEditingEnvironment;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ComboBoxActorImages extends ComboBoxImageCell{
	GUIActorEditingEnvironment aEE;
	public ComboBoxActorImages(String promptText, String imageResource, GUIActorEditingEnvironment aEE) {
		super(promptText,imageResource);
		this.aEE = aEE;
		
	}

	@Override
	public void setButtonAction() {
		comboButton.setOnAction(event->{
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(comboBox.getValue()));
			try {
				aEE.setActorImage(new ImageView(image));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

}
