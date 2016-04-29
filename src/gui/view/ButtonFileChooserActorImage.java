package gui.view;

import java.io.File;

import authoringenvironment.controller.ActorEditingEnvironment;
import authoringenvironment.model.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ButtonFileChooserActorImage extends ButtonFileChooser {

	public ButtonFileChooserActorImage(String buttonText, String imageName, IEditingElement editor, Stage stage) {
		super(buttonText, imageName, editor, stage);
	}

	@Override
	protected void updateImage(IEditingElement editor, Image image, File imageFile) {
		((ActorEditingEnvironment) editor).setActorImage(new ImageView(image), imageFile.getName());
		notifyObservers((IAuthoringActor) ((ActorEditingEnvironment) editor).getEditable());
	}

}
