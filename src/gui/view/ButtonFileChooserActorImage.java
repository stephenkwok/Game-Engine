package gui.view;

import java.io.File;

import authoringenvironment.controller.ActorEditingEnvironment;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditingElement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * ButtonFileChooser for Actor Image.
 * @author AnnieTang
 *
 */
public class ButtonFileChooserActorImage extends ButtonFileChooser {

	/**
	 * Constructor for ButtonFileChooserActorImage.
	 * @param buttonText: text for button.
	 * @param imageName: image for button.
	 * @param editor: editing environment.
	 * @param stage: stage for file chooser.
	 */
	public ButtonFileChooserActorImage(String buttonText, String imageName, IEditingElement editor, Stage stage) {
		super(buttonText, imageName, editor, stage);
	}

	@Override
	protected void updateImage(IEditingElement editor, Image image, File imageFile) {
		((ActorEditingEnvironment) editor).setActorImage(new ImageView(image), imageFile.getName());
		notifyObservers((IAuthoringActor) ((ActorEditingEnvironment) editor).getEditable());
	}

}
