package gui.view;

import java.io.File;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import authoringenvironment.view.ActorEditingEnvironment;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ButtonFileChooserActorImage extends ButtonFileChooser {

	public ButtonFileChooserActorImage(String buttonText, String imageName, IEditingEnvironment editor) {
		super(buttonText, imageName, editor);
	}

	@Override
	protected void updateImage(IEditingEnvironment editor, Image image, File imageFile) {
		((ActorEditingEnvironment) editor).setActorImage(new ImageView(image), imageFile.getName());
	}

}
